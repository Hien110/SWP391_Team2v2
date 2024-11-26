<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="include/header.jsp"%>

<div class="container-fluid container">
    <div class="row">
        <%@include file="include/navbar.jsp"%>
        <div class="col-md-9 content">
            <h2 class="text-center">List Reported Products</h2>

            <%-- Display success or error messages if available --%>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Product ID</th>
                        <th scope="col">Reported By (User ID)</th>
                        <th scope="col">Reason</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="report" items="${reportedProducts}" varStatus="status">
                    <tr id="report-${report.reportProductId}">
                        <td>${status.index + 1}</td>
                        <td>${report.productId}</td>
                        <td>${report.userId}</td>
                        <td>${report.reasonReport}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/listReportedProducts" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="deleteProduct" />
                                <input type="hidden" name="productId" value="${report.productId}" />
                                <button type="submit" class="btn btn-danger">Delete Product</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/detailProduct" method="get" style="display:inline;">
                                <input type="hidden" name="productId" value="${report.productId}" />
                                <input type="hidden" name="userId" value="${report.userId}" />
                                <button type="submit" class="btn btn-info">View Product</button>
                            </form>
                            <button type="button" class="btn btn-warning" onclick="deleteReport(${report.reportProductId})">Delete Report</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
            
</div>

<%@include file="include/footer.jsp"%>

<script>
function deleteReport(reportId) {
    const xhr = new XMLHttpRequest();
    const url = "${pageContext.request.contextPath}/listReportedProducts";
    const params = "action=deleteReport&reportProductId=" + reportId;
    
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const row = document.getElementById("report-" + reportId);
            if (row) {
                row.remove();
                updateRowNumbers();
            }
        }
    };
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
