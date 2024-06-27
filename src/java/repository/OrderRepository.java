/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DAO.DBConnection;
import com.paypal.api.payments.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class OrderRepository {
    public void insertOrder(int productId, int userId, int quantity, int receiverInfoId, String statusOrder, double totalPrice, String dateOrder, int promotionId, String color, String size, String paymentMethods) {
        String query = """
                       INSERT INTO ORDERS (productid, userid, quantity, receiverinfoid, statusorder, totalprice, dateorder, promotionid, color, size, paymentmethods)
                       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                       """;

        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            ps.setInt(2, userId);
            ps.setInt(3, quantity);
            ps.setInt(4, receiverInfoId);
            ps.setString(5, statusOrder);
            ps.setDouble(6, totalPrice);
            ps.setString(7, dateOrder); 
            ps.setInt(8, promotionId);
            ps.setString(9, color);
            ps.setString(10, size);
            ps.setString(11, paymentMethods);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
