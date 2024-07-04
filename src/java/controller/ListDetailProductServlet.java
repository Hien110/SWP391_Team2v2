package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;
import repository.ProductRepository;

@WebServlet(name = "ListDetailProductServlet", urlPatterns = {"/detailProduct"})
public class ListDetailProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productIdStr = request.getParameter("productId");

        if (productIdStr == null || productIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is missing");
            return;
        }

        try {
            ProductRepository productRepository = new ProductRepository();
            Product product = productRepository.getProductById(productIdStr);
            List<String> availableSizes = productRepository.getAvailableSizes(productIdStr);
            List<String> availableColors = productRepository.getAvailableColors(productIdStr);
            List<String> availableImages = productRepository.getAvailableImages(productIdStr);  // New line to get images

            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }

            request.setAttribute("product", product);
            request.setAttribute("availableSizes", availableSizes);
            request.setAttribute("availableColors", availableColors);
            request.setAttribute("availableImages", availableImages);  // New line to set images attribute

            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving the product details");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Call the doGet method to handle POST requests
        doGet(request, response);
    }
}
