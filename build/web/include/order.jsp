<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Product" %>
<%@ page import="model.User" %>
<%@ page import="model.InfoCustomer" %>
<%@ page import="java.util.List" %>
<%
    Product product = (Product) request.getAttribute("product");
    User user = (User) request.getAttribute("user");
    List<InfoCustomer> addresses = (List<InfoCustomer>) request.getAttribute("addresses");
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
                <% if (!addresses.isEmpty()) { %>
                    <p id="currentAddress"><b><%= addresses.get(0).getCustomerName() %></b> (<%= addresses.get(0).getPhoneCustomer() %>)</p>
                    <p><span id="currentFullAddress"><%= addresses.get(0).getAddressCustomer() %></span></p>
                <% } else { %>
                    <p id="currentAddress"><b><%= user.getFullname() %></b> (<%= user.getPhonenumber() %>)</p>
                    <p><span id="currentFullAddress"><%= user.getAddress() %></span></p>
                <% } %>
                <button type="button" class="btn btn-primary" onclick="showAddressModal()">Change</button>
            </div>

            <!-- Address Modal -->
            <div class="modal fade" id="addressModal" tabindex="-1" aria-labelledby="addressModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addressModalLabel">Select Address</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <select class="form-select" id="addressSelect" onchange="updateAddress()">
                                <c:forEach var="address" items="${addresses}">
                                    <option value="${address.customerid}" data-fulladdress="${address.addressCustomer} - ${address.customerName} - ${address.phoneCustomer}">
                                        ${address.addressCustomer} - ${address.customerName} - ${address.phoneCustomer}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
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
                    <button type="button" class="btn btn-outline-primary" onclick="selectPaymentMethod('bank')">Bank</button>
                </div>
                <div id="cod-section">
                    <p>Phí thu hộ: ₫0 VNĐ. Ưu đãi về phí vận chuyển (nếu có) áp dụng cả với phí thu hộ.</p>
                </div>
            </div>

            <form id="orderForm" action="${pageContext.request.contextPath}/reviewOrder" method="post">
                <input type="hidden" name="productId" value="<%= product.getProductId() %>"> <!-- Hidden input for productId -->
                <input type="hidden" name="productName" value="<%= product.getProductName() %>">
                <input type="hidden" name="size" value="<%= product.getSize() %>">
                <input type="hidden" name="color" value="<%= product.getColor() %>">
                <input type="hidden" name="price" value="<%= product.getPrice() %>">
                <input type="hidden" name="quantity" value="<%= product.getQuantityp() %>">
                <input type="hidden" name="image" value="<%= product.getImage() %>">
                <input type="hidden" name="description" value="<%= product.getDescription() %>">
                <input type="hidden" name="shopId" value="<%= product.getShopId() %>">
                <input type="hidden" name="userId" value="<%= user.getUserid() %>">
                <input type="hidden" name="nameOfReceiver" id="nameOfReceiver" value="<%= !addresses.isEmpty() ? addresses.get(0).getCustomerName() : user.getFullname() %>">
                <input type="hidden" name="phoneNumber" id="phoneNumber" value="<%= !addresses.isEmpty() ? addresses.get(0).getPhoneCustomer() : user.getPhonenumber() %>">
                <input type="hidden" name="address" id="address" value="<%= !addresses.isEmpty() ? addresses.get(0).getAddressCustomer() : user.getAddress() %>">
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
        function showAddressModal() {
            var addressModal = new bootstrap.Modal(document.getElementById('addressModal'));
            addressModal.show();
        }

        function updateAddress() {
            var select = document.getElementById("addressSelect");
            var selectedOption = select.options[select.selectedIndex];
            var fullAddress = selectedOption.getAttribute("data-fulladdress");
            var receiverInfoId = selectedOption.value;

            document.getElementById('currentFullAddress').innerText = fullAddress;
            document.getElementById('receiverInfoId').value = receiverInfoId;
        }

        function selectPaymentMethod(method) {
            document.getElementById('paymentMethods').value = method;
            if (method === 'cod') {
                showCod();
            } else {
                showCard();
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

        function resetPaymentForms() {
            document.querySelectorAll('.payment-form').forEach(form => {
                form.style.display = 'none';
            });
            document.querySelectorAll('.payment-method').forEach(method => {
                method.classList.remove('active');
            });
        }

        document.getElementById('orderForm').addEventListener('submit', function(event) {
            var paymentMethod = document.getElementById('paymentMethods').value;
            if (!paymentMethod) {
                event.preventDefault();
                alert('Please choose your payment method.');
            }
        });
    </script>
</body>
</html>
