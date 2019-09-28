package control;

import com.sun.tools.javac.Main;
import service.CustomerService;
import service.MainService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "control.CustomerController", urlPatterns = {"/customers"})
public class CustomerController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService customerService = MainService.getInstance().getCustomerService();

        try {
            request.setAttribute("customers", customerService.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/customer.jsp");
        rd.include(request, response);
    }
}
