package repository;

import DAO.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import model.ProductInfor;

public class ProductRepository {

    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        List<Product> carousel1Products = new ArrayList<>();
        List<Product> carousel2Products = new ArrayList<>();
        List<Product> carousel3Products = new ArrayList<>();
        List<Product> carousel4Products = new ArrayList<>();

        String query = """
                SELECT p.productid, p.productname, p.price, p.description, p.quantityp, p.avagerstar, p.typeid, s.shopid, s.shopname
                FROM PRODUCTS p
                INNER JOIN SHOPS s ON p.shopid = s.shopid
                GROUP BY p.productid, p.productname, p.price, p.description, p.quantityp, p.avagerstar, p.typeid, s.shopid, s.shopname;
                """;

        try (Connection conn = new DBConnection().getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query); 
             ResultSet rs = ps.executeQuery()) {
             
            Set<Integer> displayedProductIds = new HashSet<>();
            Random random = new Random();

            while (rs.next()) {
                int productId = rs.getInt("productid");

                // Fetch all images for the product
                List<String> images = getProductImages(conn, productId);

                // Randomly select one image
                String selectedImage = images.isEmpty() ? null : images.get(random.nextInt(images.size()));

                Product product = new Product(
                        productId,
                        rs.getString("productname"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("quantityp"),
                        rs.getDouble("avagerstar"),
                        selectedImage,
                        null, // No color
                        null, // No size
                        rs.getInt("typeid"),
                        rs.getInt("shopid"),
                        rs.getString("shopname")
                );

                if (carousel1Products.size() < 8 && !displayedProductIds.contains(product.getProductId())) {
                    carousel1Products.add(product);
                    displayedProductIds.add(product.getProductId());
                } else if (carousel2Products.size() < 8 && !displayedProductIds.contains(product.getProductId())) {
                    carousel2Products.add(product);
                    displayedProductIds.add(product.getProductId());
                } else if (carousel3Products.size() < 8 && !displayedProductIds.contains(product.getProductId())) {
                    carousel3Products.add(product);
                    displayedProductIds.add(product.getProductId());
                } else if (carousel4Products.size() < 8 && !displayedProductIds.contains(product.getProductId())) {
                    carousel4Products.add(product);
                    displayedProductIds.add(product.getProductId());
                }
            }

            // Combine all carousel products into one list for the response
            productList.addAll(carousel1Products);
            productList.addAll(carousel2Products);
            productList.addAll(carousel3Products);
            productList.addAll(carousel4Products);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
    
    private List<String> getProductImages(Connection conn, int productId) {
        List<String> images = new ArrayList<>();
        String query = "SELECT image FROM IMAGEPRODUCTS WHERE productid = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    images.add(rs.getString("image"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
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



    public void updateOrInsertProductInfos(List<ProductInfor> productInfos) throws SQLException {
        String selectSQL = "SELECT quantityp FROM ProductInfor WHERE color = ? AND size = ? AND productid = ?";
        String updateSQL = "UPDATE ProductInfor SET quantityp = quantityp + ? WHERE color = ? AND size = ? AND productid = ?";
        String insertSQL = "INSERT INTO ProductInfor (color, size, quantityp, productid) VALUES (?, ?, ?, ?)";

        try (Connection conn = new DBConnection().getConnection()) {
            for (ProductInfor productInfo : productInfos) {
                boolean exists = false;

                try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
                    selectStmt.setString(1, productInfo.getColor());
                    selectStmt.setString(2, productInfo.getSize());
                    selectStmt.setInt(3, productInfo.getProductid());

                    try (ResultSet rs = selectStmt.executeQuery()) {
                        if (rs.next()) {
                            exists = true;
                        }
                    }
                }

                if (exists) {
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                        updateStmt.setInt(1, productInfo.getQuantityp());
                        updateStmt.setString(2, productInfo.getColor());
                        updateStmt.setString(3, productInfo.getSize());
                        updateStmt.setInt(4, productInfo.getProductid());
                        updateStmt.executeUpdate();
                    }
                } else {
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                        insertStmt.setString(1, productInfo.getColor());
                        insertStmt.setString(2, productInfo.getSize());
                        insertStmt.setInt(3, productInfo.getQuantityp());
                        insertStmt.setInt(4, productInfo.getProductid());
                        insertStmt.executeUpdate();
                    }
                }
            }
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
    
    public List<String> getAvailableImages(String productId) {
        List<String> images = new ArrayList<>();
        String query = "SELECT image FROM IMAGEPRODUCTS WHERE productid = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, productId);
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

    public static void main(String[] args) {
        ProductRepository pr = new ProductRepository();
        List<Product> products = pr.getAllProduct();
        System.out.println(products);
    }
}