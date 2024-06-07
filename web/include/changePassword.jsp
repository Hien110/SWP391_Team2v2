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
        <form action="./changepassuser" method="post">
            <div class="full">
                <div class="container-fluid container">
                    <div class="row">
                        <jsp:include page="/include/navbar.jsp"/>
                        <div class="col-md-9 content">
                            <div class="profile-info">
                                <div class="profile-details" style="margin-top: 120px;">
                                    <div class="form-group">
                                        <p class="name" style="margin-left: 0px; color: #000">Mật khẩu cũ</p>
                                        <input type="text" class="form-control" required name="oldpass">
                                    </div>
                                    <div class="form-group">
                                        <p class="name" style="margin-left: 0px;  color: #000">Mật khẩu mới</p>
                                        <input class="form-control" required name="newpass">
                                    </div>
                                    <div class="form-group" style=" margin-bottom: 5px; ">
                                        <p class="name" style="margin-left: 0px; color: #000">Xác nhận lại mật khẩu</p>
                                        <input class="form-control" required name="repass">
                                    </div>
                                    <p style="color: red;font-weight: 400">${requestScope.error}</p>
                                    <p style="color: green;font-weight: 400">${requestScope.success}</p>
                                    <button class="btn btn-success delete mt-3">Đổi mật khẩu</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
