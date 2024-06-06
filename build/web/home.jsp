<%-- 
    Document   : home
    Created on : Jun 1, 2024, 1:26:42?AM
    Author     : DELL
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%
    // Set the end date for the countdown timer
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 7); // Example: promotion ends in 7 days
    Date endDate = calendar.getTime();
    String formattedEndDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(endDate);
    request.setAttribute("formattedEndDate", formattedEndDate); // Set the attribute to pass to included JSP
%>
<%@include file="include/header.jsp" %>
<%@include file="include/index.jsp" %>  
<%@include file="include/footer.jsp" %>   
<!--head-->