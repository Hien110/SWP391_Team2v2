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
        String query = "SELECT ORDERS.orderid, PRODUCTS.productname, IMAGEPRODUCTS.image, ORDERS.quantity, RECEIVERINFO.address, " +
                "ORDERS.statusorder, ORDERS.totalprice, ORDERS.dateorder, COLORPRODUCTS.color, SIZEPRODUCTS.size, ORDERS.paymentmethods, SHOPS.shopname " +
                "FROM SHOPS " +
                "JOIN PRODUCTS ON SHOPS.shopid = PRODUCTS.shopid " +
                "JOIN ORDERS ON PRODUCTS.productid = ORDERS.productid " +
                "JOIN RECEIVERINFO ON ORDERS.receiverinfoid = RECEIVERINFO.receiverinfoid " +
                "JOIN IMAGEPRODUCTS ON PRODUCTS.productid = IMAGEPRODUCTS.productid " +
                "JOIN COLORPRODUCTS ON PRODUCTS.productid = COLORPRODUCTS.productid " +
                "JOIN SIZEPRODUCTS ON PRODUCTS.productid = SIZEPRODUCTS.productid " +
                "WHERE SHOPS.shopid = ? AND ORDERS.statusorder = 'Canceled';";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopId);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new typeOrderShop(
                        rs.getInt("orderID"), 
                        rs.getString("productname"),
                        rs.getString("image"), 
                        rs.getInt("quantity"), 
                        rs.getString("address"), 
                        rs.getString("statusorder"), 
                        rs.getInt("totalprice"), 
                        rs.getString("dateorder"), 
                        rs.getString("color"), 
                        rs.getString("size"), 
                        rs.getString("paymentmethods"), 
                        rs.getString("shopname"),
                        rs.getString("reason"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return list;
    }
    public List<typeOrderShop> getDeliveredOrderListShopOwner(int shopId){
        List<typeOrderShop> list = new ArrayList<>();
        String query = "SELECT ORDERS.orderid, PRODUCTS.productname, RECEIVERINFO.nameofreceiver, RECEIVERINFO.phonenumber, IMAGEPRODUCTS.image, ORDERS.quantity, RECEIVERINFO.address, " +
"                ORDERS.statusorder, ORDERS.totalprice, ORDERS.dateorder, COLORPRODUCTS.color, SIZEPRODUCTS.size, ORDERS.paymentmethods, SHOPS.shopname, EVALUATE.star FROM SHOPS " +
"                JOIN PRODUCTS ON SHOPS.shopid = PRODUCTS.shopid " +
"                JOIN ORDERS ON PRODUCTS.productid = ORDERS.productid " +
"                JOIN EVALUATE ON PRODUCTS.productid = EVALUATE.productid"+
"                JOIN RECEIVERINFO ON ORDERS.receiverinfoid = RECEIVERINFO.receiverinfoid " +
"                JOIN IMAGEPRODUCTS ON PRODUCTS.productid = IMAGEPRODUCTS.productid " +
"                JOIN COLORPRODUCTS ON PRODUCTS.productid = COLORPRODUCTS.productid " +
"                JOIN SIZEPRODUCTS ON PRODUCTS.productid = SIZEPRODUCTS.productid " +
"                WHERE SHOPS.shopid = ? AND ORDERS.statusorder = 'Delivered' ";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopId);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new typeOrderShop(
                        rs.getInt("orderID"), 
                        rs.getString("productname"),
                        rs.getString("nameofreceiver"),
                        rs.getString("phonenumber"),
                        rs.getString("image"), 
                        rs.getInt("quantity"), 
                        rs.getString("address"), 
                        rs.getString("statusorder"), 
                        rs.getInt("totalprice"), 
                        rs.getString("dateorder"), 
                        rs.getString("color"), 
                        rs.getString("size"), 
                        rs.getString("paymentmethods"), 
                        rs.getString("shopname"),
                        rs.getDouble("star"))
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
        listTypeProductRepository lr = new listTypeProductRepository();
        List<typeOrderShop> list = lr.getDeliveredOrderListShopOwner(2);
        System.out.println(list);
    }
}
