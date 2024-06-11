<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 12:36:25 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <body>
        <div class="full">
            <div class="container-fluid container">
                <div class="row">
                    <jsp:include page="/include/navbar.jsp"/>
                    <div class="col-md-9 content">
                        <h1 style="font-size: 24px; border-bottom: 2px solid #ddd; padding-bottom: 10px; margin: 8px 0 20px 0">Hồ sơ của tôi</h1>
                        <div class="profile-info">
                            <div class="profile-avatar">
                                <img style="border-radius: 50%;"
                                     src="https://via.placeholder.com/150"
                                     alt="Avatar">
                                <div>
                                    <div class="username">${sessionScope.username}</div>
                                    <a href="#">Thay đổi avatar</a>
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
</html>
