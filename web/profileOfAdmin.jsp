<%-- 
    Document   : profileOfAdmin
    Created on : Jun 19, 2024, 8:38:19 PM
    Author     : lethanhnhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="include/header.jsp" %>

<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 12:36:25 AM
    Author     : ADMIN
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profileCSS.css">
    </head>
    <!--    <style>
            .custom-file-input {
                display: none;
            }
    
            .custom-file-label {
                display: inline-block;
                background-color: #f2f2f2;
                padding: 8px 12px;
                cursor: pointer;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
        </style>-->
    <body>
        <div class="full">
            <div class="container-fluid container">
                <div class="row">
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
            <c:when test="${sessionScope.imgavt == null}">
                <img style="border-radius: 50%;" src="https://via.placeholder.com/100" alt="Avatar">
            </c:when>
            <c:otherwise>
                <img style="border-radius: 50%;" src="${sessionScope.imgavt}" alt="Avatar">
            </c:otherwise>
        </c:choose>
    </div>
    <div class="username">${sessionScope.username}</div>
    <nav class="nav flex-column">
        <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/profile.jsp"%>Thiết lập tài khoản</a>
        <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/infocustomer">Quản lý cửa hàng</a>
        <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/changePassUser.jsp">Quản lý sản phẩm</a>
        <a class="nav-link1" style=" color: #000;" href="#">Thống kê</a>
        <c:choose>
            <c:when test="${sessionScope.roleid == 3 or sessionScope.roleid == 4 }">
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/registerToSales.jsp">Đăng kí bán hàng</a>
            </c:when>
            <c:when test="${sessionScope.roleid == 2 }">
                <a class="nav-link1" style=" color: #000;" href="${pageContext.request.contextPath}/registerToSales.jsp">Cửa hàng của tôi</a>
            </c:when>
            <c:otherwise>
                
            </c:otherwise>
        </c:choose>
    </nav>
</div>

                    <div class="col-md-9 content">
                        <h1 style="font-size: 24px; border-bottom: 2px solid #ddd; padding-bottom: 10px; margin: 8px 0 20px 0">Hồ sơ của tôi</h1>
                        <div class="profile-info">
                            <div class="profile-avatar">
                                <c:choose>
                                    <c:when test="${sessionScope.imgavt == null}">
                                        <img style="border-radius: 50%;" src="https://via.placeholder.com/100" alt="Avatar">
                                    </c:when>
                                    <c:otherwise>
                                        <img style="border-radius: 50%;" src="${sessionScope.imgavt}" alt="Avatar">
                                    </c:otherwise>
                                </c:choose>
                                <div>
                                    <div class="username">${sessionScope.username}</div>
                                    <form method="post" action="./fileuploadservlet" enctype="multipart/form-data">
                                        <input type="file" name="file" />
                                        <input type="submit" value="Upload" />
                                    </form>
                                </div>
                            </div>
                            <div class="profile-details">
                                <div class="form-group">
                                    <p class="name">Tên đăng nhập</p>
                                    <input type="text" class="form-control"
                                           id="username" disabled value="${sessionScope.username}">
                                </div>
                                <div class="form-group">
                                    <p class="name">Email</p>
                                    <input type="email" class="form-control"
                                           id="email" disabled value="${sessionScope.email}">
                                </div>
                                <button class="btn btn-danger delete mt-3">Xóa
                                    tài khoản</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <!--    <script>
            document.querySelector('.custom-file-input').addEventListener('change', function (event) {
                var fileName = event.target.files[0].name;
                var label = document.querySelector('.custom-file-label');
                label.textContent = fileName ? fileName : 'Upload File';
            });
        </script>-->
</html>
<%@include file="include/footer.jsp" %>   