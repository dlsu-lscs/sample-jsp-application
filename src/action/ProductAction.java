package action;

import model.Product;
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

public class ProductAction implements Action {
    ProductService productService = (ProductService) MainObjectService
            .getInstance()
            .getObjects()
            .get(ProductService.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String next = request.getPathInfo() == null ? "": request.getPathInfo().replace("/", "");
        String method = request.getMethod();

        if (method.equals("POST") && next.equals("delete")) {
            try {
                productService.delete(request.getParameter("id"));
            } catch (SQLException e) {
                response.sendRedirect("/products?deleted=false");
                return;
            }

            response.sendRedirect("/products?deleted=true");
            return;
        }

        if (method.equals("POST") && next.equals("upsert")) {
            Product product = new Product();

            String code = request.getParameter("product[code]");
            product.setCode(code);

            String description = request.getParameter("product[description]");
            product.setDescription(description);

            double buyPrice = Double.parseDouble(request.getParameter("product[buyPrice]"));
            product.setBuyPrice(buyPrice);

            double MSRP = Double.parseDouble(request.getParameter("product[MSRP]"));
            product.setMSRP(MSRP);

            String scale = request.getParameter("product[scale]");
            product.setScale(scale);

            String line = request.getParameter("product[line]");
            product.setLine(line);

            String vendor = request.getParameter("product[vendor]");
            product.setVendor(vendor);

            String name = request.getParameter("product[name]");
            product.setName(name);

            int quantity = Integer.parseInt(request.getParameter("product[quantity]"));
            product.setQuantity(quantity);

            boolean update = false;

            try {
                if (productService.getOne(code) != null) {
                    productService.update(code, product);
                    update = true;

                    response.sendRedirect("/products?updated=true");
                    return;
                }

                productService.create(product);
                response.sendRedirect("/products?created=true");
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (update) {
                response.sendRedirect("/products?updated=false");
                return;
            }

            response.sendRedirect("/products?created=false");
            return;
        }

        if (method.equals("GET") && next.equals("")) {
            request.setAttribute("action", "VIEW");
            List <Product> products = null;

            try {
                products = productService.getAll();
            } catch (SQLException e) {
                request.setAttribute("error", e.getLocalizedMessage());
            }

            request.setAttribute("products", products);

            RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
            rd.forward(request, response);
            return;
        }

        if (method.equals("GET") && next.equals("add")) {
            request.setAttribute("action", "CREATE");
            RequestDispatcher rd = request.getRequestDispatcher("/product-form.jsp");
            rd.forward(request, response);
            return;
        }

        if (method.equals("GET")) {
            String id = next;

            request.setAttribute("action", "VIEW");

            try {
                Product product = productService.getOne(id);
                request.setAttribute("product", product);

                if (product == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            } catch (SQLException e) {
                request.setAttribute("error", e.getLocalizedMessage());
            }

            RequestDispatcher rd = request.getRequestDispatcher("/product-form.jsp");
            rd.forward(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
