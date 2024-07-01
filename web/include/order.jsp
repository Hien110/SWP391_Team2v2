<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="model.Product" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%
    Product product = (Product) request.getAttribute("product");
    User user = (User) request.getAttribute("user");
    List<String> availableSizes = (List<String>) request.getAttribute("availableSizes");
    List<String> availableColors = (List<String>) request.getAttribute("availableColors");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Form</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <style>
        .payment-method {
            cursor: pointer;
            transition: border-color 0.3s;
        }
        .payment-method.active {
            border-color: #6064b6;
        }
        .payment-form {
            display: none;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="text-center mb-4">
            <h2>Order Form</h2>
        </div>
        <div class="bg-light p-4 rounded">
            <div class="mb-3">
                <h3 class="text-success">Shipping Address</h3>
                <p><b><%= user.getFullname() %></b> (<%= user.getPhonenumber() %>)</p>
                <p><%= user.getAddress() %></p> <!-- Display user's address -->
                <a href="#">Change</a>
            </div>

            <div class="order-summary mb-3">
                <h3>Order Summary</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead class="table-light">
                            <tr>
                                <th>Product</th>
                                <th>Size</th>
                                <th>Color</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="d-flex align-items-center">
                                    <img src="<%= product.getImage() %>" alt="Product Image" class="img-thumbnail me-2" style="width: 50px;">
                                    <div>
                                        <p class="mb-0"><%= product.getProductName() %></p>
                                        <small class="text-muted"><%= product.getDescription() %> | <a href="#" class="text-success">Chat ngay</a></small>
                                    </div>
                                </td>
                                <td><%= product.getSize() %></td>
                                <td><%= product.getColor() %></td>
                                <td>₫<%= product.getPrice() %></td>
                                <td><%= product.getQuantityp() %></td>
                                <td>₫<%= product.getPrice() * product.getQuantityp() %></td>
                            </tr>
                            <tr>
                                <td colspan="5" class="text-end">Voucher:</td>
                                <td>-₫5.000 <a href="#">Choose Another Voucher</a></td>
                            </tr>
                            <tr>
                                <td colspan="5" class="text-end">Shipping:</td>
                                <td>₫10.000 (Standard Express, Arrives between June 13 - June 17) <a href="#">Change</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="d-flex justify-content-between fw-bold border-top pt-3 mb-3">
                <span>Total:</span>
                <span>₫<%= (product.getPrice() * product.getQuantityp()) - 5000 + 10000 %></span>
            </div>

            <div class="payment-section mb-3">
                <h3>Payment Method</h3>
                <div class="mb-3">
                    <button type="button" class="btn btn-outline-primary me-2" onclick="selectPaymentMethod('cod')">Thanh toán khi nhận hàng</button>
                    <button type="button" class="btn btn-outline-primary" onclick="selectPaymentMethod('heasteal')">Heasteal Points</button>
                </div>
                <div id="cod-section">
                    <p>Phí thu hộ: ₫0 VNĐ. Ưu đãi về phí vận chuyển (nếu có) áp dụng cả với phí thu hộ.</p>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/reviewOrder" method="post">
                <input type="hidden" name="productId" value="<%= product.getProductId() %>"> 
                <input type="hidden" name="productName" value="<%= product.getProductName() %>">
                <input type="hidden" name="size" value="<%= product.getSize() %>">
                <input type="hidden" name="color" value="<%= product.getColor() %>">
                <input type="hidden" name="price" value="<%= product.getPrice() %>">
                <input type="hidden" name="quantity" value="<%= product.getQuantityp() %>">
                <input type="hidden" name="image" value="<%= product.getImage() %>">
                <input type="hidden" name="description" value="<%= product.getDescription() %>">
                <input type="hidden" name="shopId" value="<%= product.getShopId() %>">
                <input type="hidden" name="userId" value="<%= user.getUserid() %>">
                <input type="hidden" name="receiverInfoId" value="1"> <!-- Example value -->
                <input type="hidden" name="paymentMethods" id="paymentMethods" value="">

                <div class="text-end mt-4">
                    <button type="submit" class="btn btn-primary">Xác Nhận</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.min.js"></script>
    <script>
        function selectPaymentMethod(method) {
            document.getElementById('paymentMethods').value = method;
            if (method === 'cod') {
                showCod();
            } else {
                showCard();
                showForm(method + 'Form', method);
            }
        }

        function showCod() {
            document.getElementById('cod-section').style.display = 'block';
            document.getElementById('card-section').style.display = 'none';
            resetPaymentForms();
        }

        function showCard() {
            document.getElementById('cod-section').style.display = 'none';
            document.getElementById('card-section').style.display = 'block';
            resetPaymentForms();
        }

        function showForm(formId, paymentMethod) {
            document.querySelectorAll('.payment-form').forEach(form => {
                form.style.display = 'none';
            });
            document.querySelectorAll('.payment-method').forEach(method => {
                method.classList.remove('active');
            });
            document.getElementById(formId).style.display = 'block';
            document.querySelector(`label[for="${paymentMethod}"]`).classList.add('active');
        }

        function resetPaymentForms() {
            document.querySelectorAll('.payment-form').forEach(form => {
                form.style.display = 'none';
            });
            document.querySelectorAll('.payment-method').forEach(method => {
                method.classList.remove('active');
            });
        }

        document.querySelector('form').addEventListener('submit', function(event) {
            var paymentMethod = document.getElementById('paymentMethods').value;
            if (!paymentMethod) {
                event.preventDefault();
                alert('Please choose your payment method.');
            }
        });
    </script>
</body>
</html>
