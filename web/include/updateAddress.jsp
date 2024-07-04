<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet">
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/addressCSS.css">
        <script src="https://esgoo.net/scripts/jquery.js"></script>
    </head>

    <body>

        <div class="full">
            <div class="container-fluid container">
                <div class="row">
                    <jsp:include page="/include/navbar.jsp" />
                    <div class="col-md-9 content">
                        <div class="header">
                            <h1>Cập nhập địa chỉ</h1>
                        </div>
                        <c:if test="${sessionScope.error == 1}">
                            <p style=" text-align: center;color: red;font-weight: 400">Số diện thoại không hợp lệ</p>
                        </c:if>
                        <c:if test="${sessionScope.error == 2}">
                            <p style=" text-align: center;color: red;font-weight: 400">Địa chỉ không hợp lệ</p>
                        </c:if>
                        <!-- The Modal -->
                        <form action="./updateinfocus" method="POST">
                            <div id="myModal">
                                <div >
                                    <div class="modal-body"style="padding-left: 100px">
                                        <input type="hidden" class="form-control" required name="cusid" value="${requestScope.cusinfo.customerid == null ? param.cusid : requestScope.cusinfo.customerid}">
                                        <div class="form-group">
                                            <p class="name" style="width: 120px; color: #000">Họ và tên</p>
                                            <input type="text" class="form-control" required name="cusname" value="${requestScope.cusinfo.customerName == null ? param.cusname : requestScope.cusinfo.customerName}">
                                        </div>
                                        <div class="form-group">
                                            <p class="name" style="width: 120px; color: #000">Số điện thoại</p>
                                            <input type="number" class="form-control" required name="cusphone" value="${requestScope.cusinfo.phoneCustomer == null ? param.cusphone : requestScope.cusinfo.phoneCustomer}">
                                        </div>
                                        <div class="form-group">
                                            <p class="name" style="width: 120px; color: #000">Địa chỉ</p>
                                            <select class="css_select" id="tinh" name="tinh" style="width: 180px; height: 35px; border-radius: 5px; border:1px solid #ccc" required title="Chọn Tỉnh Thành" >
                                                <option value="0">Tỉnh Thành</option>
                                            </select>
                                            <select class="css_select" id="quan" name="quan" style="width: 180px; height: 35px; border-radius: 5px; border:1px solid #ccc" required title="Chọn Quận Huyện">
                                                <option value="0">Quận Huyện</option>
                                            </select>
                                            <select class="css_select" id="phuong" name="phuong" style="width: 180px; height: 35px; border-radius: 5px; border:1px solid #ccc" required title="Chọn Phường Xã">
                                                <option value="0">Phường Xã</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <p class="name" style="width: 120px; color: #000">Địa chỉ cụ thể</p>
                                            <input type="text" class="form-control" required name="diachi" value="${requestScope.shortAddress == null ? param.diachi : requestScope.shortAddress}">
                                        </div>
                                    </div>
                                    <p style=" text-align: center;color: red;font-weight: 400">${requestScope.error}</p>
                                    <div class="modal-footer" style="display: flex; justify-content: right; margin-right: 150px">
                                        <button style="border-radius: 10px">Cập nhập</button>
                                        <a href="${pageContext.request.contextPath}/infocustomer">
                                            <button type="button" class="close" onclick="closeModal()" style="border-radius: 10px; margin-left: 10px">Hủy</button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </form>

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
        </script>
    </body>
</html>
