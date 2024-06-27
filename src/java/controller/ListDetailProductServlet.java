package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import model.Product;
import repository.ProductRepository;

@WebServlet(name = "ListDetailProductServlet", urlPatterns = {"/detailProduct"})
public class ListDetailProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productIdStr = request.getParameter("productId");

        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.getProductById(productIdStr);
        List<String> availableSizes = productRepository.getAvailableSizes(productIdStr);
        List<String> availableColors = productRepository.getAvailableColors(productIdStr);

        request.setAttribute("product", product);
        request.setAttribute("availableSizes", availableSizes);
        request.setAttribute("availableColors", availableColors);

        request.getRequestDispatcher("product.jsp").forward(request, response);
    }
        

    
}
