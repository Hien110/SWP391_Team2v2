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
            color: #2a8341; /* Màu chữ xanh lá cây đậm */
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
            background-color: #2a8341; /* Màu nền xanh lá cây */
            color: white; /* Màu chữ trắng */
        }
        img {
            display: block;
            margin: 0 auto; /* Canh giữa hình ảnh */
        }
    </style>
    <script>
        function handleButtonClick(button, orderId) {
            button.disabled = true; // Disable the button to prevent multiple clicks
            button.innerHTML = 'Đang Giao Hàng'; // Change button text
            document.getElementById('updateForm-' + orderId).submit(); // Submit the form
        }
    </script>
</head>
<body>
    <h1>Xem Đơn Đặt Hàng</h1>
    <table>
        <tr>
            <th>Sản Phẩm</th>
            <th>Số lượng</th>
            <th>Thể Loại</th>
            <th>Địa Chỉ</th>
            <th>Phương thức thanh toán</th>
            <th>Trạng thái</th>
            <th></th>
        </tr>
        <c:forEach var="order" items="${l}">
        <tr>
            <td>
                ${order.productName}<br>
                <img src="${order.image}" alt="Product Image" width="50" height="50">
            </td>
            <td>${order.quantity}</td>
            <td>Kích Thước: ${order.size}, Màu: ${order.colors}</td>
            <td>${order.nameOfReceiver}, ${order.phoneNumber}, ${order.reciever_address}</td>
            <td>${order.paymentmethods}</td>
            <td>${order.statusOrder}</td>
            <td>
                <form id="updateForm-${order.orderId}" action="UpdateStatusOrder" method="post">
                    <input type="hidden" name="orderId" value="${order.orderId}">
                    <button type="button" 
                            onclick="handleButtonClick(this, ${order.orderId})"
                            ${"Đang xử lí".equals(order.statusOrder) ? "" : "disabled"}>
                            Đang Xử Lí
                    </button>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
<%@include file="include/footer.jsp" %>
