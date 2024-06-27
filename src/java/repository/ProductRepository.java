package repository;

import DAO.DBConnection;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends DBConnection {

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
    //    deleteproductShopOwner
    public void deleteProductShopOwner(int productId) {
        String query =
    "DELETE FROM EVALUATE WHERE productid = ?; " +
    "DELETE FROM CART WHERE productid = ?; " +
    "DELETE FROM ORDERS WHERE productid = ?; " +
    "DELETE FROM REPORTPRODUCT WHERE productid = ?; " +
    "DELETE FROM TYPEPRODUCT WHERE productid = ?; " + // Only include if TYPEPRODUCT table exists
    "DELETE FROM IMAGEPRODUCTS WHERE productid = ?; " +
    "DELETE FROM COLORPRODUCTS WHERE productid = ?; " +
    "DELETE FROM SIZEPRODUCTS WHERE productid = ?; " +
    "DELETE FROM PRODUCTS WHERE productid = ?;";
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
            ps.setInt(9, productId);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }
        public List<Product> getAllProductShopOwner(int shopId) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT PRODUCTS.productid, PRODUCTS.productname, PRODUCTS.price, PRODUCTS.description, PRODUCTS.quantityp, PRODUCTS.avagerstar, IMAGEPRODUCTS.image FROM PRODUCTS "
                + "INNER JOIN IMAGEPRODUCTS ON PRODUCTS.productid = IMAGEPRODUCTS.productid "
                + "WHERE PRODUCTS.shopid = ?;";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, shopId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
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
//listProductShop
 public Product getProductShopOwnerByID(String productId) {
        String query = "SELECT productId, productName, price, description, quantityp, averageStar, image FROM PRODUCTS WHERE productId = ?";
        Product product = null;

        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, productId);
            rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product(       
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getDouble("price"),
                    rs.getString("description"),
                    rs.getInt("quantityp"),
                    rs.getInt("averageStar"),
                    rs.getString("image")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }

        return product;
    }
    //updateProductShopOwner

    public void updateProductShopOwner(int productId, String productName, double price, String description, int quantity, double averageStar, String image) {
        String query = "UPDATE dbo.PRODUCTS SET productName = ?, price = ?, description = ?, quantityp = ?, averageStar = ? WHERE productid = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setString(2, productName);
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setInt(5, quantity);
            ps.setDouble(6, averageStar);
            ps.setString(7, image);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }
//addproductShopOwner

    public void addProductShopOwner(String productName, double price, String description, int quantity, double numberStar, int totalStar, int shopId) {
        String query = "INSERT INTO dbo.PRODUCTS ( productName, price, description, quantityp, numberStar, totalStar, shopId) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
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
public int addProduct(String name, String description, String price, String quantity, int shopid, String type) throws SQLException {
        String sql = "BEGIN TRANSACTION;\n"
                + "DECLARE @NewProductID TABLE (productid INT);\n"
                + "INSERT INTO [SWP391_DBv5].[dbo].[PRODUCTS] (productname, price, description, quantityp, shopid, typeid)\n"
                + "OUTPUT INSERTED.productid INTO @NewProductID\n"
                + "VALUES (?, ?, ?, ?, ?, ?);\n"
                + "DECLARE @ProductID INT;\n"
                + "SELECT @ProductID = productid FROM @NewProductID;\n"
                + "COMMIT TRANSACTION;";
        
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setDouble(2, Double.parseDouble(price));
            statement.setString(3, description);
            statement.setInt(4, Integer.parseInt(quantity));
            statement.setInt(5, shopid);
            statement.setString(6, type);
            statement.executeUpdate();

            // Lấy productid được sinh ra
            int productId;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    productId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Thêm sản phẩm không thành công, không có productid được tạo ra.");
                }
            }
            return productId;
        }
    }

    public void addSizes(int productId, String[] sizes) throws SQLException {
        String sql = "INSERT INTO [SWP391_DBv5].[dbo].[SIZEPRODUCTS] (size, productid) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (String size : sizes) {
                statement.setString(1, size);
                statement.setInt(2, productId);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public void addColors(int productId, String[] colors) throws SQLException {
        String sql = "INSERT INTO [SWP391_DBv5].[dbo].[COLORPRODUCTS] (color, productid) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (String color : colors) {
                statement.setString(1, color);
                statement.setInt(2, productId);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public void addImageUrls(int productId, List<String> imageUrls) throws SQLException {
        String sql = "INSERT INTO [SWP391_DBv5].[dbo].[IMAGEPRODUCTS] (image, productid) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (String imageUrl : imageUrls) {
                statement.setString(1, imageUrl);
                statement.setInt(2, productId);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }


    public static void main(String[] args) {
        ProductRepository pr = new ProductRepository();
        List<Product> images = pr.getAllProduct();

        // Displaying retrieved images
        System.out.println(images);
    }
}