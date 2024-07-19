<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Xin hãy kiểm tra giao dịch trước khi thanh toán</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: #fff;
            padding: 20px 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            color: #007bff;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            margin-bottom: 20px;
            border-collapse: collapse;
        }
        table td {
            padding: 10px;
            border-bottom: 1px solid #dee2e6;
        }
        table td:first-child {
            text-align: right;
            font-weight: bold;
            color: #495057;
        }
        table td:last-child {
            text-align: left;
            color: #495057;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            margin: 5px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .btn.cancel {
            background-color: #dc3545;
        }
        .btn.cancel:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${check == '1'}">
                <h1>Nạp tiền</h1>
            </c:when>
            <c:when test="${check == '2'}">
                <h1>Rút tiền</h1>
            </c:when>
            <c:otherwise>
                <h1>Giao dịch</h1>
            </c:otherwise>
        </c:choose>
        <table>
            <tr>
                <td>Số tiền:</td>
                <td>${transaction.amount.total} ${transaction.amount.currency}</td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>${payer.email}</td>
            </tr>
        </table>
        <form action="heartstealpay" method="post" style="display:inline;">
            <input type="hidden" name="userid" value="${userid}">
            <input type="hidden" name="password" value="${password}">
            <input type="hidden" name="money" value="${transaction.amount.total}">
            <input type="hidden" name="check" value="${check}">
            <button type="submit" class="btn">Xác nhận</button>
        </form>
        <a href="profileUser.jsp" class="btn cancel">Hủy giao dịch</a>
    </div>
</body>
</html>
