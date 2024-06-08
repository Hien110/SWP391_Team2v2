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
            .btn-container {
                margin-bottom: 30px;
                margin-top: 30px;
            }
            .btn {
                padding: 10px 20px;
                color: white;
                background-color: #4CAF50;
                border: none;
                cursor: pointer;
                margin-right: 5px;
            }
            .btn:disabled {
                background-color: #ddd;
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
                    <th>Tên Sản Phẩm</th>
                    <th>Ảnh</th>
                    <th>Số Lượng</th>
                    <th>Trạng Thái</th>
                    <th>Tổng Thanh Toán</th>
                    <th>Ngày Đặt Hàng</th>
                    <th>Tùy Chọn</th>
                </tr>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td>${order.productName}</td>
                        <td><img src="${order.image}" alt="${order.productName}"/></td>
                        <td>${order.quantity}</td>
                        <td>${order.statusOrder}</td>
                        <td>${order.totalPrice}</td>
                        <td>${order.dateOrder}</td>
                        <td>
                            <c:choose>
                                <c:when test="${order.statusOrder == 'Shipped'}"> 
                                    <a class="btn btn-green disabled">Hủy Đơn Hàng</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-green" href="cancelorder?orderid=${order.orderID}">Hủy Đơn Hàng</a>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </body>
</html>
<%@include file="include/footer.jsp" %>
