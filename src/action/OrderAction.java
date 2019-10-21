package action;

import model.Customer;
import model.Order;
import model.Product;
import service.CustomerService;
import service.MainObjectService;
import service.OrderService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderAction implements Action {
    private OrderService orderService = (OrderService) MainObjectService.getInstance().getObjects().get(OrderService.class.getName());

    ProductService productService = (ProductService) MainObjectService
            .getInstance()
            .getObjects()
            .get(ProductService.class.getName());

    private CustomerService customerService = (CustomerService) MainObjectService
            .getInstance()
            .getObjects()
            .get(CustomerService.class.getName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String next = request.getPathInfo() == null ? "": request.getPathInfo().replace("/", "");
        String method = request.getMethod();

        if (method.equals("GET") && next.equals("")) {
            request.setAttribute("action", "VIEW");
            List<Order> orders = null;

            try {
                orders = orderService.getAll();
            } catch (SQLException e) {
                request.setAttribute("error", e.getLocalizedMessage());
                e.printStackTrace();
            }

            request.setAttribute("orders", orders);

            RequestDispatcher rd = request.getRequestDispatcher("/order.jsp");
            rd.forward(request, response);
            return;
        }
        if (method.equals("POST") && next.equals("upsert")) {

        }

        if (method.equals("POST") && next.equals("delete")) {
            try {
                orderService.delete(request.getParameter("id"));
            } catch (SQLException e) {
                request.setAttribute("error", e.getLocalizedMessage());
                e.printStackTrace();
                response.sendRedirect("/orders?deleted=false");
                return;
            }

            response.sendRedirect("/orders?deleted=true");
            return;
        }

        if (method.equals("GET") && next.equals("add")) {
            request.setAttribute("action", "CREATE");

            List<Customer> customers = null;
            try {
                customers = customerService.getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("customers", customers);
            List<Product> products = null;
            try {
                products = productService.getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("products", products);
            RequestDispatcher rd = request.getRequestDispatcher("/order-form.jsp");
            rd.forward(request, response);
            return;
        }

        if (method.equals("GET")) {

            request.setAttribute("action", "VIEW");

            try {
                Order order = orderService.getOne(next);
                request.setAttribute("order", order);

                if (order == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            } catch (SQLException e) {
                request.setAttribute("error", e.getLocalizedMessage());
                e.printStackTrace();
            }

            RequestDispatcher rd = request.getRequestDispatcher("/order-form.jsp");
            rd.forward(request, response);
            return;
        }

    }
}
