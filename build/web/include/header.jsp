<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Document</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerCSS.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
              crossorigin="anonymous" />
    </head>
    <script>
        var isDropdownVisible = false; // Biến trạng thái của dropdown

        function toggleDropdown() {
            var dropdown = document.getElementById("dropdown");
            if (!isDropdownVisible) {
                dropdown.classList.add("show"); // Hiển thị dropdown
            } else {
                dropdown.classList.remove("show"); // Ẩn dropdown
            }
            isDropdownVisible = !isDropdownVisible; // Đảo ngược trạng thái
        }

        // Đóng dropdown nếu người dùng nhấn ra ngoài dropdown
        window.onclick = function (event) {
            if (!event.target.matches('.account-button')) {
                var dropdown = document.getElementById("dropdown");
                if (isDropdownVisible) {
                    dropdown.classList.remove("show"); // Ẩn dropdown
                    isDropdownVisible = false; // Cập nhật trạng thái
                }
            }
        }
    </script>
    <body>
        <div>
            <div class="container">
                <p>Welcome to Heartsteal</p>
            </div>
            <div class="line"></div>
            <div class="container">
                <nav class="navbar navbar-expand-lg">
                    <a href="./listProduct" class="navbar-brand">
                        <img src="${pageContext.request.contextPath}/images/logo1.png" alt="Logo">
                    </a> 
                    <div class="collapse navbar-collapse">
                        <form class="d-flex search mx-auto" role="search" action="search" method="post">
                            <div class="input-group">
                                <input type="search" class="form-control" placeholder="Tìm kiếm ..." aria-label="Search" name="search">
                                <button class="btn btn-outline-success" type="submit">
                                    <i class="fa-solid fa-magnifying-glass"></i>
                                </button>
                            </div>
                        </form>
                        <div class="d-flex align-items-center">
                            <div class="user-dropdown" style="margin-right: 25px;">
                                <c:if test="${not empty sessionScope.user.username}">
                                    <button onclick="toggleDropdown()" class="account-button" style="background: none; border: none; z-index: 3">
                                        <c:if test="${not empty sessionScope.user.imgavt}">
                                            <img  style="border-radius: 50%; width: 35px; height: 35px;margin-right: 0" src="${sessionScope.user.imgavt}" alt="Avatar">
                                        </c:if>
                                        <c:if test="${empty sessionScope.user.imgavt}">
                                            <i class="fa-regular fa-circle-user size"></i>
                                        </c:if>
                                        <div class="user-links"  >
                                            <a href="">${sessionScope.user.username}</a>
                                        </div>
                                    </button>

                                    <div id="dropdown" class="dropdown-content1">
                                        <c:if test="${sessionScope.user.roleid != 1}">
                                        <a href="./updateprofileuser">Hồ sơ</a>
                                        </c:if>
                                        <c:if test="${sessionScope.user.roleid == 1}">
                                        <a href="./processApproval">Quản lí</a>
                                        </c:if>
                                        <a href="./logout">Đăng xuất</a>
                                    </div>
                                </c:if>
                                <c:if test="${empty sessionScope.user.username}">
                                    <i class="fa-regular fa-circle-user size"></i>
                                    <div class="user-links">
                                        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                                        <a href="${pageContext.request.contextPath}/signup.jsp">Đăng kí</a>
                                    </div>
                                </c:if>

                            </div>       
                            <c:if test="${sessionScope.user.roleid != 1 and sessionScope.user.roleid != null}">
                                <div class="user-dropdown position-relative" onclick="goToCart()">
                                    <i class="fa-solid fa-bag-shopping size"></i>
                                    <span class="badge-custom">${sessionScope.cartsize}</span>
                                    <div class="user-links">
                                        <a href="javascript:void(0);">Giỏ hàng</a>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="bg-color">
                <div class="container">
                    <nav class="navbar navbar-expand-lg">
                        <div class="collapse navbar-collapse">
                            <ul class="navbar-nav" style="display: flex; justify-content: space-around; width: 100%">
                                <li class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/listProduct">Trang chủ</a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link" href="#">Sản phẩm</a>
                                    <div class="dropdown-content" style="left: -512px">
                                        <ul class="level-1 row">
                                            <li class="level-1 col-sm-2">
                                                <h3 class="menu-title">Thời Trang Nam</h3>
                                                <ul class="level-2 child-box">
                                                    <li><a href="viewproductcategory?typeid=1">Áo Khoác Nam</a></li>
                                                    <li><a href="viewproductcategory?typeid=2">Áo Thun</a></li>
                                                    <li><a href="viewproductcategory?typeid=3">Áo Sơ Mi Nam</a></li>
                                                    <li><a href="viewproductcategory?typeid=4">Blazers</a></li>
                                                    <li><a href="viewproductcategory?typeid=5">Quần Jeans</a></li>
                                                    <li><a href="viewproductcategory?typeid=6">Quần Kaki</a></li>
                                                    <li><a href="viewproductcategory?typeid=7">Quần Âu Nam</a></li>
                                                    <li><a href="viewproductcategory?typeid=8">Thể Thao Nam</a></li>
                                                </ul>
                                            </li>
                                            <li class="level-1 col-sm-2">
                                                <h3 class="menu-title">Thời Trang Nữ</h3>
                                                <ul class="level-2 child-box">
                                                    <li><a href="viewproductcategory?typeid=9">Đầm Nữ</a></li>
                                                    <li><a href="viewproductcategory?typeid=10">Áo Khoác Nữ</a></li>
                                                    <li><a href="viewproductcategory?typeid=11">Áo Nữ</a></li>
                                                    <li><a href="viewproductcategory?typeid=12">Quần Nữ</a></li>
                                                    <li><a href="viewproductcategory?typeid=13">Váy</a></li>
                                                    <li><a href="viewproductcategory?typeid=14">Đồ Ngủ</a></li>
                                                    <li><a href="viewproductcategory?typeid=15">Đồ Bộ</a></li>
                                                    <li><a href="viewproductcategory?typeid=22">Giày Nữ</a></li>
                                                </ul>
                                            </li>
                                            <li class="level-1 col-sm-2">
                                                <h3 class="menu-title">Phụ Kiện</h3>
                                                <ul class="level-2 child-box">
                                                    <li><a href="viewproductcategory?typeid=16">Đồng Hồ</a></li>
                                                    <li><a href="viewproductcategory?typeid=17">Trang Sức Nữ</a></li>
                                                    <li><a href="viewproductcategory?typeid=18">Kính</a></li>
                                                    <li><a href="viewproductcategory?typeid=19">Dây Nịt</a></li>
                                                    <li><a href="viewproductcategory?typeid=20">Phụ Kiện Tóc</a></li>
                                                    <li><a href="viewproductcategory?typeid=21">Mũ</a></li>
                                                    <li><a href="viewproductcategory?typeid=23">Túi Xách Nữ</a></li>
                                                </ul>
                                            </li>
                                            <li class="level-1 col-sm-3">
                                                <h3 class="menu-title">Thời Trang Mùa Hè</h3>
                                                <ul class="level-2 child-box">
                                                    <li><a href="viewproductcategory?typeid=3">Áo Sơ Mi Nam</a></li>
                                                    <li><a href="viewproductcategory?typeid=2">Áo Thun</a></li>
                                                    <li><a href="viewproductcategory?typeid=7">Quần Âu</a></li>
                                                    <li><a href="viewproductcategory?typeid=13">Váy</a></li>
                                                    <li><a href="viewproductcategory?typeid=24">Bộ Đồ Cặp Đôi</a></li>
                                                    <li><a href="viewproductcategory?typeid=8">Thể Thao Nam</a></li>
                                                </ul>
                                            </li>

                                            <li class="level-1 col-sm-3">
                                                <div class="child-box">
                                                    <img src='//hstatic.net/744/1000088744/1000124945/meg_col_5.jpg?v=176' alt='' />
                                                </div>
                                                <ul class="level-2 child-box"></ul>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="aboutUs.jsp">Về chúng tôi</a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </body>
    <script>
        var isDropdownVisible = false; // Biến trạng thái của dropdown
        var userId = '${sessionScope.user.userid}'; // Store userId from session

        function toggleDropdown() {
            var dropdown = document.getElementById("dropdown");
            if (!isDropdownVisible) {
                dropdown.classList.add("show"); // Hiển thị dropdown
            } else {
                dropdown.classList.remove("show"); // Ẩn dropdown
            }
            isDropdownVisible = !isDropdownVisible; // Đảo ngược trạng thái
        }

        // Đóng dropdown nếu người dùng nhấn ra ngoài dropdown
        window.onclick = function (event) {
            if (!event.target.matches('.account-button')) {
                var dropdown = document.getElementById("dropdown");
                if (isDropdownVisible) {
                    dropdown.classList.remove("show"); // Ẩn dropdown
                    isDropdownVisible = false; // Cập nhật trạng thái
                }
            }
        }

        function goToCart() {
            if (userId) {
                window.location.href = '${pageContext.request.contextPath}/ListCard?userId=' + userId;
            } else {
                alert('Please log in to view your cart.');
            }
        }
    </script>
</html>
