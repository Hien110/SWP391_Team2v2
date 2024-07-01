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
                            <h1>Địa chỉ của tôi</h1>
                            <button id="addAddressBtn" type="button">+ Thêm địa chỉ mới</button>
                        </div>
                        <c:forEach var="info" items="${info}">
                            <div class="address">
                                <div class="address-info">
                                    <p><strong>${info.customerName}</strong></p>
                                    <p>${info.phoneCustomer}</p>
                                    <p>${info.addressCustomer}</p>
                                </div>
                                <div class="address-actions">
                                    <button type="button">Cập nhập</button>
                                    <button type="button">Xoá</button>
                                </div>
                            </div>
                        </c:forEach>

                        <!-- The Modal -->
                        <form action="./infocustomer" method="POST">
                            <div id="myModal" class="modal">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h2>Địa chỉ mới</h2>
                                    </div>
                                    <div class="modal-body"style="padding-left: 100px">

                                        <div class="form-group">
                                            <p class="name" style="width: 120px; color: #000">Họ và tên</p>
                                            <input type="text" class="form-control" required name="cusname">
                                        </div>
                                        <div class="form-group">
                                            <p class="name" style="width: 120px; color: #000">Số điện thoại</p>
                                            <input type="number" class="form-control" required name="cusphone">
                                        </div>
                                        <div class="form-group">
                                            <p class="name" style="width: 120px; color: #000">Địa chỉ</p>
                                            <select class="css_select" id="tinh" name="tinh" style="width: 180px; " title="Chọn Tỉnh Thành">
                                                <option value="0">Tỉnh Thành</option>
                                            </select>
                                            <select class="css_select" id="quan" name="quan" style="width: 180px;" title="Chọn Quận Huyện">
                                                <option value="0">Quận Huyện</option>
                                            </select>
                                            <select class="css_select" id="phuong" name="phuong" style="width: 180px;" title="Chọn Phường Xã">
                                                <option value="0">Phường Xã</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <p class="name" style="width: 120px; color: #000">Địa chỉ cụ thể</p>
                                            <input type="text" class="form-control" required name="diachi">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button >Xác Nhận</button>
                                        <button type="button" class="close" onclick="closeModal()">Hủy</button>
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
        <script>
            var modal = document.getElementById("myModal");
            var btn = document.getElementById("addAddressBtn");

            btn.onclick = function () {
                modal.style.display = "flex";
                loadTinh();
            }

            function closeModal() {
                modal.style.display = "none";
            }

            function submitAddress() {
                // Logic to submit the address goes here
                closeModal();
            }

            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

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
