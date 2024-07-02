<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Chi Tiết Đơn Hàng</title>
        <style>
            table {
                width: 50%;
                border: 1px solid #dddddd; /* Add border to the table */
                border-collapse: collapse;
                margin: 50px auto;
                font-family: Arial, sans-serif;
            }
            th, td {
                border: 1px solid #dddddd; /* Add border to table cells */
                text-align: left;
                padding: 8px;
            }
            th {
                background-color: #f2f2f2;
            }
            .container {
                text-align: center;
            }
            .back-button {
                border-radius: 10px;
                background-color: #28a745;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                color: white;
                margin-top: 5px;
                margin-bottom: 40px;
                border: none;
            }
            .title {
                margin-top: 30px;
            }
            tr {
                border-bottom: 1px solid;
            }
        </style>

    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <h2 class="title" style="text-align: center;">Chi Tiết Đơn Hàng</h2>

        <table>
            <tr style="border-bottom: 1px solid;">
                <th>Mã Đơn Hàng</th>
                <td>${order.orderid}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Sản Phẩm</th>
                <td>
                    ${order.productname} <br/>
                    <img src="${order.image}" alt="${order.productname}" style="width: 200px"/>
                </td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Cửa Hàng</th>
                <td>${order.shopname}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Số Lượng</th>
                <td>${order.quantity}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Màu</th>
                <td>${order.color}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Size</th>
                <td>${order.size}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Ngày Đặt Hàng</th>
                <td>${order.dateorder}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Tổng Tiền</th>
                <td>${order.totalprice}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Địa Chỉ Nhận Hàng</th>
                <td>${order.address}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Người Nhận</th>
                <td>${order.nameofreceiver}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Khuyến Mãi</th>
                <td>${order.promotionname}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Phương Thức Thanh Toán</th>
                <td>${order.paymentmethods}</td>
            </tr>
            <tr style="border-bottom: 1px solid;">
                <th>Trạng Thái</th>
                <td>${order.statusorder}</td>
            </tr>
            <c:choose>
                <c:when test="${order.statusorder == 'Cancel'}">
                    <tr style="border-bottom: 1px solid;">
                        <th>Lý Do Hủy</th>
                        <td>${order.reasoncancel}</td>
                    </tr>
                </c:when>
            </c:choose>
        </table>

        <div class="container">
            <button class="back-button" onclick="history.back()">Quay Lại</button>
        </div>
        <%@include file="include/footer.jsp" %> 
    </body> 
</html>
