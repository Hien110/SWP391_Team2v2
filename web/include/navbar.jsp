<%-- 
    Document   : navbar
    Created on : Jun 7, 2024, 1:06:55 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-3 sidebar">
    <div class="profile-pic">
        <img style="border-radius: 50%;"
             src="https://via.placeholder.com/100"
             alt="Avatar">
    </div>
    <div class="username">${sessionScope.username}</div>
    <nav class="nav flex-column">
        <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/profileUser.jsp"%>Hồ sơ</a>
        <a class="nav-link1" style=" color: #000;" href="">Địa chỉ</a>
        <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/changePassUser.jsp">Đổi mật khẩu</a>
        <a class="nav-link1" style=" color: #000;" href="#">Đơn mua</a>
        <a class="nav-link1" style=" color: #000;" href="#">Đăng kí bán hàng</a>
    </nav>
</div>
