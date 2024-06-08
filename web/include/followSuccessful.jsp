<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Follow Success</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="" crossorigin="anonymous" />
        <style>

            .btn-container {
                background-color: #ffffff; /* White background for the container */
                padding: 30px; /*30 */

                text-align: center;
                /*            max-width: 400px;*/
                width: 100%;
                margin-bottom: 30px;
                margin-top: 30px;
            }
            .btn-green {
                background-color: #28a745; /* Green button background */
                color: #ffffff; /* White text */
            }
            .btn-green:hover {
                background-color: #218838; /* Darker green on hover */
            }
            .btn-container h2 {
                color: #28a745; /* Green text color */
            }

            .btn-container button {
                margin-top: 10px;
            }
        </style>
    </head>
    <body>

        <div class="container btn-container">
            <i class="fa-solid fa-circle-check" style="font-size: 20px; color: green; margin-bottom: 10px; font-size: 50px" ></i>
            <h2>Theo Dõi Cửa Hàng Thành Công</h2>
            <p>Bạn hiện đang theo dõi cửa hàng này. Bạn sẽ nhận được thông tin cập nhật từ cửa hàng này.</p>
            <button class="btn btn-green" onclick="window.location.href = 'home.jsp'">Về Trang Chủ</button>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
