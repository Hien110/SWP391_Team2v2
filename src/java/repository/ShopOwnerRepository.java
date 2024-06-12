/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DAO.DBConnection;
import model.Shop;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ADMIN
 */
public class ShopOwnerRepository extends DBConnection{
    
     public void newShop(Shop s) {
        String sql = "insert into SHOPS values (?, ?, ?, 0,?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getShopName());
            st.setString(2, s.getAddress());
            st.setString(3, s.getDesshop());
            st.setInt(4, s.getUserId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
