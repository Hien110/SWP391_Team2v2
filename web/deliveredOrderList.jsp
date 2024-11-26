<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="include/header.jsp" %>
<html>
<head>
    <title>Xem Đơn Đã Giao</title>
    <style>
        .detail {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 100px;
            padding-top: 10px;
        }
        h2 {
            margin-top: 10px;
            color: #4CAF50; /* Màu xanh lá cây */
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom:100px;
            background-color: #ffffff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        tr{
            
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: center; /* Center-align text */
            vertical-align: middle; /* Center-align vertically */
            padding: 8px;
        }
        th {
            background-color: #2a8341;
            color: white; /* Màu chữ trắng */
            padding-bottom: 10px;
        }
        img {
            max-width: 100px;
            height: auto;
        }
        .star-icons {
            font-size: 15px; /* Kích thước của biểu tượng sao */
            line-height: 1;
            white-space: nowrap; /* Ensure stars are on the same line */
        }
        .star-icons i {
            margin-right: 2px; /* Khoảng cách giữa các biểu tượng sao */
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" />
</head>
<body>
            <h2>Xem Đơn Đã Giao</h2>
    <div class="detail">
    <table>
        <thead>
            <tr>
                <th>Tên Sản Phẩm</th>
                <th>Hình Ảnh</th>
                <th>Số Lượng</th>
                <th>Địa Chỉ Nhận Hàng</th>
                <th>Ngày Đặt Hàng</th>
                <th>Tổng Giá Thành</th>
                <th>Thể Loại</th>
                <th>Phương Thức Thanh Toán</th>
                <th>Đánh Giá</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${l}" var="order">
                <tr>
                    <td>${order.productName}</td>
                    <td><img src="${order.image}" alt="Product Image"></td>
                    <td>${order.quantity}</td>
                    <td>${order.nameReceiver}, ${order.phone}, ${order.address}</td>
                    <td>${order.dateOrder}</td>
                    <td>${order.totalPrice}</td>
                    <td>
                        Màu:  ${order.color};
                        Kích Thước:  ${order.size}
                    </td>
                    <td>${order.paymentmethod}</td>
                    <td class="star-icons">
                        <c:forEach begin="1" end="${order.star}">
                            <i class="fas fa-star" style="color: #FFD700;"></i>
                        </c:forEach>
                        <c:forEach begin="${order.star + 1}" end="5">
                            <i class="far fa-star" style="color: #DDD;"></i>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    
</body>
</html>
        <%@include file="include/footer.jsp" %>
