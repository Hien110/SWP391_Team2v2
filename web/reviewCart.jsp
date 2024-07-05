<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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
            <h1 class="text-center">Please Review Before Paying</h1>
            <form action="${pageContext.request.contextPath}/payments" method="post">
                <table class="table table-bordered mt-3">

                    <tr>
                        <td colspan="2"><b>Transaction Detail:</b></td>
                        <td>    
                            <input type="hidden" name="userId" value="${userId}">
                            <input type="hidden" name="nameOfReceiver" value="${nameOfReceiver}">
                            <input type="hidden" name="phoneNumber" value="${phoneNumber}">
                            <input type="hidden" name="address" value="${address}">
                            <input type="hidden" name="paymentMethods" value="${paymentMethods}">
                            <input type="hidden" name="dateOrder" value="<%= currentDate %>">
                            <input type="hidden" name="checkstr" value="2">

                        </td>
                    </tr>
                    <c:forEach var="products" items="${requestScope.products}">
                        <tr>
                            <td>Shop</td>
                            <td>${products.getShopName()}</td>
                        </tr>
                        <tr>
                            <td>Sản phẩm:</td>
                            <td>${products.getProductName()}</td>
                        </tr>
                        <tr>
                            <td>Mô tả:</td>
                            <td>${products.getDescription()}</td>
                        </tr>
                        <tr>
                            <td>Size:</td>
                            <td>${products.getSize()}</td>
                        </tr>
                        <tr>
                            <td>Màu:</td>
                            <td>${products.getColor()}</td>
                        </tr>
                        <tr>
                            <td>Image:</td>
                            <td><img src="${products.getImage()}" alt="Product Image" class="img-thumbnail" style="width: 50px;"></td>
                        </tr>
                        <tr>
                            <td>Số lượng:</td>
                            <td>${products.getQuantityp()}</td>
                        </tr>
                        <tr>
                            <td>Tổng:</td>
                            <td>${products.getPrice() * products.getQuantityp()}</td>
                        </tr>
                        <tr >
                            <td>Shipping:</td>
                            <td>10.000</td>
                        </tr>
                        <tr style="margin-bottom: 10px; border-bottom: 1px solid #000">
                            <td>
                                <input type="hidden" name="productId" value="${products.productId}">
                                <input type="hidden" name="shopName" value="${products.shopName}">
                                <input type="hidden" name="productName" value="${products.productName}">
                                <input type="hidden" name="description" value="${products.description}">
                                <input type="hidden" name="size" value="${products.size}">
                                <input type="hidden" name="color" value="${products.color}">
                                <input type="hidden" name="image" value="${products.image}">
                                <input type="hidden" name="quantity" value="${products.quantityp}">
                                <input type="hidden" name="price" value="${products.price}"></td>
                        </tr>

                    </c:forEach>
                    <!--                <tr>
                                        <td>Tổng đơn hàng:</td>
                                        <td></td>
                                    </tr>-->
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
