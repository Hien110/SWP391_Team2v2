<%@ page import="model.Product" %>
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
            background: #435d7d;
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
            background: #27c5f2;
            border: none;
            min-width: 50px;
            border-radius: 2px;
            margin-left: 10px;
        }
        .table-title .btn:hover, .table-title .btn:focus {
            background: #009edf;
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
            color: #27c5f2;
            display: inline-block;
            margin: 0 5px;
        }
        table.table td a:hover {
            color: #009edf;
        }
        table.table td a.btn {
            color: #fff;
            background: #27c5f2;
            border: none;
            border-radius: 2px;
            min-width: 40px;
        }
        table.table td a.btn:hover {
            background: #009edf;
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
                        <a href="addProductShopOwner.jsp" class="btn btn-primary">Add new product</a>
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
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${l}" var="product">
                        <tr>
                            <td>${product.productName}</td>
                            <td>
                                <img src="${product.image}" alt="${product.productName}" style="width: 50px; height: 50px;"/>
                            </td>
                            <td>${product.price}</td>
                            <td>${product.description}</td>
                            <td>${product.quantity}</td>
                            <td>${product.averageStar}</td>
                            <td>
                                <a href="updateproduct?id=${product.productId}" class="btn btn-primary">Update</a>
                                <a href="delete?id=${product.productId}" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>            
    </div>
    
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
