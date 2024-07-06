package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import model.ProductShop;
import repository.ProductShopOwnerRepository;

@WebServlet("/updateProductShopOwnerServlet")
public class updateProductShopOwnerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        int quantityp = Integer.parseInt(request.getParameter("quantityp"));
        int price = Integer.parseInt(request.getParameter("price"));
        ProductShopOwnerRepository pr = new ProductShopOwnerRepository();
        pr.updateProductShopOwner(productId, productName, price, description, quantityp);
        response.sendRedirect("ListProductShopOwner");
    }
}
