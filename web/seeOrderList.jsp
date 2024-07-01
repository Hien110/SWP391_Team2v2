<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.orderShop" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Order List</h1>
    <table>
        <tr>
            <th>Order ID</th>
            <th>Quantity</th>
            <th>Status</th>
            <th>Total Price</th>
            <th>Date</th>
            <th>Product Name</th>
            <th>Receiver Name</th>
            <th>Phone Number</th>
            <th>Address</th>
            <th>Image</th>
            <th>Color</th>
            <th>Size</th>
            <th>Payment Methods</th>
        </tr>
        <%
            List<orderShop> orderList = (List<orderShop>) request.getAttribute("l");
            if (orderList != null) {
                for (orderShop order : orderList) {
        %>
        <tr>
            <td><%= order.getOrderId() %></td>
            <td><%= order.getQuantity() %></td>
            <td><%= order.getStatusOrder() %></td>
            <td><%= order.getTotalPrice() %></td>
            <td><%= order.getDateOrder() %></td>
            <td><%= order.getProductName() %></td>
            <td><%= order.getNameOfReceiver() %></td>
            <td><%= order.getPhoneNumber() %></td>
            <td><%= order.getReciever_address() %></td>
            <td><img src="<%= order.getImage() %>" alt="Product Image" width="50" height="50"></td>
            <td><%= order.getColors() %></td>
            <td><%= order.getSize() %></td>
            <td><%= order.getPaymentmethods() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
