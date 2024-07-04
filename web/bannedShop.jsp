    
<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 12:36:25 AM
    Author     : ADMIN
--%>

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
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <form action="./changepassuser" method="post">
            <div class="full">
                <div class="container-fluid container">
                    <div class="row">
                        <jsp:include page="/include/navbar.jsp"/>
                        <div class="col-md-9 content">
                            <h2 style="font-size: 24px; border-bottom: 2px solid #ddd; padding-bottom: 10px; margin: 8px 0 20px 0">Cửa hàng bị cấm hoạt động</h2>
                                                    </div>
                    </div>
                </div>
            </div>
        </form>
                                    <%@include file="include/footer.jsp" %>   
    </body>
</html>

