<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="include/header.jsp" %>
<html>
<head>
    <title>Delivered Orders List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        h2 {
            color: #009900; /* Màu xanh lá cây */
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            color: #009900; /* Màu xanh lá cây */
        }
        img {
            max-width: 100px;
            height: auto;
        }
        .star-icons {
            font-size: 12px; /* Kích thước của biểu tượng sao */
            line-height: 1;
        }
        .star-icons i {
            margin-right: 0px; /* Khoảng cách giữa các biểu tượng sao */
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" />
</head>
<body>
    <h2>Delivered Orders List</h2>
    <table>
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Image</th>
                <th>Quantity</th>
                <th>Receiver Address</th>
                <th>Order Date</th>
                <th>Total Price</th>
                <th>Type</th>
                <th>Payment Method</th>
                <th>Shop Name</th>
                <th>Star</th>
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
                    <td>${order.color}, ${order.size}</td>
                    <td>${order.paymentmethod}</td>
                    <td>${order.shopname}</td>
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
</body>
</html>
        <%@include file="include/footer.jsp" %>
