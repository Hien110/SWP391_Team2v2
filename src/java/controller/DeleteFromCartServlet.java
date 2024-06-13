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
import repository.ProductRepository;
/**
 *
 * @author DELL
 */
@WebServlet(name = "DeleteFromCartServlet", urlPatterns = {"/deleteFromCart"})
public class DeleteFromCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        ProductRepository productRepository = new ProductRepository();
        productRepository.deleteFromCart(cartId, productId, userId);

        response.sendRedirect("cartList?userId=" + userId);
    }
}
