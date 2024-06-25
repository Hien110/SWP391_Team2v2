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

@WebServlet(name = "ListProductServlet", urlPatterns = {"/listProduct"})
public class ListProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProductRepository pr = new ProductRepository();
            List<Product> list = pr.getAllProduct();

            request.setAttribute("l", list);  
            request.getRequestDispatcher("home.jsp").forward(request, response); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}