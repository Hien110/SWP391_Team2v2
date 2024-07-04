<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Product product = (Product) request.getAttribute("product");
    List<String> availableSizes = (List<String>) request.getAttribute("availableSizes");
    List<String> availableColors = (List<String>) request.getAttribute("availableColors");
    String userId = (String) request.getAttribute("userId");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Product Detail</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detailProduct.css">
    </head>
    <body>
        <div class="container mt-4">
            <!-- Product Details Section -->
            <div class="row">
                <div class="col-md-6">
                    <img src="${product.getImage()}" class="img-fluid product-main-image" alt="Product Image">
                    <div class="d-flex mt-3 thumbnail-images">
                        <img src="${product.getImage()}" class="img-thumbnail me-2" alt="Thumbnail 1">
                        <img src="${product.getImage()}" class="img-thumbnail me-2" alt="Thumbnail 2">
                        <img src="${product.getImage()}" class="img-thumbnail me-2" alt="Thumbnail 3">
                        <img src="${product.getImage()}" class="img-thumbnail" alt="Thumbnail 4">
                    </div>
                </div>
                <div class="col-md-6">
                    <h1>${product.getProductName()}</h1>
                    <div class="d-flex align-items-center mb-2 star-rating">
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star inactive"></i>
                        <a href="${pageContext.request.contextPath}/evaluate?productid=${product.getProductId()}" class="ms-2">Đánh Giá | </a> &nbsp
                        <a href="${pageContext.request.contextPath}/evaluate?productid=${product.getProductId()}"> Đã Bán</a>
                        <form method="post">
                            <button class="report-btn" type="button" data-bs-toggle="modal" data-bs-target="#reportModal" style="border: none">| Báo cáo</button>
                        </form>

                    </div>
                    <div class="bg-success text-white py-2 px-3 mb-3 d-inline-block">
                        <h2 class="mb-0">${product.getPrice()}₫</h2>
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
                        <h5>Màu</h5>
                        <div class="btn-group" role="group">
                            <% if (availableColors != null) {
                            for (String color : availableColors) { %>
                            <input type="radio" class="btn-check" name="color" id="color_<%= color %>" value="<%= color %>" autocomplete="off">
                            <label class="btn btn-outline-primary" for="color_<%= color %>"><%= color %></label>
                            <% } } %>
                        </div>
                    </div>
                    <label for="size">Choose a size:</label>
                    <select id="size" name="size">
                        <% if (availableSizes != null) {
                        for (String size : availableSizes) { %>
                        <option value="<%= size %>"><%= size %></option>
                        <% } } %>
                    </select>
                    <div class="mb-3">
                        <h5>Số Lượng</h5>
                        <div class="input-group quantity-input-group" style="width: 120px;">
                            <button class="btn btn-outline-secondary" type="button" id="button-minus" style="border-radius: 10px 0px 0px 10px; border-right: 2px solid">-</button>
                            <input type="text" class="form-control" value="1" id="quantity-input">
                            <button class="btn btn-outline-secondary" type="button" id="button-plus" style="border-radius: 0px 10px 10px 0px; border-left: 2px solid">+</button>
                        </div>
                        <small>${product.getQuantityp()} sản phẩm có sẵn</small>
                    </div>
                    <div class="d-flex">
                        <form onsubmit="return validateColorSelection()" action="${pageContext.request.contextPath}/order" method="post">
                            <input type="hidden" name="userId" value="${userId}"> <!-- Pass userId as hidden input -->
                            <input type="hidden" name="productId" value="${product.getProductId()}"> <!-- Pass productId as hidden input -->
                            <input type="hidden" name="productName" value="${product.getProductName()}">
                            <input type="hidden" name="size" id="size-hidden" value="${availableSizes.size() > 0 ? availableSizes.get(0) : ''}">
                            <input type="hidden" name="color" id="color-hidden" value="">
                            <input type="hidden" name="price" value="${product.getPrice()}">
                            <input type="hidden" name="quantity" id="quantity-input-hidden" value="1">
                            <input type="hidden" name="image" value="${product.getImage()}">
                            <input type="hidden" name="description" value="${product.getDescription()}">
                            <input type="hidden" name="shopId" value="${product.getShopId()}">
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
                            <a><h5 class="mb-0">${product.getShopName()}</h5></a>
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
                        <button class="btn btn-outline-secondary"><i class="fa fa-store"></i><a href="shopdetail?shopid=${product.getShopId()}">Xem Shop</a></button>
                    </div>
                </div>
            </div>

            <!-- Product Details Section -->
            <div class="row my-4">
                <div class="col">
                    <h4>CHI TIẾT SẢN PHẨM</h4>
                    <ul>
                        <li>Danh Mục: ${product.getTypename()} > ${product.getProductName()} > ${product.getTypename()} > Khác</li>
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
                    <p>🎉 ${product.getDescription()}</p>
                </div>
            </div>
        </div>

        <!-- Report Modal -->
        <div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="reportModalLabel" aria-hidden="true" style="background-color: rgba(0,0,0,0.5)">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="post" action="reportproduct">
                        <div class="modal-header">
                            <h5 class="modal-title" id="reportModalLabel">Báo Cáo Sản Phẩm</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="productid" value="${product.getProductId()}">
                            <div class="mb-3">
                                <label for="reportReason" class="form-label">Lý do báo cáo</label>
                                <select class="form-select" id="reportReason" name="reason">
                                    <option value="Sản Phẩm Giả">Sản Phẩm Giả</option>
                                    <option value="Sản Phẩm Nguy Hiểm">Sản Phẩm Nguy Hiểm</option>
                                    <option value="Sản phẩm không có nguồn gốc">Sản phẩm không có nguồn gốc</option>
                                    <option value="Khác">Khác</option>
                                </select>
                            </div>
                            <div class="mb-3 d-none" id="otherReasonDiv">
                                <label for="otherReason" class="form-label">Lý do khác</label>
                                <input type="text" class="form-control" id="otherReason" name="otherReason">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                            <button type="submit" class="btn btn-primary">Gửi Báo Cáo</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Success Modal -->
        <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true" style="background-color: rgba(0,0,0,0.5)" >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="successModalLabel">Báo Cáo Thành Công</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Báo cáo của bạn đã được gửi thành công!
                    </div>
                    <div class="modal-footer">
                        <button id="backButton" type="button" class="btn btn-success" data-bs-dismiss="modal">Quay lại</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="" crossorigin="anonymous"></script>
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
                            // Handle quantity change
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
                            document.querySelectorAll('input[name="color"]').forEach((input) => {
                                input.addEventListener('change', function () {
                                    document.getElementById('color-hidden').value = this.value;
                                });
                            });
                            document.getElementById('size').addEventListener('change', function () {
                                document.getElementById('size-hidden').value = this.value;
                            });
                            function validateColorSelection() {
                                var selectedColor = document.getElementById('color-hidden').value;
                                if (selectedColor === "") {
                                    alert("Please choose a color before adding to cart.");
                                    return false;
                                }
                                return true;
                            }

                            function buyNow() {
                                var quantity = document.getElementById('quantity-input').value;
                                var productName = "${product.getProductName()}";
                                var image = "${product.getImage()}";
                                var price = "${product.getPrice()}";
                                var description = "${product.getDescription()}";
                                var size = document.getElementById('size-hidden').value;
                                var color = document.getElementById('color-hidden').value;
                                var shopId = "${product.getShopId()}";
                                var productId = "${product.getProductId()}";
                                var userId = "${userId}"; // Add userId to the URL
                                if (color === "") {
                                    alert("Please choose a color before buying.");
                                    return;
                                }
                                var url = "${pageContext.request.contextPath}/order?productName=" + encodeURIComponent(productName) + "&image=" + encodeURIComponent(image) + "&price=" + encodeURIComponent(price) + "&quantity=" + encodeURIComponent(quantity) + "&description=" + encodeURIComponent(description) + "&size=" + encodeURIComponent(size) + "&color=" + encodeURIComponent(color) + "&shopId=" + encodeURIComponent(shopId) + "&productId=" + encodeURIComponent(productId) + "&userId=" + encodeURIComponent(userId);
                                window.location.href = url;
                            }

                            document.getElementById('reportReason').addEventListener('change', function () {
                                var otherReasonDiv = document.getElementById('otherReasonDiv');
                                if (this.value === 'Khác') {
                                    otherReasonDiv.classList.remove('d-none');
                                } else {
                                    otherReasonDiv.classList.add('d-none');
                                }
                            });
                            // Check if the success flag is set and show the success modal
            <c:if test="${not empty sessionScope.successful and sessionScope.successful == true}">
                            document.addEventListener('DOMContentLoaded', function () {
                                var successModal = new bootstrap.Modal(document.getElementById('successModal'));
                                successModal.show();
                                // Remove the successful attribute from session after 5 seconds
                                setTimeout(function () {
                                    fetch('removesuccessful');
                                }, 500);
                            });
            </c:if>
                            // Redirect to shopdetail servlet when the back button is clicked
                            document.getElementById('backButton').addEventListener('click', function () {
                                window.location.href = 'listProduct';
                            });
        </script>
    </body>
</html>
