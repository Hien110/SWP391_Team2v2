<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 12:36:25 AM
    Author     : ADMIN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="https://esgoo.net/scripts/jquery.js"></script>

    </head>
    <body>
        <form action="./registertosales" method="post">
            <div class="full">
                <div class="container-fluid container">
                    <div class="row">
                        <jsp:include page="/include/navbar.jsp"/>
                        <c:choose>
                            <c:when test="${sessionScope.roleid == 3   }">
                                <div class="col-md-9 content">
                                    <h2 style="font-size: 24px; border-bottom: 2px solid #ddd; padding-bottom: 10px; margin: 8px 0 20px 0">Đăng kí bán hàng</h2>
                                    <div class="profile-info">
                                        <div class="profile-details" style="margin-top: 60px;">
                                            <div class="form-group">
                                                <p class="name" style="margin-left: 0px; color: #000">Tên của hàng</p>
                                                <input type="text" class="form-control" required name="shopname">
                                            </div>
                                            <div class="form-group">
                                                <p class="name" style="margin-left: 0px;  color: #000">Mô tả về cửa hàng</p>
                                                <textarea class="form-control" required name="shopdes"></textarea>
                                            </div>
                                            <div class="form-group" id="myModal">
                                                <p class="name" style="margin-left: 0px; color: #000">Địa chỉ cửa hàng</p>
                                                <select class="css_select" id="tinh" name="shopprovince" style="width: 180px; " title="Chọn Tỉnh Thành">
                                                    <option value="0">Tỉnh Thành</option>
                                                </select>
                                                <select class="css_select" id="quan" name="shopdistrict" style="width: 180px;" title="Chọn Quận Huyện">
                                                    <option value="0">Quận Huyện</option>
                                                </select>
                                                <select class="css_select" id="phuong" name="shopward" style="width: 180px;" title="Chọn Phường Xã">
                                                    <option value="0">Phường Xã</option>
                                                </select>
                                            </div>
                                            <div class="form-group" style=" margin-bottom: 5px; ">
                                                <p class="name" style="margin-left: 0px; color: #000">Địa chỉ cụ thể</p>
                                                <input class="form-control" required name="specificaddress">
                                            </div>
                                            <p style="color: red;font-weight: 400">${requestScope.error}</p>
                                            <p style="color: green;font-weight: 400">${requestScope.success}</p>
                                            <button class="btn btn-success delete mt-3">Đăng kí bán hàng</button>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${sessionScope.roleid == 4 }">
                                <div class="col-md-9 content" style="display: flex; justify-content: center; align-items: center;">
                                    <h3 style="margin-left: 0px; color: #000">Đang chờ phê duyệt ... </h3>
                                </div>
                            </c:when>
                        </c:choose>

                    </div>
                </div>
            </div>
        </form>
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
        </script>
    </body>
</html>
