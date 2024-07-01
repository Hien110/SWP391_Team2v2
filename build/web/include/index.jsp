<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
        <script src="${pageContext.request.contextPath}/javascript/index.js"></script>
    </head>
    <body>
        <div class="container mt-4">
            <!-- Promotion -->
            <div class="row mb-4">
                <div class="col text-center">
                    <img src="${pageContext.request.contextPath}/images/anh5.jpg" class="img-fluid" alt="Promotion">
                </div>
            </div>

            <div class="row text-center mb-4">
                <div class="col-md-4 col-sm-6 mb-3">
                    <div class="p-3 border bg-light">Free Shipping</div>
                </div>
                <div class="col-md-4 col-sm-6 mb-3">
                    <div class="p-3 border bg-light">24/7 Customer Support</div>
                </div>
                <div class="col-md-4 col-sm-6 mb-3">
                    <div class="p-3 border bg-light">100% Money Back</div>
                </div>
            </div>

            <!-- New Products Carousel 1 -->
            <div id="newProductsCarousel1" class="carousel slide" data-bs-ride="carousel">
                <h2>Gentleman fashion</h2>
                <div class="carousel-inner">
                    <c:forEach var="product" items="${l}" varStatus="status" begin="0" end="7">
                        <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                            <div class="card">
                                <div class="img-wrapper">
                                    <img src="${product.image}" class="d-block w-100" alt="${product.productName}" 
                                         onclick="window.location.href = '${pageContext.request.contextPath}/detailProduct?productId=${product.productId}&userId=${userId}'">
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">${product.productName}</h5>
                                    <p class="card-text">${product.price}₫</p>
                                    <p>${product.description}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#newProductsCarousel1" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#newProductsCarousel1" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

            <!-- Promotion with Countdown Timer -->
            <div class="row mb-4">
                <div class="col">
                    <div class="p-4 bg-secondary text-white text-center">
                        <h3>50% Off</h3>
                        <p>FashionShop offers up to 50% discount on all products</p>
                        <!-- Countdown Timer -->
                        <div class="countdown d-flex justify-content-center">
                            <div class="me-3 text-center">
                                <h4 id="days">0</h4>
                                <small>Days</small>
                            </div>
                            <div class="me-3 text-center">
                                <h4 id="hours">0</h4>
                                <small>Hours</small>
                            </div>
                            <div class="me-3 text-center">
                                <h4 id="minutes">0</h4>
                                <small>Minutes</small>
                            </div>
                            <div class="me-3 text-center">
                                <h4 id="seconds">0</h4>
                                <small>Seconds</small>
                            </div>
                        </div>
                        <button class="btn btn-warning mt-3">View All</button>
                    </div>
                </div>
            </div>

            <!-- New Products Carousel 2 -->
            <div id="newProductsCarousel2" class="carousel slide" data-bs-ride="carousel">
                <h2>Gentleman fashion</h2>
                <div class="carousel-inner">
                    <c:forEach var="product" items="${l}" varStatus="status" begin="0" end="7">
                        <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                            <div class="card">
                                <div class="img-wrapper">
                                    <img src="${product.image}" class="d-block w-100" alt="${product.productName}" 
                                         onclick="window.location.href = '${pageContext.request.contextPath}/detailProduct?productId=${product.productId}&userId=${userId}'">
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">${product.productName}</h5>
                                    <p class="card-text">${product.price}₫</p>
                                    <p>${product.description}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#newProductsCarousel2" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#newProductsCarousel2" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

            <!-- New Products Carousel 3 -->
            <div id="newProductsCarousel3" class="carousel slide" data-bs-ride="carousel">
                <h2>Laddy fashion</h2>
                <div class="carousel-inner">
                    <c:forEach var="product" items="${l}" varStatus="status" begin="0" end="7">
                        <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                            <div class="card">
                                <div class="img-wrapper">
                                    <img src="${product.image}" class="d-block w-100" alt="${product.productName}" 
                                         onclick="window.location.href = '${pageContext.request.contextPath}/detailProduct?productId=${product.productId}&userId=${userId}'">
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">${product.productName}</h5>
                                    <p class="card-text">${product.price}₫</p>
                                    <p>${product.description}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#newProductsCarousel3" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#newProductsCarousel3" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

            <!-- New Products Carousel 4 -->
            <div id="newProductsCarousel4" class="carousel slide" data-bs-ride="carousel">
                <h2>Watch Products</h2>
                <div class="carousel-inner">
                    <c:forEach var="product" items="${l}" varStatus="status" begin="0" end="7">
                        <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                            <div class="card">
                                <div class="img-wrapper">
                                    <img src="${product.image}" class="d-block w-100" alt="${product.productName}" 
                                         onclick="window.location.href = '${pageContext.request.contextPath}/detailProduct?productId=${product.productId}&userId=${userId}'">
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">${product.productName}</h5>
                                    <p class="card-text">${product.price}₫</p>
                                    <p>${product.description}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#newProductsCarousel4" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#newProductsCarousel4" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>

        <script>
            function calculateTimeLeft(endDate) {
                const difference = new Date(endDate).getTime() - new Date().getTime();
                let timeLeft = {};

                if (difference > 0) {
                    timeLeft = {
                        days: Math.floor(difference / (1000 * 60 * 60 * 24)),
                        hours: Math.floor((difference / (1000 * 60 * 60)) % 24),
                        minutes: Math.floor((difference / 1000 / 60) % 60),
                        seconds: Math.floor((difference / 1000) % 60),
                    };
                } else {
                    timeLeft = {days: 0, hours: 0, minutes: 0, seconds: 0};
                }

                return timeLeft;
            }

            function updateCountdown(endDate) {
                const timeLeft = calculateTimeLeft(endDate);
                document.getElementById("days").innerText = timeLeft.days;
                document.getElementById("hours").innerText = timeLeft.hours;
                document.getElementById("minutes").innerText = timeLeft.minutes;
                document.getElementById("seconds").innerText = timeLeft.seconds;
            }

            window.onload = function () {
                const endDate = "<%= formattedEndDate %>";
                updateCountdown(endDate);

                setInterval(function () {
                    updateCountdown(endDate);
                }, 1000);
            };
        </script>
    </body>
</html>
