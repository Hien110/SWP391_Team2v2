<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Your Cart</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    </head>
    <body>
        <div class="container mt-5">
            <h3>Giỏ hàng của bạn</h3>

            <c:if test="${not empty message}">
                <div class="alert alert-${messageType}">${message}</div>
            </c:if>

            <c:if test="${not empty cartItems}">
                <form id="orderForm" action="${pageContext.request.contextPath}/cartToOrder" method="post">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Chọn</th>
                                <th>Ảnh sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th>Cửa hàng</th>
                                <th>Loại</th>
                                <th>Giá</th>
                                <th>Số lượng</th>
                                <th>Tổng giá</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cartItems}">
                                <tr>
                                    <td><input type="checkbox" name="selectedItems" value="${item.cartId}" data-cartid="${item.cartId}"></td>
                                    <td><img src="${item.image}" alt="${item.productName}" style="width: 100px; height: auto;"></td>
                                    <td>${item.productName}</td>
                                    <td>${item.shopname}</td>
                                    <td>
                                        <p style="color: #000">Kích thước: ${item.size}</p>
                                        <p style="color: #000">Màu sắc: ${item.color}</p>
                                    </td>
                                    <td>
                                        <p style="color: #000"><fmt:formatNumber value="${item.price}" pattern="#,###" /> VNĐ</p>

                                    </td>
                                    <td>${item.quantity}</td>
                                    <td>₫${item.price * item.quantity}</td>
                                    <td>
                                        <a onclick="window.location.href = '${pageContext.request.contextPath}/detailProduct?productId=${item.productId}&userId=${userId}'" style="border: 1px solid">Chi tiết </a>
                                        <a href="${pageContext.request.contextPath}/deleteCa?cartId=${item.cartId}&productId=${item.productId}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="d-flex justify-content-end">
                        <button type="button" class="btn btn-primary" onclick="submitOrderForm()">Order</button>
                    </div>
                </form>
            </c:if>
            <c:if test="${empty cartItems}">
                <p>Your cart is empty.</p>
            </c:if>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.min.js"></script>
        <script>
                            function submitOrderForm() {
                                var selectedItems = document.querySelectorAll('input[name="selectedItems"]:checked');
                                if (selectedItems.length === 0) {
                                    alert('Please select at least one item to order.');
                                    return;
                                }

                                // Loop through each selected checkbox and log its value
                                selectedItems.forEach(function (item) {
                                    console.log('Selected cartId:', item.value);
                                });

                                // Optionally, you can also collect these values into an array for further processing
                                var selectedCartIds = Array.from(selectedItems).map(function (item) {
                                    return item.value;
                                });

                                // Proceed with your form submission or further processing
                                var form = document.getElementById('orderForm');
                                selectedCartIds.forEach(function (cartId) {
                                    var input = document.createElement('input');
                                    input.type = 'hidden';
                                    input.name = 'cartIds';
                                    input.value = cartId;
                                    form.appendChild(input);
                                });


                                form.submit();
                            }
        </script>
    </body>
</html>

