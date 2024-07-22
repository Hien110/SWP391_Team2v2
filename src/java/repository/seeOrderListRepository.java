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
        String query = "WITH RankedImages AS (\n"
                + "    SELECT \n"
                + "        imageid,\n"
                + "        image,\n"
                + "        productid,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY productid ORDER BY imageid) AS rn\n"
                + "    FROM \n"
                + "        IMAGEPRODUCTS\n"
                + ")\n"
                + "SELECT \n"
                + "    O.orderid,\n"
                + "    O.quantity,\n"
                + "    O.statusorder,\n"
                + "    O.totalprice,\n"
                + "    O.dateorder,\n"
                + "    P.productname,\n"
                + "    O.nameofreceiver,\n"
                + "    O.phonenumber,\n"
                + "    O.address,\n"
                + "    RI.image,\n"
                + "    O.color,\n"
                + "    O.size,\n"
                + "    O.paymentmethods\n"
                + "FROM \n"
                + "    ORDERS O\n"
                + "JOIN \n"
                + "    PRODUCTS P ON O.productid = P.productid\n"
                + "JOIN \n"
                + "    SHOPS S ON P.shopid = S.shopid\n"
                + "LEFT JOIN \n"
                + "    RankedImages RI ON P.productid = RI.productid AND RI.rn = 1\n"
                + "WHERE \n"
                + "    (O.statusorder = N'Đang xử lí' OR O.statusorder = N'Đang giao')\n"
                + "    AND S.shopid = ?;";
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

    public void updateStatusOrderList(int orderid) {
        String query = "UPDATE ORDERS SET statusorder =N'Đang giao' WHERE orderid = ?";
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
        list =
                lr.getOrderListShopOwner(2);
        System.out.println(list);
                
    }
}
