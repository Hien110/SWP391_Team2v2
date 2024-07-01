<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                background-color: #2a8341;
                color: white;
            }
            img {
                width: 100px;
            }
            .btn-container{
                margin-bottom: 30px;
                margin-top: 30px;
                margin-left: 10%;
                margin-right: 10%;
            }
            .btn-container h1{
                margin-left: 35%;
                margin-bottom: 20px;
            }
            .buttondelivered {
                border-radius: 10px;
                padding: 7px 25px;
                color: white;
                background-color: #4CAF50;
                border: 1px solid black;
                cursor: pointer;
                margin-right: 5px;
                text-decoration: none;
                display: inline-block;
                margin-bottom: 7px;
            }

            .buttondelivered b{
                margin-right: 15px;
            }
        </style>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div class="btn-container">
            <h1>Lịch Sử Mua Hàng</h1>
            <table>
                <tr>
                    <th>Mã Đơn Hàng</th>
                    <th>Sản Phẩm</th>
                    <th>Số Lượng</th>
                    <th>Trạng Thái</th>
                    <th>Tổng Thanh Toán</th>
                    <th>Ngày Đặt Hàng</th>
                    <th>Tùy Chọn</th>
                </tr>
                <c:forEach var="order" items="${orderList}">
                    <tr style="border-bottom: 1px solid;">
                        <td>${order.orderID}</td>
                        <td>
                            <img src="${order.image}" alt="${order.productName}"/>
                            ${order.productName}
                        </td>
                        <td>${order.quantity}</td>
                        <td>${order.statusOrder}</td>
                        <td>${order.totalPrice}</td>
                        <td>${order.dateOrder}</td>
                        <td>
                            <a class="buttondelivered" href="evaluate?productid= ${order.productid}"><b>Đánh giá</b></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <%@include file="include/footer.jsp" %>   
    </body>
</html>
