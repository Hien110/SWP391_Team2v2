<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Product List</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
            }
            h1 {
                color: #2e7d32;
                text-align: center;
            }
            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                background-color: #e8f5e9;
            }
            th, td {
                border: 1px solid #2e7d32;
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #66bb6a;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #c8e6c9;
            }
        </style>
    </head>
    <body>
        <h1>Report Product List</h1>
        <table>
            <thead>
                <tr>
                    <th>Shop Name</th>
                    <th>Address</th>
                    <th>Reason Report</th>
                    <th>User Full Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="l" items="${l}">
                    <tr>
                        <td>${l.shopName}</td>
                        <td>${l.address}</td>
                        <td>${l.reasonReport}</td>
                        <td>${l.fullName}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <%@include file="include/footer.jsp" %>  
    </body>
</html>



