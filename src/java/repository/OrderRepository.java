package repository;

import DAO.DBConnection;
import model.InfoCustomer;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.walletHeartsteal;
import model.CartItem;
import model.Product;

public class OrderRepository {

    public walletHeartsteal getWalletByUserId(int userId) {
        String sql = "SELECT * FROM WALLET WHERE userid = ?";
        try (Connection connection = new DBConnection().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    walletHeartsteal wallet = new walletHeartsteal();
                    wallet.setWalletid(rs.getInt("walletid"));
                    wallet.setUserid(rs.getInt("userid"));
                    wallet.setSurplus(rs.getFloat("surplus"));
                    return wallet;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<InfoCustomer> getAllAddressesByUserId(int userId) {
        String sql = "SELECT * FROM RECEIVERINFO WHERE userid = ?";
        List<InfoCustomer> InfoCustomer = new ArrayList<>();
        try (Connection connection = new DBConnection().getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    InfoCustomer address = new InfoCustomer();
                    address.setCustomerid(rs.getInt("receiverinfoid"));
                    address.setCustomerName(rs.getString("nameofreceiver"));
                    address.setPhoneCustomer(rs.getString("phonenumber"));
                    address.setAddressCustomer(rs.getString("address"));
                    InfoCustomer.add(address);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return InfoCustomer;
    }

    public void insertOrder(int productId, int userId, int quantity, String nameOfReceiver, String phoneNumber, String address, String statusOrder, double totalPrice, String dateOrder, int promotionId, String color, String size, String paymentMethods) {
        String query = "INSERT INTO ORDERS (productid, userid, quantity, nameofreceiver, phonenumber, address, statusorder, totalprice, dateorder, promotionid, color, size, paymentmethods) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            ps.setInt(2, userId);
            ps.setInt(3, quantity);
            ps.setString(4, nameOfReceiver);
            ps.setString(5, phoneNumber);
            ps.setString(6, address);
            ps.setString(7, statusOrder);
            ps.setDouble(8, totalPrice);
            ps.setString(9, dateOrder);
            ps.setInt(10, promotionId);
            ps.setString(11, color);
            ps.setString(12, size);
            ps.setString(13, paymentMethods);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserWithAddressById(int userId) {
        String sql = "SELECT u.*, r.address "
                + "FROM USERS u "
                + "LEFT JOIN RECEIVERINFO r ON u.userid = r.userid "
                + "WHERE u.userid = ?";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserid(rs.getInt("userid"));
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    user.setGender(rs.getBoolean("gender"));
                    user.setDob(rs.getString("dob"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRoleid(rs.getInt("roleid"));
                    user.setImgavt(rs.getString("imgavt"));
                    user.setBankname(rs.getString("bankname"));
                    user.setBanknumber(rs.getString("banknumber"));
                    user.setEmailpaypal(rs.getString("emailpaypal"));
                    user.setBanstatus(rs.getBoolean("banstatus"));
                    user.setAddress(rs.getString("address"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editOrder(int productid, int quantity, String size, String color) {
        String updateProductQuery = "UPDATE PRODUCTINFOR SET quantityp = quantityp - ? WHERE productid = ? and size = ? and color = ?";

        try (Connection conn = new DBConnection().getConnection(); PreparedStatement psProduct = conn.prepareStatement(updateProductQuery)) {

            conn.setAutoCommit(false); // Begin transaction

            psProduct.setInt(1, quantity);
            psProduct.setInt(2, productid);
            psProduct.setString(3, size);
            psProduct.setString(4, color);
            psProduct.executeUpdate();

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
            try (Connection conn = new DBConnection().getConnection()) {
                if (conn != null) {
                    conn.rollback(); // Rollback transaction in case of error
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace(); // Log the rollback exception
            }
        }
    }

    public List<CartItem> getCartItemsByUserId(int userId) {
        String sql = "WITH FirstImage AS ( "
                + "    SELECT ip.imageid, ip.image, ip.productid, "
                + "           ROW_NUMBER() OVER (PARTITION BY ip.productid ORDER BY ip.imageid) AS rn "
                + "    FROM IMAGEPRODUCTS ip "
                + "), ProductQuantity AS ( "
                + "    SELECT productid, SUM(quantityp) AS total_quantity "
                + "    FROM PRODUCTINFOR "
                + "    GROUP BY productid "
                + ") "
                + "SELECT c.cartid, c.productid, c.userid, c.quantity, c.size, c.color, "
                + "       p.productName, p.price, fi.image, p.description, p.shopId, s.shopname "
                + "FROM CART c "
                + "JOIN PRODUCTS p ON c.productid = p.productid "
                + "JOIN FirstImage fi ON c.productid = fi.productid "
                + "JOIN SHOPS s ON p.shopId = s.shopId "
                + "JOIN ProductQuantity pq ON c.productid = pq.productid "
                + "WHERE c.userid = ? AND fi.rn = 1 AND pq.total_quantity > 0 AND c.quantity <= pq.total_quantity";

        List<CartItem> cartItems = new ArrayList<>();
        try (Connection connection = new DBConnection().getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    CartItem item = new CartItem(
                            rs.getInt("cartid"),
                            rs.getInt("productid"),
                            rs.getInt("userid"),
                            rs.getInt("quantity"),
                            rs.getString("size"),
                            rs.getString("color"),
                            rs.getString("productName"),
                            rs.getDouble("price"),
                            rs.getString("image"),
                            rs.getString("description"),
                            rs.getInt("shopId"),
                            rs.getString("shopname")
                    );
                    cartItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }
    
    public int checkProductQuantity(String size, String color, int productId) {
        String sql = "SELECT quantityp FROM PRODUCTINFOR WHERE size = ? AND color = ? AND productid = ?";
        try (Connection connection = new DBConnection().getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, size);
            st.setString(2, color);
            st.setInt(3, productId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantityp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public void addItemToCart(int productId, int userId, int quantity, String size, String color) {
        // SQL queries
        String selectSql = "SELECT quantity FROM CART WHERE productid = ? AND userid = ? AND size = ? AND color = ?";
        String updateSql = "UPDATE CART SET quantity = quantity + ? WHERE productid = ? AND userid = ? AND size = ? AND color = ?";
        String insertSql = "INSERT INTO CART (productid, userid, quantity, size, color) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new DBConnection().getConnection(); PreparedStatement selectPs = connection.prepareStatement(selectSql); PreparedStatement updatePs = connection.prepareStatement(updateSql); PreparedStatement insertPs = connection.prepareStatement(insertSql)) {

            // Set parameters for select query
            selectPs.setInt(1, productId);
            selectPs.setInt(2, userId);
            selectPs.setString(3, size);
            selectPs.setString(4, color);

            ResultSet rs = selectPs.executeQuery();

            if (rs.next()) {

                updatePs.setInt(1, quantity);
                updatePs.setInt(2, productId);
                updatePs.setInt(3, userId);
                updatePs.setString(4, size);
                updatePs.setString(5, color);
                updatePs.executeUpdate();
            } else {
                // If item does not exist, insert a new row
                insertPs.setInt(1, productId);
                insertPs.setInt(2, userId);
                insertPs.setInt(3, quantity);
                insertPs.setString(4, size);
                insertPs.setString(5, color);
                insertPs.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteByCartId(int cartId) {
        String sql = "DELETE FROM CART WHERE cartid = ?";
        try (Connection connection = new DBConnection().getConnection(); PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, cartId);
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Product getProductFromCart(int cartId, int userId) {
        String sql = "WITH FirstImage AS ( "
                + "    SELECT ip.imageid, ip.image, ip.productid, "
                + "           ROW_NUMBER() OVER (PARTITION BY ip.productid ORDER BY ip.imageid) AS rn "
                + "    FROM IMAGEPRODUCTS ip "
                + ") "
                + "SELECT p.productid, p.productName, p.price, p.description, c.quantity, "
                + "       fi.image, c.color, c.size, s.shopid, s.shopname "
                + "FROM CART c "
                + "JOIN PRODUCTS p ON c.productid = p.productid "
                + "JOIN SHOPS s ON p.shopid = s.shopid "
                + "JOIN FirstImage fi ON p.productid = fi.productid AND fi.rn = 1 "
                + "WHERE c.cartid = ? AND c.userid = ?";

        try (Connection connection = new DBConnection().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("productid"));
                    product.setProductName(rs.getString("productName"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDescription(rs.getString("description"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setImage(rs.getString("image"));
                    product.setColor(rs.getString("color"));
                    product.setSize(rs.getString("size"));
                    product.setShopId(rs.getInt("shopid"));
                    product.setShopName(rs.getString("shopname"));
                    return product;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void subtractSurplus(int userId, double amount) {
        String sql = "UPDATE WALLET SET surplus = surplus - ? WHERE userid = ?";
        try (Connection connection = new DBConnection().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editPromotion(int promotionid) {

        String updatePromotionQuery = "UPDATE PROMOTION SET quantity = quantity - 1 WHERE promotionid = ?";

        try (Connection conn = new DBConnection().getConnection(); PreparedStatement psPromotion = conn.prepareStatement(updatePromotionQuery)) {

            conn.setAutoCommit(false);

            psPromotion.setInt(1, promotionid);
            psPromotion.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection conn = new DBConnection().getConnection()) {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        OrderRepository test = new OrderRepository();
//        List<InfoCustomer> addresses = test.getAllAddressesByUserId(3);
//        for (InfoCustomer address : addresses) {
//            System.out.println(address);
//        }
        test.getCartItemsByUserId(3);

        System.err.println("use" + test.getCartItemsByUserId(3));
    }
}
