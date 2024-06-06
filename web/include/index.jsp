<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
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

            <!-- New Products -->
            <div class="row mb-4">
                <div class="col">
                    <h2>New Products</h2>
                    <div id="newProductsCarousel" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <div class="row">
                                    <c:forEach var="product" items="${l}">
                                        <div class="col-lg-3 col-md-4 col-sm-6 mb-3">
                                            <div class="card">
                                                <img src="${product.image}" class="card-img-top" alt="${product.productName}">
                                                <div class="card-body">
                                                    <h5 class="card-title ">${product.productName}</h5>
                                                    <p class="card-text">${product.price}₫</p>


                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Promotion -->
            <div class="row mb-4">
                <div class="col">
                    <div class="p-4 bg-secondary text-white text-center">
                        <h3>50% Off</h3>
                        <p>FashionShop offers up to 50% discount on all products</p>
                        <!-- Countdown Timer -->
                        <div class="countdown">
                            <div>
                                <h4 id="days">0</h4>
                                <small>Days</small>
                            </div>
                            <div>
                                <h4 id="hours">0</h4>
                                <small>Hours</small>
                            </div>
                            <div>
                                <h4 id="minutes">0</h4>
                                <small>Minutes</small>
                            </div>
                            <div>
                                <h4 id="seconds">0</h4>
                                <small>Seconds</small>
                            </div>
                        </div>
                        <button class="btn btn-warning mt-3">View All</button>
                    </div>
                </div>
            </div>

            <!-- Men's Fashion -->
            <div class="row">
                <div id="carouselExampleControlsNoTouching" class="carousel slide" data-bs-touch="false">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <div class="col">
                                <h2>Men's Fashion</h2>
                                <div class="row">
                                    <c:forEach var="product" items="${l}">
                                        <div class="col-lg-3 col-md-4 col-sm-6 mb-3">
                                            <div class="card">
                                                <img src="${product.image}" class="card-img-top" alt="${product.productName}">
                                                <div class="card-body">
                                                    <h5 class="card-title">${product.productName}</h5>
                                                    <p class="card-text">${product.price}₫</p>

                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>

                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControlsNoTouching" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControlsNoTouching" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>

                    </div>
                </div>
            </div>





            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
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
