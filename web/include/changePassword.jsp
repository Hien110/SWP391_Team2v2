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
                            <h2 style="font-size: 24px; border-bottom: 2px solid #ddd; padding-bottom: 10px; margin: 8px 0 20px 0">Đổi mật khẩu</h2>
                            <div class="profile-info">
                                <div class="profile-details" style="margin-top: 120px;">
                                    <div class="form-group">
                                        <p class="name" style="margin-left: 0px; color: #000">Mật khẩu cũ</p>
                                        <!--<input type="password" class="form-control" required name="oldpass">-->
                                        <input type="password" class="form-control" id ="password" name="oldpass" required/>
                                        <i class="fa-regular fa-eye" id="togglePassword" onclick="togglePasswordVisibility()" style="position: absolute; right: 140px; cursor: pointer;"></i>
                                    </div>
                                    <div class="form-group">
                                        <p class="name" style="margin-left: 0px;  color: #000">Mật khẩu mới</p>
                                        <input class="form-control" type="password" id ="password1" required name="newpass">
                                        <i class="fa-regular fa-eye" id="togglePassword1" onclick="togglePasswordVisibility1()" style="position: absolute; right: 140px; cursor: pointer;"></i>
                                    </div>
                                    <div class="form-group" style=" margin-bottom: 5px; ">
                                        <p class="name" style="margin-left: 0px; color: #000">Xác nhận lại mật khẩu</p>
                                        <input class="form-control" type="password" id ="password2" required name="repass">
                                        <i class="fa-regular fa-eye" id="togglePassword2" onclick="togglePasswordVisibility2()" style="position: absolute; right: 140px; cursor: pointer;"></i>

                                    </div>
                                    <p style="color: red;font-weight: 400">${requestScope.error}</p>
                                    <p style="color: green;font-weight: 400">${requestScope.success}</p>
                                    <button style="background-color: none" class="btn delete mt-3">Đổi mật khẩu</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script>
            function togglePasswordVisibility() {
                const passwordField = document.getElementById('password');
                const togglePassword = document.getElementById('togglePassword');
                if (passwordField.type === 'password') {
                    passwordField.type = 'text';
                    togglePassword.classList.remove('fa-eye');
                    togglePassword.classList.add('fa-eye-slash');
                } else {
                    passwordField.type = 'password';
                    togglePassword.classList.remove('fa-eye-slash');
                    togglePassword.classList.add('fa-eye');
                }
            }

            function togglePasswordVisibility1() {
                const passwordField = document.getElementById('password1');
                const togglePassword = document.getElementById('togglePassword1');
                if (passwordField.type === 'password') {
                    passwordField.type = 'text';
                    togglePassword.classList.remove('fa-eye');
                    togglePassword.classList.add('fa-eye-slash');
                } else {
                    passwordField.type = 'password';
                    togglePassword.classList.remove('fa-eye-slash');
                    togglePassword.classList.add('fa-eye');
                }
            }
            
            function togglePasswordVisibility2() {
                const passwordField = document.getElementById('password2');
                const togglePassword = document.getElementById('togglePassword2');
                if (passwordField.type === 'password') {
                    passwordField.type = 'text';
                    togglePassword.classList.remove('fa-eye');
                    togglePassword.classList.add('fa-eye-slash');
                } else {
                    passwordField.type = 'password';
                    togglePassword.classList.remove('fa-eye-slash');
                    togglePassword.classList.add('fa-eye');
                }
            }
        </script>
    </body>
</html>
