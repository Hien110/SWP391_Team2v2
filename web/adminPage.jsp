<%@page import="java.sql.*"%>
<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 12:36:25 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profileCSS.css">
</head>
<body>
    <%@include file="include/header.jsp" %>
    
    <div class="container-fluid container">
        <div class="row">
            <jsp:include page="/include/navbar.jsp"/>
            <div class="col-md-9 content">
                <h2 class="text-center">Danh Sách Người Dùng</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">User ID</th>
                            <th scope="col">Username</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
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
                    %>
                    <tr>
                        <td colspan="4">Lỗi kết nối cơ sở dữ liệu: <%= ex.getMessage() %></td>
                    </tr>
                    <% 
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
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <%@include file="include/footer.jsp" %>
</body>
</html>
