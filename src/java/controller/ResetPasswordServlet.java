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
import java.util.regex.Pattern;
import model.User;
import repository.UserRepository;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/resetpassword"})
public class ResetPasswordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        String repass = request.getParameter("re_password");
        String email = (String) session.getAttribute("email");
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"); // Ít nhất 1 chữ và 1 số, độ dài từ 6 trở lên
        UserRepository cdb = new UserRepository();
        String oldpass = cdb.getAccountByEmail(email).getPassword();
        if (!password.equals(repass)) {
            String ms = "Mật khẩu nhập lại không đúng";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./resetpassword.jsp").forward(request, response);
        } else if (!passwordPattern.matcher(password).matches()) {
            String ms = "Mật khẩu phải dài ít nhất 6 ký tự và chứa ít nhất một chữ cái và một số";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./resetpassword.jsp").forward(request, response);
        } else {
            User c = new User(password, email);
            cdb.resetPassword(c);
            response.sendRedirect("./login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
