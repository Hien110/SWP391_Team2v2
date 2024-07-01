<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <h2 class="text-center">Add New Product</h2>
                <form action="/action_page.php">
                    <label for="Chọn loại áo quần">Chọn loại áo quần:</label>
                    <select name="clothingType" id="clothingType">
                        <option value="Áo Khoác Nữ">Áo Khoác Nữ</option>
                        <option value="Áo Nữ">Áo Nữ</option>
                        <option value="Quần Nữ">Quần Nữ</option>
                        <option value="Váy">Váy</option>
                        <option value="Đồ Ngủ, Đồ Mặc Nhà">Đồ Ngủ, Đồ Mặc Nhà</option>
                        <option value="Bộ Đồ, Jumpsuits">Bộ Đồ, Jumpsuits</option>
                        <option value="Đồng Hồ">Đồng Hồ</option>
                        <option value="Phụ Kiện Đồng Hồ">Phụ Kiện Đồng Hồ</option>
                        <option value="Trang Sức Nữ">Trang Sức Nữ</option>
                        <option value="Kính">Kính</option>
                        <option value="Dây Nịt">Dây Nịt</option>
                        <option value="Phụ Kiện Tóc">Phụ Kiện Tóc</option>
                        <option value="Nón">Nón</option>
                        <option value="Giày Nữ">Giày Nữ</option>
                        <option value="Túi Xách Nữ">Túi Xách Nữ</option>
                        <option value="Bộ Đồ Cặp Đôi">Bộ Đồ Cặp Đôi</option>
                    </select>
                    <br><br>
                    <input type="submit" value="Submit">
                </form>
                <form action="insertProduct" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="productName">Product Name</label>
                        <input name="productName" type="text" class="form-control" id="productName" required>
                    </div>
                    <div class="form-group">
                        <label for="price">Price</label>
                        <input name="price" type="number" step="10000" class="form-control" id="price" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <input name="description" type="text" class="form-control" id="description" required>
                    </div>
                    <div class="form-group">
                        <label for="quantity">Quantity</label>
                        <input name="quantity" type="number" class="form-control" id="quantity" required>
                    </div>
                    <div class="form-group">
                        <label for="productImage">Upload Image</label>
                        <input name="productImage" type="file" class="form-control-file" id="productImage" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Product</button>
                </form>
            </div>
        </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

