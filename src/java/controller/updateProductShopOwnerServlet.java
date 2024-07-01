package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductShop;
import repository.ProductShopOwnerRepository;

@WebServlet("/updateProductShopOwnerServlet")
public class updateProductShopOwnerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int quantityp = Integer.parseInt(request.getParameter("quantityp"));
        double averageStar = Double.parseDouble(request.getParameter("averageStar"));
        String image = request.getParameter("image");

        ProductShopOwnerRepository pr = new ProductShopOwnerRepository();
        pr.updateProductShopOwner(productId, productName, price, description, quantityp, averageStar, image);

        // Redirect to the product list page after updating the product
        response.sendRedirect("ListProductShopOwner");
    }
}

    



