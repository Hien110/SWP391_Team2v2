package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import model.Product;
import repository.ProductRepository;

@WebServlet(name = "ListDetailProductServlet", urlPatterns = {"/detailProduct"})
public class ListDetailProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productIdStr = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");
        String quantitypStr = request.getParameter("quantityp");
        String averageStarStr = request.getParameter("averageStar");
        String image = request.getParameter("image");
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        String typeIdStr = request.getParameter("typeId");
        String shopIdStr = request.getParameter("shopId");
        String shopName = request.getParameter("shopName");
        PrintWriter out = response.getWriter();
        ProductRepository cb = new ProductRepository();
        Product product = cb.getProductById(productIdStr);
        request.setAttribute("product", product);
        request.getRequestDispatcher("product.jsp").forward(request, response);


    }
}
