<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <% 
            Connection connection = null;
            try {
                // Kết nối tới cơ sở dữ liệu
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String dbURL = "jdbc:sqlserver://localhost;databaseName=SWP391_DBV5;user=sa;password=Password.1;trustServerCertificate=true";
                connection = DriverManager.getConnection(dbURL);

                // Lấy danh sách người dùng
                String query = "SELECT userid, username, banstatus FROM USERS";
                try (PreparedStatement stmt = connection.prepareStatement(query);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int userId = rs.getInt("userid");
                        String username = rs.getString("username");
                        boolean isBanned = rs.getBoolean("banstatus");
        %>
        <tr>
            <td><%= userId %></td>
            <td><%= username %></td>
            <td><%= isBanned ? "Banned" : "Active" %></td>
            <td>
                <form action="banUser" method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="<%= userId %>"/>
                    <input type="submit" value="<%= isBanned ? "Unban" : "Ban" %>"/>
                </form>
            </td>
        </tr>
        <% 
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        %>
    </table>
</body>
</html>
