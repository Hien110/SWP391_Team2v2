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
import repository.UserRepository;

@WebServlet(name = "VerifyServlet", urlPatterns = {"/verify"})
public class VerifyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerifyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("verificationemail.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String code1 = request.getParameter("code1");
        String code2 = request.getParameter("code2");
        String code3 = request.getParameter("code3");
        String code4 = request.getParameter("code4");

        String verificationCode = code1 + code2 + code3 + code4;
        String randomCode = (String) session.getAttribute("randomCode");

        if (randomCode.equals(verificationCode)) {
            String username = (String) session.getAttribute("username");
            String email = (String) session.getAttribute("email");
            String password = (String) session.getAttribute("password");

            if (username == null && email != null) {
                response.sendRedirect("./resetpassword.jsp");
            } else {
                UserRepository cdb = new UserRepository();
                User newUser = new User(username, email, password);
                cdb.newUser(newUser);
                session.invalidate();
                response.sendRedirect("./login.jsp");
            }
        } else {
            request.setAttribute("error", "Verification code is incorrect");
            request.getRequestDispatcher("./verificationemail.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
