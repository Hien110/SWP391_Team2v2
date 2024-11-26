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
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900&display=swap" rel="stylesheet">
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
                            <h2 class="form-title">Nhập email của bạn</h2>
                            <form method="get" action="./resendemail">
                                <div class="form-group" style="display: flex; align-items: center;">
                                    <i class="fas fa-envelope left"></i>
                                    <input type="text" name="email" placeholder="Email của bạn" value="${param.email}" required/>
                                </div>
                                <h4 style="color: red; padding-top: 10px; font-weight: 400">${requestScope.error}</h4>
                                <div class="form-group form-button">
                                    <input type="submit" class="form-submit" value="Gửi mã xác nhận"/>
                                </div>
                            </form>
                        </div>
                        <div class="signup-image">
                            <figure><img src="${pageContext.request.contextPath}/images/logo.png" alt="sign up image"></figure>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>
