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
        <div class="header">
            <div class="container">
                <p>Welcome to Heartsteal</p>
            </div>
            <div class="line"></div>
            <div class="container">
                <nav class="navbar navbar-expand-lg">
                    <a href="#" class="navbar-brand">
                        <img src="${pageContext.request.contextPath}/images/logo1.png" alt="Logo">
                    </a> 
                    <div class="collapse navbar-collapse">
                        <form class="d-flex search mx-auto" role="search">
                            <div class="input-group">
                                <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
                                <button class="btn btn-outline-success" type="submit">
                                    <i class="fa-solid fa-magnifying-glass"></i>
                                </button>
                            </div>
                        </form>
                        <div class="d-flex align-items-center">
                            <div class="user-dropdown" style="margin-right: 25px;">
                                <c:if test="${not empty sessionScope.username}">
                                    <button onclick="toggleDropdown()" class="account-button" style="background: none; border: none; z-index: 3">
                                        <i class="fa-regular fa-circle-user size" >
                                        </i>
                                    <div class="user-links"  >
                                        <a href="">${sessionScope.username}</a>
                                    </div>
                                    </button>

                                    <div id="dropdown" class="dropdown-content1">
                                        <a href="updateinformation">Hồ sơ</a>
                                        <a href="./logout">Đăng xuất</a>
                                    </div>
                                </c:if>
                                <c:if test="${empty sessionScope.username}">
                                    <i class="fa-regular fa-circle-user size"></i>
                                    <div class="user-links">
                                        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                                        <a href="${pageContext.request.contextPath}/signup.jsp">Đăng kí</a>
                                    </div>
                                </c:if>

                            </div>
                            <div class="user-dropdown position-relative">
                                <i class="fa-solid fa-bag-shopping size"></i>
                                <span class="badge-custom">2</span>
                                <div class="user-links">
                                    <a href="#">Giỏ hàng</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="bg-color">
                <div class="container">
                    <nav class="navbar navbar-expand-lg">
                        <div class="collapse navbar-collapse">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/listProduct">Trang chủ</a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link" href="#">Sản phẩm</a>
                                    <div class="dropdown-content">
                                        <ul class="level-1 row">
                                            <li class="level-1 col-sm-2">
                                                <h3 class="menu-title">Thời Trang Nam</h3>
                                                <ul class="level-2 child-box">
                                                    <li><a href="/collections/thoi-trang-nam">Áo Khoác Nam</a></li>
                                                    <li><a href="/collections/san-pham-moi">Áo Thun, Áo Polo Nam</a></li>
                                                    <li><a href="/collections/hot-products">Áo Sơ Mi Nam</a></li>
                                                    <li><a href="/collections/onsale">Blazers</a></li>
                                                    <li><a href="/collections/san-pham-moi">Quần Jeans</a></li>
                                                    <li><a href="/collections/all">Quần Kaki, Quần Âu Nam</a></li>
                                                    <li><a href="/collections/all">Thể Thao Nam</a></li>
                                                </ul>
                                            </li>
                                            <li class="level-1 col-sm-2">
                                                <h3 class="menu-title">Thời Trang Nữ</h3>
                                                <ul class="level-2 child-box">
                                                    <li><a href="/collections/thoi-trang-nu">Đầm Nữ</a></li>
                                                    <li><a href="/collections/onsale">Áo Khoác Nữ</a></li>
                                                    <li><a href="/collections/san-pham-moi">Áo Nữ</a></li>
                                                    <li><a href="/collections/onsale">Quần Nữ</a></li>
                                                    <li><a href="/collections/all">Váy</a></li>
                                                    <li><a href="/collections/all">Đồ Ngủ, Đồ Mặc Nhà</a></li>
                                                    <li><a href="/collections/all">Bộ Đồ, Jumpsuits</a></li>
                                                </ul>
                                            </li>
                                            <li class="level-1 col-sm-2">
                                                <h3 class="menu-title">Phụ Kiện</h3>
                                                <ul class="level-2 child-box">
                                                    <li><a href="/">Đồng Hồ</a></li>
                                                    <li><a href="/">Phụ Kiện Đồng Hồ</a></li>
                                                    <li><a href="/">Trang Sức Nữ</a></li>
                                                    <li><a href="/">Kính</a></li>
                                                    <li><a href="/">Dây Nịt</a></li>
                                                    <li><a href="/">Phụ Kiện Tóc</a></li>
                                                    <li><a href="/">Nón</a></li>
                                                </ul>
                                            </li>
                                            <li class="level-1 col-sm-3">
                                                <h3 class="menu-title">Thời Trang Mùa Hè</h3>
                                                <ul class="level-2 child-box">
                                                    <li><a href="/">Áo Sơ Mi Nam</a></li>
                                                    <li><a href="/">Áo Thun</a></li>
                                                    <li><a href="/">Quần Âu</a></li>
                                                    <li><a href="/">Đầm</a></li>
                                                    <li><a href="/">Giày Nữ</a></li>
                                                    <li><a href="/">Túi Xách Nữ</a></li>
                                                    <li><a href="/">Bộ Đồ Cặp Đôi</a></li>
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
                                    <a class="nav-link" href="#">Tin tức</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Về chúng tôi</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Liên hệ</a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </body>

</html>
