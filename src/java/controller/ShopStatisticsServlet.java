package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.DBConnection;

@WebServlet("/shopStatistics")
public class ShopStatisticsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Assuming you have a session attribute for shopId after user login
        int shopId = -1; // Default to -1 if shopId is not found

        // Retrieve shopId from session
        Object shopIdObj = request.getSession().getAttribute("shopId");
        if (shopIdObj != null) {
            shopId = (int) shopIdObj;
        }

        try (Connection connection = new DBConnection().getConnection()) {
            String query = "SELECT p.productname, SUM(o.quantity) AS total_quantity_sold\n" +
"FROM ORDERS o\n" +
"JOIN PRODUCTS p ON o.productid = p.productid\n" +
"WHERE o.statusorder = 'Shipped'\n" +
"GROUP BY p.productname\n" +
"ORDER BY total_quantity_sold DESC;"; // Use LIMIT 10 for MySQL, TOP 10 for SQL Server
            
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, shopId);
                try (ResultSet rs = ps.executeQuery()) {
                    List<Map<String, Object>> topProducts = new ArrayList<>();

                    while (rs.next()) {
                        String productName = rs.getString("productname");
                        int totalQuantity = rs.getInt("total_quantity_sold");

                        // Create a map for each product
                        Map<String, Object> productMap = new TreeMap<>();
                        productMap.put("productName", productName);
                        productMap.put("totalQuantitySold", totalQuantity);

                        topProducts.add(productMap);
                    }

                    Gson gson = new Gson();
                    String json = gson.toJson(topProducts);
                    PrintWriter out = response.getWriter();
                    out.print(json);
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error retrieving top products data: " + e.getMessage());
        }
    }
}
