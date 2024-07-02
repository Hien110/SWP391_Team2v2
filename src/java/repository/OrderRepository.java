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

public class OrderRepository {
    
    public walletHeartsteal getWalletByUserId(int userId) {
        String sql = "SELECT * FROM WALLET WHERE userid = ?";
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
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
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
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
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
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
        String sql = "SELECT u.*, r.address " +
                     "FROM USERS u " +
                     "LEFT JOIN RECEIVERINFO r ON u.userid = r.userid " +
                     "WHERE u.userid = ?";
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
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
    
    

    public static void main(String[] args) {
        OrderRepository test = new OrderRepository();
//        List<InfoCustomer> addresses = test.getAllAddressesByUserId(3);
//        for (InfoCustomer address : addresses) {
//            System.out.println(address);
//        }

      
        System.err.println("use" + test.getWalletByUserId(2));
    }
}
