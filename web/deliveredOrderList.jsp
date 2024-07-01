<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        .star-ratings-css {
            unicode-bidi: bidi-override;
            color: #c5c5c5;
            font-size: 25px;
            height: 25px;
            width: 100px;
            margin: 0 auto;
            position: relative;
            padding: 0;
            text-shadow: 0px 1px 0 #a2a2a2;
        }
        .star-ratings-css::before {
            content: "★★★★★";
            opacity: 0;
            position: absolute;
            z-index: 1;
            display: block;
            top: 0;
            left: 0;
            right: 0;
            overflow: hidden;
        }
        .star-ratings-css span {
            color: #e7711b;
            display: inline-block;
            position: absolute;
            top: 0;
            left: 0;
            overflow: hidden;
            white-space: nowrap;
        }
    </style>
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
                    <td>${order.color}, ${order.size}</td>
                    <td>${order.paymentmethod}</td>
                    <td>${order.shopname}</td>
                    <td>
                        <div class="star-ratings-css">
                            <div style="width: ${order.star * 20}%;">
                                <span>★★★★★</span>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
