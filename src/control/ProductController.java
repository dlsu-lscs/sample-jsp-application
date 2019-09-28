package control;

import service.MainService;
import service.ProductService;
import service.QueryBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "control.ProductController", urlPatterns = {"/products/*"})
public class ProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = MainService.getInstance().getProductService();


        try {
            request.setAttribute("products", productService.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/products.jsp");
        rd.forward(request, response);
    }
}
