package repository;



import DAO.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class BannedUserDAO {

    private DBConnection dbConnection;

    public BannedUserDAO() {
        dbConnection = new DBConnection();
    }

    public List<User> getBannedUsers() {
        List<User> bannedUsers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dbConnection.getConnection();
            String sql = "SELECT userid, username, email FROM users WHERE banstatus = 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int userid = rs.getInt("userid");
                String username = rs.getString("username");
                String email = rs.getString("email");

                User user = new User(userid, username, email);
                bannedUsers.add(user);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
            
            }
        }

        return bannedUsers;
    }
}

