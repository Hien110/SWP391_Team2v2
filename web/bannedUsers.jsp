<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="include/header.jsp" %>

<div class="container-fluid container">
    <div class="row">
        <%@ include file="include/navbar.jsp" %>  
        <div class="col-md-9 content">
            <h2 class="text-center">Banned Users</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">User ID</th>
                        <th scope="col">Username</th>
                        <th scope="col">Email</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${bannedUsers}">
                        <tr>
                            <td>${user.userid}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="include/footer.jsp" %>
