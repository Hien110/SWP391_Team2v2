<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detailProduct.css">
    </head>
    <body>
        <div class="container mt-4">
            <!-- Product Details Section -->
            <div class="row">
                <div class="col-md-6">
                    <img src="${param.image}" class="img-fluid product-main-image" alt="Product Image">
                    <div class="d-flex mt-3 thumbnail-images">
                        <img src="${param.image}" class="img-thumbnail me-2" alt="Thumbnail 1">
                        <img src="${param.image}" class="img-thumbnail me-2" alt="Thumbnail 2">
                        <img src="${param.image}" class="img-thumbnail me-2" alt="Thumbnail 3">
                        <img src="${param.image}" class="img-thumbnail" alt="Thumbnail 4">
                    </div>
                </div>
                <div class="col-md-6">
                    <h1>${param.productName}</h1>
                    <div class="d-flex align-items-center mb-2 star-rating">
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star inactive"></i>
                        <a href="evaluate?productid=${param.productid}" class="ms-2">${param.reviews} Đánh Giá</a>
                        <a href="evaluate?productid=${param.productid}">| ${param.sold} Đã Bán</a>
                        <a href="reportProduct.jsp?productid=${param.productid} ">| ${param.sold} Báo cáo</a>
                    </div>
                    <div class="bg-success text-white py-2 px-3 mb-3 d-inline-block">
                        <h2 class="mb-0">${param.price}₫</h2>
                        <small class="text-decoration-line-through">₫2.512.620</small>
                        <span class="badge bg-warning text-dark ms-2">29% GIẢM</span>
                    </div>
                    <div class="bg-light text-dark py-2 px-3 mb-3 d-inline-block">
                        <h5 class="mb-0">Flash Sale</h5>
                        <!-- Countdown Timer -->
                        <div class="countdown d-flex">
                            <div>
                                <h4 id="days">0</h4>
                                <small>Ngày</small>
                            </div>
                            <div>
                                <h4 id="hours">0</h4>
                                <small>Giờ</small>
                            </div>
                            <div>
                                <h4 id="minutes">0</h4>
                                <small>Phút</small>
                            </div>
                            <div>
                                <h4 id="seconds">0</h4>
                                <small>Giây</small>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <h5>Chính Sách Trả Hàng</h5>
                        <p>Trả hàng 15 ngày | Đổi ý miễn phí</p>
                    </div>
                    <div class="mb-3">
                        <h5>Vận Chuyển</h5>
                        <p>Vận Chuyển Từ Nước ngoài tới Phường Hòa Hải, Quận Ngũ Hành Sơn</p>
                        <p>Phí Vận Chuyển: Không hỗ trợ</p>
                    </div>
                    <div class="mb-3">
                        <h5>Mô Hình</h5>
                        <div class="btn-group" role="group">
                            <input type="radio" class="btn-check" name="model" id="model1" autocomplete="off">
                            <label class="btn btn-outline-primary" for="model1">Áo xanh</label>

                            <input type="radio" class="btn-check" name="model" id="model2" autocomplete="off">
                            <label class="btn btn-outline-primary" for="model2">Áo gile</label>

                            <input type="radio" class="btn-check" name="model" id="model3" autocomplete="off">
                            <label class="btn btn-outline-primary" for="model3">Áo trắng</label>

                            <input type="radio" class="btn-check" name="model" id="model4" autocomplete="off">
                            <label class="btn btn-outline-primary" for="model4">Áo đen</label>

                            <input type="radio" class="btn-check" name="model" id="model5" autocomplete="off">
                            <label class="btn btn-outline-primary" for="model5">Áo đỏ</label>
                        </div>
                    </div>
                    <div class="mb-3" style="padding-left: 10px">
                        <h5>Số Lượng</h5>
                        <div class="input-group quantity-input-group" style="width: 120px;">
                            <button class="btn btn-outline-secondary" type="button" id="button-minus" style="border-radius: 10px 0px 0px 10px; border-right: 2px solid">-</button>
                            <input type="text" class="form-control" value="1" id="quantity-input">
                            <button class="btn btn-outline-secondary" type="button" id="button-plus" style="border-radius: 0px 10px 10px 0px; border-left: 2px solid">+</button>
                        </div>
                        <small>55 sản phẩm có sẵn</small>
                    </div>
                    <div class="d-flex">
                        <form action="${pageContext.request.contextPath}/ordertracking" method="post">
                            <input type="hidden" name="cartId" value="${param.cartId}">
                            <input type="hidden" name="productId" value="${param.productid}">
                            <input type="hidden" name="userId" value="${param.userId}">
                            <input type="hidden" name="quantity" id="quantity-input-hidden" value="1">
                            <button type="submit" class="btn btn-outline-primary me-3">
                                <i class="fa fa-shopping-cart"></i> Thêm Vào Giỏ Hàng
                            </button>
                        </form>

                        <button class="btn btn-success" onclick="buyNow()">
                            <i class="fa fa-bolt"></i> Mua Ngay
                        </button>
                    </div>
                </div>
            </div>

            <div class="container mt-5">
                <!-- Shop Info Section -->
                <div class="d-flex justify-content-between align-items-center bg-white shadow-sm rounded p-3 mb-4">
                    <div class="shop-info">
                        <img src="${pageContext.request.contextPath}/images/logo1.png" alt="Shop Logo" class="shop-logo rounded-circle" style="width: 60px; height: 60px;">
                        <div class="shop-details ms-3">
                            <h5 class="mb-0">headset</h5>
                            <p class="text-muted">Online 5 Giờ Trước</p>
                        </div>
                    </div>
                    <div class="d-flex flex-grow-1 flex-column align-items-center">
                        <div class="d-flex justify-content-around w-100">
                            <div class="shop-stats text-center">
                                <p>Đánh Giá</p>
                                <p class="value">533</p>
                            </div>
                            <div class="shop-stats text-center">
                                <p>Tỉ Lệ Phản Hồi</p>
                                <p class="value">96%</p>
                            </div>
                            <div class="shop-stats text-center">
                                <p>Tham Gia</p>
                                <p class="value">24 tháng trước</p>
                            </div>
                        </div>
                        <div class="d-flex justify-content-around w-100 mt-3">
                            <div class="shop-stats text-center">
                                <p>Sản Phẩm</p>
                                <p class="value">359</p>
                            </div>
                            <div class="shop-stats text-center">
                                <p>Thời Gian Phản Hồi</p>
                                <p class="value">trong vài giờ</p>
                            </div>
                            <div class="shop-stats text-center">
                                <p>Người Theo Dõi</p>
                                <p class="value">1,6k</p>
                            </div>
                        </div>
                    </div>
                    <div class="shop-buttons text-end">
                        <button class="btn btn-outline-danger btn-custom mb-2"><i class="fa fa-comment"></i> Chat Ngay</button>
                        <button class="btn btn-outline-secondary"><i class="fa fa-store"></i> Xem Shop</button>
                    </div>
                </div>
            </div>

            <!-- Product Details Section -->
            <div class="row my-4">
                <div class="col">
                    <h4>CHI TIẾT SẢN PHẨM</h4>
                    <ul>
                        <li>Danh Mục: Headsteal > Áo Gile > Nam > Khác</li>
                        <li>Hạn bảo hành: 3 tháng</li>
                        <li>Loại bảo hành: Bảo hành nhà sản xuất</li>
                        <li>Số lượng hàng khuyến mãi: 55</li>
                        <li>Số sản phẩm còn lại: 38752</li>
                        <li>Gửi từ: Nước ngoài</li>
                    </ul>
                </div>
            </div>

            <!-- Product Description Section -->
            <div class="row my-4">
                <div class="col">
                    <h4>MÔ TẢ SẢN PHẨM</h4>
                    <p>🎉 ${param.description}</p>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.min.js"></script>
        <script>
                            function calculateTimeLeft(endDate) {
                                const difference = new Date(endDate).getTime() - new Date().getTime();
                                let timeLeft = {};

                                if (difference > 0) {
                                    timeLeft = {
                                        days: Math.floor(difference / (1000 * 60 * 60 * 24)),
                                        hours: Math.floor((difference / (1000 * 60 * 60)) % 24),
                                        minutes: Math.floor((difference / 1000 / 60) % 60),
                                        seconds: Math.floor((difference / 1000) % 60),
                                    };
                                } else {
                                    timeLeft = {days: 0, hours: 0, minutes: 0, seconds: 0};
                                }

                                return timeLeft;
                            }

                            function updateCountdown(endDate) {
                                const timeLeft = calculateTimeLeft(endDate);
                                document.getElementById("days").innerText = timeLeft.days;
                                document.getElementById("hours").innerText = timeLeft.hours;
                                document.getElementById("minutes").innerText = timeLeft.minutes;
                                document.getElementById("seconds").innerText = timeLeft.seconds;
                            }

                            window.onload = function () {
                                const endDate = "2024-07-31T23:59:59"; // Set your end date here
                                updateCountdown(endDate);

                                setInterval(function () {
                                    updateCountdown(endDate);
                                }, 1000);
                            };

                            document.getElementById('button-minus').addEventListener('click', function () {
                                var quantity = document.getElementById('quantity-input');
                                var currentValue = parseInt(quantity.value);
                                if (currentValue > 1) {
                                    quantity.value = currentValue - 1;
                                    document.getElementById('quantity-input-hidden').value = quantity.value;
                                }
                            });

                            document.getElementById('button-plus').addEventListener('click', function () {
                                var quantity = document.getElementById('quantity-input');
                                var currentValue = parseInt(quantity.value);
                                quantity.value = currentValue + 1;
                                document.getElementById('quantity-input-hidden').value = quantity.value;
                            });

                            function buyNow() {
                                var quantity = document.getElementById('quantity-input').value;
                                var productName = "${param.productName}";
                                var image = "${param.image}";
                                var price = "${param.price}";
                                var description = "${param.description}";
                                var url = "${pageContext.request.contextPath}/orderForm.jsp?productName=" + encodeURIComponent(productName) + "&image=" + encodeURIComponent(image) + "&price=" + encodeURIComponent(price) + "&quantity=" + encodeURIComponent(quantity) + "&description=" + encodeURIComponent(description);
                                window.location.href = url;
                            }
        </script>
    </body>
</html>
