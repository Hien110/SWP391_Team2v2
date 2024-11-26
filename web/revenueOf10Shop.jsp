<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="include/header.jsp" %>

<div class="container-fluid container">
    <div class="row">
        <jsp:include page="include/navbar.jsp"/>
        <div class="col-md-9 content">
            <h2 class="text-center">Top 10 Shops by Shipped Orders
</h2>
            <canvas id="salesChart" width="800" height="400"></canvas>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        fetch('sale10Data')
            .then(response => response.json())
            .then(data => {
                const labels = Object.keys(data);
                const quantities = Object.values(data);

                const backgroundColors = labels.map(() => getRandomColor());

                const ctx = document.getElementById('salesChart').getContext('2d');
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'Total Shipped Orders',
                            data: quantities,
                            backgroundColor: backgroundColors,
                            borderColor: backgroundColors.map(color => darkenColor(color, 20)),
                            borderWidth: 1
                        }]
                    },
                    options: {
                        indexAxis: 'x',
                        scales: {
                            x: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    });

    function getRandomColor() {
        const letters = '0123456789ABCDEF';
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    function darkenColor(color, percent) {
        let R = parseInt(color.substring(1, 3), 16);
        let G = parseInt(color.substring(3, 5), 16);
        let B = parseInt(color.substring(5, 7), 16);

        R = parseInt(R * (100 - percent) / 100);
        G = parseInt(G * (100 - percent) / 100);
        B = parseInt(B * (100 - percent) / 100);

        R = (R < 255) ? ((R < 0) ? 0 : R) : 255;
        G = (G < 255) ? ((G < 0) ? 0 : G) : 255;
        B = (B < 255) ? ((B < 0) ? 0 : B) : 255;

        let RR = ((R.toString(16).length === 1) ? "0" + R.toString(16) : R.toString(16));
        let GG = ((G.toString(16).length === 1) ? "0" + G.toString(16) : G.toString(16));
        let BB = ((B.toString(16).length === 1) ? "0" + B.toString(16) : B.toString(16));

        return "#" + RR + GG + BB;
    }
</script>

<%@ include file="include/footer.jsp" %>
