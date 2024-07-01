<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report Product</title>
<link rel="stylesheet" type="text/css" href="./css/reportProduct.css">
</head>
<body>
    <div class="container">
        <h2>Report Product</h2>
        <form action="reportproduct" method="post">
            <label for="reason">Reason:</label>
            <select name="reason" id="reason">
                <option value="Fake product">Fake product</option>
                <option value="Damaged item">Damaged item</option>
                <option value="Not as described">Not as described</option>
                <option value="Late delivery">Late delivery</option>
                <option value="Poor quality">Poor quality</option>
                <option value="other">Other</option>
            </select>
            <div id="otherReason">
                <label for="customReason">Custom Reason:</label>
                <input type="text" name="customReason" id="customReason">
            </div>
            <input type="submit" value="Submit">
        </form>
    </div>
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
