/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.ProductRepository;
import model.Product;

/**
 *
 * @author HP
 */
@WebServlet(name = "addProductServlet", urlPatterns = {"/addproduct"})
public class addProductShopOwnerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String productName = request.getParameter("name");
        String price = request.getParameter("price");
        double priceValue = Double.parseDouble(price);
        String description = request.getParameter("description");
        String quantity = request.getParameter("quantity");
        int quantityValue = Integer.parseInt(quantity);
        String numberStar = request.getParameter("numStar");
        double numStar = Double.parseDouble(numberStar);
        String totalStar = request.getParameter("totalStar");
        int totalStarValue = Integer.parseInt(totalStar);
        String shopId = request.getParameter("shopId");
        int shopID = Integer.parseInt(shopId);
        HttpSession session = request.getSession();
        Product a = (Product) session.getAttribute("product");
        // Gọi phương thức thêm sản phẩm từ ProductRepository
        ProductRepository pr = new ProductRepository();
        pr.addProductShopOwner(productName, priceValue, description, quantityValue, numStar, totalStarValue, shopID);
        
        response.sendRedirect("manager");
        request.getRequestDispatcher("addProductShopOwner.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
