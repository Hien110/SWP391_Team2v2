<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 12:36:25 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profileCSS.css">
        <style>
            select {
                appearance: none;
                -webkit-appearance: none;
                -moz-appearance: none;
                border: 1px solid #ccc;
                padding: 10px;
                background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor"><path d="M6 9l6 6 6-6" /></svg>') no-repeat right 10px center; /* Mũi tên nhỏ */
                background-color: white; /* Màu nền */
                background-size: 12px; /* Kích thước của mũi tên */
            }
            .file-upload-wrapper {
                position: relative;
                overflow: hidden;
                display: inline-block;
                top: 9px;
            }
            .file-upload-input {
                position: absolute;
                left: 0;
                top: 0;
                opacity: 0;
            }
        </style>
        <script>
            function validateForm() {
                var fileInput = document.querySelector('input[type="file"]');
                if (fileInput.files.length === 0) {
                    alert("Bạn chưa chọn file ảnh sản phẩm.");
                    return false; // Ngăn không submit form
                }
                return true; // Cho phép submit form
            }
        </script>
    </head>
    <body>
        <form action="./createproduct" enctype="multipart/form-data"  method="post">
            <div class="full">
                <div class="container-fluid container">
                    <div class="row">
                        <jsp:include page="/include/navbarshop.jsp"/>
                        <div class="col-md-9 content">
                            <h2 style="font-size: 24px; border-bottom: 2px solid #ddd; padding-bottom: 10px; margin: 8px 0 20px 0">Tạo sản phẩm mới</h2>
                            <div class="profile-info">
                                <div class="profile-details" style="margin-top: 0; gap: 4px;">

                                    <div class="form-group" style=" margin-bottom: 5px; ">
                                        <p class="name" style="margin-left: 0px; color: #000">Tên sản phẩm</p>
                                        <input type="text" class="form-control" required name="nameP">
                                    </div>
                                    <div class="form-group"  style=" margin-bottom: 5px; ">
                                        <p class="name" style="margin-left: 0px;  color: #000">Mô tả sản phẩm</p>
                                        <textarea class="form-control" required name="descP"></textarea>
                                    </div>
                                    <div class="form-group" style=" margin-bottom: 5px; ">
                                        <p class="name" style="margin-left: 0px; color: #000">Giá sản phẩm</p>
                                        <input type="number" class="form-control" required name="priceP">
                                    </div>
                                    <div class="form-group" style=" margin-bottom: 5px; ">
                                        <p class="name" style="margin-left: 0px; color: #000">Số lượng trong kho</p>
                                        <input type="number" class="form-control" required name="quantityP">
                                    </div>
                                    <div class="form-group" style=" margin-bottom: 5px; ">
                                        <p class="name" style="margin-left: 0px; color: #000">Loại sản phẩm</p>
                                        <select id="productCategory" name="categoryP" class="form-control">
                                            <option value="1">Áo Khoác Nam</option>
                                            <option value="2">Áo Thun, Áo Polo Nam</option>
                                            <option value="3">Áo Sơ Mi Nam</option>
                                            <option value="4">Blazers</option>
                                            <option value="5">Quần Jeans</option>
                                            <option value="6">Quần Kaki</option>
                                            <option value="7">Quần Âu Nam</option>
                                            <option value="8">Thể Thao Nam</option>
                                            <option value="9">Đầm Nữ</option>
                                            <option value="10">Áo Khoác Nữ</option>
                                            <option value="11">Áo Nữ</option>
                                            <option value="12">Quần Nữ</option>
                                            <option value="13">Váy</option>
                                            <option value="14">Đồ Ngủ</option>
                                            <option value="15">Đồ Bộ</option>
                                            <option value="16">Đồng Hồ</option>
                                            <option value="17">Trang Sức Nữ</option>
                                            <option value="18">Kính</option>
                                            <option value="19">Dây Nịt</option>
                                            <option value="20">Phụ Kiện Tóc</option>
                                            <option value="21">Mũ</option>
                                            <option value="22">Giày Nữ</option>
                                            <option value="23">Túi Xách Nữ</option>
                                            <option value="24">Bộ Đồ Cặp Đôi</option>
                                        </select>
                                    </div>
                                    <div class="form-group" style=" margin-bottom: 5px; ">
                                        <p class="name" style="margin-left: 0px; color: #000; width: 185px">Màu sắc</p>
                                        <button style="width: 150px; background-color:  #2a8341; border: none; margin-right: 5px" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#colorModal">Chọn màu sắc</button>
                                        <input type="hidden" id="selectedColors" name="selectedColors">
                                    </div>
                                    <div class="form-group" style=" margin-bottom: -4px; ">
                                        <p class="name" style="margin-left: 0px; color: #000; width: 185px">Kích thước</p>
                                        <button style="width: 150px; background-color: #2a8341;  border: none;  margin-right: 5px" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#sizeModal">Chọn Kích thước</button>
                                        <input type="hidden" id="selectedSizes" name="selectedSizes">
                                    </div>
                                    <div class="form-group" style=" margin-bottom: 5px; ">
                                        <p class="name" style="margin-left: 0px; color: #000; width: 185px">Ảnh sản phẩm</p>
                                        <div class="file-upload-wrapper">
                                            <button class="file-upload-button" type="button" style="padding: 6px 12px; border: 1px solid #2a8341; background-color: #2a8341; border-radius: 5px; color: #fff;">Ảnh sản phẩm</button>
                                            <input class="file-upload-input" type="file" name="file" multiple/>
                                        </div >
                                    </div>
                                    <p style="color: green;font-weight: 400">${requestScope.success}</p>
                                    <p style="color: red;font-weight: 400">${requestScope.error}</p>
                                    <button type="submit" style="background-color: none; margin-top: 20px" class="btn delete mt-3">Đăng sản phẩm</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <!-- Modal -->
        <div class="modal fade" id="colorModal" tabindex="-1" aria-labelledby="colorModalLabel" aria-hidden="true" style="background-color: #0000">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="colorModalLabel">Chọn màu</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" >
                        <div class="container" style="background-color: #0000">
                            <div class="row">
                                <div class="col"><button type="button" class="btn color-button" value="Trắng" onclick="toggleColor(this)">Trắng</button></div>
                                <div class="col"><button type="button" class="btn color-button" value="Đen" onclick="toggleColor(this)">Đen</button></div>
                                <div class="col"><button type="button" class="btn color-button" value="Nâu" onclick="toggleColor(this)">Nâu</button></div>
                            </div>
                            <div class="row mt-2">
                                <div class="col"><button type="button" class="btn color-button" value="Đỏ" onclick="toggleColor(this)">Đỏ</button></div>
                                <div class="col"><button type="button" class="btn color-button" value="Vàng" onclick="toggleColor(this)">Vàng</button></div>
                                <div class="col"><button type="button" class="btn color-button" value="Xanh lá" onclick="toggleColor(this)">Xanh lá</button></div>
                            </div>
                            <div class="row mt-2">
                                <div class="col"><button type="button" class="btn color-button" value="Cam" onclick="toggleColor(this)">Cam</button></div>
                                <div class="col"><button type="button" class="btn color-button" value="Tím" onclick="toggleColor(this)">Tím</button></div>
                                <div class="col"><button type="button" class="btn color-button" value="Kem" onclick="toggleColor(this)">Kem</button></div>
                            </div>
                            <div class="row mt-2">
                                <div class="col"><button type="button" class="btn color-button" value="Hồng" onclick="toggleColor(this)">Hồng</button></div>
                                <div class="col"><button type="button" class="btn color-button" value="Bạc" onclick="toggleColor(this)">Bạc</button></div>
                                <div class="col"><button type="button" class="btn color-button" value="Xanh dương" onclick="toggleColor(this)">Xanh dương</button></div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="button" class="btn btn-primary"data-bs-dismiss="modal" onclick="saveSelectedColors()">Chọn</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Size Selection Modal -->
        <div class="modal fade" id="sizeModal" tabindex="-1" aria-labelledby="sizeModalLabel" aria-hidden="true" style="background-color: #0000">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="sizeModalLabel">Chọn kích thước</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <h6>Kích thước quần áo</h6>
                        <div class="form-group">
                            <button type="button" class="btn size-button size-button-clothes" onclick="toggleSize(this, 'clothes')">XS</button>
                            <button type="button" class="btn size-button size-button-clothes" onclick="toggleSize(this, 'clothes')">S</button>
                            <button type="button" class="btn size-button size-button-clothes" onclick="toggleSize(this, 'clothes')">M</button>
                            <button type="button" class="btn size-button size-button-clothes" onclick="toggleSize(this, 'clothes')">L</button>
                            <button type="button" class="btn size-button size-button-clothes" onclick="toggleSize(this, 'clothes')">XL</button>
                            <button type="button" class="btn size-button size-button-clothes" onclick="toggleSize(this, 'clothes')">XXL</button>
                            <button type="button" class="btn size-button size-button-clothes" onclick="toggleSize(this, 'clothes')">XXXL</button>
                        </div>
                        <h6>Kích thước giày dép</h6>
                        <div class="form-group">
                            <button type="button" class="btn size-button size-button-shoes" onclick="toggleSize(this, 'shoes')">37</button>
                            <button type="button" class="btn size-button size-button-shoes" onclick="toggleSize(this, 'shoes')">38</button>
                            <button type="button" class="btn size-button size-button-shoes" onclick="toggleSize(this, 'shoes')">39</button>
                            <button type="button" class="btn size-button size-button-shoes" onclick="toggleSize(this, 'shoes')">40</button>
                            <button type="button" class="btn size-button size-button-shoes" onclick="toggleSize(this, 'shoes')">41</button>
                            <button type="button" class="btn size-button size-button-shoes" onclick="toggleSize(this, 'shoes')">42</button>
                            <button type="button" class="btn size-button size-button-shoes" onclick="toggleSize(this, 'shoes')">43</button>
                            <button type="button" class="btn size-button size-button-shoes" onclick="toggleSize(this, 'shoes')">44</button>
                            <button type="button" class="btn size-button size-button-shoes" onclick="toggleSize(this, 'shoes')">45</button>
                            <button type="button" class="btn size-button size-button-shoes" onclick="toggleSize(this, 'shoes')">46</button>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="saveSelectedSizes()">Chọn</button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function toggleSize(button, type) {
                // Hủy chọn tất cả các nút kích thước của loại khác
                if (type === 'shoes') {
                    const clothesButtons = document.querySelectorAll('.size-button-clothes');
                    clothesButtons.forEach(btn => btn.classList.remove('selected'));
                }
                if (type === 'clothes') {
                    const shoeButtons = document.querySelectorAll('.size-button-shoes');
                    shoeButtons.forEach(btn => btn.classList.remove('selected'));
                }

                // Chọn hoặc bỏ chọn nút hiện tại
                button.classList.toggle('selected');
            }

            function saveSelectedSizes() {
                const selectedButtons = document.querySelectorAll('.size-button.selected');
                const selectedSizes = Array.from(selectedButtons).map(button => button.textContent.trim());
                document.getElementById('selectedSizes').value = selectedSizes.join(', ');
                console.log(document.getElementById('selectedSizes').value);
            }

            function toggleColor(button) {
                if (button.classList.contains('selected')) {
                    // Nếu đã được chọn, bỏ chọn
                    button.classList.remove('selected');
                    button.style.backgroundColor = ''; // Xóa màu nền khi bỏ chọn
                    // Xóa giá trị khỏi input ẩn
                    removeColor(button.value);
                } else {
                    // Nếu chưa được chọn, chọn
                    button.classList.add('selected');
                    button.style.backgroundColor = 'lightblue'; // Đổi màu nền khi chọn
                    // Lưu giá trị vào input ẩn
                    saveColor(button.value);
                }
            }
            function saveColor() {
                const selectedButtons = document.querySelectorAll('.color-button.selected');
                const selectedcolors = Array.from(selectedButtons).map(button => button.textContent.trim());
                document.getElementById('selectedColors').value = selectedcolors.join(', ');
                console.log(document.getElementById('selectedColors').value);
            }

            function removeColor(color) {
                // Xóa giá trị màu khỏi input ẩn
                let selectedColors = document.getElementById('selectedColors');
                let currentColors = selectedColors.value.split(',').map(c => c.trim());
                let index = currentColors.indexOf(color);
                if (index !== -1) {
                    currentColors.splice(index, 1);
                }
                selectedColors.value = currentColors.join(', ');
            }

            function saveSelectedColors() {
                // Function này để lưu màu đã chọn, bạn có thể không cần thiết nếu chỉ cần lấy một lần khi chọn màu
            }

            function validateForm() {
                var fileInput = document.querySelector('input[type="file"]');
                if (fileInput.files.length === 0) {
                    alert("Bạn chưa chọn file ảnh sản phẩm.");
                    return false; // Ngăn không submit form
                }
                return true; // Cho phép submit form
            }
        </script>

        <style>
            .color-button.selected{
                background-color: lightblue; /* Màu nền khi nút được chọn */

            }
            .size-button.selected {
                background-color: lightblue; /* Màu nền khi nút được chọn */

            }
            .color-button {
                border: 1px solid #000; /* Màu nền khi nút được chọn */
                width: 120px;
            }

            .size-button {
                border: 1px solid #000; /* Màu nền khi nút được chọn */
                width: 70px;
            }
            .color-button:hover,  .size-button:hover{
                border: 1px solid #000; /* Màu nền khi nút được chọn */
                background-color: lightblue;
            }
        </style>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
