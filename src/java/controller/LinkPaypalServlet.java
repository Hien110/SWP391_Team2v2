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
import model.User;
import model.walletHeartsteal;
import repository.UserRepository;
import repository.WalletRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LinkPaypalServlet", urlPatterns = {"/linkpaypal"})
public class LinkPaypalServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LinkPaypalServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LinkPaypalServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        User user = (User) session.getAttribute("user");
        String password1 = user.getPassword();
        UserRepository cb = new UserRepository();
        if (!password.equals(password1)) {
            String ms = "Huỷ liên kết không thành công do sai mật khẩu";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./walletHeartsteal.jsp").forward(request, response);
        } else {
            User u = new User(0, user.getUsername(), null, null, null, null, null, null, 0, null, null, null, null, null);
            walletHeartsteal w = new walletHeartsteal(0, user.getUserid(), 0);
            cb.cancalEmailPaypal(u);
            User user1 = cb.getAccountByUsername(user.getUsername());
            session.setAttribute("user", user1);
            response.sendRedirect("./walletHeartsteal.jsp");
        }
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
        String email = request.getParameter("emailPaypal");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String password1 = user.getPassword();
        UserRepository cb = new UserRepository();
        if (!password.equals(password1)) {
            String ms = "Liên kết không thành công do sai mật khẩu";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./walletHeartsteal.jsp").forward(request, response);
        } else {
            User u = new User(0, user.getUsername(), null, null, null, null, null, null, 0, null, null, null, email, null);   
            cb.updateEmailPaypal(u);
            User user1 = cb.getAccountByUsername(user.getUsername());
            session.setAttribute("user", user1);
            response.sendRedirect("./walletHeartsteal.jsp");
        }

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
