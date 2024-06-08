<%@include file="include/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Report Shop</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .btn-container{
                margin-bottom: 70px;
                margin-top: 70px;
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
        </style>
    </head>
    <body>

        <div class="container btn-container">
            <h2 class="text-center">Báo cáo cửa hàng</h2>
            <form action="reportshop" method="post">
                <div class="form-group">
                    <label for="reason" class="form-label">Lý do:</label>
                    <select name="reason" id="reason" class="form-control">
                        <option value="Cửa hàng giả mạo">Cửa hàng giả mạo</option>
                        <option value="Dịch vụ kém">Dịch vụ kém</option>
                        <option value="Bán hàng giả">Bán hàng giả</option>
                        <option value="Thời gian phản hồi chậm">Thời gian phản hồi chậm</option>
                        <option value="Khác">Khác</option>
                    </select>
                </div>
                <div id="otherReason" class="form-group">
                    <label for="customReason" class="form-label">Custom Reason:</label>
                    <input type="text" name="customReason" id="customReason" class="form-control">
                </div>
                <div class="text-center">
                    <input type="submit" value="Xác nhận" class="btn btn-green">
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