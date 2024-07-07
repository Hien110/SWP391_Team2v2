<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Xin hãy kiểm tra giao dịch trước khi thanh toán</title>
</head>
<body>
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
    <form action="heartstealpay" method="post">
        <input type="hidden" name="userid" value="${userid}">
        <input type="hidden" name="password" value="${password}">
        <input type="hidden" name="money" value="${transaction.amount.total}">
        <input type="hidden" name="check" value="${check}">
        <button type="submit">Xác nhận</button>
    </form>
</body>
</html>
