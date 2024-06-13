package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import repository.ProductRepository;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/addToCart"})
public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cartIdStr = request.getParameter("cartId");
        String productIdStr = request.getParameter("productId");
        String userIdStr = request.getParameter("userId");
        String quantityStr = request.getParameter("quantity");

        // Validate parameters
        if (cartIdStr == null || productIdStr == null || userIdStr == null || quantityStr == null ||
            cartIdStr.isEmpty() || productIdStr.isEmpty() || userIdStr.isEmpty() || quantityStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid parameters");
            return;
        }

        try {
            int cartId = Integer.parseInt(cartIdStr);
            int productId = Integer.parseInt(productIdStr);
            int userId = Integer.parseInt(userIdStr);
            int quantity = Integer.parseInt(quantityStr);

            ProductRepository productRepository = new ProductRepository();
            productRepository.addToCartById(cartId, productId, userId, quantity);

            // Fetch the updated cart items for the user
            List<Product> cartProducts = productRepository.listToCart(userId);
            request.setAttribute("cartProducts", cartProducts);

            // Add order details attributes
            Date orderDate = new Date(); // Example order date
            String status = "Pending"; // Example status

            request.setAttribute("orderDate", orderDate);
            request.setAttribute("status", status);

            // Forward the request to cart.jsp
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format in parameters");
        }
    }
}
