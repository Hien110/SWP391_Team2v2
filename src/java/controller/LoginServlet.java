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
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("roleid", c1.getRoleid());
            session.setAttribute("email", c1.getEmail());
            session.setAttribute("uid", String.valueOf(c1.getUserid()));
            session.setAttribute("imgavt", c1.getImgavt());
            session.setMaxInactiveInterval(864000); // 1440 phút = 24 giờ
            response.sendRedirect("./listProduct");
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
