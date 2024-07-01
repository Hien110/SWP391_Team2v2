<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report Shop</title>
<link rel="stylesheet" type="text/css" href="./css/reportShop.css">
</head>
<body>
    <h2>Report Shop</h2>
    <form action="reportshop" method="post">
        <label for="reason">Reason:</label>
        <select name="reason" id="reason">
            <option value="Fake shop">Fake shop</option>
            <option value="Poor service">Poor service</option>
            <option value="Misleading information">Misleading information</option>
            <option value="Unauthorized charges">Unauthorized charges</option>
            <option value="other">Other</option>
        </select>
        <br><br>
        <div id="otherReason" style="display:none;">
            <label for="customReason">Custom Reason:</label>
            <input type="text" name="customReason" id="customReason">
        </div>
        <br><br>
        <input type="submit" value="Submit">
    </form>
    
    <script>
        // Hiển thị ô nhập liệu khi chọn "Other"
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
