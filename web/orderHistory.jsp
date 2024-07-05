<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
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
            .img {
                width: 100px;
                height: 125px;
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
                color: #2a8341;
                background-color: #fff;
                border: 1px solid #2a8341;
                cursor: pointer;
                margin-right: 5px;
                text-decoration: none;
                display: inline-block;
                margin-bottom: 7px;
                width: 116px;
            }

            .button1 {
                border-radius: 10px;
                padding: 7px 25px;
                color: #2a8341;
                background-color: #fff;
                border: 1px solid #2a8341;
                cursor: pointer;
                margin-right: 5px;
                text-decoration: none;
                display: inline-block;
                width: 103px;
            }

            .button1:hover{
                color: #fff;
                background-color: #2a8341;
                border: 1px solid #2a8341;
                cursor: pointer;
                transition: 0.3s;
            }

            .buttondelivered:hover{
                color: #fff;
                background-color: #2a8341;
                border: 1px solid #2a8341;
                cursor: pointer;
                transition: 0.3s;
            }
            .button1 b {
                margin-right: 15px;
            }
            .back-button {
                border-radius: 10px;
                background-color: #fff;
                padding: 10px 10px 10px 0px;
                font-size: 16px;
                cursor: pointer;
                color: #28a745;
                margin-top: 5px;
                margin-bottom: 40px;
                border:1px solid #28a745;
            }

            .back-button:hover {
                background-color: #28a745;
                color: #fff;
                border:1px solid #28a745;
                transition: 0.3s;
            }
            .container1 {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <%
        HttpSession currentSession = request.getSession();
        User loggedInAccount = (User) currentSession.getAttribute("user");
        // Kiểm tra nếu người dùng chua đăng nhập
        if (loggedInAccount == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        %>
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
                            <img class="img" src="${order.image}" alt="${order.productname}"/>
                            ${order.productname}
                        </td>
                        <td>${order.quantity}</td>
                        <td>${order.statusorder}</td>
                        <td>${order.totalprice}</td>
                        <td>${order.dateorder}</td>
                        <td>
                            <a class="buttondelivered" href="evaluate?productid=${order.productid}"><b>Đánh giá</b></a>
                            <br/>        
                            <a class="button1" href="detailorder?orderid=${order.orderid}"><b>Chi Tiết</b></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <h4 style="color: red; padding-top: 20px; font-weight: 400; text-align: center">${requestScope.aler}</h4>
        </div>
        <div class="container1">
            <button class="back-button" onclick="window.location.href = 'ordertracking'"><i class="fa-solid fa-arrow-left-long"></i>Quay Lại</button>
        </div>
        <%@include file="include/footer.jsp" %>   
    </body>
</html>
