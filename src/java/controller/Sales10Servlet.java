package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.DBConnection;

@WebServlet("/sale10Data")
public class Sales10Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (Connection connection = new DBConnection().getConnection()) {
            String query = "SELECT TOP 10 s.shopid, s.shopname, COUNT(o.orderid) AS total_shipped_orders\n" +
                           "FROM SHOPS s\n" +
                           "JOIN PRODUCTS p ON s.shopid = p.shopid\n" +
                           "JOIN ORDERS o ON p.productid = o.productid\n" +
                           "WHERE o.statusorder = N'Đã giao'\n" +
                           "GROUP BY s.shopid, s.shopname\n" +
                           "ORDER BY total_shipped_orders DESC";
            
            try (PreparedStatement ps = connection.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                Map<String, Integer> salesData = new TreeMap<>();

                while (rs.next()) {
                    String shopName = rs.getString("shopname");
                    int totalShippedOrders = rs.getInt("total_shipped_orders");
                    salesData.put(shopName, totalShippedOrders);
                }

                Gson gson = new Gson();
                String json = gson.toJson(salesData);
                PrintWriter out = response.getWriter();
                out.print(json);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
