<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ShopReport" %>

<%@ include file="include/header.jsp" %>

<div class="container-fluid container">
    <div class="row">
        <jsp:include page="include/navbar.jsp"/>
        <div class="col-md-9 content">
            <h2 class="text-center">Reported Shops</h2>

            <%-- Debug information --%>
            <%
                List<ShopReport> reportedShops = (List<ShopReport>) request.getAttribute("reportedShops");
                if (reportedShops != null) {
                    out.println("Number of reported shops in JSP: " + reportedShops.size());
                } else {
                    out.println("reportedShops attribute is null.");
                }
            %>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Shop ID</th>
                        <th scope="col">Reason</th>
                        <th scope="col">Reported By (User ID)</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="report" items="${reportedShops}" varStatus="status">
                        <tr id="report-${report.reportedBy}-${report.shopId}" data-shopid="${report.shopId}">
                            <td>${status.index + 1}</td>
                            <td>${report.shopId}</td>
                            <td>${report.reason}</td>
                            <td>${report.reportedBy}</td>
                            <td>
                                <!-- Form to ban user -->
                                <form action="banUser" method="post" style="display:inline;">
                                    <input type="hidden" name="userId" value="${report.reportedBy}" />
                                    <input type="hidden" name="shopId" value="${report.shopId}" />
                                    <button type="submit" class="btn btn-danger">Ban User</button>
                                </form>
                                <!-- Form to delete report -->
                                <form action="deleteReport" method="post" style="display:inline;" onsubmit="deleteReport(event, '${report.reportedBy}-${report.shopId}')">
                                    <input type="hidden" name="shopId" value="${report.shopId}" />
                                    <input type="hidden" name="userId" value="${report.reportedBy}" />
                                    <button type="submit" class="btn btn-warning">Delete Report</button>
                                </form>
                                <!-- Form to view shop -->
                                <form action="shopdetail" method="get" style="display:inline;">
                                    <input type="hidden" name="shopid" value="${report.shopId}" />
                                    <button type="submit" class="btn btn-info">View Shop</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="include/footer.jsp" %>

<script>
function deleteReport(event, reportId) {
    event.preventDefault();
    const form = event.target;
    const xhr = new XMLHttpRequest();
    xhr.open("POST", form.action, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Remove the row from the table after successful delete
            const row = document.getElementById("report-" + reportId);
            if (row) {
                row.remove();
                updateRowNumbers();
            }
        }
    };
    const formData = new FormData(form);
    const params = new URLSearchParams(formData).toString();
    xhr.send(params);
}

function updateRowNumbers() {
    const rows = document.querySelectorAll("tbody tr");
    rows.forEach((row, index) => {
        const cell = row.querySelector("td:first-child");
        cell.textContent = index + 1;
    });
}
</script>
