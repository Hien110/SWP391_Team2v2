/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Promotion;
import DAO.DBConnection;

public class VoucherRepository {

    private DBConnection dbConnection;

    public VoucherRepository() {
        dbConnection = new DBConnection();
    }

    public List<Promotion> getAllVouchers() {
        String sql = "SELECT * FROM PROMOTION where quantity > 0";
        List<Promotion> vouchers = new ArrayList<>();
        
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Promotion v = new Promotion();
                v.setPromotionId(rs.getInt("promotionid"));
                v.setPromotionName(rs.getString("promotionname"));
                v.setPercentPromotion(rs.getInt("pecentpromotion"));
                v.setQuantity(rs.getInt("quantity"));
                v.setDescription(rs.getString("description"));
                v.setStartDate(rs.getString("startdate"));
                v.setEndDate(rs.getString("enddate"));
                vouchers.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        
        return vouchers;
    }

    public static void main(String[] args) {
        VoucherRepository voucher = new VoucherRepository();
        List<Promotion> vouchers = voucher.getAllVouchers();
        for (Promotion v : vouchers){
            System.out.println("Voucher ID: " + v.getPromotionId());
            System.out.println("Voucher Name: " + v.getPromotionName());
            System.out.println("Percent Promotion: " + v.getPercentPromotion());
            System.out.println("Quantity: " + v.getQuantity());
            System.out.println("Description: " + v.getDescription());
            System.out.println("Start Date: " + v.getStartDate());
            System.out.println("End Date: " + v.getEndDate());
            System.out.println("-------------------------");
        }
    }
}