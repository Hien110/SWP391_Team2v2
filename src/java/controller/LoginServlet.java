package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import repository.UserRepository;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("./login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserRepository cdb = new UserRepository();
        User c1 = cdb.getAccountByUsername(username);

        if (c1 == null) {
            String ms = "Username not exist";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./login.jsp").forward(request, response);
        } else if (password.equals(c1.getPassword())) {
                Cookie uidCookie = new Cookie("uid-cookie", String.valueOf(c1.getUserid()));
                Cookie passwordCookie = new Cookie("password-cookie", password);
                Cookie roleCookie = new Cookie("role-cookie", String.valueOf(c1.getRoleid()));
                uidCookie.setMaxAge(24 * 60 * 60);
                passwordCookie.setMaxAge(24 * 60 * 60);
                roleCookie.setMaxAge(24 * 60 * 60);

                response.addCookie(uidCookie);
                response.addCookie(passwordCookie);
                response.addCookie(roleCookie);
            switch (c1.getRoleid()) {
                case 1 ->{
//                    request.setAttribute("name", c1.getName());
                    response.sendRedirect("#");
                }
                case 2 ->{
//                    request.setAttribute("name", c1.getName());
                    request.getRequestDispatcher("#").forward(request, response);
                }
                default ->
                    response.sendRedirect("./home.jsp");
            }
        } else {
            String ms = "Password was wrong";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
