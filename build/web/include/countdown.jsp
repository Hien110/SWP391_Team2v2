<%-- 
    Document   : countdown
    Created on : Jun 2, 2024, 4:06:36 PM
    Author     : DELL
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Countdown Timer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <style>
        .countdown {
            display: flex;
            justify-content: center;
        }
        .countdown div {
            padding: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <div class="countdown">
            <div>
                <h4 id="days">0</h4>
                <small>Days</small>
            </div>
            <div>
                <h4 id="hours">0</h4>
                <small>Hours</small>
            </div>
            <div>
                <h4 id="minutes">0</h4>
                <small>Minutes</small>
            </div>
            <div>
                <h4 id="seconds">0</h4>
                <small>Seconds</small>
            </div>
        </div>
    </div>

    <script>
        function calculateTimeLeft(endDate) {
            const difference = new Date(endDate).getTime() - new Date().getTime();
            let timeLeft = {};

            if (difference > 0) {
                timeLeft = {
                    days: Math.floor(difference / (1000 * 60 * 60 * 24)),
                    hours: Math.floor((difference / (1000 * 60 * 60)) % 24),
                    minutes: Math.floor((difference / 1000 / 60) % 60),
                    seconds: Math.floor((difference / 1000) % 60),
                };
            } else {
                timeLeft = { days: 0, hours: 0, minutes: 0, seconds: 0 };
            }

            return timeLeft;
        }

        function updateCountdown(endDate) {
            const timeLeft = calculateTimeLeft(endDate);
            document.getElementById("days").innerText = timeLeft.days;
            document.getElementById("hours").innerText = timeLeft.hours;
            document.getElementById("minutes").innerText = timeLeft.minutes;
            document.getElementById("seconds").innerText = timeLeft.seconds;
        }

        window.onload = function() {
            const endDate = new Date("<%= request.getAttribute('endDate') %>");
            updateCountdown(endDate);

            setInterval(function() {
                updateCountdown(endDate);
            }, 1000);
        };
    </script>
</body>
</html>

