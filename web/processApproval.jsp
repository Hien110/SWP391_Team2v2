<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <!-- Add other meta tags, stylesheets, and scripts as needed -->

    <!-- App css -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/icons.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/app.min.css" rel="stylesheet">
</head>
<body>

<div class="content-page">
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card-box">
                        <h4 class="header-title">User List</h4>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>User ID</th>
                                <th>Username</th>
                                <th>Email</th>
                                <!-- Add more table headers if needed -->
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- Iterate over users and display their information -->
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.userid}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <!-- Thêm các cột khác nếu cần -->

                                    <!-- Thêm nút chấp nhận và từ chối -->
                                    <td>
                                        <button class="btn btn-success btn-sm" onclick="acceptUser('${user.userid}')">Chấp nhận</button>
                                        <button class="btn btn-danger btn-sm" onclick="rejectUser('${user.userid}')">Từ chối</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




</body>
</html>
