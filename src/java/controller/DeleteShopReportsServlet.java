package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteShopReports")
public class DeleteShopReportsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DeleteShopReportsServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shopId = request.getParameter("shopId");

        try {
            // Kết nối tới cơ sở dữ liệu
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost;databaseName=SWP391_DBV6;user=sa;password=Password.1;trustServerCertificate=true";
            try (Connection connection = DriverManager.getConnection(dbURL)) {
                // Xoá các báo cáo về cửa hàng
                deleteShopReports(connection, shopId);
            }

            // Chuyển hướng về trang quản trị để hiển thị lại danh sách cửa hàng
            response.sendRedirect(request.getContextPath() + "/listReportedShops");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting shop reports", e);
            response.sendRedirect(request.getContextPath() + "/listReportedShops"); // Xử lý lỗi và chuyển hướng trở lại trang quản trị
        }
    }

    // Phương thức để xoá các báo cáo về cửa hàng
    private void deleteShopReports(Connection connection, String shopId) {
        try {
            String deleteQuery = "DELETE FROM shop_reports WHERE shopid = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
                pstmt.setString(1, shopId);
                pstmt.executeUpdate();
            }
            // Ghi nhật ký để xem báo cáo về cửa hàng đã được xoá
            logger.info("Deleted shop reports for shop " + shopId);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting shop reports for shop: " + shopId, e);
        }
    }
}
