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

@WebServlet(name = "DeleteReportProductsServlet", urlPatterns = {"/deleteReportProducts"})
public class DeleteReportProductsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "deleteReport":
                    deleteReport(request, response);
                    return;
                case "deleteProduct":
                    deleteProduct(request, response);
                    return;
                default:
                    break;
            }
        }

        ReportedProductDAO rpDAO = new ReportedProductDAO();
        List<ProductReport> reportedProducts = null;
        try {
            reportedProducts = rpDAO.getReportedProducts();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(DeleteReportProductsServlet.class.getName()).log(Level.SEVERE, null, e);
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

    private void deleteReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int reportProductId = Integer.parseInt(request.getParameter("reportProductId"));

        try {
            ReportedProductDAO rpDAO = new ReportedProductDAO();
            rpDAO.deleteReportsByProductId(reportProductId);
            request.setAttribute("successMessage", "Report deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(DeleteReportProductsServlet.class.getName()).log(Level.SEVERE, null, e);
            // Forward to an error page or handle the error appropriately
            request.setAttribute("errorMessage", "Error deleting report: " + e.getMessage());
            RequestDispatcher errorDispatcher = request.getRequestDispatcher("errorPage.jsp");
            errorDispatcher.forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/listReportedProducts");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        try {
            ReportedProductDAO rpDAO = new ReportedProductDAO();
            rpDAO.deleteProduct(productId);
            request.setAttribute("successMessage", "Product deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(DeleteReportProductsServlet.class.getName()).log(Level.SEVERE, null, e);
            // Forward to an error page or handle the error appropriately
            request.setAttribute("errorMessage", "Error deleting product: " + e.getMessage());
            RequestDispatcher errorDispatcher = request.getRequestDispatcher("errorPage.jsp");
            errorDispatcher.forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/listReportedProducts");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for listing and managing reported products";
    }
}
