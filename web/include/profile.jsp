<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 12:36:25 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profileCSS.css">
    </head>
    <script>
        // Lấy giá trị của input khi thay đổi lựa chọn
        document.querySelectorAll('input[name="gender"]').forEach((elem) => {
            elem.addEventListener("change", function (event) {
                const value = event.target.value;
            });
        });
        document.getElementById("dateForm").addEventListener("submit", function (event) {
            event.preventDefault();
            var date = document.getElementById("date").value;
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "DateServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    document.getElementById("result").innerText = xhr.responseText;
                }
            };
            xhr.send("date=" + date);
        });
    </script>
    <body>
        <form method="post" action="./updateprofileuser" >
            <div class="full">
                <div class="container-fluid container">
                    <div class="row">
                        <jsp:include page="/include/navbar.jsp"/>
                        <div class="col-md-9 content">
                            <h1 style="font-size: 24px; border-bottom: 2px solid #ddd; padding-bottom: 10px; margin: 8px 0 20px 0">Hồ sơ của tôi</h1>
                            <div class="profile-info">
                                <div class="profile-avatar">
                                    <c:choose>
                                        <c:when test="${sessionScope.user.imgavt == null}">
                                            <i style="font-size:120px" class="fa-regular fa-circle-user size"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <img style="border-radius: 50%;" src="${sessionScope.user.imgavt}" alt="Avatar">
                                        </c:otherwise>
                                    </c:choose>
                                    <div>
                                        <div class="username">${sessionScope.user.username}</div>
                                        <input type="file" name="file" />
                                        <input type="submit" value="Upload" />
                                    </div>
                                </div>
                                <div class="profile-details">
                                    <div class="form-group">
                                        <p class="name">Tên đăng nhập</p>
                                        <input name="usernmae" type="text" class="form-control" id="username" disabled value="${sessionScope.user.username}">
                                    </div>
                                    <div class="form-group">
                                        <p class="name">Họ và tên</p>
                                        <input name="fullname" type="text" class="form-control" id="fullname" value="${sessionScope.user.fullname}">
                                    </div>
                                    <div class="form-group">
                                        <p class="name">Email</p>
                                        <input name="email" type="email" class="form-control" id="email" disabled value="${sessionScope.user.email}">
                                    </div>
                                    <div class="form-group">
                                        <p class="name">Số điện thoại</p>
                                        <input name="phonenumber" type="text" class="form-control" id="phonenumber" value="${sessionScope.user.phonenumber}">
                                    </div>
                                    <div class="form-group">
                                        <p class="name">Giới tính</p>
                                        <div style="display: flex; width: 100%; justify-content: space-around">
                                            <div style="display: flex; align-items: center;">
                                                <label class="round-radio" style="margin-right: 10px;">
                                                    <input type="radio" name="gender" value="1" style="height: 20px; width: 20px;"
                                                           ${sessionScope.user.gender == null ? '' :sessionScope.user.gender == true ? 'checked' : ''}>
                                                    <span></span>
                                                </label>
                                                <span class="form-control" style="width: 100px; text-align: center">Nam</span>
                                            </div>
                                            <div style="display: flex; align-items: center;">
                                                <label class="round-radio" style="margin-right: 10px;">
                                                    <input type="radio" name="gender" value="0" style="height: 20px; width: 20px;"
                                                           ${sessionScope.user.gender == null ? '' : sessionScope.user.gender == false ? 'checked' : ''}>
                                                    <span></span>
                                                </label>
                                                <span class="form-control" style="width: 100px; text-align: center">Nữ</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group" id="dateForm">
                                        <p class="name">Ngày sinh</p>
                                        <input class="form-control" type="date" id="date" name="date" value=${sessionScope.user.dob}>
                                    </div>
                                </div>
                                <p style=" text-align: center;color: green;font-weight: 400">${requestScope.success}</p>
                                <div style="width: 100%; display: flex; justify-content: center;">
                                    <button class="btn delete mt-3">Cập nhập hồ sơ</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
    <script>
        document.querySelector('.custom-file-input').addEventListener('change', function (event) {
            var fileName = event.target.files[0].name;
            var label = document.querySelector('.custom-file-label');
            label.textContent = fileName ? fileName : 'Upload File';
        });
    </script>
</html>
