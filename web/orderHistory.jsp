<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            background-color: #f2f2f2;
        }
        img {
            width: 100px;
        }
    </style>
</head>
<body>
    <%@include file="include/header.jsp" %>
    <h1>Order History</h1>
    <table>
        <tr>
            <th>Product Name</th>
            <th>Image</th>
            <th>Quantity</th>
            <th>Status</th>
            <th>Total Price</th>
            <th>Order Date</th>
        </tr>
        <c:forEach var="order" items="${orderList}">
            <tr>
                <td>${order.productName}</td>
                <td><img src="${order.image}" alt="${order.productName}"/></td>
                <td>${order.quantity}</td>
                <td>${order.statusOrder}</td>
                <td>${order.totalPrice}</td>
                <td>${order.dateOrder}</td>
            </tr>
        </c:forEach>
    </table>
    <%@include file="include/footer.jsp" %>   
</body>
</html>
