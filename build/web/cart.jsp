<%-- 
    Document   : cart
    Created on : Jun 13, 2024, 9:37:22 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .payment-method {
                cursor: pointer;
                transition: border-color 0.3s;
            }
            .payment-method.active {
                border-color: #6064b6;
            }
            .payment-form {
                display: none;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5"> 
            <div class="text-center mb-4">
                <h2>Order Form</h2>
            </div>
            <div class="bg-light p-4 rounded">
                <div class="mb-3">
                    <h3 class="text-success">Shipping Address</h3>
                    <p><b>Cao Hoàng Linh</b> (+84) 868645800</p>
                    <p>84 Trần Văn Hải, Phường Hòa Hải, Quận Ngũ Hành Sơn, Đà Nẵng</p>
                    <a href="#">Change</a>
                </div>

                <div class="order-summary mb-3">
                    <h3>Order Summary</h3>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead class="table-light">
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" items="${cartProducts}">
                                    <tr>
                                        <td class="d-flex align-items-center">
                                            <img src="${product.image}" alt="Product Image" class="img-thumbnail me-2" style="width: 50px;">
                                            <div>
                                                <p class="mb-0">${product.productName}</p>
                                                <small class="text-muted">${product.description} | <a href="#" class="text-success">Chat ngay</a></small>
                                            </div>
                                        </td>
                                        <td>₫${product.price}</td>
                                        <td>${product.quantity}</td>
                                        <td>₫${product.price * product.quantity}</td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="3" class="text-end">Voucher:</td>
                                    <td>-₫5.000 <a href="#">Choose Another Voucher</a></td>
                                </tr>
                                <tr>
                                    <td colspan="3" class="text-end">Shipping:</td>
                                    <td>₫10.000 (Standard Express, Arrives between June 13 - June 17) <a href="#">Change</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="d-flex justify-content-between fw-bold border-top pt-3 mb-3">
                    <span>Total:</span>
                    <span>
                        ₫<c:out value="${cartProducts.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum() - 5000 + 10000}" />
                    </span>
                </div>
            </div>
    </body>
</html>
