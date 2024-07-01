package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.User;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReviewOrderServlet", urlPatterns = {"/reviewOrder"})
public class ReviewOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int productId = Integer.parseInt(request.getParameter("productId")) ; // Retrieve productId
            String productName = request.getParameter("productName");
            String size = request.getParameter("size");
            String color = request.getParameter("color");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String image = request.getParameter("image");
            String description = request.getParameter("description");
            int shopId = Integer.parseInt(request.getParameter("shopId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            int receiverInfoId = Integer.parseInt(request.getParameter("receiverInfoId"));
            String paymentMethods = request.getParameter("paymentMethods");

            Product product = new Product(productId, productName, price, description, quantity, image, color, size, shopId); // Include productId in Product object

            request.setAttribute("product", product);
            request.setAttribute("userId", userId);
            request.setAttribute("receiverInfoId", receiverInfoId);
            request.setAttribute("paymentMethods", paymentMethods);

            RequestDispatcher dispatcher = request.getRequestDispatcher("review.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }
}
