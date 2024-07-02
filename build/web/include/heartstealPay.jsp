<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 12:36:25 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profileCSS.css">
</head>
<body>
    <div class="full">
        <div class="container-fluid container">
            <div class="row">
                <c:if test="${sessionScope.checknav == 1}">
                    <jsp:include page="/include/navbar.jsp"/>
                </c:if>
                <c:if test="${sessionScope.checknav == 2}">
                    <jsp:include page="/include/navbarshop.jsp"/>
                </c:if>
                <div class="col-md-9 content">
                    <h1 style="font-size: 24px; border-bottom: 2px solid #ddd; padding-bottom: 10px; margin: 8px 0 20px 0">Ví HeartstealPay</h1>
                    <div class="profile-info">
                        <div>
                            <div class="profile-details">
                                <div class="form-group">
                                    <p class="name">Email PayPal</p>
                                    <c:if test="${not empty sessionScope.user.emailpaypal}">
                                        <input name="emailPaypal" type="text" value="${sessionScope.user.emailpaypal}" readonly class="form-control" id="username">
                                    </c:if>
                                    <c:if test="${empty sessionScope.user.emailpaypal}">
                                        <input name="emailPaypal" type="text" class="form-control" value="Chưa liên kết tài khoản" id="username" style="color: #ddd">
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <p class="name">Số dư tài khoản ($)</p>
                                    <p style="color: #000"><fmt:formatNumber value="${sessionScope.wallet.surplus}" pattern="#,##0.##" /> $</p>
                                </div>
                                <div class="form-group">
                                    <p class="name">Số dư tài khoản (VNĐ)</p>
                                    <p style="color: #000"><fmt:formatNumber value="${sessionScope.wallet.surplus * 24000}" pattern="#,##0.##" /> VNĐ</p>
                                </div>
                            </div>
                            <p style="text-align: center; color: red; font-weight: 400">${requestScope.error}</p>
                            <p style="text-align: center; color: green; font-weight: 400">${requestScope.success}</p>
                            <div style="width: 100%; display: flex; justify-content: space-around;">
                                <c:if test="${not empty sessionScope.user.emailpaypal}">
                                    <button type="button" class="btn delete mt-3" data-bs-toggle="modal" data-bs-target="#paymentModal">Nộp tiền</button>
                                    <button type="button" class="btn delete mt-3" data-bs-toggle="modal" data-bs-target="#withdrawModal">Rút tiền</button>
                                    <button type="button" class="btn delete mt-3" data-bs-toggle="modal" data-bs-target="#passwordModal">Huỷ liên kết</button>
                                </c:if>
                                <c:if test="${empty sessionScope.user.emailpaypal}">
                                    <button type="button" class="btn delete mt-3" data-bs-toggle="modal" data-bs-target="#linkpaypalModal">Liên kết PayPal</button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal password-->
    <form id="passwordForm" method="get" action="./linkpaypal">
        <div class="modal fade" id="passwordModal" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true" style="background-color: #0000; margin-top: 200px">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="passwordModalLabel">Xác nhận huỷ liên kết</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="password" class="form-label">Mật khẩu xác nhận</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Xác nhận</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <!-- Modal link PayPal-->
    <form id="cc" method="post" action="./linkpaypal">
        <div class="modal fade" id="linkpaypalModal" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true" style="background-color: #0000; margin-top: 200px">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="passwordModalLabel">Liên kết PayPal</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="emailPaypal" class="form-label">Email PayPal</label>
                            <input type="email" class="form-control" id="emailPaypal" name="emailPaypal" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Xác nhận mật khẩu</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Xác nhận</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <!--payment-->
    <form id="paymentModalForm" method="post" action="./heartstealpay">
        <div class="modal fade" id="paymentModal" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true" style="background-color: #0000; margin-top: 200px">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="passwordModalLabel">Nhập số tiền</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="money" class="form-label">Số tiền</label>
                            <input type="text" class="form-control" id="money" name="money" placeholder="Đơn vị: $ (Đô la)" required>
                            <input type="hidden" value="1" name="check">
                        </div>
                        <div class="mb-3">
                            <label for="method" class="form-label">Phương thức thanh toán</label>
                            <select id="method" name="method" class="form-select" required>
                                <option value="" disabled selected>Chọn phương thức thanh toán</option>
                                <c:if test="${not empty sessionScope.user.emailpaypal}">
                                    <option value="paypal">PayPal</option>
                                </c:if>
                                <c:if test="${not empty sessionScope.user.banknumber}">
                                    <option value="bank">Bank</option>
                                </c:if>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Xác nhận mật khẩu</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Xác nhận</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <!--withdraw-->
    <form id="withdrawModalForm" method="post" action="./heartstealpay">
        <div class="modal fade" id="withdrawModal" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true" style="background-color: #0000; margin-top: 200px">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="passwordModalLabel">Nhập số tiền</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="amount" class="form-label">Số tiền</label>
                            <input type="text" class="form-control" id="amount" name="money" placeholder="Đơn vị: $ (Đô la)" required>
                            <input type="hidden" value="2" name="check">
                        </div>
                        <div class="mb-3">
                            <label for="method" class="form-label">Phương thức thanh toán</label>
                            <select id="method" name="method" class="form-select" required>
                                <option value="" disabled selected>Chọn phương thức thanh toán</option>
                                <c:if test="${not empty sessionScope.user.emailpaypal}">
                                    <option value="paypal">PayPal</option>
                                </c:if>
                                <c:if test="${not empty sessionScope.user.banknumber}">
                                    <option value="bank">Bank</option>
                                </c:if>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Xác nhận mật khẩu</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Xác nhận</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
