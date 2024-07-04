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
        String query = "SELECT \n"
                + "    O.orderid, \n"
                + "    O.quantity, \n"
                + "    O.statusorder, \n"
                + "    O.totalprice, \n"
                + "    O.dateorder, \n"
                + "    P.productname, \n"
                + "    RI.nameofreceiver, \n"
                + "    RI.phonenumber, \n"
                + "    RI.address, \n"
                + "    (SELECT TOP 1 IP.image FROM IMAGEPRODUCTS IP WHERE IP.productid = O.productid ORDER BY IP.imageid) AS image, \n"
                + "    O.color, \n"
                + "    O.size, \n"
                + "    O.paymentmethods\n"
                + "FROM \n"
                + "    ORDERS O\n"
                + "JOIN \n"
                + "    PRODUCTS P ON O.productid = P.productid\n"
                + "JOIN \n"
                + "    RECEIVERINFO RI ON O.userid = RI.userid\n"
                + "WHERE \n"
                + "    P.shopid = ?;";
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
        List<orderShop> list = new ArrayList<>();
        list
                = lr.getOrderListShopOwner(2);
        System.out.println(list);
    }
}
