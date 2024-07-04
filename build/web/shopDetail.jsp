<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${shop.shopName}</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" integrity="" crossorigin="anonymous" />
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="" crossorigin="anonymous" />
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f5f5f5;
            }

            .container1 {
                width: 80%;
                margin: auto;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .header {
                display: flex;
                align-items: center;
                border-bottom: 2px solid #f5f5f5;
                padding-bottom: 20px;
            }

            .profile-img {
                width: 100px;
                height: 100px;
                border-radius: 50%;
                margin-right: 20px;
            }

            .info {
                display: flex;
                flex: 1;
                margin-left: 50px;
            }

            .info h1 {
                margin: 0;
                font-size: 24px;
                color: #333;
            }

            .follow-btn, .report-btn {
                background-color: #f44336;
                color: white;
                border: none;
                padding: 10px 20px;
                margin-left: 10px;
                cursor: pointer;
                border-radius: 5px;
            }

            .follow-btn {
                background-color: #4CAF50;
            }

            .details {
                list-style: none;
                padding: 0;
                margin: 10px 0 0 0;
                color: #777;
            }

            .details li {
                margin-bottom: 15px;
            }

            .description {
                margin-top: 20px;
                padding: 20px;
                border-radius: 5px;
                background-color: #f9f9f9;
            }

            .description h2 {
                margin-top: 0;
                color: #333;
            }

            .products {
                margin-top: 20px;
            }

            .products h2 {
                margin-top: 0;
                color: #333;
            }

            .product-list {
                display: flex;
                gap: 20px;
                flex-wrap: wrap;
            }

            .product-item {
                width: calc(25% - 20px);
                margin-bottom: 20px;
                margin-right: 20px;
                margin-left: 40px;
                position: relative;
                overflow: hidden;
                border: 1px solid #ddd;
                border-radius: 5px;
                transition: transform 0.3s ease-out;
            }

            .product-item:hover {
                transform: translateY(-5px);
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }

            .product-item img {
                width: 100%;
                height: 200px;
                object-fit: cover;
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
            }

            .product-item .product-info {
                padding: 15px;
                background-color: #fff;
            }

            .product-item h5 {
                margin: 0;
                font-size: 18px;
                color: #333;
            }

            .product-item p {
                margin: 5px 0;
                font-size: 16px;
                color: #777;
            }

            .info1 {
                background-color: aqua;
                padding-left: 70px;
                padding-right: 70px;
                padding-top: 30px;
                padding-bottom: 30px;
                border-radius: 20px;
            }

            .info2 {
                margin-top: 40px;
                margin-left: 50px;
            }

            .avt {
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
            }

            .avt h1 {
                margin-top: 30px;
            }

            .details li i {
                margin-right: 5px;
            }

            /* Custom modal backdrop */
            #reportModal{
                background-color: rgba(0, 0, 0, 0.5);
            }

            #successModal, #followSuccessModal {
                background-color: rgba(0, 0, 0, 0.5);
            }
        </style>
    </head>
    <%@include file="include/header.jsp" %>
    <body>
        <div class="container1">
            <div class="header">
                <div class="info">
                    <div class="info1" method="post">
                        <div class="avt">
                            <img src="${shop.avt}" alt="${shop.shopName}" class="profile-img">
                            <h1>${shop.shopName}</h1>
                        </div>
                        <form method="post">
                            <input type="hidden" name="shopid" value="${shop.shopId}">
                            <c:choose>
                                <c:when test="${isFollow.shopID == 0}">
                                    <button class="follow-btn" formaction="shopfollow" type="submit">Theo dõi</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="follow-btn" formaction="cancelfollow" type="submit">Đang theo dõi</button>
                                </c:otherwise>
                            </c:choose>
                            <button class="report-btn" type="button" data-bs-toggle="modal" data-bs-target="#reportModal">Báo cáo</button>
                        </form>
                    </div>
                    <div class="info2">
                        <ul class="details">
                            <li><i class="fa-solid fa-house"></i>Sản phẩm: ${shop.totalProduct}</li>
                            <li><i class="fa-solid fa-location-pin"></i>Địa chỉ: ${shop.address}</li>
                            <li><i class="fa-solid fa-user-plus"></i>Người theo dõi: ${shop.totalFollower}</li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="description">
                <h2>Giới thiệu:</h2>
                <p>${shop.desshop}</p>
            </div>

            <div class="products">
                <h2>Sản phẩm</h2>
                <div class="product-list row">
                    <c:forEach var="product" items="${listP}">
                        <div class="product-item col-lg-3 col-md-4 col-sm-6 col-12">
                            <div class="product-thumbnail">
                                <a href="detailProduct?productId=${product.productId}">
                                    <img src="${product.image}" alt="${product.productName}">
                                </a> 
                            </div>
                            <div class="product-info">
                                <h5>${product.productName}</h5>
                                <p>${product.price}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- Report Modal -->
        <div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="reportModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="post" action="reportshop">
                        <div class="modal-header">
                            <h5 class="modal-title" id="reportModalLabel">Báo Cáo Cửa Hàng</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="shopid" value="${shop.shopId}">
                            <div class="mb-3">
                                <label for="reportReason" class="form-label">Lý do báo cáo</label>
                                <select class="form-select" id="reportReason" name="reason">
                                    <option value="Cửa hàng giả mạo">Cửa hàng giả mạo</option>
                                    <option value="Dịch vụ kém">Dịch vụ kém</option>
                                    <option value="Bán Hàng Giả">Bán Hàng Giả</option>
                                    <option value="Thời gian phản hồi chậm">Thời gian phản hồi chậm</option>
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
        <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
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

        <!-- Follow Success Modal -->
        <div class="modal fade" id="followSuccessModal" tabindex="-1" aria-labelledby="followSuccessModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="followSuccessModalLabel">Theo Dõi Thành Công</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Bạn đã theo dõi cửa hàng thành công!
                    </div>
                    <div class="modal-footer">
                        <button id="backButtonFollow" type="button" class="btn btn-success" data-bs-dismiss="modal">Quay lại</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="" crossorigin="anonymous"></script>

        <script>
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

            // Check if the follow success flag is set and show the follow success modal
            <c:if test="${not empty sessionScope.followSuccessful and sessionScope.followSuccessful == true}">
            document.addEventListener('DOMContentLoaded', function () {
                var followSuccessModal = new bootstrap.Modal(document.getElementById('followSuccessModal'));
                followSuccessModal.show();

                // Remove the followSuccessful attribute from session after 5 seconds
                setTimeout(function () {
                    fetch('removefollowsuccessful');
                }, 500);
            });
            </c:if>
        </script>
    </body>
    <%@include file="include/footer.jsp" %>   
</html>
