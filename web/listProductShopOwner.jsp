<%@ page import="model.ProductShop" %>
<%@include file="include/header.jsp" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>List Product Shop Owner</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
                font-family: 'Arial', sans-serif;
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
                background: #28a745; /* Màu xanh lá cây */
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
                background: #28a745; /* Màu xanh lá cây */
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
                color: #28a745; /* Màu xanh lá cây */
                display: inline-block;
                margin: 0 5px;
            }
            table.table td a:hover {
                color: #218838; /* Màu xanh lá cây đậm hơn */
            }
            table.table td a.btn {
                color: #fff;
                background: #28a745; /* Màu xanh lá cây */
                border: none;
                border-radius: 2px;
                min-width: 50px;
            }
            table.table td a.btn:hover {
                background: #218838; /* Màu xanh lá cây đậm hơn */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Shop <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="home.jsp" class="btn btn-primary">Back to home</a>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            <th>Average Star</th>
                            <th>colors</th>
                            <th>size</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${l}" var="product" varStatus="loop">
                            <tr>
                                <td>${product.productName}</td>
                                <td>
                                    <img src="${product.image}" alt="${product.productName}" style="width: 50px; height: 50px;"/>
                                </td>
                                <td>${product.price}</td>
                                <td>${product.description}</td>
                                <td>${product.quantityp}</td>
                                <td>${product.averageStar}</td>
                                <td>
                                    <c:set var="colorListKey" value="lc${loop.index}" />
                                    <c:forEach items="${requestScope[colorListKey]}" var="color">
                                        <p>${color.colors}</p>
                                    </c:forEach>
                                </td>
                                <td>
                                   <c:set var="sizeListKey" value="ls${loop.index}" />
                                    <c:forEach items="${requestScope[sizeListKey]}" var="size">
                                        <p>${size.size}</p>
                                    </c:forEach>
                                </td>
                                <td>
                                    <button class="btn btn-primary" onclick="populateUpdateForm('${product.productId}', '${product.productName}', '${product.price}', '${product.description}', '${product.quantityp}', '${product.averageStar}', '${product.image}')">Update</button>
                                    <button class="btn btn-danger" onclick="prepareDelete('${product.productId}', '${product.productName}')">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>            
        </div>

        <!-- Update Modal -->
        <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true" style="background-color: rgba(0,0,0,0.5)">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateModalLabel">Update Product</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="updateProductShopOwnerServlet" method="post">
                            <input type="hidden" id="productId" name="productId">
                            <div class="form-group">
                                <label for="productName">Product Name</label>
                                <input type="text" class="form-control" id="productName" name="productName" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Price</label>
                                <input type="text" class="form-control" id="price" name="price" required>
                            </div>
                            <div class="form-group">
                                <label for="description">Description</label>
                                <textarea class="form-control" id="description" name="description" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="quantityp">Quantity</label>
                                <input type="text" class="form-control" id="quantityp" name="quantityp" required>
                            </div>
                            <div class="form-group">
                                <label for="image">Image</label>
                                <input type="text" class="form-control" id="image" name="image" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Update Product</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Delete Confirmation Modal -->
        <div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Delete</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete <strong><span id="productNameToDelete"></span></strong>?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <a id="confirmDeleteButton" href="#" class="btn btn-danger">Delete</a>
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
                $('#productId').val(productId);
                $('#productName').val(productName);
                $('#price').val(price);
                $('#description').val(description);
                $('#quantityp').val(quantityp);
                $('#averageStar').val(averageStar);
                $('#image').val(image);
                $('#updateModal').modal('show');
            }
        </script>
        <%@include file="include/footer.jsp" %>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
