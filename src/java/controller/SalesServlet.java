package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.DBConnection;

@WebServlet("/salesData")
public class SalesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (Connection connection = new DBConnection().getConnection()) {
            String query = "SELECT productName, SUM(quantity) AS totalQuantity FROM Sales GROUP BY productName ORDER BY totalQuantity DESC";
            try (PreparedStatement ps = connection.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                Map<String, Integer> salesData = new TreeMap<>();

                while (rs.next()) {
                    String productName = rs.getString("productName");
                    int totalQuantity = rs.getInt("totalQuantity");
                    salesData.put(productName, totalQuantity);
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
