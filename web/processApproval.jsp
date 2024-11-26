<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    function processApproval(userId, action) {
        $.ajax({
            url: '${pageContext.request.contextPath}/processApproval.jsp',
            type: 'POST',
            data: {
                userId: userId,
                action: action
            },
            success: function (response) {
                $('#user-row-' + userId).remove();
                console.log(response); // Để kiểm tra phản hồi từ server
            },
            error: function (xhr, status, error) {
                alert('Error processing request: ' + xhr.responseText);
            }
        });
    }

</script>

<div class="container-fluid container">
    <div class="row">
        <jsp:include page="include/navbar.jsp"/>
        <div class="col-md-9 content">
            <div class="content-page">
                <div class="content">
                    <div class="container-fluid">
                        <h2 class="text-center">Duyệt đơn đăng ký bán hàng</h2>
                        <h4 class="header-title">User Approval</h4>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card-box">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>User ID</th>
                                                <th>Username</th>
                                                <th>Email</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <!-- Iterate over users and display their information -->
                                            <c:forEach var="user" items="${users}" varStatus="loop">
                                             <tr id="user-row-${user.userid}">
                                                    <td>${loop.index + 1}</td>
                                                    <td>${user.userid}</td>
                                                    <td>${user.username}</td>
                                                    <td>${user.email}</td>
                                                    <td>
                                                        <form action="./processApproval" method="post">
                                                            <input type="hidden" value="${user.userid}" name="userId"/>
                                                             <input type="hidden" value="accept" name="action"/> 
                                                            <button class="btn btn-success btn-sm">Accept</button>
                                                        </form>
                                                             
                                                            <form  action="./processApproval" method="post">
                                                               <input type="hidden" value="${user.userid}" name="userId"/> 
                                                                <input type="hidden" value="reject" name="action"/> 
                                                        <button  class="btn btn-danger btn-sm">Reject</button>
                                                            </form>

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
        </div>
    </div>
</div>

<%@include file="include/footer.jsp" %>
