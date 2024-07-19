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

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ChangePassUserServlet", urlPatterns = {"/changepassuser"})
public class ChangePassUserServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePassUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassUserServlet at " + request.getContextPath() + "</h1>");
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
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String repass = request.getParameter("repass");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        String email = (String) session.getAttribute("email");
        UserRepository cdb = new UserRepository();
        String oldpassword = cdb.getAccountByUsername(username).getPassword();
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_-]*$");
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");
        session.setMaxInactiveInterval(1440 * 60); // 1440 phút = 24 giờ
        if (!oldpass.equals(oldpassword)) {
            String ms = "Sai mật khẩu cũ";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./changePassUser.jsp").forward(request, response);
        } else if (!passwordPattern.matcher(newpass).matches()) {
            String ms = "Mật khẩu phải có độ dài lớn hơn 5 kí tự và ít nhất 1 chữ cái và 1 chữ số";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./changePassUser.jsp").forward(request, response);
        } else if (!newpass.equals(repass)) {
            String ms = "Mật khẩu mới không trùng khớp";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./changePassUser.jsp").forward(request, response);
        } else {
            User c = new User(username, email, newpass);
            cdb.updatePassword(c);
            String ms = "Đổi mật khẩu thành công";
            request.setAttribute("success", ms);
            request.getRequestDispatcher("./changePassUser.jsp").forward(request, response);
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
