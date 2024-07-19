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
                + "    PRODUCTS.productname, \n"
                + "    MIN(ORDERS.orderid) AS orderid,\n"
                + "    MIN(ORDERS.quantity) AS quantity,\n"
                + "    MIN(ORDERS.statusorder) AS statusorder,\n"
                + "    MIN(ORDERS.totalprice) AS totalprice,\n"
                + "    MIN(ORDERS.dateorder) AS dateorder,\n"
                + "    MIN(RECEIVERINFO.nameofreceiver) AS nameofreceiver,\n"
                + "    MIN(RECEIVERINFO.phonenumber) AS phonenumber,\n"
                + "    MIN(RECEIVERINFO.address) AS address,\n"
                + "    MIN(IMAGEPRODUCTS.image) AS image,\n"
                + "    MIN(ORDERS.color) AS color,\n"
                + "    MIN(ORDERS.size) AS size,\n"
                + "    MIN(ORDERS.paymentmethods) AS paymentmethods\n"
                + "FROM \n"
                + "    ORDERS \n"
                + "JOIN \n"
                + "    IMAGEPRODUCTS ON ORDERS.productid = IMAGEPRODUCTS.productid\n"
                + "JOIN \n"
                + "    PRODUCTS ON ORDERS.productid = PRODUCTS.productid\n"
                + "JOIN \n"
                + "    RECEIVERINFO ON ORDERS.userid = RECEIVERINFO.userid\n"
                + "JOIN \n"
                + "    SHOPS ON ORDERS.userid = SHOPS.userid\n"
                + "WHERE \n"
                + "    SHOPS.shopid = ? AND( ORDERS.statusorder =N'Đang xử lí' OR ORDERS.statusorder =N'Đang giao')\n"
                + "GROUP BY \n"
                + "    PRODUCTS.productname;";
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
    public void updateStatusOrderList(int orderid){
        String query ="UPDATE ORDERS SET statusorder =N'Đang giao' WHERE orderid = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderid);
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
        seeOrderListRepository lr = new seeOrderListRepository();
        List<orderShop> list = new ArrayList<>();
lr.updateStatusOrderList( 1);
    }
}
