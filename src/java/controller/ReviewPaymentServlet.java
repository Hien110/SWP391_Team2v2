package controller;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PaymentServices;

@WebServlet(name = "ReviewPaymentServlet", urlPatterns = {"/review_payment"})
public class ReviewPaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ReviewPaymentServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        if (paymentId == null || payerId == null) {
            request.setAttribute("errorMessage", "Payment ID or Payer ID is missing.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.getPaymentDetail(paymentId);
            
            PayerInfo payerInfo = payment.getPayer().getPayerInfo(); 
            Transaction transaction = payment.getTransactions().get(0);
            ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
            
            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);
            request.setAttribute("shippingAddress", shippingAddress);

            // Forward to review.jsp
            request.getRequestDispatcher("review.jsp").forward(request, response);
        } catch (PayPalRESTException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Could not get payment details.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles the review of payment details.";
    }
}
