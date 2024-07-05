<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Order History</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid black;
                border-spacing: 0;
            }
            th, td {
                padding: 15px;
                text-align: left;
            }
            th {
                background-color: #2a8341;
                color: white;
            }
            .img {
                width: 100px;
                height: 125px;
            }
            .btn-container {
                margin-bottom: 30px;
                margin-top: 30px;
                margin-left: 5%;
                margin-right: 5%;
            }
            .btn-container h1 {
                text-align: center;
                margin-bottom: 20px;
            }
            .button {
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
            .button1 {
                border-radius: 10px;
                padding: 7px 25px;
                color: #2a8341;
                background-color: #fff;
                border: 1px solid #2a8341;
                cursor: pointer;
                margin-right: 5px;
                text-decoration: none;
                display: inline-block;
            }

            .button:hover {
                color: #fff;
                background-color: #ff0000;
                border: 1px solid #ff0000;
                transition: 0.3s;
            }
            .button1:hover {
                color: #fff;
                background-color: #2a8341;
                border: 1px solid #2a8341;
                transition: 0.3s;
            }
            .button b {
                margin-right: 12px;
            }
            .button1 b {
                margin-right: 15px;
            }
            .btn.disabled {
                margin-bottom: 5px;
                background-color: #ddd;
                border: 1px solid #ddd;
                cursor: not-allowed;
            }

            /* Modal styles */
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.4);
            }

            .modal-content h2 {
                text-align: center;
                margin-bottom: 20px;
                color: #333;
            }

            .modal-content {
                background-color: #fff;
                margin: 10% auto;
                padding: 20px;
                border: none;
                border-radius: 8px;
                width: 80%;
                max-width: 500px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                animation: modalOpen 0.4s;
            }

            @keyframes modalOpen {
                from {
                    opacity: 0;
                    transform: scale(0.8);
                }
                to {
                    opacity: 1;
                    transform: scale(1);
                }
            }

            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
                margin-top: -10px;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            .form-cancel {
                margin: 0 auto;
                text-align: left;
            }

            .modal-content button{
                margin-left: 34%;
                border: 1px solid;
                border-radius: 10px;
            }

            .form-cancel h5 {
                margin-bottom: 10px;
                color: #333;
            }

            .reason {
                margin-bottom: 10px;
            }

            .reason input {
                margin-right: 10px;
            }

            #otherReason {
                width: 100%;
                height: 60px;
                margin-top: 10px;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
                resize: none;
            }

            .buttondelivered {
                border-radius: 10px;
                padding: 7px 25px;
                color: white;
                background-color: #4CAF50;
                border: 1px solid black;
                cursor: pointer;
                margin-right: 5px;
                text-decoration: none;
                display: inline-block;
                margin-bottom: 7px;
            }

            .buttondelivered b{
                margin-right: 15px;
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
        <%
        HttpSession currentSession = request.getSession();
        User loggedInAccount = (User) currentSession.getAttribute("user");
        // Kiểm tra nếu người dùng chua đăng nhập
        if (loggedInAccount == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        %>
        <%@include file="include/header.jsp" %>
        <div class="btn-container">
            <h1>Theo Dõi Đơn Hàng</h1>
            <table>
                <tr>
                    <th>Mã Đơn Hàng</th>
                    <th>Sản Phẩm</th>
                    <th>Số Lượng</th>
                    <th>Trạng Thái</th>
                    <th>Tổng Thanh Toán</th>
                    <th>Ngày Đặt Hàng</th>
                    <th>Tùy Chọn</th>
                </tr>
                <c:forEach var="order" items="${orderList}">
                    <tr style="border-bottom: 1px solid;">
                        <td>${order.orderid}</td>
                        <td>
                            <img class="img" src="${order.image}" alt="${order.productname}"/>
                            ${order.productname}
                        </td>
                        <td>${order.quantity}</td>
                        <td>${order.statusorder}</td>
                        <td>${order.totalprice}</td>
                        <td>${order.dateorder}</td>
                        <td>
                            <c:choose>
                                <c:when test="${order.statusorder == 'Đã hủy'}"> 
                                    <a class="btn disabled" style="width: 124px; border-radius:10px ">Đã Hủy Đơn</a>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.statusorder == 'Đang xử lí'}">
                                    <a class="button" href="javascript:void(0);" onclick="showCancelModal('${order.orderid}', '${order.productid}')"><b>Hủy Đơn Hàng</b></a>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.statusorder == 'Đang giao'}"> 
                                    <a class="buttondelivered" href="submitdelivered?orderid=${order.orderid}"><b>Đã Nhận Hàng</b></a>
                                </c:when>
                            </c:choose>
                            <br/>        
                            <a class="button1" href="detailorder?orderid=${order.orderid}"><b>Chi Tiết</b></a>       
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <h4 style="color: red; padding-top: 20px; font-weight: 400; text-align: center">${requestScope.aler}</h4>
            <div class="container1">
                <button class="back-button" onclick="history.back()"><i class="fa-solid fa-arrow-left-long"></i> Quay Lại</button>
            </div>
        </div>

        <!-- Modal HTML -->
        <div id="cancelModal" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h2>Hủy Đơn Hàng</h2>
                <form id="cancelForm" action="cancelorder" method="post">
                    <div class="form-cancle">
                        <input type="hidden" name="orderid" id="orderid">
                        <input type="hidden" name="productid" id="productid"> 
                        <h5>Lí Do Hủy:</h5>
                        <div class="reason">
                            <input type="radio" id="reason1" name="cancelReason" value="Tôi không muốn mua nữa">
                            <label for="reason1">Tôi không muốn mua nữa</label>
                        </div>
                        <div class="reason">
                            <input type="radio" id="reason2" name="cancelReason" value="Đơn hàng chuẩn bị quá lâu">
                            <label for="reason2">Đơn hàng chuẩn bị quá lâu</label>
                        </div>
                        <div class="reason">
                            <input type="radio" id="reason3" name="cancelReason" value="Phí giao hàng quá đắt">
                            <label for="reason3">Phí giao hàng quá đắt</label>
                        </div>
                        <div class="reason">
                            <input type="radio" id="reason4" name="cancelReason" value="Sản phẩm không phù hợp">
                            <label for="reason4">Sản phẩm không phù hợp</label>
                        </div>
                        <div class="reason">
                            <input type="radio" id="reason5" name="cancelReason" value="other">
                            <label for="reason5">Lý do khác</label>
                        </div>
                        <textarea id="otherReason" name="otherReason" placeholder="Nhập lý do khác" style="display: none;"></textarea>
                        <br>
                    </div>
                    <button class="button" type="submit">Xác nhận hủy</button>
                </form>
            </div>
        </div>

        <script>
            // Get the modal
            var modal = document.getElementById("cancelModal");

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // Function to show modal and set orderid
            function showCancelModal(orderID, productID) {
                document.getElementById("orderid").value = orderID;
                document.getElementById("productid").value = productID;
                modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

            // Show/hide textarea based on "Other" selection
            document.getElementById("reason5").addEventListener("change", function () {
                var otherReason = document.getElementById("otherReason");
                if (this.checked) {
                    otherReason.style.display = "block";
                } else {
                    otherReason.style.display = "none";
                }
            });

            // Hide the textarea if any other radio button is selected
            var otherReasons = document.querySelectorAll('input[name="cancelReason"]:not(#reason5)');
            otherReasons.forEach(function (reason) {
                reason.addEventListener("change", function () {
                    var otherReason = document.getElementById("otherReason");
                    otherReason.style.display = "none";
                });
            });
        </script>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
