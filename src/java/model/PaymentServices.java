package model;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServices {

    private static final String CLIENT_ID = "AbYsPVv48j7kPrBLJiLJ7m8qxnJh2HUd8G8LxWHnhzzqfvdIx80FKKmPNNqi7cLTQQxrXZTs8E9DuDtx";
    private static final String CLIENT_SECRET = "EGm3EYIbpQuFkneoqvMTSQZVVvUSJZn87OlQdQTpGAjK1Zz_B4z9Hdop2chIrHqBuugkUWljmJ6XWz20";
    private static final String MODE = "sandbox";

    public String authorizePayment(double surplus, String emailuser, String check) throws PayPalRESTException {
        // Thiết lập thông tin người trả
        Payer payer = getPayerInformation(emailuser);

        // Thiết lập URL chuyển hướng
        RedirectUrls redirectUrls = getRedirectURLs();

        // Thiết lập thông tin giao dịch
        List<Transaction> listTransactions = getTransactionInformation(surplus, check);

        // Tạo đối tượng Payment
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransactions);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        // Tạo APIContext
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        // Tạo thanh toán
        Payment approvedPayment = requestPayment.create(apiContext);

        // Lấy và trả về liên kết chấp thuận
        return getApprovalLink(approvedPayment);
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                return link.getHref();
            }
        }
        return null;
    }

    private List<Transaction> getTransactionInformation(double surplus, String check) {
        // Tạo đối tượng Amount và thiết lập đơn vị tiền tệ và tổng số tiền
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.2f", surplus));

        // Thiết lập thông tin chi tiết giao dịch
        Details details = new Details();
        details.setSubtotal(String.format("%.2f", surplus));
        details.setHandlingFee(check); // Đặt giá trị check vào HandlingFee

        // Tạo đối tượng Transaction và thiết lập các thông tin giao dịch
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Add funds to wallet");
        transaction.setDescription("Add funds to wallet - Check: " + check);

        // Thiết lập thông tin người nhận tiền (payee) chỉ với email
        Payee payee = new Payee();
        payee.setEmail("Admin1233@gmail.com"); // Email của người nhận tiền
        transaction.setPayee(payee);

        // Tạo danh sách các giao dịch và thêm giao dịch vào danh sách
        List<Transaction> listTransactions = new ArrayList<>();
        listTransactions.add(transaction);

        return listTransactions;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/SWP391_Team2v2/cancel.html");
        redirectUrls.setReturnUrl("http://localhost:8080/SWP391_Team2v2/review_payment");
        return redirectUrls;
    }

    public Payment getPaymentDetail(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        return payment.execute(apiContext, paymentExecution);
    }

    // Thiết lập thông tin người trả
    private Payer getPayerInformation(String emailuser) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setEmail(emailuser);

        payer.setPayerInfo(payerInfo);
        return payer;
    }
}
