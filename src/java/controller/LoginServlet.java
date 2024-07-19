package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.CartItem;
import model.Shop;
import model.User;
import repository.OrderRepository;
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
        OrderRepository cb1 = new OrderRepository();
        UserRepository cdb = new UserRepository();
        User c1 = cdb.getAccountByUsername(username);
        ShopOwnerRepository shopr = new ShopOwnerRepository();

        if (c1 == null) {
            String ms = "Tài khoản không tồn tại";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./login.jsp").forward(request, response);
        } else if (password.equals(c1.getPassword())) {
            if (c1.getBanstatus() == true){
                response.sendRedirect("ban.jsp");
            } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", c1);
            session.setMaxInactiveInterval(864000); // 1440 phút = 24 giờ
            Shop shop = shopr.getShopByUserid(c1.getUserid());
            List<CartItem> cart = cb1.getCartItemsByUserId(c1.getUserid());
            int cartsize = cart.size();
            session.setAttribute("cartsize", cartsize);
            if (shop != null) {
                session.setAttribute("shop", shop);
            }
            response.sendRedirect("./listProduct");
            }
        } else {
            String ms = "Sai mật khẩu";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
