<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Product Confirmation</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Delete Product</h3>
            </div>
            <div class="card-body">
                <form action="delete" method="post">
                    <input type="hidden" name="pid" value="${param.pid}">
                    <div class="alert alert-warning" role="alert">
                        Are you sure you want to delete this product?
                    </div>
                    <a href="deleteProductShopOwnerServlet" class="btn btn-primary"><button type="submit">Delete</button></a>
                    <a href="ListProductShopOwnerServlet" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

