package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Shop;
import model.User;
import repository.ShopOwnerRepository;
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
        ShopOwnerRepository shopr = new ShopOwnerRepository();
        Shop shop = shopr.getShopByUserid(c1.getUserid());
        if (c1 == null) {
            String ms = "Username not exist";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./login.jsp").forward(request, response);
        } else if (password.equals(c1.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", c1);
            session.setMaxInactiveInterval(864000); // 1440 phút = 24 giờ
            if(shop != null){
            session.setAttribute("shop", shop);
        }
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
