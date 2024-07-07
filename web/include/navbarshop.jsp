<%-- 
   Document   : navbar
   Created on : Jun 7, 2024, 1:06:55 AM
   Author     : ADMIN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-3 sidebar">
    <div class="profile-pic">
        <c:choose>
            <c:when test="${sessionScope.user.imgavt == null}">
                <i style="font-size:80px" class="fa-regular fa-circle-user size"></i>
            </c:when>
            <c:otherwise>
                <img style="border-radius: 50%;" src="${sessionScope.user.imgavt}" alt="Avatar">
            </c:otherwise>
        </c:choose>
    </div>
    <div class="username">${sessionScope.username}</div>
    <nav class="nav flex-column">
        <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/profileShop.jsp">Thiết lập tài khoản</a>
        <a class="nav-link1" style=" color: #000;">Quản lí đơn hàng</a>
        <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px" href="seeOrderList">Đơn đặt hàng</a>
        <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px" href="DeliveredOrderList">Đơn giao thành công</a>
        <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px" href="CancelOrderListShopOwner">Đã huỷ</a>
        <a class="nav-link1" style=" color: #000;">Quản lí sản phẩm</a>
        <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px" href="ListProductShopOwner">Tất cả sản phẩm</a>
        <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px" href="${pageContext.request.contextPath}/createProductShop.jsp">Đăng sản phẩm</a>
        <a class="nav-link1" style=" color: #000;">Tài chính</a>
            <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px" href="${pageContext.request.contextPath}/heartstealpay">Ví HeartstealPay</a>
            <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px" href="${pageContext.request.contextPath}/shopStatistics">Thống kê</a>
    </nav>
</div>
