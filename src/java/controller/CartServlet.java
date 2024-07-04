package controller;

import repository.OrderRepository;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import model.User;

@WebServlet(name = "CartServlet", urlPatterns = {"/Cart"})
public class CartServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CartServlet.class.getName());
    private final OrderRepository orderRepository = new OrderRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Call doPost method to handle the request
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Extract item details from the request
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            int userId = user.getUserid();
            String productIdStr = request.getParameter("productId");
            String size = request.getParameter("size");
            String color = request.getParameter("color");
            String quantityStr = request.getParameter("quantity");

            // Check for null or empty values
            if (isNullOrEmpty(productIdStr, size, color, quantityStr)) {
                throw new IllegalArgumentException("One or more parameters are missing or empty.");
            }

            int productId = Integer.parseInt(productIdStr);
            int quantity = Integer.parseInt(quantityStr);

            // Add item to cart using the repository
            orderRepository.addItemToCart(productId, userId, quantity, size, color);

            // Set success message
            request.setAttribute("message", "Product added to cart successfully!");
            request.setAttribute("messageType", "success");

        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Invalid input: {0}", e.getMessage());
            request.setAttribute("message", "Failed to add product to cart. Invalid input. Please try again.");
            request.setAttribute("messageType", "error");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to add product to cart", e);
            request.setAttribute("message", "Failed to add product to cart. Please try again.");
            request.setAttribute("messageType", "error");
        }

        // Forward back to the product page
        request.getRequestDispatcher("/detailProduct").forward(request, response);
    }

    private boolean isNullOrEmpty(String... values) {
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getServletInfo() {
        return "Handles adding items to the cart.";
    }
}
