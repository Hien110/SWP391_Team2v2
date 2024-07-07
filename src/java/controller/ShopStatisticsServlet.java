package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.StatisticsDAO;

@WebServlet(name = "ShopStatisticsServlet", urlPatterns = {"/shopStatistics"})
public class ShopStatisticsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        StatisticsDAO statsDAO = new StatisticsDAO();
        Map<String, Double> monthlyRevenue = statsDAO.getMonthlyRevenue();
        Map<String, Integer> productSales = statsDAO.getProductSales();
        request.setAttribute("monthlyRevenue", monthlyRevenue);
        request.setAttribute("productSales", productSales);
        request.getRequestDispatcher("shopStatistics.jsp").forward(request, response);
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
        return "Servlet for displaying shop statistics";
    }
}
