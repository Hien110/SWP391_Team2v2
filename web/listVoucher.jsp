<%@ include file="include/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Voucher List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Đảm bảo navbar và content căn chỉnh đều nhau */
        .form-container {
            margin-top: -200px;
            margin-right:200px; 
            padding: 20px;
            border: 1px solid #ddd; /* Light border */
            border-radius: 8px;
        }
        .navbar-container {
            margin-top: -250px; /* Space below navbar */
            margin-left: 40px;  
            color: #ffffff; /* Navbar text color */
            padding: 10px 0; /* Vertical padding */
            border-radius: 8px;
        }
        .form-title {
            margin-bottom: 20   0px;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="container mt-6 content">
                    <h2 class="text-center">Danh Sách Voucher</h2>
                    <table class="table table-striped"> 
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Tên khuyến mãi</th>
                                <th scope="col">Phần trăm giảm giá</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Mô tả</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                Connection connection = null;
                                try {
                                    // Kết nối tới cơ sở dữ liệu
                                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                    String dbURL = "jdbc:sqlserver://localhost;databaseName=SWP391_DBfinal;user=sa;password=123;trustServerCertificate=true";
                                    connection = DriverManager.getConnection(dbURL);

                                    // Lấy danh sách voucher
                                    String query = "SELECT * FROM PROMOTION";
                                    try (PreparedStatement stmt = connection.prepareStatement(query);
                                         ResultSet rs = stmt.executeQuery()) {
                                        while (rs.next()) {
                                            int id = rs.getInt("promotionid");
                                            String promotionName = rs.getString("promotionname");
                                            int percentPromotion = rs.getInt("pecentpromotion");
                                            int quantity = rs.getInt("quantity");
                                            String description = rs.getString("description");
                            %>
                            <tr>
                                <td><%= id %></td>
                                <td><%= promotionName %></td>
                                <td><%= percentPromotion %> %</td>
                                <td><%= quantity %></td>
                                <td><%= description %></td>
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
        <div class="col-md-6 navbar-container">
            <jsp:include page="/include/navbar.jsp"/>
        </div>
    </div>
    
    <jsp:include page="/include/footer.jsp" />
</body>
</html>
