<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Report Shop</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Light background */
        }
        .container {
            margin-top: 50px;
            background-color: #ffffff; /* White background for form container */
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
    <%@include file="include/header.jsp" %>
    <div class="container">
        <h2 class="text-center">Report Shop</h2>
        <form action="reportshop" method="post">
            <div class="form-group">
                <label for="reason" class="form-label">Reason:</label>
                <select name="reason" id="reason" class="form-control">
                    <option value="Fake shop">Fake shop</option>
                    <option value="Poor service">Poor service</option>
                    <option value="Misleading information">Misleading information</option>
                    <option value="Unauthorized charges">Unauthorized charges</option>
                    <option value="other">Other</option>
                </select>
            </div>
            <div id="otherReason" class="form-group">
                <label for="customReason" class="form-label">Custom Reason:</label>
                <input type="text" name="customReason" id="customReason" class="form-control">
            </div>
            <div class="text-center">
                <input type="submit" value="Submit" class="btn btn-green">
            </div>
        </form>
    </div>
    <%@include file="include/header.jsp" %>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        document.getElementById("reason").addEventListener("change", function() {
            var otherReasonDiv = document.getElementById("otherReason");
            var customReasonInput = document.getElementById("customReason");
            if (this.value === "other") {
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
