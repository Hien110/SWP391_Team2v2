<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        th {
            background-color: #2a8341;
            color: white;
        }
        img {
            width: 100px;
        }
        .btn-container {
            margin-bottom: 30px;
            margin-top: 30px;
            margin-left: 10%;
            margin-right: 10%;
        }
        .btn-container h1 {
            margin-left: 35%;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <%@include file="include/header.jsp" %>
    
    <jsp:include page="/include/navbar.jsp"/>
    
    <div class="btn-container">
        <h1>Danh sách khuyến mãi</h1>
        <table>
            <tr>
                <th>STT</th>
                <th>Tên Khuyến Mãi</th>
                <th>Phần trăm giảm giá</th>
                <th>Số lượng</th>
                <th>Mô tả</th>
                <th>Áp dụng</th>
                <th>Thời gian</th>
            </tr>
            
            <!-- Đoạn code để hiển thị danh sách khuyến mãi -->
        </table>
    </div>

    <%@include file="include/footer.jsp" %>   
</body>
</html>
