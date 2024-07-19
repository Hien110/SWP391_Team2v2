<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 12:36:25 AM
    Author     : ADMIN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
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
            function addNewProductType() {
                var container = document.getElementById('productTypeContainer');
                var rows = container.getElementsByClassName('product-type-row');
                if (rows.length > 0) {
                    var lastRow = rows[rows.length - 1];
                    var inputs = lastRow.getElementsByTagName('input');
                    for (var i = 0; i < inputs.length; i++) {
                        if (inputs[i].value === '') {
                            alert("Vui lòng điền đầy đủ thông tin cho loại sản phẩm hiện tại.");
                            return;
                        }
                    }
                }

                var newProductType = `
                <div class="form-group product-type-row" style="margin-bottom: 5px; width: 100%">
                    <p class="name" style="margin-left: 0px; color: #000">Số lượng sản phẩm</p>
                    <input style="margin-left: 35px; width: 34%" type="text" placeholder="Màu sắc" class="form-control" required name="colorP">
                    <input style="width: 34%" type="text" placeholder="Kích thước" class="form-control" required name="sizeP">
                    <input style="margin-right: 80px; width: 34%" type="number" placeholder="Số lượng" class="form-control" required name="quantityP">
                </div>`;
                container.insertAdjacentHTML('beforeend', newProductType);
            }

            function removeLastProductType() {
                var container = document.getElementById('productTypeContainer');
                var rows = container.getElementsByClassName('product-type-row');
                if (rows.length > 0) {
                    rows[rows.length - 1].remove();
                }
            }

            function collectProductTypes() {
                var productTypes = [];
                var container = document.getElementById('productTypeContainer');
                var rows = container.getElementsByClassName('product-type-row');
                for (var i = 0; i < rows.length; i++) {
                    var inputs = rows[i].getElementsByTagName('input');
                    var productType = {
                        color: inputs[0].value,
                        size: inputs[1].value,
                        quantityP: inputs[2].valueAsNumber
                    };
                    productTypes.push(productType);
                }
                console.log(productTypes); // You can further process or send this data
            }
        </script>
    </head>
    <body>
        <form action="./createproduct" enctype="multipart/form-data" method="post" onsubmit="return validateForm()">
            <div class="full">
                <div class="container-fluid container">
                    <div class="row">
                        <jsp:include page="/include/navbarshop.jsp"/>
                        <div class="col-md-9 content">
                            <h2 style="font-size: 24px; border-bottom: 2px solid #ddd; padding-bottom: 10px; margin: 8px 0 20px 0">Cập nhập sản phẩm</h2>
                            <div class="profile-info">
                                <div class="profile-details" style="margin-top: 0; gap: 4px;">

                                    <div class="form-group" style="margin-bottom: 5px;">
                                        <p class="name" style="margin-left: 0px; color: #000">Tên sản phẩm</p>
                                        <input type="text" class="form-control" required name="nameP" value="${requestScope.product.productName == null ? param.nameP : requestScope.product.productName}">
                                    </div>
                                    <div class="form-group" style="margin-bottom: 5px;">
                                        <p class="name" style="margin-left: 0px; color: #000">Mô tả sản phẩm</p>
                                        <textarea class="form-control" required name="descP">${requestScope.product.description == null ? param.descP : requestScope.product.description}</textarea>
                                    </div>
                                    <div class="form-group" style="margin-bottom: 5px;">
                                        <p class="name" style="margin-left: 0px; color: #000">Giá sản phẩm</p>
                                        <input type="number" class="form-control" required name="priceP" value="${requestScope.product.price == null ? param.priceP : requestScope.product.price}">
                                    </div>
                                    <div class="form-group" style="margin-bottom: 5px;">
                                        <p class="name" style="margin-left: 0px; color: #000">Loại sản phẩm</p>
                                        <input type="text" class="form-control" required name="typeP" value="${requestScope.product.typename == null ? param.typeP : requestScope.product.typename}">
                                    </div>
                                    <div id="productTypeContainer" style="margin-left: 95px">
                                        <div class="form-group product-type-row" style="margin-bottom: 5px; width: 100%">
                                            <p class="name" style="margin-left: 0px; color: #000">Số lượng sản phẩm</p>
                                            <input style="margin-left: 35px; width: 34%" type="text" placeholder="Màu sắc" class="form-control" required name="colorP" value="${param.colorP}">
                                            <input style="width: 34%" type="text" placeholder="Kích thước" class="form-control" required name="sizeP" value="${param.sizeP}">
                                            <input style="margin-right: 80px; width: 34%" type="number" placeholder="Số lượng" class="form-control" required name="quantityP" value="${param.quantityP}">
                                        </div>
                                    </div>
                                    <div class="form-group" style="margin-bottom: 5px;">
                                        <p class="name" style="margin-left: 0px; color: #000; width: 185px"></p>
                                        <button type="button" class="btn btn-primary" onclick="addNewProductType()">Thêm Loại</button>
                                        <button type="button" class="btn btn-danger" onclick="removeLastProductType()">Xóa Loại</button>
                                    </div>
                                    <div class="form-group" style="margin-bottom: 5px;">
                                        <p class="name" style="margin-left: 0px; color: #000; width: 185px">Ảnh sản phẩm</p>
                                        <div  class="col">
                                            <c:forEach var="imageUrl" items="${image}" >
                                                <img style="height: 100px; width: 100px" src="${imageUrl}" class="img-fluid col-md-3" alt="Image">
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <p style="color: green; font-weight: 400">${requestScope.success}</p>
                                    <p style="color: red; font-weight: 400">${requestScope.error}</p>
                                    <button type="submit" style="background-color: none; margin-top: 20px" class="btn delete mt-3" onclick="collectProductTypes()">Cập nhập</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </form>
        <style>
            .color-button.selected {
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

            .color-button:hover,
            .size-button:hover {
                border: 1px solid #000; /* Màu nền khi nút được chọn */
                background-color: lightblue;
            }
        </style>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
