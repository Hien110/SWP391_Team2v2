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
                border-spacing: 0;
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
            .btn-container {
                margin-bottom: 30px;
                margin-top: 30px;
                margin-left: 5%;
                margin-right: 5%;
            }
            .btn-container h1 {
                text-align: center;
                margin-bottom: 20px;
            }
            .button {
                border-radius: 10px;
                padding: 7px 20px;
                color: white;
                background-color: #E4184E;
                border: 1px solid black;
                cursor: pointer;
                margin-right: 5px;
                text-decoration: none;
                display: inline-block;
                margin-bottom: 5px;
            }
            .button1 {
                border-radius: 10px;
                padding: 7px 25px;
                color: white;
                background-color: #4CAF50;
                border: 1px solid black;
                cursor: pointer;
                margin-right: 5px;
                text-decoration: none;
                display: inline-block;
            }
            .button b {
                margin-right: 12px;
            }
            .button1 b {
                margin-right: 15px;
            }
            .btn.disabled {
                margin-bottom: 5px;
                background-color: #ddd;
                border: 1px solid #ddd;
                cursor: not-allowed;
            }
        </style>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div class="btn-container">
            <h1>Theo Dõi Đơn Hàng</h1>
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
                            <c:choose>
                                <c:when test="${order.statusOrder == 'Cancel'}"> 
                                    <a class="btn disabled" style="width: 124px;">Đã Hủy Đơn</a>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.statusOrder == 'Pending'}">
                                    <a class="button" href="cancelorder?orderid=${order.orderID}"><b>Hủy Đơn Hàng</b></a>
                                </c:when>
                            </c:choose>
                            <br/>        
                            <a class="button1" href="detailorder?orderid=${order.orderID}"><b>Chi Tiết</b></a>       
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
<%@include file="include/footer.jsp" %>
