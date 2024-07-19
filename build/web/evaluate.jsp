<%
        HttpSession currentSession = request.getSession();
        User loggedInAccount = (User) currentSession.getAttribute("user");
        // Ki?m tra n?u ng??i dùng chua ??ng nh?p
        if (loggedInAccount == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
%>
<%@include file="include/header.jsp" %>
<%@include file="include/evaluate.jsp" %>  
<%@include file="include/footer.jsp" %> 
<%@ page import="model.User" %>
