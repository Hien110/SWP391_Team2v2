///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.List;
//import model.Product;
//import repository.ProductRepository;
///**
// *
// * @author DELL
// */
//@WebServlet(name = "ListCartServlet", urlPatterns = {"/cartList"})
//public class ListCartServlet extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//
//        ProductRepository productRepository = new ProductRepository();
//        List<Product> cartProducts = productRepository.listToCart(userId);
//
//        request.setAttribute("cartProducts", cartProducts);
//        request.getRequestDispatcher("cart.jsp").forward(request, response);
//    }
//}
