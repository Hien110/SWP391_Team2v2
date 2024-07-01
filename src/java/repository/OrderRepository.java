package repository;

import DAO.DBConnection;
import model.Address;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    public List<Address> getAllAddressesByUserId(int userId) {
        String sql = "SELECT * FROM RECEIVERINFO WHERE userid = ?";
        List<Address> addresses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connection = new DBConnection().getConnection();
            st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            rs = st.executeQuery();
            while (rs.next()) {
                Address address = new Address();
                address.setReceiverInfoId(rs.getInt("receiverinfoid"));
                address.setNameOfReceiver(rs.getString("nameofreceiver"));
                address.setPhoneNumber(rs.getString("phonenumber"));
                address.setAddress(rs.getString("address"));
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return addresses;
    }

    public void insertOrder(int productId, int userId, int quantity, int receiverInfoId, String statusOrder, double totalPrice, String dateOrder, int promotionId, String color, String size, String paymentMethods) {
        String query = "INSERT INTO ORDERS (productid, userid, quantity, receiverinfoid, statusorder, totalprice, dateorder, promotionid, color, size, paymentmethods) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnection().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            ps.setInt(2, userId);
            ps.setInt(3, quantity);
            ps.setInt(4, receiverInfoId);
            ps.setString(5, statusOrder);
            ps.setDouble(6, totalPrice);
            ps.setString(7, dateOrder);
            ps.setInt(8, promotionId);
            ps.setString(9, color);
            ps.setString(10, size);
            ps.setString(11, paymentMethods);
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
                user.setAddress(rs.getString("address")); // Set address
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
        test.insertOrder(3, 3, 3, 3, "Pending", 0, "11-2-2024", 4, "red", "xl", "cod");
    }
}
