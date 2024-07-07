package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import repository.BannedUserDAO; // Thay thế bằng tên gói của DAO thực tế

@WebServlet("/listBannedUsers")
public class ListBannedUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BannedUserDAO bannedUserDAO; // DAO cho phép truy cập cơ sở dữ liệu

    @Override
    public void init() throws ServletException {
        super.init();
        bannedUserDAO = new BannedUserDAO(); // Khởi tạo DAO trong phương thức init()
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> bannedUsers = bannedUserDAO.getBannedUsers(); // Gọi phương thức từ DAO để lấy danh sách banned users

        request.setAttribute("bannedUsers", bannedUsers);
        request.getRequestDispatcher("/bannedUsers.jsp").forward(request, response);
    }
}
