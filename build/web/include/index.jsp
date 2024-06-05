<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%
    // Set the end date for the countdown timer
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 7); // Example: promotion ends in 7 days
    Date endDate = calendar.getTime();
    String formattedEndDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(endDate);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Content</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <style>
        .header-container {
            margin-bottom: 20px;
        }
        .carousel-item img {
            max-width: 100%;
        }
        .card {
            margin-bottom: 20px;
        }
        .bg-body-tertiary {
            background-color: #f8f9fa;
        }
        .countdown {
            display: flex;
            justify-content: center;
        }
        .countdown div {
            padding: 20px;
            text-align: center;
        }
    </style>
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
                                <div class="col-lg-3 col-md-4 col-sm-6 mb-3">
                                    <div class="card">
                                        <img src="${pageContext.request.contextPath}/images/gile2.jpg" class="card-img-top" alt="Product 1">
                                        <div class="card-body">
                                            <h5 class="card-title">White Shirt</h5>
                                            <p class="card-text">322,000₫</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-4 col-sm-6 mb-3">
                                    <div class="card">
                                        <img src="${pageContext.request.contextPath}/images/gile2.jpg" class="card-img-top" alt="Product 2">
                                        <div class="card-body">
                                            <h5 class="card-title">Men's Gilet</h5>
                                            <p class="card-text">422,000₫</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-4 col-sm-6 mb-3">
                                    <div class="card">
                                        <img src="${pageContext.request.contextPath}/images/gile2.jpg" class="card-img-top" alt="Product 3">
                                        <div class="card-body">
                                            <h5 class="card-title">Blue Shirt</h5>
                                            <p class="card-text">322,000₫</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-4 col-sm-6 mb-3">
                                    <div class="card">
                                        <img src="${pageContext.request.contextPath}/images/gile2.jpg" class="card-img-top" alt="Product 4">
                                        <div class="card-body">
                                            <h5 class="card-title">Men's T-Shirt</h5>
                                            <p class="card-text">232,000₫</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Add more carousel items if needed -->
                    </div>
                    <a class="carousel-control-prev" href="#newProductsCarousel" role="button" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#newProductsCarousel" role="button" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
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
            <div class="col">
                <h2>Men's Fashion</h2>
                <div class="row">
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-3">
                        <div class="card">
                            <img src="${pageContext.request.contextPath}/images/gile2.jpg" class="card-img-top" alt="Product 1">
                            <div class="card-body">
                                <h5 class="card-title">White Shirt</h5>
                                <p class="card-text">322,000₫</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-3">
                        <div class="card">
                            <img src="${pageContext.request.contextPath}/images/gile2.jpg" class="card-img-top" alt="Product 2">
                            <div class="card-body">
                                <h5 class="card-title">Men's Gilet</h5>
                                <p class="card-text">422,000₫</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-3">
                        <div class="card">
                            <img src="${pageContext.request.contextPath}/images/gile2.jpg" class="card-img-top" alt="Product 3">
                            <div class="card-body">
                                <h5 class="card-title">Blue Shirt</h5>
                                <p class="card-text">322,000₫</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-3">
                        <div class="card">
                            <img src="${pageContext.request.contextPath}/images/gile2.jpg" class="card-img-top" alt="Product 4">
                            <div class="card-body">
                                <h5 class="card-title">Men's T-Shirt</h5>
                                <p class="card-text">232,000₫</p>
                            </div>
                        </div>
                    </div>
                    <!-- Add more products if needed -->
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
                timeLeft = { days: 0, hours: 0, minutes: 0, seconds: 0 };
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

        window.onload = function() {
            const endDate = "<%= formattedEndDate %>";
            updateCountdown(endDate);

            setInterval(function() {
                updateCountdown(endDate);
            }, 1000);
        };
    </script>
</body>
</html>
