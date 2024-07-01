package controller;

import jakarta.servlet.http.HttpServlet;
import repository.SellerRequest;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.User;

public class SellerRequestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SellerRequest sellerRequest = new SellerRequest();
            List<User> user = sellerRequest.getUsersWithRoleId();

            request.setAttribute("users", user);
            request.getRequestDispatcher("processApproval.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // In ra thông báo lỗi và console
            System.out.println("An error occurred while fetching user data: " + e.getMessage());
            
            // Gửi thông báo lỗi đến trang error.jsp hoặc xử lý ngoại lệ khác tùy theo yêu cầu
            request.setAttribute("errorMessage", "An error occurred while fetching user data: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
