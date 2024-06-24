<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <title>Product Ratings</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel='stylesheet prefetch' href='https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>

        <style>

            .review-container {
                width: 1000px;
                margin: 0 auto;
                border: 1px solid #ddd;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                background-color: #fff;
                margin-top: 20px;
                margin-bottom: 30px;
            }
            .review {
                border-bottom: 1px solid #ddd;
                padding: 10px 0;
                display: flex;
                flex-direction: column;
                align-items: flex-start;
            }
            .review:last-child {
                border-bottom: none;
            }
            .review-header {
                display: flex;
                align-items: center;
                margin-bottom: 5px;
            }
            .avatar {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                margin-right: 10px;
            }
            .username {
                font-weight: bold;
                margin-right: 10px;
            }
            .star-rating {
                color: #ffd700; /* Vàng */
                font-size: 20px;
                margin-bottom: 5px;
            }
            .star-rating i {
                margin-right: 2px;
                font-size: 15px;
            }
            .comment {
                margin-top: 5px;
            }
            .form-container {
                border: 1px solid #ddd;
                padding: 20px;
                margin-bottom: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-container form {
                display: flex;
                flex-direction: column;
            }
            .form-container label {
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-container input[type="text"],
            .form-container textarea {
                margin-bottom: 10px;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                width: 100%;
            }

            .form-container .star-rating label {
                font-size: 20px;
                color: #ddd;
                cursor: pointer;
            }
            .form-container button {
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }
            div.stars {
                width: 270px;
                display: inline-block;
            }
            input.star {
                display: none;
            }
            label.star {
                float: right;
                padding: 10px;
                font-size: 36px;
                color: #444;
                transition: all .2s;
            }
            input.star:checked ~ label.star:before {
                content: '\f005';
                color: #FD4;
                transition: all .25s;
            }
            input.star-5:checked ~ label.star:before {
                color: #FE7;
                text-shadow: 0 0 20px #952;
            }
            input.star-1:checked ~ label.star:before {
                color: #F62;
            }
            label.star:hover {
                transform: rotate(-15deg) scale(1.3);
            }
            label.star:before {
                content: '\f006';
                font-family: FontAwesome;
            }
            label.star {
                float: right;
                padding: 10px;
                font-size: 25px; /* Adjusted the font-size */
                color: #444;
                transition: all .2s;
            }
            .form-container button {
                padding: 5px; /* Đã thay đổi padding */
                margin-left: 35%;
                margin-right: 35%;
                padding-top: 10px;
                padding-bottom: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                background-color: #2a8341;
                /*                font-size: 14px;  Đã thay đổi font-size */
            }
            .review-container h2{
                margin-left: 33%;
            }
        </style>
    </head>
    <body>

        <div class="review-container">
            <h2>ĐÁNH GIÁ SẢN PHẨM</h2>
            <!-- Div for adding a new review -->
            <div class="form-container">
                <form action="submitreview" method="post">
                    <label for="comment">Đánh Giá Của Bạn</label>
                    <textarea id="comment" name="comment" rows="4" required></textarea>

                    <label for="rating">Đánh Giá Sao:</label>
                    <div class="stars">
                        <input class="star star-5" id="star-5" type="radio" name="star" value="5"/>
                        <label class="star star-5" for="star-5"></label>
                        <input class="star star-4" id="star-4" type="radio" name="star" value="4"/>
                        <label class="star star-4" for="star-4"></label>
                        <input class="star star-3" id="star-3" type="radio" name="star" value="3"/>
                        <label class="star star-3" for="star-3"></label>
                        <input class="star star-2" id="star-2" type="radio" name="star" value="2"/>
                        <label class="star star-2" for="star-2"></label>
                        <input class="star star-1" id="star-1" type="radio" name="star" value="1"/>
                        <label class="star star-1" for="star-1"></label>
                    </div>

                    <button type="submit">Hoàn Thành</button>
                </form>
            </div>

            <!-- Loop through individual reviews -->
            <c:forEach var="review" items="${listComment}">
                <div class="review">
                    <div class="review-header">
                        <img src="${review.image}" alt="Avatar" class="avatar">
                        <div class="username"><c:out value="${review.userName}" /></div>
                    </div>
                    <div class="star-rating">
                        <c:forEach var="star" begin="1" end="5">
                            <i class="${star <= review.star ? 'fas' : 'far'} fa-star"></i>
                        </c:forEach>
                    </div>
                    <div class="comment"><c:out value="${review.comment}" /></div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
