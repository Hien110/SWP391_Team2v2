<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" integrity="" crossorigin="anonymous" />
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="" crossorigin="anonymous" />
        <style>
            .products {
                margin-top: 20px;
                display: flex;
                justify-content: center;
            }

            .products h2 {
                margin-top: 0;
                color: #333;
                text-align: left;
            }

            .product-list {
                display: flex;
                gap: 20px;
                flex-wrap: wrap;
                justify-content: center;
                max-width: 1200px; /* Adjust as needed for your layout */
            }

            .product-item {
                width: calc(25% - 20px);
                margin-bottom: 20px;
                border: 1px solid #ddd;
                border-radius: 5px;
                transition: transform 0.3s ease-out;
                overflow: hidden;
            }

            .product-item:hover {
                transform: translateY(-5px);
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }

            .product-thumbnail img {
                width: 100%;
                height: 200px;
                object-fit: cover;
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
            }

            .product-info {
                padding: 15px;
                background-color: #fff;
            }

            .product-info h5 {
                margin: 0;
                font-size: 18px;
                color: #333;
            }

            .product-info p {
                margin: 5px 0;
                font-size: 16px;
                color: #777;
            }
            .list h2{
                margin-top: 10px;
                margin-bottom: 10px;
                margin-left: 10%;
            }
        </style>
    </head>
    <%@include file="include/header.jsp" %>
    <body>
        <div class="list">
            <c:choose>
                <c:when test="${count == 0}">
                    <h2>Không có sản phẩm về: ${typename}</h2>
                </c:when>
                <c:otherwise>
                    <h2>Sản phẩm về: ${typename}</h2>
                </c:otherwise>
            </c:choose>
            <div class="products">
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
        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="" crossorigin="anonymous"></script>
    </body>
    <%@include file="include/footer.jsp" %>
</html>
