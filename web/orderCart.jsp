<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Product" %>
<%@ page import="model.User" %>
<%@ page import="model.InfoCustomer" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) request.getAttribute("user");
    List<Product> products = (List<Product>) request.getAttribute("products");
    List<InfoCustomer> addresses = (List<InfoCustomer>) request.getAttribute("addresses");
    double total = 0;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Form</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/order.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <div class="text-center mb-4">
            <h2>Order Form</h2>
        </div>
        <div class="bg-light p-4 rounded">         
            <!-- Shipping Address Section -->
            <div class="mb-3">
                <h3 class="text-success">Shipping Address</h3>
                <% if (!addresses.isEmpty()) { %>
                <p id="currentAddress"><b><%= addresses.get(0).getCustomerName() %></b> (<%= addresses.get(0).getPhoneCustomer() %>)</p>
                <p><span id="currentFullAddress"><%= addresses.get(0).getAddressCustomer() %></span></p>
                <% } else { %>
                <p id="currentAddress"><b><%= user.getFullname() %></b> (<%= user.getPhonenumber() %>)</p>
                <p><span id="currentFullAddress"><%= user.getAddress() %></span></p>
                <% } %>
                
            </div>
            <!-- Address Modal -->
            <div class="modal fade" id="addressModal" tabindex="-1" aria-labelledby="addressModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addressModalLabel">Select Address</h5>
<!--                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>-->
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
            <!-- Order Summary Section -->
            <div class="order-summary mb-3">
                <h3>Order Summary</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead class="table-light">
                            <tr>
                                <th>Product</th>
                                <th>Shop</th>
                                <th>Size</th>
                                <th>Color</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" items="${products}">
                                <tr>
                                    <td class="d-flex align-items-center">
                                        <img src="${product.image}" alt="Product Image" class="img-thumbnail me-2" style="width: 50px;">
                                        <div>
                                            <p class="mb-0">${product.productName}</p>
                                        </div>
                                    </td>
                                    <td>${product.shopName}</td>
                                    <td>${product.size}</td>
                                    <td>${product.color}</td>
                                    <td>₫${product.price}</td>
                                    <td>${product.quantityp}</td>
                                    <td style="width: 100px">₫${product.price * product.quantityp}</td>
                                </tr>
                                <c:set var="total" value="${total + (product.price * product.quantityp)}"/>
                            </c:forEach>
                            <tr>
                                <td colspan="6" class="text-end">Voucher:</td>
                                <td>-₫5,000 <a href="#" style="text-decoration: none">Choose Another Voucher</a></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="text-end">Shipping:</td>
                                <td>₫10,000 (Standard Express, Arrives between June 13 - June 17) <a href="#" style="text-decoration: none">Change</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- Total Calculation -->
            <div class="d-flex justify-content-between fw-bold border-top pt-3 mb-3">
                <span>Total:</span>
                <span>₫${total - 5000 + 10000}</span>
            </div>
            <!-- Payment Method Section -->
            <div class="payment-section mb-3">
                <h3>Payment Method</h3>
                <div class="mb-3">
                    <button type="button" class="btn btn-outline-primary me-2 payment-method" onclick="selectPaymentMethod('cod')">Thanh toán khi nhận hàng</button>
                    <button type="button" class="btn btn-outline-primary me-2 payment-method" onclick="selectPaymentMethod('heasteal')">Heasteal Points</button>
                </div>
                <div id="cod-section" class="payment-form" style="border: 1px solid #ccc; padding: 10px;">
                    <p>Phí thu hộ: ₫0 VNĐ. Ưu đãi về phí vận chuyển (nếu có) áp dụng cả với phí thu hộ.</p>
                </div>
            </div>
            <!-- Order Form -->
            <form id="orderForm" action="${pageContext.request.contextPath}/ReviewCart" method="post">
                <c:forEach var="cartIds" items="${cartIds}">
                    <input type="hidden" name="cartIds" value="${cartIds}">
                </c:forEach>
                <input type="hidden" name="userId" value="${user.userid}">
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

            document.getElementById('currentFullAddress').innerText = fullAddress;
            var addressParts = fullAddress.split(" - ");
            document.getElementById('nameOfReceiver').value = addressParts[1];
            document.getElementById('phoneNumber').value = addressParts[2];
            document.getElementById('address').value = addressParts[0];
        }

        function selectPaymentMethod(method) {
            document.getElementById('paymentMethods').value = method;
            resetPaymentForms();
            document.querySelectorAll('.payment-method').forEach(btn => {
                btn.classList.remove('active');
            });
            if (method === 'cod') {
                showCod();
            } else {
                showCard();
            }
            document.querySelector(`.payment-method[onclick="selectPaymentMethod('${method}')"]`).classList.add('active');
        }

        function showCod() {
            document.getElementById('cod-section').style.display = 'block';
            document.getElementById('card-section').style.display = 'none';
        }

        function showCard() {
            document.getElementById('cod-section').style.display = 'none';
            document.getElementById('card-section').style.display = 'block';
        }

        function resetPaymentForms() {
            document.querySelectorAll('.payment-form').forEach(form => {
                form.style.display = 'none';
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
