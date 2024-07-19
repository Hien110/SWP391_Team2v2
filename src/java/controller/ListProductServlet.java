package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Product;
import model.User;
import repository.ProductRepository;
import repository.listProductHomeDAO;

@WebServlet(name = "ListProductServlet", urlPatterns = {"/listProduct"})
public class ListProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProductRepository pr = new ProductRepository();
            listProductHomeDAO lph = new listProductHomeDAO();
            List<Product> list = pr.getAllProduct();
            List<Product> list1 = lph.getAllProductByTypeID(1);
            List<Product> list2 = lph.getAllProductByTypeID(2);
            List<Product> list5 = lph.getAllProductByTypeID(5);

            request.setAttribute("l5", list5);
            request.setAttribute("l2", list2);
            request.setAttribute("l1", list1);
            request.setAttribute("l", list);
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving the product details.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet that handles listing products.";
    }
}