<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <title>Sign Up</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signupCSS.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="" crossorigin="anonymous" />
        <style>
            body, h2, span, a, input, p, h4 {
                font-family: 'Poppins', sans-serif;
            }
        </style>
    </head>
    <body>

        <div class="main">
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Đặt lại mật khẩu</h2>
                            <form method="post" action="./resetpassword">
                                <div class="form-group">
                                    <i class="fas fa-lock left"></i>
                                    <input type="password" id="password" name="password" placeholder="Mật khẩu mới" required/>
                                    <i class="fa-regular fa-eye" id="togglePassword" onclick="togglePasswordVisibility()" style="position: absolute; right: 10px; cursor: pointer;"></i>
                                </div>
                                <div class="form-group">
                                    <i class="fas fa-lock left"></i>
                                    <input type="password" id="re_password" name="re_password" placeholder="Xác nhận lại mật khẩu" required/>
                                    <i class="fa-regular fa-eye" id="togglePassword1" onclick="togglePasswordVisibility1()" style="position: absolute; right: 10px; cursor: pointer;"></i>
                                </div>
                                <h4 style="color: red; padding-top: 10px; font-weight: 400">${requestScope.error}</h4>
                                <div class="form-group form-button">
                                    <input type="submit" class="form-submit" value="Đặt lại mật khẩu"/>
                                </div>
                            </form>
                        </div>
                        <div class="signup-image" >
                            <figure><img src="${pageContext.request.contextPath}/images/logo.png" alt="sing up image"></figure>
                        </div>
                    </div>
                </div>
            </section>
        </div>
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
                const passwordField = document.getElementById('re_password');
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
        </script>
    </body>
</html>