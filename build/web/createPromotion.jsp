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
        /* Điều chỉnh margin giữa navbar và form */
        .form-container {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <jsp:include page="/include/navbar.jsp"/>
        </div>
        <div class="col-md-8 form-container">
            <div class="container">
                <h2 class="text-center">Tạo Voucher</h2>
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
                    <div class="mb-3">
                        <label for="startDate" class="form-label">Ngày bắt đầu</label>
                        <input type="date" class="form-control" id="startDate" name="startDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="endDate" class="form-label">Ngày kết thúc</label>
                        <input type="date" class="form-control" id="endDate" name="endDate" required>
                    </div>
                    <div class="text-center"> <!-- Đặt nội dung ở giữa -->
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
