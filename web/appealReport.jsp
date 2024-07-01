<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        th {
            background-color: #2a8341;
            color: white;
        }
        img {
            width: 100px;
        }
        .btn-container {
            margin-bottom: 30px;
            margin-top: 30px;
            margin-left: 10%;
            margin-right: 10%;
        }
        .btn-container h1 {
            margin-left: 35%;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <%@include file="include/header.jsp" %>
    
    <div class="container-fluid">
        <div class="row">
            <%@include file="/include/navbar.jsp" %>
            <div class="col-md-7 content">
                <h1>Kháng cáo từ cửa hàng</h1>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Tên Cửa Hàng</th>
                            <th scope="col">Email</th>
                            <th scope="col">Lý do báo cáo</th>
                            <th scope="col">Kháng cáo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Place your table rows here -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <%@include file="include/footer.jsp" %>   
</body>
</html>
