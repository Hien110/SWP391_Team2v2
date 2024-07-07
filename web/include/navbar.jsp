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

        <c:choose>
            <c:when test="${sessionScope.user.roleid == 3 or sessionScope.user.roleid == 4 }">
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/profileUser.jsp"%>Hồ sơ</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/infocustomer">Địa chỉ</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/changePassUser.jsp">Đổi mật khẩu</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/orderhistory">Đơn đã mua</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/ordertracking">Đơn đang mua</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/heartstealpay">Ví HeartstealPay</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/registerToSales.jsp">Đăng kí bán hàng</a>
            </c:when>
            <c:when test="${sessionScope.user.roleid == 2 }">
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/profileUser.jsp"%>Hồ sơ</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/infocustomer">Địa chỉ</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/changePassUser.jsp">Đổi mật khẩu</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/orderhistory">Đơn đã mua</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/ordertracking">Đơn đang mua</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/heartstealpay">Ví HeartstealPay</a>
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/updateprofileshop">Cửa hàng của tôi
                </a>
            </c:when>
            <c:when test="${sessionScope.user.roleid == 1 }">
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/changePassUser.jsp">Đổi mật khẩu</a>
                <a class="nav-link1" style=" color: #000;">Quản lí cửa hàng</a>
                <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px"href="${pageContext.request.contextPath}/processApproval">Đăng kí bán hàng</a>
                <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px"href="${pageContext.request.contextPath}/listReportedShops">Cửa hàng bị báo cáo</a>
                <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px"href="${pageContext.request.contextPath}/listBannedUsers">Cửa hàng bị cấm</a>
                <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px">Kháng cáo</a>
                <a class="nav-link1" style=" color: #000;">Quản lí sản phẩm</a>
                <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px" href="${pageContext.request.contextPath}/listReportedProducts">Sản phẩm bị báo cáo</a>
                 <a class="nav-link1" style=" color: #000;">Quản lí người dùng</a>
                <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px" href="${pageContext.request.contextPath}/adminPage.jsp">Danh sách người dùng</a>
                <a class="nav-link1" style=" color: #000;">Khuyến mãi</a>
                <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px">Danh sách khuyến mãi</a>
                <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px" href="${pageContext.request.contextPath}/createPromotion.jsp">Tạo khuyến mãi</a>
                <a class="nav-link1" style=" color: #000;">Thống kế</a>
                <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px"href="${pageContext.request.contextPath}/revenueOf10Shop.jsp">Thống kê cửa hàng</a>
                <a class="nav-link1" style=" color: #000; margin-left: 20px; font-size: 16px"href="${pageContext.request.contextPath}/revenueOfShop.jsp">Thống kê sản phẩm</a>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
    </nav>
</div>
