<%-- 
    Document   : error
    Created on : Jun 12, 2024, 5:47:23 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Error</title>
    </head>
    <body>
        <div align="center">
            <h1>Payment Error:</h1>
            <br/>
            <h3>${errorMessage}</h3>
            <br/>
            <a href="./include/detailProduct.jsp">Go back to Product</a>
        </div>
    </body>
</html>
