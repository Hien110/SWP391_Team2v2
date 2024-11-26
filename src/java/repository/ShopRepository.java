package repository;

import DAO.DBConnection;
import model.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShopRepository {

    public static List<Shop> getAllShopInfoWallet() {
        List<Shop> shopList = new ArrayList<>();

        String query = """
                SELECT S.shopid, S.shopname, W.surplus, U.emailpaypal
                FROM SHOPS S
                INNER JOIN WALLET W ON S.userid = W.userid
                INNER JOIN USERS U ON S.userid = U.userid;
                """;

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Shop shop = new Shop(
                        rs.getInt("shopid"),
                        rs.getString("shopname"),
                        rs.getDouble("surplus"),
                        rs.getString("emailpaypal")
                );
                shopList.add(shop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopList;
    }
    
    public static Shop getShopById(int shopId) {
        String query = """
                SELECT S.shopid, S.shopname, W.surplus, U.emailpaypal
                FROM SHOPS S
                INNER JOIN WALLET W ON S.userid = W.userid
                INNER JOIN USERS U ON S.userid = U.userid
                WHERE S.shopid = ?;
                """;

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, shopId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Shop(
                            rs.getInt("shopid"),
                            rs.getString("shopname"),
                            rs.getDouble("surplus"),
                            rs.getString("emailpaypal")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }

    public static void main(String[] args) {
        ShopRepository shopService = new ShopRepository();
//        List<Shop> shops = shopService.getAllShopInfoWallet();
////
////        for (Shop shop : shops) {
////            System.out.println(shop);
////        }
        
        int testShopId = 6; // Example shop ID
        Shop shop = shopService.getShopById(testShopId);
        if (shop != null) {
            System.out.println(shop);
        } else {
            System.out.println("Shop not found with ID: " + testShopId);
        }
    }
}
