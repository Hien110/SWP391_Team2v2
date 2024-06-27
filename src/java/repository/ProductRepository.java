package repository;

import DAO.DBConnection;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    // Method to delete a product by shop owner
    public void deleteProductShopOwner(int productId) {
        String query = """
                       DELETE FROM EVALUATE WHERE productid = ?;
                       DELETE FROM CART WHERE productid = ?;
                       DELETE FROM ORDERS WHERE productid = ?;
                       DELETE FROM REPORTPRODUCT WHERE productid = ?;
                       DELETE FROM TYPEPRODUCT WHERE productid = ?;
                       DELETE FROM IMAGEPRODUCTS WHERE productid = ?;
                       DELETE FROM COLORPRODUCTS WHERE productid = ?;
                       DELETE FROM SIZEPRODUCTS WHERE productid = ?;
                       DELETE FROM PRODUCTS WHERE productid = ?;
                       """;
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            for (int i = 1; i <= 9; i++) {
                ps.setInt(i, productId);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProductShopOwner(int shopId) {
        List<Product> list = new ArrayList<>();
        String query = """
                       SELECT PRODUCTS.productid, PRODUCTS.productname, PRODUCTS.price, PRODUCTS.description, PRODUCTS.quantityp, PRODUCTS.avagerstar, IMAGEPRODUCTS.image
                       FROM PRODUCTS
                       INNER JOIN IMAGEPRODUCTS ON PRODUCTS.productid = IMAGEPRODUCTS.productid
                       WHERE PRODUCTS.shopid = ?;
                       """;
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, shopId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Product(
                            rs.getInt("productid"),
                            rs.getString("productname"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getInt("quantityp"),
                            rs.getDouble("avagerstar"),
                            rs.getString("image")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getProductShopOwnerByID(String productId) {
        String query = "SELECT productId, productName, price, description, quantityp, averageStar, image FROM PRODUCTS WHERE productId = ?";
        Product product = null;
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public void updateProductShopOwner(int productId, String productName, double price, String description, int quantity, double averageStar, String image) {
        String query = "UPDATE dbo.PRODUCTS SET productName = ?, price = ?, description = ?, quantityp = ?, averageStar = ?, image = ? WHERE productid = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, productName);
            ps.setDouble(2, price);
            ps.setString(3, description);
            ps.setInt(4, quantity);
            ps.setDouble(5, averageStar);
            ps.setString(6, image);
            ps.setInt(7, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductShopOwner(String productName, double price, String description, int quantity, double numberStar, int totalStar, int shopId) {
        String query = "INSERT INTO dbo.PRODUCTS (productName, price, description, quantityp, numberStar, totalStar, shopId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
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
        }
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

//    public List<Product> listToCart(int userId) {
//        List<Product> list = new ArrayList<>();
//        String query = """
//                       SELECT p.productname, p.price, p.description, c.quantity, p.avagerstar, i.image, cp.color, sp.size, p.typeid
//                       FROM CART c
//                       INNER JOIN PRODUCTS p ON c.productid = p.productid
//                       INNER JOIN IMAGEPRODUCTS i ON p.productid = i.productid
//                       INNER JOIN COLORPRODUCTS cp ON p.productid = cp.productid
//                       INNER JOIN SIZEPRODUCTS sp ON p.productid = sp.productid
//                       WHERE c.userid = ?""";
//        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setInt(1, userId);
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    list.add(new Product(
//                            rs.getString(1),
//                            rs.getDouble(2),
//                            rs.getString(3),
//                            rs.getInt(4),
//                            rs.getInt(5),
//                            rs.getString(6),
//                            rs.getString(7),
//                            rs.getString(8),
//                            rs.getInt(9)
//                    ));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

    // Method to delete a product from cart by ID
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
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, productId);
            while (rs.next()) {
                images.add(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }

    public List<Product> getAllProductsByShop(int shopId) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT productid, productname, price, description, quantityp, avagerstar, image, color, size, typeid FROM PRODUCTS WHERE shopid = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, shopId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Product(
                            rs.getInt("productid"),
                            rs.getString("productname"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getInt("quantityp"),
                            rs.getDouble("avagerstar"),
                            rs.getString("image"),
                            rs.getString("color"),
                            rs.getString("size"),
                            rs.getInt("typeid")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getProductByIdAndShop(int productId, int shopId) {
        Product product = null;
        String query = """
                       SELECT p.productid, p.productname, p.price, p.description, p.quantityp, p.avagerstar, p.image, p.color, p.size, p.typeid, s.shopid, s.shopname
                       FROM PRODUCTS p
                       INNER JOIN SHOPS s ON p.shopid = s.shopid
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
                            rs.getInt("typeid"),
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
// code use for detailProduct page

    public Product getProductById(String productId) {
        Product product = null;
        String query = """
                       SELECT p.productid, p.productname, p.price, p.description, p.quantityp, p.avagerstar, i.image, cp.color, sp.size, p.typeid, s.shopid, s.shopname
                                       FROM PRODUCTS p
                                       INNER JOIN IMAGEPRODUCTS i ON p.productid = i.productid
                                       INNER JOIN COLORPRODUCTS cp ON p.productid = cp.productid
                                       INNER JOIN SIZEPRODUCTS sp ON p.productid = sp.productid
                                       INNER JOIN SHOPS s ON p.shopid = s.shopid
                                       WHERE p.productid = ?""";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
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
                            rs.getInt("typeid"),
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
