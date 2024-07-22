<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Canceled Orders</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            color: #4CAF50; /* Green text color for the title */
            padding: 20px 0;
            margin: 0 0 20px 0;
        }
        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        table, th, td {
            border: 1px solid #4CAF50;
        }
        th, td {
            padding: 15px;
            text-align: center; /* Center-align text */
            vertical-align: middle; /* Center-align vertically */
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        td img {
            display: block;
            margin: 0 auto;
            max-width: 100px;
        }
    </style>
</head>
<body>
    <h1>Xem Đơn Đã Hủy</h1>
    <table>
        <thead>
            <tr>
                <th>Tên Sản Phẩm</th>
                <th>Địa chỉ</th>
                <th>Số Lượng</th>
                <th>Ngày Đặt Hàng</th>
                <th>Thể Loại</th>
                <th>Lí Do Hủy Đơn</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${l}">
                <tr>
                    <td>
                        ${order.productName}
                        <img src="${order.image}" alt="${order.productName}">
                    </td>
                    <td>
                        ${order.nameReceiver}<br>
                        ${order.phone}<br>
                        ${order.address}
                    </td>
                    <td>${order.quantity}</td>
                    <td>${order.dateOrder}</td>
                    <td>
                        Màu: ${order.color}<br>
                        Kích Thước: ${order.size}
                    </td>
                    <td>${order.reasonCancel}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
        <%@include file="include/footer.jsp" %>
