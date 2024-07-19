<%@ page import="model.ProductShop" %>
<%@include file="include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>List Product Shop Owner</title>
        <style>
            .delete{
                width: 50px !important;
                padding: 7px 3px !important;
            }

            .cancel{
                background-color: #fff !important;
                color: #565e64 !important;
                border-radius: 10px;
            }

            .update{
                padding: 6px 12px !important;
            }
            .cancel:hover{
                background-color: #565e64 !important;
                color: #fff !important;
            }
            table, th, td{
                border-bottom: 1px solid #000 !important;
            }

            .table{
                border: 1px solid;
            }
            .table-wrapper {
                margin: 30px auto;
                background: #fff;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0,0,0,.15);
                border-radius: 8px;
            }
            .table-title {
                padding-bottom: 15px;
                background: #2a8341;
                color: #fff;
                padding: 15px;
                border-radius: 8px 8px 0 0;
            }
            .table-title h2 {
                margin: 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #fff;
                background: #2a8341;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                background: #218838; /* Màu xanh lá cây đậm hơn */
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child {
                width: 150px;
            }
            table.table td a {
                color: #28a745;
                display: inline-block;
                margin: 0 5px;
            }
            table.table td a:hover {
                color: #218838;
            }
            table.table td a.btn {
                color: #fff;
                background: #2a8341;
                border: none;
                border-radius: 2px;
                min-width: 50px;
            }
            table.table td a.btn:hover {
                background: #218838;
            }

            .btn-primary{
                border-radius: 10px;
                padding: 7px 25px;
                color: #2a8341;
                background-color: #fff;
                border: 1px solid #2a8341;
                cursor: pointer;
                margin-right: 5px;
                text-decoration: none;
                display: inline-block;
                margin-bottom: 7px;
            }

            .btn-primary:hover{
                background-color: #2a8341;
                color: #fff;
                border: 1px solid #2a8341;
            }

            .btn-danger{
                border-radius: 10px;
                padding: 7px 20px;
                color: #ff0000;
                background-color: #fff;
                border: 1px solid #ff0000;
                cursor: pointer;
                margin-right: 5px;
                text-decoration: none;
                display: inline-block;
                margin-bottom: 5px;
            }
            .back-button {
                border-radius: 10px;
                background-color: #fff;
                padding: 10px 10px 10px 0px;
                font-size: 16px;
                cursor: pointer;
                color: #28a745;
                margin-top: 5px;
                margin-bottom: 40px;
                border:1px solid #28a745;
            }

            .back-button:hover {
                background-color: #28a745;
                color: #fff;
                border:1px solid #28a745;
                transition: 0.3s;
            }
            .container1 {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Sản phẩm của cửa hàng</h2>
                    </div>
                </div>
            </div>
            <p style="color: red; padding-top: 10px; font-weight: 400; text-align: center">${sessionScope.errorPrice}</p>
            <p style="color: red; padding-top: 10px; font-weight: 400; text-align: center">${sessionScope.updateSuccess}</p>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Tên sản phẩm</th>
                        <th>Hình ảnh</th>
                        <th>Giá</th>
                        <th>Mô tả</th>
                        <th>Màu Sắc</th>
                        <th>Kích thước</th>
                        <th>Số Lượng</th>
                        <th>Số Sao Trung Bình</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${l}" var="product" varStatus="loop">
                        <tr>
                            <td>${product.productName}</td>
                            <td>
                                <img src="${product.image}" alt="${product.productName}" style="width: 100px; height: 125px;"/>
                            </td>
                            <td>
                                <p style="color: #000"><fmt:formatNumber value="${product.price}" pattern="#,##0" /> VNĐ</p>
                            </td>
                            <td>${product.description}</td>
                            <td>
                                <c:set var="colorListKey" value="listP${loop.index}" />
                                <c:forEach items="${requestScope[colorListKey]}" var="color">
                                    <p>${color.color}</p>
                                </c:forEach>
                            </td>
                            <td>
                                <c:set var="sizeListKey" value="listP${loop.index}" />
                                <c:forEach items="${requestScope[sizeListKey]}" var="size">
                                    <p>${size.size}</p>
                                </c:forEach>
                            </td>
                            <td>
                                <c:set var="quantitypListKey" value="listP${loop.index}" />
                                <c:forEach items="${requestScope[quantitypListKey]}" var="quantity">
                                    <p>${quantity.quantityp}</p>
                                </c:forEach>
                            </td>

                            <td>${product.averageStar}</td>

                            <td>
                                <a style="margin: 0" href="updateProductShopOwnerServlet?productId=${product.productId}">
                                    <button class="btn btn-primary">
                                        Cập nhập
                                    </button>
                                </a>
                                <a style="margin: 0" href="detailProduct?productId=${product.productId}">
                                    <button class="btn btn-primary"">Chi tiết</button>
                                </a>
                                <button class="btn btn-danger" onclick="prepareDelete('${product.productId}', '${product.productName}')">Xoá</button>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
            <div class="container1">
                <button class="back-button" onclick="window.location.href = 'profileShop.jsp'"><i class="fa-solid fa-arrow-left-long"></i>Quay Lại</button>
            </div>
        </div>            

        <!-- Update Modal -->
        <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true" style="background-color: rgba(0,0,0,0.5); top: 150px">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateModalLabel">Cập nhập sản phẩm</h5>
                    </div>
                    <div class="modal-body">
                        <form action="updateProductShopOwnerServlet" method="post">
                            <input type="hidden" id="productId" name="productId">
                            <div class="form-group">
                                <label for="productName">Tên sản phẩm</label>
                                <input type="text" class="form-control" id="productName" name="productName" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Giá</label>
                                <input type="number" class="form-control" id="price" name="price" required>
                            </div>
                            <div class="form-group">
                                <label for="description">Mô tả</label>
                                <textarea class="form-control" id="description" name="description" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="quantityp">Số lượng</label>
                                <input type="number" class="form-control" id="quantityp" name="quantityp" required>
                            </div>
                            <div style="display: flex; justify-content: end">
                                <button type="button" class="btn btn-secondary cancel" data-dismiss="modal" style="padding: 6px 12px; margin: 5px 10px 7px 0">Huỷ</button>
                                <button type="submit" class="btn btn-primary  update" style="margin-top: 5px">Cập nhập</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Delete Confirmation Modal -->
        <div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true" style="background-color: #0000; top: 200px">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận xoá</h5>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn xoá sản phẩm<strong><span id="productNameToDelete"></span></strong>?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary cancel" data-dismiss="modal">Huỷ</button>
                        <a id="confirmDeleteButton" href="#" class="btn btn-danger delete">Xoá</a>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function prepareDelete(productId, productName) {
                var deleteUrl = "delete?id=" + productId;
                $('#confirmDeleteButton').attr('href', deleteUrl);  // Set href of Delete button in modal
                $('#productNameToDelete').text(productName);  // Set product name in modal body
                $('#confirmDeleteModal').modal('show');  // Show the modal
            }

            function populateUpdateForm(productId, productName, price, description, quantityp, averageStar, image) {
                var formattedPrice = parseFloat(price).toFixed(0);  // Định dạng giá trị price thành số nguyên

                $('#productId').val(productId);
                $('#productName').val(productName);
                $('#price').val(formattedPrice);  // Đặt giá trị price đã được định dạng
                $('#description').val(description);
                $('#quantityp').val(quantityp);
                $('#updateModal').modal('show');
                ;
            }
        </script>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>