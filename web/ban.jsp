<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thông báo tài khoản bị khóa</title>
    <style>
        body {
            background-image: url('./images/ban.jpg');
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            color: white;
            text-align: center;
        }
        .container {
            background-color: rgba(0, 0, 0, 0.5);
            padding: 20px;
            border-radius: 10px;
        }
        .btn-login {
            text-decoration: none;
            background-color: red;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .btn-login:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>THÔNG BÁO!</h1>
        <p>Tài khoản của bạn đã bị khóa, vui lòng phản hồi qua email <a href="mailto:heartstealshop@gmail.com" title="Click để gửi email">heartstealshop@gmail.com</a>.</p>
        <a href="login.jsp" class="btn-login">Đăng nhập</a>
    </div>
</body>
</html>
