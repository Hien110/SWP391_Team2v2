/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DAO.DBConnection;
import model.orderShop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class seeOrderListRepository {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<orderShop> getOrderListShopOwner(int Shopid) {
    List<orderShop> list = new ArrayList<>();
    String query = "SELECT ORDERS.orderid, ORDERS.quantity, ORDERS.statusorder, ORDERS.totalprice, ORDERS.dateorder, " +
            "PRODUCTS.productname, RECEIVERINFO.nameofreceiver, RECEIVERINFO.phonenumber, RECEIVERINFO.address, " +
            "IMAGEPRODUCTS.image, COLORPRODUCTS.color, SIZEPRODUCTS.size, ORDERS.paymentmethods " +
            "FROM ORDERS " +
            "JOIN PRODUCTS ON ORDERS.productid = PRODUCTS.productid " +
            "INNER JOIN RECEIVERINFO ON ORDERS.receiverinfoid = RECEIVERINFO.receiverinfoid " +
            "INNER JOIN IMAGEPRODUCTS ON PRODUCTS.productid = IMAGEPRODUCTS.productid " +
            "INNER JOIN COLORPRODUCTS ON PRODUCTS.productid = COLORPRODUCTS.productid " +
            "INNER JOIN SIZEPRODUCTS ON PRODUCTS.productid = SIZEPRODUCTS.productid " +
            "INNER JOIN USERS ON ORDERS.userid = USERS.userid " +
            "INNER JOIN SHOPS ON USERS.userid = SHOPS.userid " +
            "WHERE SHOPS.shopid = ?";
    try {
        conn = new DBConnection().getConnection();
        ps = conn.prepareStatement(query);
        ps.setInt(1, Shopid);

        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new orderShop(
                    rs.getInt("orderid"),
                    rs.getInt("quantity"),
                    rs.getString("statusorder"),
                    rs.getDouble("totalprice"),
                    rs.getDate("dateorder").toString(), // Lưu ý: dateorder là kiểu Date, chuyển đổi sang String
                    rs.getString("productname"),
                    rs.getString("nameofreceiver"),
                    rs.getString("phonenumber"),
                    rs.getString("address"),
                    rs.getString("image"),
                    rs.getString("color"),
                    rs.getString("size"),
                    rs.getString("paymentmethods"))
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        closeConnections();
    }
    return list;
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
        seeOrderListRepository lr = new seeOrderListRepository();
        List<orderShop> list = lr.getOrderListShopOwner(1);
        System.out.println(list);
    }
}
