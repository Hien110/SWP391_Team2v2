package controller;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.PaymentServices;
import model.User;

@WebServlet(name = "ReviewPaymentServlet", urlPatterns = {"/review_payment"})
public class ReviewPaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ReviewPaymentServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("home.jsp"); // Redirect to home if user is not logged in
            return;
        }

        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        if (paymentId == null || payerId == null) {
            request.setAttribute("errorMessage", "Payment ID hoặc Payer ID bị thiếu.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.getPaymentDetail(paymentId);

            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            String check = transaction.getDescription().split("Check: ")[1]; // Lấy giá trị check từ mô tả

            // Đặt các thuộc tính vào yêu cầu
            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);
            request.setAttribute("userid", user.getUserid());
            request.setAttribute("password", user.getPassword());
            request.setAttribute("check", check); // Truyền giá trị check đến JSP

            // Chuyển tiếp đến reviewTransaction.jsp
            request.getRequestDispatcher("reviewTransaction.jsp").forward(request, response);
        } catch (PayPalRESTException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Không thể lấy chi tiết thanh toán.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Giá trị check không tồn tại trong mô tả.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Xử lý xem lại chi tiết thanh toán.";
    }
}
