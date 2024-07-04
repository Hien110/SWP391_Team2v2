<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.orderShop" %>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Order List</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }
            h1 {
                color: #2e7d32; /* Màu chữ xanh lá cây đậm */
                text-align: center;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid black;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #66bb6a; /* Màu nền xanh lá cây */
                color: white; /* Màu chữ trắng */
            }
            img {
                display: block;
                margin: 0 auto; /* Canh giữa hình ảnh */
            }
        </style>
    </head>
    <body>
        <h1>Order List</h1>
        <table>
            <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Type</th>
                <th>Address</th>
                <th>Payment Methods</th>
            </tr>
            <%
                List<orderShop> orderList = (List<orderShop>) request.getAttribute("l");
                if (orderList != null) {
                    for (orderShop order : orderList) {
            %>
            <tr>
                <td>
                    <%= order.getProductName() %><br>
                    <img src="<%= order.getImage() %>" alt="Product Image" width="50" height="50">
                </td>
                <td><%= order.getQuantity() %></td>
                <td><%= order.getColors() + ", " + order.getSize() %></td>
                <td><%= order.getNameOfReceiver() + ", " + order.getPhoneNumber() + ", " + order.getReciever_address() %></td>
                <td><%= order.getPaymentmethods() %></td>
                <td>
                <button onclick="completePreparation(this)">Complete preparation of products</button>
            </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <script>
        function completePreparation(button) {
            button.textContent = "Completed preparation of products";
            // Example: You can add AJAX request here to update backend status
            // Example: Fetch orderId or other relevant data from button's data attributes
        }
    </script>
    </body>
</html>
        <%@include file="include/footer.jsp" %>
