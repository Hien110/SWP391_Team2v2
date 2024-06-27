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
import java.util.List;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class ShopOwnerRepository extends DBConnection {

    public void newShop(Shop s) {
        String sql = "insert into SHOPS values (?, ?, ?,?);";
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

    public Shop getShopByUserid(int userid) {
        String sql = "select * from SHOPS where userid=?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(1, userid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Shop c = new Shop();
                c.setShopId(rs.getInt(1));
                c.setShopName(rs.getString(2));
                c.setAddress(rs.getString(3));
                c.setDesshop(rs.getString(4));
                c.setUserId(rs.getInt(5));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateProfileShop(Shop shop) {
        String sql = "update SHOPS set shopname=?, address=?, desshop=? where shopid = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, shop.getShopName());
            st.setString(2, shop.getAddress());
            st.setString(3, shop.getDesshop());
            st.setInt(4, shop.getShopId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }




    public static void main(String[] args) {
        ShopOwnerRepository cb = new ShopOwnerRepository();
        Shop shop1 = new Shop(8, "shopname", "address", "shopdess", 0);
        cb.updateProfileShop(shop1);
    }
}
