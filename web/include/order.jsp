<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order Form</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
                <p><b>Cao Hoàng Linh</b> (+84) 868645800</p>
                <p>84 Trần Văn Hải, Phường Hòa Hải, Quận Ngũ Hành Sơn, Đà Nẵng</p>
                <a href="#">Change</a>
            </div>

            <div class="order-summary mb-3">
                <h3>Order Summary</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead class="table-light">
                            <tr>
                                <th>Select</th>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                
                                <td class="d-flex align-items-center">
                                    <img src="${param.image}" alt="Product Image" class="img-thumbnail me-2" style="width: 50px;">
                                    <div>
                                        <p class="mb-0">${param.productName}</p>
                                        <small class="text-muted">${param.description} | <a href="#" class="text-success">Chat ngay</a></small>
                                    </div>
                                </td>
                                <td>₫${param.price}</td>
                                <td>${param.quantity}</td>
                                <td>₫${param.price * Integer.parseInt(param.quantity)}</td>
                            </tr>
                            <tr>
                                <td colspan="4" class="text-end">Voucher:</td>
                                <td>-₫5.000 <a href="#">Choose Another Voucher</a></td>
                            </tr>
                            <tr>
                                <td colspan="4" class="text-end">Shipping:</td>
                                <td>₫10.000 (Standard Express, Arrives between June 13 - June 17) <a href="#">Change</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="d-flex justify-content-between fw-bold border-top pt-3 mb-3">
                <span>Total:</span>
                <span>₫${param.price * Integer.parseInt(param.quantity) - 5000 + 10000}</span>
            </div>

            <div class="payment-section mb-3">
                <h3>Payment Method</h3>
                <div class="mb-3">
                    <button class="btn btn-outline-primary me-2" onclick="showCod()">Thanh toán khi nhận hàng</button>
                    <button class="btn btn-outline-primary" onclick="showCard()">Thẻ Tín dụng/Ghi nợ</button>
                </div>
                <div id="cod-section">
                    <p>Phí thu hộ: ₫0 VNĐ. Ưu đãi về phí vận chuyển (nếu có) áp dụng cả với phí thu hộ.</p>
                </div>
                <div id="card-section" style="display:none;">
                    <div class="container mt-3">
                        <div class="title">
                            <h4>Select a <span class="text-primary">Payment</span> method</h4>
                        </div>
                        <form action="authorize_payment" method="post">
                            <div class="row row-cols-2 g-3 mt-3">
                                <div class="col">
                                    <label for="visa" class="payment-method border rounded d-flex flex-column align-items-center p-3" onclick="showForm('visaForm', 'visa')">
                                        <input type="radio" name="payment" id="visa" class="d-none">
                                        <div class="imgContainer visa">
                                            <img src="https://i.ibb.co/vjQCN4y/Visa-Card.png" style="width: 60px" alt="" class="img-fluid">
                                        </div>
                                        <span class="name mt-2">VISA</span>
                                    </label>
                                </div>
                                <div class="col">
                                    <label for="mastercard" class="payment-method border rounded d-flex flex-column align-items-center p-3" onclick="showForm('mastercardForm', 'mastercard')">
                                        <input type="radio" name="payment" id="mastercard" class="d-none">
                                        <div class="imgContainer mastercard">
                                            <img src="https://i.ibb.co/vdbBkgT/mastercard.jpg" style="width: 60px" alt="" class="img-fluid">
                                        </div>
                                        <span class="name mt-2">Mastercard</span>
                                    </label>
                                </div>
                                <div class="col">
                                    <label for="paypal" class="payment-method border rounded d-flex flex-column align-items-center p-3" onclick="showForm('paypalForm', 'paypal')">
                                        <input type="radio" name="payment" id="paypal" class="d-none">
                                        <div class="imgContainer paypal">
                                            <img src="https://i.ibb.co/KVF3mr1/paypal.png" style="width: 60px" alt="" class="img-fluid">
                                        </div>
                                        <span class="name mt-2">Paypal</span>
                                    </label>
                                </div>
                                <div class="col">
                                    <label for="AMEX" class="payment-method border rounded d-flex flex-column align-items-center p-3" onclick="showForm('amexForm', 'amex')">
                                        <input type="radio" name="payment" id="AMEX" class="d-none">
                                        <div class="imgContainer AMEX">
                                            <img src="https://cdn.haitrieu.com/wp-content/uploads/2022/10/Logo-Napas.png" style="width: 60px" alt="" class="img-fluid">
                                        </div>
                                        <span class="name mt-2">Napas</span>
                                    </label>
                                </div>
                            </div>
                            <input type="hidden" name="product" value="${param.productName}">
                            <input type="hidden" name="subtotal" value="${param.price * Integer.parseInt(param.quantity)}">
                            <input type="hidden" name="shipping" value="10">
                            <input type="hidden" name="tax" value="10">
                            <input type="hidden" name="total" value="${param.price * Integer.parseInt(param.quantity) - 5000 + 10000}">
                            <!-- Remove Proceed with Payment Button -->
                        </form>
                        <div id="visaForm" class="payment-form mt-3">
                            <h5>VISA Payment Form</h5>
                            <div class="mb-3">
                                <label for="visaCardName" class="form-label">Tên trên thẻ</label>
                                <input type="text" id="visaCardName" class="form-control" placeholder="Tên trên thẻ">
                            </div>
                            <div class="mb-3">
                                <label for="visaCardNumber" class="form-label">Số thẻ</label>
                                <input type="text" id="visaCardNumber" class="form-control" placeholder="1234 5678 9012 3456">
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="visaExpiry" class="form-label">Ngày hết hạn</label>
                                    <input type="text" id="visaExpiry" class="form-control" placeholder="MM/YY">
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="visaCVC" class="form-label">CVC/CVV</label>
                                    <input type="text" id="visaCVC" class="form-control" placeholder="CVC">
                                </div>
                            </div>
                        </div>
                        <div id="mastercardForm" class="payment-form mt-3">
                            <h5>Mastercard Payment Form</h5>
                            <div class="mb-3">
                                <label for="mastercardCardName" class="form-label">Tên trên thẻ</label>
                                <input type="text" id="mastercardCardName" class="form-control" placeholder="Tên trên thẻ">
                            </div>
                            <div class="mb-3">
                                <label for="mastercardCardNumber" class="form-label">Số thẻ</label>
                                <input type="text" id="mastercardCardNumber" class="form-control" placeholder="1234 5678 9012 3456">
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="mastercardExpiry" class="form-label">Ngày hết hạn</label>
                                    <input type="text" id="mastercardExpiry" class="form-control" placeholder="MM/YY">
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="mastercardCVC" class="form-label">CVC/CVV</label>
                                    <input type="text" id="mastercardCVC" class="form-control" placeholder="CVC">
                                </div>
                            </div>
                        </div>
                        <div id="paypalForm" class="payment-form mt-3">
                            <form action="${initParam['posturl']}">
                                <h5>Paypal Payment Form</h5>
                                <div class="mb-3">
                                    <input type="hidden" name="upload" value="1" >
                                    <input type="hidden" name="return" value="${initParam['returnurl']}" >
                                    <input type="hidden" name="cmd" value="cart" >
                                    <input type="hidden" name="business" value="${initParam['demoShopp1@gmail.com']}" >
                                    <input type="hidden" name="address" value="84 Trần Văn Hải, Phường Hòa Hải, Quận Ngũ Hành Sơn, Đà Nẵng">
                                    <input type="hidden" name="phone" value="(+84) 868645800">
                                    <input type="hidden" name="productName" value="${param.productName}">
                                    <input type="hidden" name="price" value="${param.price}">
                                    <input type="hidden" name="quantity" value="${param.quantity}">
                                    <input type="hidden" name="total" value="${param.price * Integer.parseInt(param.quantity) - 5000 + 10000}">
                                    <div class="bg-light p-3 rounded">
                                        <div class="d-flex justify-content-between">
                                            <span>Tổng tiền hàng</span>
                                            <span>₫${param.price * Integer.parseInt(param.quantity)}</span>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <span>Phí vận chuyển</span>
                                            <span>₫10.000</span>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <span>Miễn Phí Vận Chuyển</span>
                                            <span>-₫100</span>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <span>Tổng cộng Voucher giảm giá:</span>
                                            <span>-₫5.000</span>
                                        </div>
                                        <div class="d-flex justify-content-between fw-bold border-top pt-3">
                                            <span>Tổng thanh toán</span>
                                            <span class="text-danger">₫${param.price * Integer.parseInt(param.quantity) - 5000 + 10000}</span>
                                        </div>
                                    </div>
                                    <!-- Remove Proceed with Payment Button -->
                                </div>
                            </form>
                        </div>
                        <div id="amexForm" class="payment-form mt-3">
                            <h5>Napas Payment Form</h5>
                            <div class="mb-3">
                                <label for="amexBank" class="form-label">Bank</label>
                                <select id="amexBank" class="form-select">
                                    <option>Select Bank</option>
                                    <option>BIDV</option>
                                    <option>Vietcombank</option>
                                    <option>MBBank</option>
                                    <option>Agribank</option>
                                    <option>TPBank</option>
                                    <option>Vietinbank</option>
                                    <option>Sacombank</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="amexAccountNumber" class="form-label">Account Number</label>
                                <input type="text" id="amexAccountNumber" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="amexAccountName" class="form-label">Account Name</label>
                                <input type="text" id="amexAccountName" class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Add Đặt hàng Button -->
            <form action="authorize_payment" method="post">
                <input type="hidden" name="product" value="${param.productName}">
                <input type="hidden" name="subtotal" value="${param.price * Integer.parseInt(param.quantity)}">
                <input type="hidden" name="shipping" value="10">
                <input type="hidden" name="tax" value="10">
                <input type="hidden" name="total" value="${param.price * Integer.parseInt(param.quantity) - 5000 + 10000}">
                <div class="text-end mt-4">
                    <button type="submit" class="btn btn-primary">Đặt hàng</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.min.js"></script>
    <script>
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
    </script>
</body>
</html>
