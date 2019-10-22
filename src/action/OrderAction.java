package action;

import model.Customer;
import model.LineItem;
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
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderAction implements Action {
    private OrderService orderService = (OrderService) MainObjectService.getInstance().getObjects().get(OrderService.class.getName());
    Order tempOrder = new Order();
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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            System.out.println(request.getParameter("order[orderDate]"));
            Date orderDate = Date.valueOf(request.getParameter("order[orderDate]"));
            tempOrder.setOrderDate(orderDate);

            Date requiredDate = Date.valueOf(request.getParameter("order[requiredDate]"));
            tempOrder.setRequiredDate(requiredDate);

            Date shippedDate = Date.valueOf(request.getParameter("order[shippedDate]"));
            tempOrder.setShippedDate(shippedDate);

            String status = request.getParameter("order[status]");
            tempOrder.setStatus(status);

            String comments = request.getParameter("order[comments]");
            tempOrder.setComments(comments);

            Customer customer = null;
            try {
                customer = customerService.getOne(request.getParameter("order[customerNumber]"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            tempOrder.setCustomer(customer);

            boolean update = false;

            try {
                if (orderService.getOne(String.valueOf(tempOrder.getOrderNumber())) != null) {
                    orderService.update(String.valueOf(tempOrder.getOrderNumber()), tempOrder);
                    update = true;

                    response.sendRedirect("/orders?updated=true");
                    return;
                }

                orderService.create(tempOrder);
                response.sendRedirect("/orders?created=true");
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (update) {
                response.sendRedirect("/orders?updated=false");
                return;
            }

            response.sendRedirect("/orders?created=false");
            return;
        }
        if (method.equals("POST") && next.equals("reset")) {
            tempOrder = new Order();
            response.sendRedirect("/orders/add");
        }
        if (method.equals("POST") && next.equals("add-product")) {
            LineItem details = new LineItem();
            List <LineItem> orderDetails = new ArrayList<>();
            int id;

            if(tempOrder.getOrderDetails() != null) {
                orderDetails = tempOrder.getOrderDetails();
            }

            id = orderDetails.size()+1;

            String code = request.getParameter("lineitem[code]");
            int quantity = Integer.parseInt(request.getParameter("lineitem[quantity]"));
            int order_no = Integer.parseInt(request.getParameter("lineitem[orderno]"));

            Product product = null;

            try {
                product = productService.getOne(code);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            details.setOrderLineNumber(id);
            if (product != null) {
                details.setPriceEach(product.getBuyPrice());
                details.setProduct(product);
                details.setTotal(quantity * product.getBuyPrice());
            }

            details.setQuantityOrdered(quantity);
            details.setOrderNumber(order_no);

            orderDetails.add(details);
            tempOrder.setOrderDetails(orderDetails);
            response.sendRedirect("/orders/add");
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
            request.setAttribute("order", tempOrder);
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
        }

    }
}
