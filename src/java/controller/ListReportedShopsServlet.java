package controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ShopReport;
import repository.ReportedShopDAO;

@WebServlet(name = "ListReportedShopsServlet", urlPatterns = {"/listReportedShops"})
public class ListReportedShopsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ReportedShopDAO rpDAO = new ReportedShopDAO();
        List<ShopReport> reportedShops = rpDAO.getAllReportedShops();

        // Logging to check the reportedShops list
        if (reportedShops != null) {
            System.out.println("Number of reported shops: " + reportedShops.size());
            for (ShopReport report : reportedShops) {
                System.out.println("Shop ID: " + report.getShopId());
                System.out.println("Reason: " + report.getReason());
                System.out.println("Reported By: " + report.getReportedBy());
            }
        } else {
            System.out.println("No reported shops found.");
        }

        // Set attribute for JSP
        request.setAttribute("reportedShops", reportedShops);

        // Forward to JSP
        request.getRequestDispatcher("listReportedShops.jsp").forward(request, response);
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
        return "Servlet for listing reported shops";
    }
}
