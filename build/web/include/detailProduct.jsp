<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Product product = (Product) request.getAttribute("product");
    List<String> availableSizes = (List<String>) request.getAttribute("availableSizes");
    List<String> availableColors = (List<String>) request.getAttribute("availableColors");
    List<String> availableImages = (List<String>) request.getAttribute("availableImages");
    String userId = (String) request.getAttribute("userId");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Product Detail</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome CDN -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detailProduct.css">
        <style>
            /* CSS tùy chỉnh cho nút mũi tên */
            .carousel-control-prev-icon,
            .carousel-control-next-icon {
                background-color: black; /* Đổi màu thành đen */
                width: 50px; /* Tăng kích thước */
                height: 50px; /* Tăng kích thước */
            }

            .carousel-control-prev,
            .carousel-control-next {
                width: 5%; /* Đảm bảo nút mũi tên không chiếm quá nhiều diện tích */
            }
            .cancel{
                background-color: #fff !important;
                color: #000 !important;
            }

            .cancel:hover{
                background-color:  #000 !important;
                color: #fff !important;
                transition: 0.3s !important;
            }

            .report{
                background-color: #fff !important;
                color: #2a8341 !important;
                border: 1px solid #2a8341 !important;
            }

            .report:hover{
                background-color: #2a8341!important;
                color: #fff!important;
                transition: 0.3s !important;
            }
        </style>
    </head>
    <body>
        <div class="container mt-4">
            <!-- Display messages -->
            <c:if test="${not empty message}">
                <div class="alert alert-${messageType == 'success' ? 'success' : 'danger'} alert-dismissible fade show" role="alert">
                    ${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <!-- Product Details Section -->
            <div class="row">
                <div class="col-md-6">
                    <div id="carouselExample" class="carousel slide">
                        <div class="carousel-inner">
                            <c:forEach var="image" items="${availableImages}" varStatus="status">
                                <div class="carousel-item ${status.first ? 'active' : ''}">
                                    <c:if test="${status.count <= 4}">
                                        <img src="${image}" class="d-block w-100" alt="..." style="min-height: 600px; max-height: 600px">
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>

                    <div class="d-flex mt-3 thumbnail-images">
                        <c:forEach var="image" items="${availableImages}" varStatus="status">
                            <c:if test="${status.count <= 4}">
                                <img src="${image}" class="img-thumbnail me-2" alt="Thumbnail ${status.count}">
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-6">
                    <h1 style="color: #2a8341">${product.getProductName()}</h1>
                    <div class="d-flex align-items-center mb-2 star-rating">
                        <a href="${pageContext.request.contextPath}/evaluate?productid=${product.getProductId()}" class="ms-2">Đánh Giá ${product.getAverageStar()} <i class="fa fa-star" style="margin: 0"></i>  &nbsp|  </a> &nbsp
                        <a href="${pageContext.request.contextPath}/evaluate?productid=${product.getProductId()}"> Đã Bán ${requestScope.countOrder}</a>
                        <c:if test="${sessionScope.user.roleid != 1 }">
                            <form method="post">
                                <button class="report-btn" type="button" data-bs-toggle="modal" data-bs-target="#reportModal" style="border: none; background-color: #fff">| Báo cáo</button>
                            </form>
                        </c:if>
                    </div>
                    <h3 style="color: #2a8341">${product.getPrice()} vnđ</h3>
                    <div class="mb-3">
                        <h5>Chính Sách Trả Hàng</h5>
                        <p>Trả hàng 15 ngày | Đổi ý miễn phí</p>
                    </div>
                    <div class="mb-3">
                        <h5>Vận Chuyển</h5>
                        <p>Vận Chuyển Từ ${requestScope.shop.address}</p>
                        <p>Phí Vận Chuyển: Không hỗ trợ</p>
                    </div>
                    <div class="mb-3">
                        <h5>Màu</h5>
                        <div class="btn-group" role="group">
                            <% if (availableColors != null) {
                        for (String color : availableColors) { %>
                            <input type="radio" class="btn-check"  name="color" id="color_<%= color %>" value="<%= color %>" autocomplete="off">
                            <label class="btn btn-outline-primary" for="color_<%= color %>"><%= color %></label>
                            <% } } %>
                        </div>
                    </div>
                    <div class="mb-3">
                        <h5>Kích thước</h5>
                        <div class="btn-group" role="group">
                            <% if (availableSizes != null) {
                        for (String size : availableSizes) { %>
                            <input type="radio" class="btn-check"  name="size" id="size_<%= size %>" value="<%= size %>" autocomplete="off">
                            <label class="btn btn-outline-primary" for="size_<%= size %>"><%= size %></label>
                            <% } } %>
                        </div>
                    </div>

                    <div class="mb-3">
                        <h5>Số Lượng</h5>
                        <div class="input-group quantity-input-group" style="width: 120px;">
                            <button class="btn btn-outline-secondary" type="button" id="button-minus" style="border-radius: 10px 0px 0px 10px; border-right: 2px solid">-</button>
                            <input type="text" class="form-control" value="1" id="quantity-input">
                            <button class="btn btn-outline-secondary" type="button" id="button-plus" style="border-radius: 0px 10px 10px 0px; border-left: 2px solid">+</button>
                        </div>
                        <small id="available-quantity">${product.getQuantityp()} sản phẩm có sẵn</small>
                    </div>
                    <c:if test="${sessionScope.user.roleid != 1 }">
                        <c:if test="${product.getQuantityp() > 0}">
                            <div class="d-flex">
                                <form id="add-to-cart-form" onsubmit="return validateColorSelection()" action="./Cart" method="post">
                                    <input type="hidden" name="userId" value="${userId}">
                                    <input type="hidden" name="productId" value="${product.getProductId()}">
                                    <input type="hidden" name="size" id="size-hidden" value="">
                                    <input type="hidden" name="color" id="color-hidden" value="">
                                    <input type="hidden" name="quantity" id="quantity-input-hidden" value="1">
                                    <button type="button" class="btn btn-outline-primary me-3" onclick="addToCart()">
                                        <i class="fa fa-shopping-cart"></i> Thêm Vào Giỏ Hàng
                                    </button>
                                </form> 
                                <button class="btn btn-success" onclick="buyNow()">
                                    <i class="fa fa-bolt"></i> Mua Ngay
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${product.getQuantityp() == 0}">
                            <div style="color: red">Sản phẩm đã hết hàng</div>
                        </c:if>
                    </c:if>
                </div>
            </div>

            <div class="container mt-5">
                <!-- Shop Info Section -->
                <div class="d-flex justify-content-between align-items-center bg-white shadow-sm rounded p-3 mb-4">
                    <div class="shop-info">
                        <img src="${requestScope.user.getImgavt()}" alt="Shop Logo" class="shop-logo rounded-circle" style="width: 60px; height: 60px; margin: 0">
                        <div class="shop-details ms-3">
                            <a><h5 class="mb-0">${product.getShopName()}</h5></a>
                        </div>
                    </div>
                    <div class="d-flex flex-grow-1 flex-column align-items-center">
                        <div class="d-flex justify-content-around w-100">
                            <div class="shop-stats text-center">
                                <p>Đánh Giá</p>
                                <p class="value">${requestScope.countEva}</p>
                            </div>
                            <div class="shop-stats text-center">
                                <p>Số điện thoại</p>
                                <p class="value">${requestScope.user.phonenumber}</p>
                            </div>
                            <div class="shop-stats text-center">
                                <p>Tham Gia</p>
                                <p class="value">24 tháng trước</p>
                            </div>
                        </div>
                        <div class="d-flex justify-content-around w-100 mt-3">
                            <div class="shop-stats text-center">
                                <p>Sản Phẩm</p>
                                <p class="value">${requestScope.listP.size()}</p>
                            </div>
                            <div class="shop-stats text-center">
                                <p>Sản phẩm đã bán</p>
                                <p class="value">${requestScope.countOrderShop}</p>
                            </div>
                            <div class="shop-stats text-center">
                                <p>Người Theo Dõi</p>
                                <p class="value">${requestScope.countWishList}</p>
                            </div>
                        </div>
                    </div>
                    <div class="shop-buttons text-end">
                        <a href="shopdetail?shopid=${product.getShopId()}">
                            <button class="btn btn-outline-danger"><i class="fa fa-store"></i>Xem Shop</button>
                        </a>
                    </div>
                </div>
            </div>

            <!-- Product Details Section -->
            <div class="row my-4">
                <div class="col">
                    <h4>CHI TIẾT SẢN PHẨM</h4>
                    <ul>
                        <li>Danh Mục: ${product.getTypename()} -> ${product.getProductName()}</li>
                        <li>Hạn bảo hành: 3 tháng</li>
                        <li>Loại bảo hành: Bảo hành nhà sản xuất</li>
                        <li>Số sản phẩm còn lại: ${product.getQuantityp()}</li>
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
            <div style="top:200px" class="modal-dialog">
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
                                    <option value="Sản phẩm sản phẩm không rõ ảnh">Sản phẩm sản phẩm không rõ ảnh</option>
                                    <option value="Sản phẩm không giống mô tả">Sản phẩm không giống mô tả</option>
                                    <option value="Sản phẩm là hàng bị cấm thương mại">Sản phẩm là hàng bị cấm thương mại</option>
                                    <option value="Tên sản phẩm không phù hợp">Tên sản phẩm không phù hợp</option>
                                    <option value="Khác">Khác</option>
                                </select>
                            </div>
                            <div class="mb-3 d-none" id="otherReasonDiv">
                                <label for="otherReason" class="form-label">Lý do khác</label>
                                <input type="text" class="form-control" id="otherReason" name="otherReason">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary cancel" data-bs-dismiss="modal">Đóng</button>
                            <button type="submit" class="btn btn-primary report">Gửi Báo Cáo</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Success Modal -->
        <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true" style="background-color: rgba(0,0,0,0.5)">
            <div style="top: 200px" class="modal-dialog">
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
                                        const endDate = "2024-07-31T23:59:59";
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
                                        var availableQuantity = parseInt(document.getElementById('available-quantity').textContent);
                                        if (currentValue < availableQuantity) {
                                            quantity.value = currentValue + 1;
                                            document.getElementById('quantity-input-hidden').value = quantity.value;
                                        } else {
                                            alert("Sản phẩm không đủ");
                                        }
                                    });
                                    document.querySelectorAll('input[name="color"]').forEach((input) => {
                                        input.addEventListener('change', function () {
                                            document.getElementById('color-hidden').value = this.value;
                                        });
                                    });
                                    document.querySelectorAll('input[name="size"]').forEach((input) => {
                                        input.addEventListener('change', function () {
                                            document.getElementById('size-hidden').value = this.value;
                                        });
                                    });
