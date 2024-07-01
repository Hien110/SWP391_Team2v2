package repository;

import DAO.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();

        String query = """
                SELECT p.productid, p.productname, p.price, p.description, p.quantityp, p.avagerstar, i.image, p.typeid, s.shopid, s.shopname
                FROM PRODUCTS p
                LEFT JOIN IMAGEPRODUCTS i ON p.productid = i.productid
                INNER JOIN SHOPS s ON p.shopid = s.shopid
                GROUP BY p.productid, p.productname, p.price, p.description, p.quantityp, p.avagerstar, i.image, p.typeid, s.shopid, s.shopname;
                """;

        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("productid"),
                        rs.getString("productname"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("quantityp"),
                        rs.getDouble("avagerstar"),
                        rs.getString("image"),
                        null, // No color
                        null, // No size
                        rs.getInt("typeid"),
                        rs.getInt("shopid"),
                        rs.getString("shopname")
                );
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }


    public void deleteProduct(int productId) {
        String query = "DELETE FROM dbo.PRODUCTS WHERE productId = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
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
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.setInt(3, userId);
            ps.setInt(4, quantity);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFromCart(int cartId, int productId, int userId) {
        String query = "DELETE FROM CART WHERE cartid = ? AND productid = ? AND userid = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.setInt(3, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getImage(int productId) {
        List<String> images = new ArrayList<>();
        String query = "SELECT image FROM IMAGEPRODUCTS WHERE productid = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    images.add(rs.getString("image"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }

    public Product getProductByIdAndShop(int productId, int shopId) {
        Product product = null;
        String query = """
                       SELECT p.productid, p.productname, p.price, p.description, p.quantityp, p.avagerstar, p.image, p.color, p.size, t.typename, s.shopid, s.shopname
                       FROM PRODUCTS p
                       INNER JOIN SHOPS s ON p.shopid = s.shopid
                       LEFT JOIN TYPEITEM t ON p.typeid = t.typeid
                       WHERE p.productid = ? AND p.shopid = ?""";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            ps.setInt(2, shopId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new Product(
                            rs.getInt("productid"),
                            rs.getString("productname"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getInt("quantityp"),
                            rs.getDouble("avagerstar"),
                            rs.getString("image"),
                            rs.getString("color"),
                            rs.getString("size"),
                            rs.getString("typename"),
                            rs.getInt("shopid"),
                            rs.getString("shopname")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product getProductById(String productId) {
        Product product = null;
        String query = """
                       SELECT p.productid, p.productname, p.price, p.description, p.quantityp, p.avagerstar, i.image, cp.color, sp.size, t.typename, s.shopid, s.shopname
                       FROM PRODUCTS p
                       LEFT JOIN IMAGEPRODUCTS i ON p.productid = i.productid
                       LEFT JOIN COLORPRODUCTS cp ON p.productid = cp.productid
                       LEFT JOIN SIZEPRODUCTS sp ON p.productid = sp.productid
                       LEFT JOIN TYPEITEM t ON p.typeid = t.typeid
                       LEFT JOIN SHOPS s ON p.shopid = s.shopid
                       WHERE p.productid = ?""";
        try (Connection conn = new DBConnection().getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new Product(
                            rs.getInt("productid"),
                            rs.getString("productname"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getInt("quantityp"),
                            rs.getDouble("avagerstar"),
                            rs.getString("image"),
                            rs.getString("color"),
                            rs.getString("size"),
                            rs.getString("typename"),
                            rs.getInt("shopid"),
                            rs.getString("shopname")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public int addProduct(String name, String description, String price, String quantity, int shopid, String type) throws SQLException {
        String sql = "BEGIN TRANSACTION;\n"
                + "DECLARE @NewProductID TABLE (productid INT);\n"
                + "INSERT INTO PRODUCTS (productname, price, description, quantityp, shopid, typeid)\n"
                + "OUTPUT INSERTED.productid INTO @NewProductID\n"
                + "VALUES (?, ?, ?, ?, ?, ?);\n"
                + "DECLARE @ProductID INT;\n"
                + "SELECT @ProductID = productid FROM @NewProductID;\n"
                + "COMMIT TRANSACTION;";
    
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setDouble(2, Double.parseDouble(price));
            statement.setString(3, description);
            statement.setInt(4, Integer.parseInt(quantity));
            statement.setInt(5, shopid);
            statement.setString(6, type);
            statement.executeUpdate();

            int productId;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    productId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Adding product failed, no ID obtained.");
                }
            }
            return productId;
        }
    }

    public void addSizes(int productId, String[] sizes) throws SQLException {
        String sql = "INSERT INTO SIZEPRODUCTS (size, productid) VALUES (?, ?);";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            for (String size : sizes) {
                statement.setString(1, size);
                statement.setInt(2, productId);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public void addColors(int productId, String[] colors) throws SQLException {
        String sql = "INSERT INTO COLORPRODUCTS (color, productid) VALUES (?, ?);";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            for (String color : colors) {
                statement.setString(1, color);
                statement.setInt(2, productId);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public void addImageUrls(int productId, List<String> imageUrls) throws SQLException {
        String sql = "INSERT INTO IMAGEPRODUCTS (image, productid) VALUES (?, ?);";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            for (String imageUrl : imageUrls) {
                statement.setString(1, imageUrl);
                statement.setInt(2, productId);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public List<String> getAvailableSizes(String productId) {
        List<String> sizes = new ArrayList<>();
        String query = "SELECT size FROM SIZEPRODUCTS WHERE productid = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sizes.add(rs.getString("size"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sizes;
    }

    public List<String> getAvailableColors(String productId) {
        List<String> colors = new ArrayList<>();
        String query = "SELECT color FROM COLORPRODUCTS WHERE productid = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    colors.add(rs.getString("color"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colors;
    }

    public static void main(String[] args) {
        ProductRepository pr = new ProductRepository();
        List<Product> products = pr.getAllProduct();
        System.out.println(products);
    }
}
