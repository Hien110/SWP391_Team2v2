<%@include file="include/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Cancel Order</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .btn-container{
                margin-top: 70px;
                margin-bottom: 70px;
            }

            .btn-green {
                background-color: #28a745; /* Green button background */
                color: #ffffff; /* White text */
            }
            .btn-green:hover {
                background-color: #218838; /* Darker green on hover */
            }
            .form-label {
                font-weight: bold;
            }
            #otherReason {
                display: none; /* Initially hidden */
            }
            #button{
                background-color: #28a745;
                width: 100px;
                margin-left: 46%;
                border-radius: 5%;
                margin-top: 50px;
            }

            /* Animation keyframes */
            @keyframes fadeInOut {
                0% {
                    opacity: 0;
                }
                50% {
                    opacity: 1;
                }
                100% {
                    opacity: 0;
                }
            }
        </style>
    </head>
    <body>

        <div class="container btn-container">
            <h2 class="text-center">Hủy Đơn Hàng</h2>
            <form id="reportForm" action="reportproduct" method="post">
                <div class="form-group">
                    <label for="reason" class="form-label">Lý do:</label>
                    <select name="reason" id="reason" class="form-control">
                        <option value="Tôi không muốn mua nữa">Tôi không muốn mua nữa</option>
                        <option value="Thay đổi phương thức thanh toán">Thay đổi phương thức thanh toán</option>
                        <option value="Giá sản phẩm quá cao">Giá sản phẩm quá cao</option>
                        <option value="Khác">Khác</option>
                    </select>
                </div>
                 <input type="hidden" name="productid" value="${productid}" />
                <div id="otherReason" class="form-group">
                    <label for="customReason" class="form-label">Lý do khác:</label>
                    <input type="text" name="customReason" id="customReason" class="form-control">
                </div>
                <div class="text-center " id="button">
                    <input type="submit" value="Xác nhận" class="btn btn-green" >
                </div>
            </form>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            document.getElementById("reason").addEventListener("change", function () {
                var otherReasonDiv = document.getElementById("otherReason");
                var customReasonInput = document.getElementById("customReason");
                if (this.value === "Khác") {
                    otherReasonDiv.style.display = "block";
                    customReasonInput.required = true;
                } else {
                    otherReasonDiv.style.display = "none";
                    customReasonInput.required = false;
                }
            });
        </script>
    </body>
</html>
<%@include file="include/footer.jsp" %>
