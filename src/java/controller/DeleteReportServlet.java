package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.DBConnection;

@WebServlet("/deleteReport")
public class DeleteReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int shopId = Integer.parseInt(request.getParameter("shopId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        try (Connection connection = new DBConnection().getConnection()) {
            String query = "DELETE FROM REPORTSHOP WHERE shopid = ? AND userid = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, shopId);
                ps.setInt(2, userId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/listReportedShops");
    }
}
