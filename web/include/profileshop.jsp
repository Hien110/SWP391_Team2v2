<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 12:36:25 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profileCSS.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://esgoo.net/scripts/jquery.js"></script>
        <style>
            .file-upload-wrapper {
                position: relative;
                overflow: hidden;
                display: inline-block;
                top: 9px;
            }


            .file-upload-input {
                position: absolute;
                left: 0;
                top: 0;
                opacity: 0;
            }
        </style>
    </head>
    <body>
        <div class="full">
            <div class="container-fluid container">
                <div class="row">
                    <jsp:include page="/include/navbarshop.jsp"/>
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
                                    <form method="post" action="./fileuploadservlet" enctype="multipart/form-data">
                                        <div class="file-upload-wrapper">
                                            <button class="file-upload-button" type="button" style="border: 1px solid #2a8341; background-color: #fff; border-radius: 5px; color: #2a8341;">Thay đổi avatar</button>
                                            <input class="file-upload-input" type="file" name="file" />
                                        </div >
                                        <input type="submit" value="Cập nhập" style="background-color: #2a8341; border-radius: 5px; border: #2a8341; color: #fff;" />
                                    </form>
                                </div>
                            </div>
                            <form method="post" action="./updateprofileshop" >
                                <div>
                                    <div class="profile-details">
                                        <div class="form-group">
                                            <p class="name">Tên cửa hàng</p>
                                            <input name="shopname" type="text" class="form-control" id="username" value="${sessionScope.shop.shopName}">
                                        </div>
                                        <div class="form-group">
                                            <p class="name">Mô tả</p>
                                            <textarea name="shopdesc" type="text" class="form-control" id="fullname" >${sessionScope.shop.desshop}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <p class="name">Địa chỉ</p>
                                            <input name="address" type="email" class="form-control" id="email" readonly value="${sessionScope.shop.address}">                                       
                                        </div>
                                        <div class="form-group">
                                            <p class="name" style="width: 245px; color: #878787">Địa chỉ mới</p>
                                            <select class="css_select" id="tinh" name="tinh" title="Chọn Tỉnh Thành" style="width: 250px; height: 35px; border-radius: 5px; border:1px solid #ccc">
                                                <option value="0">Tỉnh Thành</option>
                                            </select> 
                                            <select class="css_select" id="quan" name="quan" title="Chọn Quận Huyện" style="width: 250px; height: 35px; border-radius: 5px; border:1px solid #ccc">
                                                <option value="0">Quận Huyện</option>
                                            </select> 
                                            <select class="css_select" id="phuong" name="phuong" title="Chọn Phường Xã" style="width: 250px; height: 35px; border-radius: 5px; border:1px solid #ccc">
                                                <option value="0">Phường Xã</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <p class="name" >Địa chỉ cụ thể</p>
                                            <input type="text" class="form-control" name="diachi">
                                        </div>
                                        <div class="form-group">
                                            <p class="name">Số điện thoại</p>
                                            <input name="phonenumber" type="text" class="form-control" id="phonenumber" readonly value="${sessionScope.user.phonenumber}">
                                        </div>
                                    </div>
                                    <p style=" text-align: center;color: green;font-weight: 400">${requestScope.success}</p>
                                    <div style="width: 100%; display: flex; justify-content: center;">
                                        <button class="btn delete mt-3">Cập nhập hồ sơ</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>

            $(document).ready(function () {
                //Lấy tỉnh thành
                $.getJSON('https://esgoo.net/api-tinhthanh/1/0.htm', function (data_tinh) {
                    if (data_tinh.error == 0) {
                        $.each(data_tinh.data, function (key_tinh, val_tinh) {
                            $("#tinh").append('<option value="' + val_tinh.id + '">' + val_tinh.full_name + '</option>');
                        });
                        $("#tinh").change(function (e) {
                            var idtinh = $(this).val();
                            //Lấy quận huyện
                            $.getJSON('https://esgoo.net/api-tinhthanh/2/' + idtinh + '.htm', function (data_quan) {
                                if (data_quan.error == 0) {
                                    $("#quan").html('<option value="0">Quận Huyện</option>');
                                    $("#phuong").html('<option value="0">Phường Xã</option>');
                                    $.each(data_quan.data, function (key_quan, val_quan) {
                                        $("#quan").append('<option value="' + val_quan.id + '">' + val_quan.full_name + '</option>');
                                    });
                                    //Lấy phường xã  
                                    $("#quan").change(function (e) {
                                        var idquan = $(this).val();
                                        $.getJSON('https://esgoo.net/api-tinhthanh/3/' + idquan + '.htm', function (data_phuong) {
                                            if (data_phuong.error == 0) {
                                                $("#phuong").html('<option value="0">Phường Xã</option>');
                                                $.each(data_phuong.data, function (key_phuong, val_phuong) {
                                                    $("#phuong").append('<option value="' + val_phuong.id + '">' + val_phuong.full_name + '</option>');
                                                });
                                            }
                                        });
                                    });

                                }
                            });
                        });

                    }
                });
            });


            document.querySelector('.custom-file-input').addEventListener('change', function (event) {
                var fileName = event.target.files[0].name;
                var label = document.querySelector('.custom-file-label');
                label.textContent = fileName ? fileName : 'Upload File';
            });

        </script>
    </body>
</html>
