<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Product" %>
<%@ page import="model.User" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    int userId = (int) request.getAttribute("userId");
    String nameOfReceiver = (String) request.getAttribute("nameOfReceiver");
    String phoneNumber = (String) request.getAttribute("phoneNumber");
    String address = (String) request.getAttribute("address");
    String voucherId = (String) request.getAttribute("voucherId");
    String calculatedTotal = (String) request.getAttribute("calculatedTotal");
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
        table {
            border: 0;
        }
        table td {
            padding: 10px;
        }
    </style>
</head>
<body>
    <%@ include file="include/header.jsp" %>
    <div class="container mt-5">
        <h1 class="text-center">Xin hãy kiểm tra đơn hàng của bạn</h1>
        <form action="${pageContext.request.contextPath}/payments" method="post">
            <table class="table table-bordered mt-3">
                <tr>
                    <td colspan="2"><b>Chi tiết giao dịch:</b></td>
                    <td>    
                        <input type="hidden" name="userId" value="${userId}">
                        <input type="hidden" name="nameOfReceiver" value="${nameOfReceiver}">
                        <input type="hidden" name="phoneNumber" value="${phoneNumber}">
                        <input type="hidden" name="address" value="${address}">
                        <input type="hidden" name="paymentMethods" value="${paymentMethods}">
                        <input type="hidden" name="dateOrder" value="<%= currentDate %>">
                        <input type="hidden" name="checkstr" value="2">
                        <input type="hidden" name="voucherId" value="${voucherId}">
                        <input type="hidden" name="calculatedTotal" value="${calculatedTotal}">
                    </td>
                </tr>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>Cửa hàng:</td>
                        <td>${product.getShopName()}</td>
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
                        <td>Kích thước:</td>
                        <td>${product.getSize()}</td>
                    </tr>
                    <tr>
                        <td>Màu:</td>
                        <td>${product.getColor()}</td>
                    </tr>
                    <tr>
                        <td>Ảnh:</td>
                        <td><img src="${product.getImage()}" alt="Product Image" class="img-thumbnail" style="width: 50px;"></td>
                    </tr>
                    <tr>
                        <td>Số lượng:</td>
                        <td>${product.getQuantityp()}</td>
                    </tr>
                    <tr style="margin-bottom: 10px; border-bottom: 1px solid #000">
                        <td colspan="2">
                            <input type="hidden" name="productId" value="${product.productId}">
                            <input type="hidden" name="shopName" value="${product.shopName}">
                            <input type="hidden" name="productName" value="${product.productName}">
                            <input type="hidden" name="description" value="${product.description}">
                            <input type="hidden" name="size" value="${product.size}">
                            <input type="hidden" name="color" value="${product.color}">
                            <input type="hidden" name="image" value="${product.image}">
                            <input type="hidden" name="quantity" value="${product.quantityp}">
                            <input type="hidden" name="price" value="${product.price}">
                            <input type="hidden" name="voucherId" value="${voucherId}">  
                            <input type="hidden" name="amount" value="${calculatedTotal}"> 
                        </td>
                    </tr>
                </c:forEach>
                <tr><td><br/></td></tr>
                <tr>
                    <td colspan="2"><b>Tổng tiền</b></td>
                    <td>${voucherId}</td>
                </tr>
                <tr>
                    <td colspan="2"><b>Phương thức thanh toán:</b></td>
                    <td>${paymentMethods}</td>
                </tr>
                <tr><td><br/></td></tr>
                <tr>
                    <td colspan="2"><b>Điạ chỉ giao hàng</b></td>
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
