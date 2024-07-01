<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%
    // Set the end date for the countdown timer
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 7); // Example: promotion ends in 7 days
    Date endDate = calendar.getTime();
    String formattedEndDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(endDate);
    request.setAttribute("formattedEndDate", formattedEndDate); // Set the attribute to pass to included JSP
%>

<%@include file="include/header.jsp" %> <!-- Include header -->
<%@include file="include/index.jsp" %>  <!-- Include main content -->
<%@include file="include/footer.jsp" %>  <!-- Include footer -->
