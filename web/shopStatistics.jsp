<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ShopReport" %>
<%@ page import="java.util.Map" %>

<%@ include file="include/header.jsp" %>

<div class="container-fluid container">
    <div class="row">
        <jsp:include page="include/navbar.jsp"/>
        <div class="col-md-9 content">
            <h2 class="text-center">Báo Cáo Doanh Thu</h2>
            
            <div class="row">
                <div class="col-md-6">
                    <h4>Doanh Thu Hàng Tháng</h4>
                    <canvas id="monthlyRevenueChart" width="400" height="300"></canvas>
                </div>
                <div class="col-md-6">
                    <h4>Doanh Thu Sản Phẩm</h4>
                    <canvas id="productSalesChart" width="400" height="300"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="include/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    // Dữ liệu doanh thu tháng
    var monthlyRevenueLabels = [
        <c:forEach var="entry" items="${monthlyRevenue}">
            "${entry.key}",
        </c:forEach>
    ];
    var monthlyRevenueData = [
        <c:forEach var="entry" items="${monthlyRevenue}">
            ${entry.value},
        </c:forEach>
     ];

    // Dữ liệu sản phẩm bán ra
    var productSalesLabels = [
        <c:forEach var="entry" items="${productSales}">
            "${entry.key}",
        </c:forEach>
    ];
    var productSalesData = [
        <c:forEach var="entry" items="${productSales}">
            ${entry.value},
        </c:forEach>
    ];

    // Biểu đồ doanh thu tháng
    var ctx1 = document.getElementById('monthlyRevenueChart').getContext('2d');
    var monthlyRevenueChart = new Chart(ctx1, {
        type: 'bar',
        data: {
            labels: monthlyRevenueLabels,
            datasets: [{
                label: 'Monthly Revenue',
                data: monthlyRevenueData,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    // Biểu đồ sản phẩm bán ra
    var ctx2 = document.getElementById('productSalesChart').getContext('2d');
    var productSalesChart = new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: productSalesLabels,
            datasets: [{
                label: 'Product Sales',
                data: productSalesData,
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
  