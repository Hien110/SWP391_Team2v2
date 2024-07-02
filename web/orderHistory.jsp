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
            .button1 b {
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
                        <td>${order.orderid}</td>
                        <td>
                            <img src="${order.image}" alt="${order.productname}"/>
                            ${order.productname}
                        </td>
                        <td>${order.quantity}</td>
                        <td>${order.statusorder}</td>
                        <td>${order.totalprice}</td>
                        <td>${order.dateorder}</td>
                        <td>
                            <a class="buttondelivered" href="evaluate?productid= ${order.productid}"><b>Đánh giá</b></a>
                            <br/>        
                            <a class="button1" href="detailorder?orderid=${order.orderid}"><b>Chi Tiết</b></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <%@include file="include/footer.jsp" %>   
    </body>
</html>
