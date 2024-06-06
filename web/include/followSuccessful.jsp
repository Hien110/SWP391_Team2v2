<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Follow Success</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="" crossorigin="anonymous" />
        <style>
            body {
                background-color: #f8f9fa; /* Light background */
                /*            display: flex;*/
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .container {
                background-color: #ffffff; /* White background for the container */
                padding: 30px; /*30 */
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                /*            max-width: 400px;*/
                width: 100%;
            }
            .btn-green {
                background-color: #28a745; /* Green button background */
                color: #ffffff; /* White text */
            }
            .btn-green:hover {
                background-color: #218838; /* Darker green on hover */
            }
            .container h2 {
                color: #28a745; /* Green text color */
            }
        </style>
    </head>
    <body>

        <div class="container">
            <i class="fa-solid fa-circle-check" style="font-size: 20px; color: green; margin-bottom: 10px; font-size: 50px" ></i>
            <h2>Follow Shop Successful</h2>
            <p>You are now following this shop. You will receive updates from this shop.</p>
            <button class="btn btn-green" onclick="window.location.href = 'home.jsp'">Back to Home</button>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
