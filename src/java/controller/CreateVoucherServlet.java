package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createVoucher")
public class CreateVoucherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String promotionName = request.getParameter("promotionName");
        String percentPromotionStr = request.getParameter("pecentPromotion");
        String quantityStr = request.getParameter("quantity");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Kiểm tra null hoặc chuỗi rỗng trước khi chuyển đổi
        if (promotionName == null || percentPromotionStr == null || quantityStr == null || description == null || startDate == null || endDate == null ||
            promotionName.isEmpty() || percentPromotionStr.isEmpty() || quantityStr.isEmpty() || description.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("createPromotion.jsp").forward(request, response);
            return;
        }

        int percentPromotion;
        int quantity;
        try {
            percentPromotion = Integer.parseInt(percentPromotionStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format.");
            request.getRequestDispatcher("createPromotion.jsp").forward(request, response);
            return;
        }

        Connection connection = null;

        try {
            // Kết nối tới cơ sở dữ liệu
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost;databaseName=SWP391_DBV5;user=sa;password=Password.1;trustServerCertificate=true";
            connection = DriverManager.getConnection(dbURL);

            // Chèn dữ liệu vào bảng PROMOTION
            String sql = "INSERT INTO PROMOTION (promotionname, pecentpromotion, quantity, description, startdate, enddate) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, promotionName);
            statement.setInt(2, percentPromotion);
            statement.setInt(3, quantity);
            statement.setString(4, description);
            statement.setDate(5, java.sql.Date.valueOf(startDate));
            statement.setDate(6, java.sql.Date.valueOf(endDate));
            statement.executeUpdate();

            // Chuyển hướng tới trang thành công
            request.setAttribute("message", "Voucher created successfully!");
            request.getRequestDispatcher("listVoucher.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error.");
            request.getRequestDispatcher("createPromotion.jsp").forward(request, response);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
