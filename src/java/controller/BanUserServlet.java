package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/banUser")
public class BanUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        try {
            // Kết nối tới cơ sở dữ liệu
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost;databaseName=SWP391_DBV5;user=sa;password=Password.1;trustServerCertificate=true";
            Connection connection = DriverManager.getConnection(dbURL);

            // Kiểm tra trạng thái hiện tại của người dùng
            int currentBanStatus = getCurrentBanStatus(connection, userId);

            // Cập nhật trạng thái ban của người dùng
            int newBanStatus = (currentBanStatus == 1) ? 0 : 1; // Toggle giữa 0 và 1
            updateBanStatus(connection, userId, newBanStatus);

            connection.close();

            // Chuyển hướng về trang quản trị để hiển thị lại danh sách người dùng
            response.sendRedirect(request.getContextPath() + "/adminPage.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/adminPage.jsp"); // Xử lý lỗi và chuyển hướng trở lại trang quản trị
        }
    }

    // Phương thức để lấy trạng thái ban hiện tại của người dùng
    private int getCurrentBanStatus(Connection connection, String userId) {
        int currentBanStatus = 0; // Mặc định là chưa bị ban

        try {
            String query = "SELECT banstatus FROM users WHERE userid = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                currentBanStatus = rs.getInt("banstatus");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentBanStatus;
    }

    // Phương thức để cập nhật trạng thái ban của người dùng
    private void updateBanStatus(Connection connection, String userId, int newBanStatus) {
        try {
            String updateQuery = "UPDATE users SET banstatus = ? WHERE userid = ?";
            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setInt(1, newBanStatus);
            pstmt.setString(2, userId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Kiểm tra và ghi nhật ký để xem trạng thái ban/unban được cập nhật như thế nào
System.out.println("Updating ban status for user " + userId + " to " + newBanStatus);

    }
    
    
}
