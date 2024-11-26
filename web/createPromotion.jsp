<%@include file="include/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Voucher</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-container {
            margin-top: -500px;
              margin-left: 100px; 
            padding: 20px;
  
            border: 1px solid #ddd; /* Light border */
            border-radius: 8px;
        }
        .navbar-container {
            margin-bottom: 20px; /* Space below navbar */
            margin-left: 60px;  

            color: #ffffff; /* Navbar text color */
            padding: 10px 0; /* Vertical padding */
            border-radius: 8px;
        }
        .form-title {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 navbar-container">
            <jsp:include page="/include/navbar.jsp"/>
        </div>
    </div>
    <div class="row justify-content-center">
        
        <div class="col-md-6">
            <div class="container form-container">
                <h2 class="form-title text-center">Tạo Voucher</h2>
                <form action="createVoucher" method="post">
                    <div class="mb-3">
                        <label for="promotionName" class="form-label">Tên khuyến mãi</label>
                        <input type="text" class="form-control" id="promotionName" name="promotionName" required>
                    </div>
                    <div class="mb-3">
                        <label for="pecentPromotion" class="form-label">Phần trăm giảm giá</label>
                        <input type="number" class="form-control" id="pecentPromotion" name="pecentPromotion" min="0" max="100" required>
                    </div>
                    <div class="mb-3">
                        <label for="quantity" class="form-label">Số lượng</label>
                        <input type="number" class="form-control" id="quantity" name="quantity" min="1" required>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Mô tả</label>
                        <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Tạo Voucher</button>
                    </div>
                </form>
                <% if (request.getAttribute("successMessage") != null) { %>
                    <div class="alert alert-success mt-4">
                        <%= request.getAttribute("successMessage") %>
                        <a href="listVoucher.jsp" class="btn btn-success ml-3">Xem khuyến mãi</a>
                    </div>
                <% } %>
            </div>
        </div>
    </div>
</div>

<%@include file="include/footer.jsp" %>

</body>
</html>
