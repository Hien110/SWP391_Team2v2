<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="model.Product" %>
<%@ page import="model.User" %>
<%@ page import="java.time.LocalDate" %>
<%
    Product product = (Product) request.getAttribute("product");
    int userId = (int) request.getAttribute("userId");
    String nameOfReceiver = (String) request.getAttribute("nameOfReceiver");
    String phoneNumber = (String) request.getAttribute("phoneNumber");
    String address = (String) request.getAttribute("address");
    String paymentMethods = (String) request.getAttribute("paymentMethods");
    LocalDate currentDate = LocalDate.now();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Review Payment</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        table { border: 0; }
        table td { padding: 10px; }
    </style>
</head>
<body>
    <%@ include file="include/header.jsp" %>
    <div class="container mt-5">
        <h1 class="text-center">Please Review Before Paying</h1>
        <form action="${pageContext.request.contextPath}/payments" method="post">
            <table class="table table-bordered mt-3">
                <tr>
                    <td colspan="2"><b>Transaction Detail:</b></td>
                    <td>    
                        <input type="hidden" name="productId" value="${product.getProductId()}">
                        <input type="hidden" name="userId" value="${userId}">
                        <input type="hidden" name="productName" value="${product.getProductName()}">
                        <input type="hidden" name="quantity" value="${product.getQuantityp()}">
                        <input type="hidden" name="nameOfReceiver" value="${nameOfReceiver}">
                        <input type="hidden" name="phoneNumber" value="${phoneNumber}">
                        <input type="hidden" name="address" value="${address}">
                        <input type="hidden" name="size" value="${size}">
                        <input type="hidden" name="color" value="${color}">
                        <input type="hidden" name="paymentMethods" value="${paymentMethods}">
                        <input type="hidden" name="totalPrice" value="${(product.getPrice() * product.getQuantityp()) + 10000}">
                        <input type="hidden" name="dateOrder" value="<%= currentDate %>">
                        <input type="hidden" name="promotionId" value="1"> <!-- Example value -->
                    </td>
                </tr>
                <tr>
                    <td>Sản phẩm:</td>
                    <td>${product.getProductName()}</td>
                </tr>
                <tr>
                    <td>Mô tả:</td>
                    <td>${product.getDescription()}</td>
                </tr>
                <tr>
                    <td>Size:</td>
                    <td>${product.getSize()}</td>
                </tr>
                <tr>
                    <td>Màu:</td>
                    <td>${product.getColor()}</td>
                </tr>
                <tr>
                    <td>Image:</td>
                    <td><img src="${product.getImage()}" alt="Product Image" class="img-thumbnail" style="width: 50px;"></td>
                </tr>
                <tr>
                    <td>Số lượng:</td>
                    <td>${product.getQuantityp()}</td>
                </tr>
                <tr>
                    <td>Tổng:</td>
                    <td>${product.getPrice() * product.getQuantityp()}</td>
                </tr>
                <tr>
                    <td>Shipping:</td>
                    <td>10.000</td>
                </tr>
                <tr>
                    <td>Tổng đơn hàng:</td>
                    <td>${(product.getPrice() * product.getQuantityp()) + 10000}</td>
                </tr>
                <tr><td><br/></td></tr>
                <tr>
                    <td colspan="2"><b>Phương thức thanh toán:</b></td>
                    <td>${paymentMethods}</td>
                </tr>
                <tr><td><br/></td></tr>
                <tr>
                    <td colspan="2"><b>Shipping Address:</b></td>
                    <td>${nameOfReceiver} (${phoneNumber})<br>${address}</td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Đặt hàng" class="btn btn-primary"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <%@ include file="include/footer.jsp" %>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.min.js"></script>
</body>
</html>
