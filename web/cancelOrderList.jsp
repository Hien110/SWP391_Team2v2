<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cancelled Orders List</title>
</head>
<body>
    <h2>Cancelled Orders List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Product Name</th>
                <th>Image</th>
                <th>Quantity</th>
                <th>Receiver Address</th>
                <th>Status</th>
                <th>Total Price</th>
                <th>Order Date</th>
                <th>Color</th>
                <th>Size</th>
                <th>Payment Method</th>
                <th>Shop Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${l}" var="order">
                <tr>
                    <td>${order.orderID}</td>
                    <td>${order.productname}</td>
                    <td><img src="${order.image}" alt="Product Image" height="50"></td>
                    <td>${order.quantity}</td>
                    <td>${order.address}</td>
                    <td>${order.statusorder}</td>
                    <td>${order.totalprice}</td>
                    <td>${order.dateorder}</td>
                    <td>${order.color}</td>
                    <td>${order.size}</td>
                    <td>${order.paymentmethods}</td>
                    <td>${order.shopname}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
