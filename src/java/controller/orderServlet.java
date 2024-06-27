package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

/**
 * Servlet to handle order form submissions.
 */
@WebServlet(name = "orderServlet", urlPatterns = {"/order"})
public class orderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from the request
        String productName = request.getParameter("productName");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String image = request.getParameter("image");
        String description = request.getParameter("description");

        // Create a product object using the constructor
        Product product = new Product(productName, price, description, quantity, image, color, size);

        // Set the product object as an attribute
        request.setAttribute("product", product);

        // Forward the request to orderForm.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
