/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DAO.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Shop;

/**
 *
 * @author HP
 */
public class ShopWishlistRepository {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Shop> listShopWishList(int shopid) {
        List<Shop> list = new ArrayList<>();
        String query = "SELECT WISHLIST.shopid, SHOPS.shopname, USERS.imgavt\n"
                + "FROM WISHLIST\n"
                + "JOIN SHOPS ON SHOPS.shopid = WISHLIST.shopid\n"
                + "JOIN USERS ON USERS.userid = WISHLIST.userid\n"
                + "WHERE USERS.userid = ?;";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Shop(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return list;
    }
    public void deleteShopWishList(int shopid, int userid){
        String query = "DELETE FROM WISHLIST WHERE shopid = ? AND userid = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopid);
            ps.setInt(2, userid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    private void closeConnections() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ShopWishlistRepository swl = new ShopWishlistRepository();
        List<Shop> list = new ArrayList<>();
      swl.deleteShopWishList(1, 1);
    }

}
