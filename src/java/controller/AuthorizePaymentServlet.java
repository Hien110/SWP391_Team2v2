package controller;

import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderDetail;
import model.PaymentServices;

@WebServlet(name = "AuthorizePaymentServlet", urlPatterns = {"/authorize_payment"})
public class AuthorizePaymentServlet extends HttpServlet {

    public AuthorizePaymentServlet() {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String product = request.getParameter("product");
        String subtotal = request.getParameter("subtotal");
        String shipping = request.getParameter("shipping");
        String tax = request.getParameter("tax");

        if (product == null || subtotal == null || shipping == null || tax == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
            return;
        }

        try {
            float subtotalValue = Float.parseFloat(subtotal);
            float shippingValue = Float.parseFloat(shipping);
            float taxValue = Float.parseFloat(tax);
            float totalValue = subtotalValue + shippingValue + taxValue;

            OrderDetail orderDetail = new OrderDetail(product, subtotal, shipping, tax, String.valueOf(totalValue));
            PaymentServices paymentServices = new PaymentServices();
            String approvalLink = paymentServices.authorizePayment(orderDetail);
            response.sendRedirect(approvalLink);
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
