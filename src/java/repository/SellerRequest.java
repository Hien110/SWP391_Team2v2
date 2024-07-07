package repository;

import DAO.DBConnection;
import com.google.gson.Gson;
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

    public List<User> getUsersWithRoleId(int roleId) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE roleId = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("userid"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
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
        SellerRequest sellerRequest = new SellerRequest();
        List<User> users = sellerRequest.getUsersWithRoleId(4);
        
        // Convert list of users to JSON
        Gson gson = new Gson();
        String usersJson = gson.toJson(users);
        System.out.println(usersJson);

        // If you want to display information as text, keep this part unchanged
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
