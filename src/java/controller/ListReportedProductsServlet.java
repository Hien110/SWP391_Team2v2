package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import model.ProductReport;
import repository.ReportedProductDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ListReportedProductsServlet", urlPatterns = {"/listReportedProducts"})
public class ListReportedProductsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ReportedProductDAO rpDAO = new ReportedProductDAO();
        List<ProductReport> reportedProducts = null;
        try {
            reportedProducts = rpDAO.getReportedProducts();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(ListReportedProductsServlet.class.getName()).log(Level.SEVERE, null, e);
            // Forward to an error page or handle the error appropriately
            request.setAttribute("errorMessage", "Error fetching reported products: " + e.getMessage());
            RequestDispatcher errorDispatcher = request.getRequestDispatcher("errorPage.jsp");
            errorDispatcher.forward(request, response);
            return;
        }

        // Add sequence number to each report
        for (int i = 0; i < reportedProducts.size(); i++) {
            reportedProducts.get(i).setSequenceNumber(i + 1);
        }

        request.setAttribute("reportedProducts", reportedProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listReportedProducts.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "deleteReport":
                    deleteReport(request, response);
                    break;
                case "deleteProduct":
                    deleteProduct(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/listReportedProducts");
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/listReportedProducts");
        }
    }

    private void deleteReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reportProductIdStr = request.getParameter("reportProductId");
        if (reportProductIdStr == null || reportProductIdStr.isEmpty()) {
            // Handle case where reportProductId parameter is missing or empty
            response.sendRedirect(request.getContextPath() + "/listReportedProducts");
            return;
        }

        int reportProductId = Integer.parseInt(reportProductIdStr);

        try {
            ReportedProductDAO rpDAO = new ReportedProductDAO();
            rpDAO.deleteReport(reportProductId);
            // Thông báo thành công nếu cần thiết
            request.setAttribute("successMessage", "Report deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(ListReportedProductsServlet.class.getName()).log(Level.SEVERE, null, e);
            // Forward to an error page or handle the error appropriately
            request.setAttribute("errorMessage", "Error deleting report: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/listReportedProducts");
    }

   private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String productIdStr = request.getParameter("productId");
    if (productIdStr == null || productIdStr.isEmpty()) {
        // Handle case where productId parameter is missing or empty
        response.sendRedirect(request.getContextPath() + "/listReportedProducts");
        return;
    }

    int productId = Integer.parseInt(productIdStr);

    try {
        ReportedProductDAO rpDAO = new ReportedProductDAO();
        
        // Xoá tất cả các báo cáo liên quan đến sản phẩm
        rpDAO.deleteReportsByProductId(productId);
        
        // Xoá sản phẩm
        rpDAO.deleteProduct(productId);
        
        // Thông báo thành công nếu cần thiết
        request.setAttribute("successMessage", "Product and its reports deleted successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
        Logger.getLogger(ListReportedProductsServlet.class.getName()).log(Level.SEVERE, null, e);
        // Forward to an error page or handle the error appropriately
        request.setAttribute("errorMessage", "Error deleting product and its reports: " + e.getMessage());
    }

    response.sendRedirect(request.getContextPath() + "/listReportedProducts");
}

    @Override
    public String getServletInfo() {
        return "Servlet for listing reported products";
    }
}
