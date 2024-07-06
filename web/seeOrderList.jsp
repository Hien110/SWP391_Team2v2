<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.orderShop" %>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Xem Đơn Đặt Hàng</title>
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
            text-align: center; /* Center-align text */
            vertical-align: middle; /* Center-align vertically */
        }
        th {
            background-color: #4CAF50; /* Màu nền xanh lá cây */
            color: white; /* Màu chữ trắng */
        }
        img {
            display: block;
            margin: 0 auto; /* Canh giữa hình ảnh */
        }
    </style>
</head>
<body>
    <h1>Xem Đơn Đặt Hàng</h1>
    <table>
        <tr>
            <th>Sản Phẩm</th>
            <th>Số lượng</th>
            <th>Loại</th>
            <th>Địa Chỉ</th>
            <th>Phương thức thanh toán</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
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
            <td>Size: <%= order.getSize() %>, Color: <%= order.getColors() %></td>
            <td><%= order.getNameOfReceiver() %>, <%= order.getPhoneNumber() %>, <%= order.getReciever_address() %></td>
            <td><%= order.getPaymentmethods() %></td>
            <td><%= order.getStatusOrder() %></td>
            <td>
                <form action="UpdateStatusOrder" method="post">
                    <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                    <button type="submit" <%= "Đang xử lí".equals(order.getStatusOrder()) ? "" : "disabled" %>>Submit</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
<%@include file="include/footer.jsp" %>
