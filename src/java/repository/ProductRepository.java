package repository;

import DAO.DBConnection;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = """
                       SELECT productname, price, description, quantityp, avagerstar, image, color, size, typeid
                       FROM PRODUCTS 
                       INNER JOIN IMAGEPRODUCTS ON PRODUCTS.productid = IMAGEPRODUCTS.productid
                       INNER JOIN COLORPRODUCTS ON PRODUCTS.productid = COLORPRODUCTS.productid
                       INNER JOIN SIZEPRODUCTS ON PRODUCTS.productid = SIZEPRODUCTS.productid;""";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return list;
    }
   

    public void deleteProduct(int productId) {
        String query = "DELETE FROM dbo.PRODUCTS WHERE productId = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }


    public List<Product> getListByPage(List<Product> list, int start, int end) {
        List<Product> sublist = new ArrayList<>();
        for (int i = start; i < end && i < list.size(); i++) {
            sublist.add(list.get(i));
        }
        return sublist;
    }
    public void addToCartById(int cartId, int productId, int userId, int quantity) {
        String query = "INSERT INTO CART (cartid, productid, userid, quantity) VALUES (?, ?, ?, ?)";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.setInt(3, userId);
            ps.setInt(4, quantity);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }
    public List<Product> listToCart(int userId) {
        List<Product> list = new ArrayList<>();
        String query = """
                       SELECT p.productname, p.price, p.description, c.quantity, p.avagerstar, i.image, cp.color, sp.size, p.typeid
                       FROM CART c
                       INNER JOIN PRODUCTS p ON c.productid = p.productid
                       INNER JOIN IMAGEPRODUCTS i ON p.productid = i.productid
                       INNER JOIN COLORPRODUCTS cp ON p.productid = cp.productid
                       INNER JOIN SIZEPRODUCTS sp ON p.productid = sp.productid
                       WHERE c.userid = ?""";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return list;
    }
    
    public void deleteFromCart(int cartId, int productId, int userId) {
        String query = "DELETE FROM CART WHERE cartid = ? AND productid = ? AND userid = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.setInt(3, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    public List<String> getImage(int productId) {
        List<String> images = new ArrayList<>();
        String query = "SELECT image FROM IMAGEPRODUCTS WHERE productid = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                images.add(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return images;
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
        ProductRepository pr = new ProductRepository();
        List<Product> images = pr.getAllProduct();

        // Displaying retrieved images
        System.out.println(images);
    }
}