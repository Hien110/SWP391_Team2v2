/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import DAO.DBConnection;
import model.ProductShop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.productColors;
import model.productSize;

public class ProductShopOwnerRepository {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    //    deleteproductShopOwner

    public void deleteProductShopOwner(int productId) {
        String query
                = "DELETE FROM EVALUATE WHERE productid = ?; "
                + "DELETE FROM CART WHERE productid = ?; "
                + "DELETE FROM ORDERS WHERE productid = ?; "
                + "DELETE FROM REPORTPRODUCT WHERE productid = ?; "
                + // Only include if TYPEPRODUCT table exists
                "DELETE FROM IMAGEPRODUCTS WHERE productid = ?; "
                + "DELETE FROM COLORPRODUCTS WHERE productid = ?; "
                + "DELETE FROM SIZEPRODUCTS WHERE productid = ?; "
                + "DELETE FROM PRODUCTS WHERE productid = ?;";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, productId);
            ps.setInt(3, productId);
            ps.setInt(4, productId);
            ps.setInt(5, productId);
            ps.setInt(6, productId);
            ps.setInt(7, productId);
            ps.setInt(8, productId);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    public List<productColors> getAllProductColors(int productId) {
        List<productColors> list = new ArrayList<>();
        String query = "SELECT color from COLORPRODUCTS WHERE productid = ?";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new productColors(
                        rs.getString(1)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<productSize> getAllProductSize(int productId) {
        List<productSize> list = new ArrayList<>();
        String query = "SELECT size from SIZEPRODUCTS WHERE productid = ?";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new productSize(
                        rs.getString(1)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<ProductShop> getAllProductShopOwner(int shopId) {
        List<ProductShop> list = new ArrayList<>();
        String query = "SELECT DISTINCT \n"
                + "    P.productid, \n"
                + "    P.productname, \n"
                + "    P.price, \n"
                + "    P.description, \n"
                + "    P.quantityp, \n"
                + "    P.avagerstar, \n"
                + "    IP.image\n"
                + "FROM \n"
                + "    PRODUCTS P\n"
                + "INNER JOIN \n"
                + "    (\n"
                + "        SELECT \n"
                + "            productid, \n"
                + "            MIN(imageid) AS min_imageid \n"
                + "        FROM \n"
                + "            IMAGEPRODUCTS \n"
                + "        GROUP BY \n"
                + "            productid\n"
                + "    ) AS FirstImage ON P.productid = FirstImage.productid\n"
                + "INNER JOIN \n"
                + "    IMAGEPRODUCTS IP ON FirstImage.productid = IP.productid AND FirstImage.min_imageid = IP.imageid\n"
                + "WHERE \n"
                + "    P.shopid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductShop(
                        rs.getInt("productid"), // sửa từ productId thành productid
                        rs.getString("productname"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("quantityp"), // sửa từ quantity thành quantityp
                        rs.getDouble("avagerstar"), // sửa từ averageStar thành avagerstar
                        rs.getString("image")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    //updateProductShopOwner
    public void updateProductShopOwner(int productId, String productName, double price, String description, int quantityp, double averageStar, String image) {
        String query = "UPDATE PRODUCTS SET  productname = ?,  price = ?, description = ?, quantityp = ?, avagerstar = ? WHERE productid = ?;"
                + "UPDATE IMAGEPRODUCTS SET image = ? WHERE productid =?;";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, productName);
            ps.setDouble(2, price);
            ps.setString(3, description);
            ps.setInt(4, quantityp);
            ps.setDouble(5, averageStar);
            ps.setInt(6, productId);
            ps.setString(7, image);
            ps.setInt(8, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }
//addproductShopOwner

    public void addProductShopOwner(String productName, double price, String description, int quantityp, double averageStar, String image) {
        String query = "INSERT INTO dbo.PRODUCTS ( productName, price, description, quantityp, avagerstar, image) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, productName);
            ps.setDouble(2, price);
            ps.setString(3, description);
            ps.setInt(4, quantityp);
            ps.setDouble(5, averageStar);
            ps.setString(6, image);
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
        ProductShopOwnerRepository pr = new ProductShopOwnerRepository();
        List<productColors> list = new ArrayList<>();
        list
                = pr.getAllProductColors(4);
        System.out.println(list);
    }
}
