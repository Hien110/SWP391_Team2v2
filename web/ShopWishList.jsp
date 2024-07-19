<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cửa Hàng Yêu Thích</title>
    <style>
        /* CSS cho hình ảnh trong phần avatar */
        .avatar img {
            width: 100px;
            height: 100px;
            border-radius: 50px; 
            cursor: pointer;
            margin-left: 30px;
        }

        /* CSS chung cho layout */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .detail {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            min-height: 50vh;
            font-family: Arial, sans-serif;
            padding-bottom: 50px;  
        }

        h1 {
            text-align: center;
            color: #2a8341; /* Màu xanh lá cây đậm */
            padding-top: 20px;
        }

        .wishlist-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
            padding: 20px; /* Khoảng cách giữa các phần tử */
        }

        .wishlist-item {
            border: 1px solid black;
            padding: 10px;
            width: 200px;
            text-align: center;
        }

        .wishlist-item img {
            width: 100px;
            height: 100px;
            cursor: pointer; /* Con trỏ chuột */
        }

        .wishlist-item .shop-name {
            margin-top: 10px;
        }

        .wishlist-item .delete-button {
            margin-top: 10px;
            padding: 5px 10px;
            background-color: red;
            color: white;
            border: none;
            cursor: pointer;
        }

        #confirmDialog {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
            justify-content: center;
            align-items: center;
        }

        .dialog-content {
            background-color: white;
            padding: 20px;
            border: 1px solid #888;
            width: 300px;
            text-align: center;
        }

        .dialog-buttons {
            margin-top: 20px;
            display: flex;
            justify-content: space-around;
        }

        .dialog-buttons button {
            padding: 10px 20px;
            border: none;
            cursor: pointer;
        }

        .cancel-button {
            background-color: grey;
            color: white;
        }

        .confirm-button {
            background-color: red;
            color: white;
        }
    </style>
    <script>
        // JavaScript cho xác nhận xóa
        function showConfirmDialog(shopId) {
            document.getElementById('confirmDialog').style.display = 'flex';
            document.getElementById('confirmButton').onclick = function () {
                document.getElementById('deleteForm-' + shopId).submit();
            };
        }

        function closeConfirmDialog() {
            document.getElementById('confirmDialog').style.display = 'none';
        }
    </script>
</head>
<body>
<h1>Cửa Hàng Yêu Thích</h1>
<div class="detail">
    <div class="wishlist-container">
        <c:forEach var="shop" items="${l}">
            <div class="wishlist-item">
                <div class="avatar">
                    <a href="shopdetail?shopid=${shop.shopId}">
                        <img src="${shop.avt}" alt="Avatar">
                    </a>
                </div>
                <div class="shop-name">
                    ${shop.shopName}
                </div>
                <form id="deleteForm-${shop.shopId}" method="post" action="DeleteShopWishList">
                    <input type="hidden" name="shopid" value="${shop.shopId}">
                    <button type="button" class="delete-button" onclick="showConfirmDialog(${shop.shopId})">Bỏ Yêu Thích</button>
                </form>
            </div>
        </c:forEach>
    </div>
    <div id="confirmDialog" class="confirm-dialog">
        <div class="dialog-content">
            <p>Bạn có chắc chắn muốn xóa cửa hàng này khỏi danh sách yêu thích?</p>
            <div class="dialog-buttons">
                <button class="cancel-button" onclick="closeConfirmDialog()">Hủy</button>
                <button class="confirm-button" id="confirmButton">Xác nhận</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<%@include file="include/footer.jsp" %>
