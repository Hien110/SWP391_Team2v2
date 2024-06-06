<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900&display=swap" rel="stylesheet">
        <title>Sign Up</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signupCSS.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="" crossorigin="anonymous" />
    </head>
    <body>

        <div class="main">
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Login</h2>
                            <form method="post" action="./login">
                                <div class="form-group" style="display: flex; align-items: center;">
                                    <i class="fas fa-user left" style="margin-right: 10px;"></i>
                                    <input type="text" name="username" placeholder="User Name" value="${param.username != null ? param.username : sessionScope.username}" required/>
                                </div>
                                <div class="form-group" style="position: relative; display: flex; align-items: center;">
                                    <i class="fas fa-lock left" style="margin-right: 10px;"></i>
                                    <input type="password" id="password" name="password" placeholder="Password" required/>
                                    <i class="fa-regular fa-eye" id="togglePassword" onclick="togglePasswordVisibility()" style="position: absolute; right: 10px; cursor: pointer;"></i>
                                </div>
                                <h4 style="color: red; padding-top: 10px; font-weight: 400">${requestScope.error}</h4>
                                <div class="form-group form-button">
                                    <input type="submit" class="form-submit" value="Login"/>
                                </div>
                            </form>
                            <a href="./searchuser.jsp?" style="display: contents; color: #2a8341;">Forgotten password?</a>
                        </div>
                        <div class="signup-image">
                            <figure><img src="${pageContext.request.contextPath}/images/logo.png" alt="sign up image"></figure>
                            <div style="display: flex; justify-content: center; font-size: 16px">
                                <span>You do not have an account?</span>&nbsp;
                                <a href="./signup" style="display: contents; color: #2a8341;">Sign up</a>
                            </div>
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
        </script>
    </body>
</html>
