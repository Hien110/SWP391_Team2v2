package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.ProductRepository;
import model.Product;

@WebServlet(name = "updateProductServlet", urlPatterns = {"/updateproduct"})
public class updateProductShopOwnerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String pid = request.getParameter("id");
        ProductRepository pr = new ProductRepository();
        Product p = pr.getProductShopOwnerByID(pid);
        request.setAttribute("Product", p);
        request.getRequestDispatcher("updateProductShopOwner.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String productId = request.getParameter("id");
        String productName = request.getParameter("name");
        String price_raw = request.getParameter("price");
        String description = request.getParameter("description");
        String quantity_raw = request.getParameter("quantity");
        String averageStar_raw = request.getParameter("aveStar");
        String image = request.getParameter("image");

        double pricee;
        int quantityy;
        double aveStar;
        int pid;

        try {
            pid = Integer.parseInt(productId);
            pricee = Double.parseDouble(price_raw);
            quantityy = Integer.parseInt(quantity_raw);
            aveStar = Double.parseDouble(averageStar_raw);

            Product p = new Product(pid, productName, pricee, description, quantityy, aveStar, image);
            ProductRepository pr = new ProductRepository();
            pr.updateProductShopOwner(pid, productName, pricee, description, quantityy, aveStar, image);

            response.sendRedirect("manager"); // Redirect to manager page or relevant page

        } catch (NumberFormatException e) {
            System.out.println(e);
            // Optionally, set an error message and forward back to the update form
            request.setAttribute("errorMessage", "Invalid input. Please check the values and try again.");
            request.getRequestDispatcher("updateProductShopOwner.jsp").forward(request, response);
        }
    }
}


