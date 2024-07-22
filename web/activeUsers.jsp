<%@ include file="include/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Active Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Đảm bảo navbar và content căn chỉnh đều nhau */
        .container-fluid {
            margin-top: 0px; /* Khoảng cách từ navbar đến content, sửa lại từ 50px thành 0px */
        }
        .content {
            margin-top: 20px; /* Khoảng cách giữa tiêu đề và bảng */
        }
        .form-container {
            margin-top: -500px; /* Dịch chuyển bảng lên để giảm khoảng trống */
            padding: 20px;
       
            border: 1px solid #ddd; /* Viền nhẹ */
            border-radius: 8px;
        }
        .navbar-container {
          
            margin-bottom: 100px; /* Dịch chuyển navbar xuống phía dưới tiêu đề */
            margin-left: 50px;
            color: #ffffff; /* Màu chữ của navbar */
            padding: 10px 0; /* Đệm theo chiều dọc */
            border-radius: 8px;
        }
        .form-title {
            margin-bottom: 20px; /* Khoảng cách dưới tiêu đề */
        }
        .content {
    margin-top: -650px; /* Thử điều chỉnh margin-top tại đây */
         margin-left: 120px;
}

    </style>
</head>
<body>
     <div class="col-md-6 navbar-container">
            <jsp:include page="/include/navbar.jsp"/>
        </div>
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="container mt-6 content">
                    <h2 class="text-center">Danh Sách Người Dùng Đang Hoạt Động</h2>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">User ID</th>
                                <th scope="col">Username</th>
                                <th scope="col">Email</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                Connection connection = null;
                                try {
                                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                    String dbURL = "jdbc:sqlserver://localhost;databaseName=SWP391_DBfinal;user=sa;password=123;trustServerCertificate=true";
                                    connection = DriverManager.getConnection(dbURL);

                                    String query = "SELECT userid, username, email FROM users WHERE banstatus = 0";
                                    try (PreparedStatement stmt = connection.prepareStatement(query);
                                         ResultSet rs = stmt.executeQuery()) {
                                        while (rs.next()) {
                                            String userId = rs.getString("userid");
                                            String username = rs.getString("username");
                                            String email = rs.getString("email");
                            %>
                            <tr>
                                <td><%= userId %></td>
                                <td><%= username %></td>
                                <td><%= email %></td>
                                <td>Đang hoạt động</td>
                                <td>
                                    <form action="banUser" method="post">
                                        <input type="hidden" name="userId" value="<%= userId %>">
                                        <button type="submit" class="btn btn-danger">Ban</button>
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
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
       
    </div>
    
    <jsp:include page="/include/footer.jsp" />
</body>
</html>
