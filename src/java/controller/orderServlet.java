package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.User;
import repository.OrderRepository;

/**
 * Servlet to handle order form submissions.
 */
@WebServlet(name = "orderServlet", urlPatterns = {"/order"})
public class orderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getUserid();
        OrderRepository orderRepository = new OrderRepository();
        User userWithAddress = orderRepository.getUserWithAddressById(userId);

        try {
            String productName = request.getParameter("productName");
            String size = request.getParameter("size");
            String color = request.getParameter("color");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String image = request.getParameter("image");
            String description = request.getParameter("description");
            int shopId = Integer.parseInt(request.getParameter("shopId"));

            Product product = new Product(productName, price, description, quantity, image, color, size, shopId);

            request.setAttribute("product", product);
            request.setAttribute("user", userWithAddress);

            RequestDispatcher dispatcher = request.getRequestDispatcher("orderForm.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