//                            document.getElementById('size').addEventListener('change', function () {
//                                document.getElementById('size-hidden').value = this.value;
//                            });
                                    function validateColorSelection() {
                                        var selectedColor = document.getElementById('color-hidden').value;
                                        if (selectedColor === "") {
                                            alert("Hãy chọn màu sắc trước khi thêm vào giỏ hàng.");
                                            return false;
                                        }
                                        return true;
                                    }
                                    function validateSizeSelection() {
                                        var selectedSize = document.getElementById('size-hidden').value;
                                        if (selectedSize === "") {
                                            alert("Hãy chọn kích thước trước khi thêm vào giỏ hàng.");
                                            return false;
                                        }
                                        return true;
                                    }
                                    function addToCart() {
                                        if (validateColorSelection() && validateSizeSelection()) {
                                            document.getElementById('add-to-cart-form').submit();
                                        }
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
                                        var shopName = " ${product.getShopName()}"
                                        var productId = "${product.getProductId()}";
                                        var userId = "${userId}";
                                        if (color === "") {
                                            alert("Hãy chọn màu sắc trước khi mua.");
                                            return;
                                        } else if (size === "") {
                                            alert("Hãy chọn kích thước trước khi mua.");
                                            return;
                                        }
                                        var url = "${pageContext.request.contextPath}/order?productName=" + encodeURIComponent(productName) + "&image=" + encodeURIComponent(image) + "&price=" + encodeURIComponent(price) + "&quantity=" + encodeURIComponent(quantity) + "&description=" + encodeURIComponent(description) + "&size=" + encodeURIComponent(size) + "&color=" + encodeURIComponent(color) + "&shopId=" + encodeURIComponent(shopId) + "&shopName=" + encodeURIComponent(shopName) + "&productId=" + encodeURIComponent(productId) + "&userId=" + encodeURIComponent(userId);
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
