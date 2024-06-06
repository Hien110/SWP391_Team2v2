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
        String query = "select productname, price, description, quantity, numberstar, image, color, size, typeid from PRODUCTS "
                + "INNER JOIN IMAGEPRODUCTS ON PRODUCTS.productid = IMAGEPRODUCTS.productid "
                + "INNER JOIN COLORPRODUCTS ON PRODUCTS.productid = COLORPRODUCTS.productid "
                + "INNER JOIN TYPEPRODUCT ON PRODUCTS.productid = TYPEPRODUCT.productid "
                + "INNER JOIN SIZEPRODUCTS ON PRODUCTS.productid = SIZEPRODUCTS.productid";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getString("productname"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getInt("numberstar"),
                        rs.getString("image"),
                        rs.getString("color"),
                        rs.getString("size"),
                        rs.getInt("typeid")
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

    public void insertProduct(int productId, String productName, double price, String description, int quantity, double numberStar, int totalStar, int shopId) {
        String query = "INSERT INTO dbo.PRODUCTS (productId, productName, price, description, quantity, numberStar, totalStar, shopId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setString(2, productName);
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setInt(5, quantity);
            ps.setDouble(6, numberStar);
            ps.setInt(7, totalStar);
            ps.setInt(8, shopId);
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

    public void updateProduct(int productId, String productName, double price, String description, int quantity, double numberStar, int totalStar, int shopId) {
        String query = "UPDATE dbo.PRODUCTS SET productName = ?, price = ?, description = ?, quantity = ?, numberStar = ?, totalStar = ?, shopId = ? WHERE productId = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, productName);
            ps.setDouble(2, price);
            ps.setString(3, description);
            ps.setInt(4, quantity);
            ps.setDouble(5, numberStar);
            ps.setInt(6, totalStar);
            ps.setInt(7, shopId);
            ps.setInt(8, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    public List<Product> getNumberProductSell() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT " +
                "    p.productid, " +
                "    p.productname, " +
                "    p.price, " +
                "    p.description, " +
                "    SUM(o.quantity) AS total_sales_quantity, " +
                "    p.numberstar, " +
                "    p.avaragestar, " +
                "    p.shopid " +
                "FROM " +
                "    [SWP391_DBv3].[dbo].[PRODUCTS] p " +
                "LEFT JOIN " +
                "    [SWP391_DBv3].[dbo].[ORDERS] o ON p.productid = o.productid " +
                "GROUP BY " +
                "    p.productid, " +
                "    p.productname, " +
                "    p.price, " +
                "    p.description, " +
                "    p.numberstar, " +
                "    p.avaragestar, " +
                "    p.shopid " +
                "ORDER BY " +
                "    total_sales_quantity DESC";
        
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productid"));
                product.setProductName(rs.getString("productname"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("total_sales_quantity"));
                product.setNumberStar(rs.getInt("numberstar"));
                product.setAverageStar(rs.getDouble("avaragestar"));
                product.setShopId(rs.getInt("shopid"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getListByPage(List<Product> list, int start, int end) {
        List<Product> sublist = new ArrayList<>();
        for (int i = start; i < end && i < list.size(); i++) {
            sublist.add(list.get(i));
        }
        return sublist;
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
        List<Product> products = pr.getNumberProductSell();
        
        
        // Displaying retrieved products
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
