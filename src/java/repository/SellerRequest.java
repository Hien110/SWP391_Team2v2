package repository;

import DAO.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SellerRequest {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<User> getUsersWithRoleId() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE roleId = 3";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
               userList.add(new User(
    rs.getInt("userid"),
    rs.getString("username"),
    rs.getString("email"),
    rs.getString("password"),                  
    rs.getInt("roleId"), // Giữ nguyên vì roleId là kiểu int
    rs.getString("imgavt"),
    rs.getBoolean("banstatus")
));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return userList;
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
        // Thay đổi phần main để hiển thị danh sách người dùng dưới dạng JSON
        SellerRequest sellerRequest = new SellerRequest();
        List<User> users = sellerRequest.getUsersWithRoleId();
        for (User user : users) {
            System.out.println("User ID: " + user.getUserid());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Role ID: " + user.getRoleid());
            System.out.println("Avatar Image: " + user.getImgavt());
            System.out.println("Ban Status: " + user.getBanstatus());
            System.out.println();
        }
    }
}
