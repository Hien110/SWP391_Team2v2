package controller;

import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PaymentServices;

@WebServlet(name = "AuthorizePaymentServlet", urlPatterns = {"/authorize_payment"})
public class AuthorizePaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AuthorizePaymentServlet() {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String surplusStr = request.getParameter("money");
        String emailUser = request.getParameter("emailpaypal");
        String check = request.getParameter("check"); // Nhận giá trị check từ yêu cầu

        if (surplusStr == null || emailUser == null || check == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
            return;
        }

        try {
            double surplus = Double.parseDouble(surplusStr);
            PaymentServices paymentServices = new PaymentServices();
            String approvalLink = paymentServices.authorizePayment(surplus, emailUser, check); // Truyền giá trị check
            response.sendRedirect(approvalLink + "&check=" + check); // Thêm giá trị check vào URL
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format.");
        } catch (PayPalRESTException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Error processing payment");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles payment authorization using PayPal";
    }
}
