/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.typeOrderShop;

/**
 *
 * @author HP
 */
public class listTypeProductRepository {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<typeOrderShop> getCancelOrderListShopOwner(int shopId) {
        List<typeOrderShop> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    MAX(ORDERS.orderid) AS orderid,\n"
                + "    PRODUCTS.productname,\n"
                + "    MAX(ORDERS.nameofreceiver) AS nameofreceiver,\n"
                + "    MAX(ORDERS.phonenumber) AS phonenumber,\n"
                + "    MAX(IMAGEPRODUCTS.image) AS image,\n"
                + "    MAX(ORDERS.quantity) AS quantity,\n"
                + "    MAX(RECEIVERINFO.address) AS address,\n"
                + "    MAX(ORDERS.statusorder) AS statusorder,\n"
                + "    MAX(ORDERS.dateorder) AS dateorder,\n"
                + "    MAX(ORDERS.color) AS color,\n"
                + "    MAX(ORDERS.size) AS size,\n"
                + "    MAX(SHOPS.shopname) AS shopname,\n"
                + "    MAX(ORDERS.reasoncancel) AS reasoncancel\n"
                + "FROM \n"
                + "    ORDERS\n"
                + "JOIN \n"
                + "    PRODUCTS ON ORDERS.productid = PRODUCTS.productid\n"
                + "JOIN \n"
                + "    RECEIVERINFO ON ORDERS.userid = RECEIVERINFO.userid\n"
                + "JOIN \n"
                + "    SHOPS ON PRODUCTS.shopid = SHOPS.shopid\n"
                + "JOIN \n"
                + "    IMAGEPRODUCTS ON PRODUCTS.productid = IMAGEPRODUCTS.productid\n"
                + "WHERE \n"
                + "    SHOPS.shopid = ?\n"
                + "    AND ORDERS.statusorder = N'Đã hủy'\n"
                + "GROUP BY \n"
                + "    PRODUCTS.productname;";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopId);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new typeOrderShop(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return list;
    }

    public List<typeOrderShop> getDeliveredOrderListShopOwner(int shopId) {
        List<typeOrderShop> list = new ArrayList<>();
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
                + "    P.productname,\n"
                + "	o.nameofreceiver,\n"
                + "	o.phonenumber,\n"
                + "	Ri.image,\n"
                + "	o.quantity,\n"
                + "    o.address,\n"
                + "	o.statusorder,\n"
                + "	o.totalprice,\n"
                + "	o.dateorder,\n"
                + "	o.color,\n"
                + "	o.size,\n"
                + "	o.paymentmethods,\n"
                + "	s.shopname,\n"
                + "	e.star\n"
                + "FROM \n"
                + "    ORDERS O\n"
                + "JOIN \n"
                + "    PRODUCTS P ON O.productid = P.productid\n"
                + "JOIN \n"
                + "    SHOPS S ON P.shopid = S.shopid\n"
                + "JOIN \n"
                + "    USERS U ON S.userid = U.userid\n"
                + "LEFT JOIN \n"
                + "    RankedImages RI ON P.productid = RI.productid AND RI.rn = 1\n"
                + "LEFT JOIN \n"
                + "    EVALUATE E ON O.productid = E.productid AND O.userid = E.userid\n"
                + "WHERE \n"
                + "    O.statusorder = N'Đã giao'\n"
                + "    AND S.shopid = ?;";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopId);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new typeOrderShop(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getInt(15)));
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
        listTypeProductRepository lr = new listTypeProductRepository();
        List<typeOrderShop> list = new ArrayList<>();
        list
                = lr.getCancelOrderListShopOwner(2);
        System.out.println(list);
    }
}
