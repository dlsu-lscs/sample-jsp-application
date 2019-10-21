package action;

import model.Customer;
import model.Order;
import model.Product;
import service.CustomerService;
import service.MainObjectService;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerAction implements Action {

    CustomerService customerService = (CustomerService) MainObjectService
            .getInstance()
            .getObjects()
            .get(CustomerService.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String next = request.getPathInfo() == null ? "": request.getPathInfo().replace("/", "");
        String method = request.getMethod();

        if (method.equals("GET") && next.equals("")) {
            request.setAttribute("action", "VIEW");
            List<Customer> customers = null;

            try {
                customers = customerService.getAll();
            } catch (SQLException e) {
                request.setAttribute("error", e.getLocalizedMessage());
            }

            request.setAttribute("customers", customers);

            RequestDispatcher rd = request.getRequestDispatcher("/customer.jsp");
            rd.forward(request, response);
            return;
        }

        if (method.equals("GET") && next.equals("add")) {
            request.setAttribute("action", "CREATE");

            RequestDispatcher rd = request.getRequestDispatcher("/customer-form.jsp");
            rd.forward(request, response);
            return;
        }


        if (method.equals("POST") && next.equals("upsert")) {
            Customer customer = new Customer();

            Integer id = request.getParameter("customer[customerNumber]") != null &&
                    request.getParameter("customer[customerNumber]") != ""
                    ? Integer.valueOf(request.getParameter("customer[customerNumber]"))
                    : null;

            if (id != null)
                customer.setCustomerNumber(id);

            String name = request.getParameter("customer[customerName]");
            customer.setCustomerName(name);

            String contactLastName = request.getParameter("customer[contactLastName]");
            customer.setContactLastName(contactLastName);

            String contactFirstName = request.getParameter("customer[contactFirstName]");
            customer.setContactFirstName(contactFirstName);

            String phone = request.getParameter("customer[phone]");
            customer.setPhone(phone);


            String addr1 = request.getParameter("customer[addressLine1]");
            customer.setAddressLine1(addr1);


            String addr2 = request.getParameter("customer[addressLine2]");
            customer.setAddressLine2(addr2);

            String city = request.getParameter("customer[city]");
            customer.setCity(city);

            String country = request.getParameter("customer[country]");
            customer.setCountry(country);

            String state = request.getParameter("customer[state]");
            customer.setState(state);

            String postalCode = request.getParameter("customer[postalCode]");
            customer.setPostalCode(postalCode);

            String creditLimit = request.getParameter("customer[creditLimit]");
            customer.setCreditLimit(creditLimit);

            boolean update = false;

            try {
                if (id != null && customerService.getOne(String.valueOf(id)) != null) {
                    customerService.update(String.valueOf(id), customer);
                    update = true;

                    response.sendRedirect("/customers?updated=true");
                    return;
                }

                customerService.create(customer);
                response.sendRedirect("/customers?created=true");
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (update) {
                response.sendRedirect("/customers?updated=false");
                return;
            }

            response.sendRedirect("/customers?created=false");
            return;
        }

        if (method.equals("POST") && next.equals("delete")) {

            try {
                customerService.delete(request.getParameter("id"));
            } catch (SQLException e) {
                request.setAttribute("error", e.getLocalizedMessage());
                e.printStackTrace();
                response.sendRedirect("/customers?deleted=false");
                return;
            }

            response.sendRedirect("/customers?deleted=true");
            return;
        }


        if (method.equals("GET")) {
            String id = next;

            request.setAttribute("action", "VIEW");

            try {
                Customer customer = customerService.getOne(id);
                request.setAttribute("customer", customer);

                if (customer == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            } catch (SQLException e) {
                request.setAttribute("error", e.getLocalizedMessage());
                e.printStackTrace();
            }

            RequestDispatcher rd = request.getRequestDispatcher("/customer-form.jsp");
            rd.forward(request, response);
            return;
        }


        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
