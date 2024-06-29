package model;
// Linh
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServices {

    private static final String CLIENT_ID = "AbYsPVv48j7kPrBLJiLJ7m8qxnJh2HUd8G8LxWHnhzzqfvdIx80FKKmPNNqi7cLTQQxrXZTs8E9DuDtx";
    private static final String CLIENT_SECRET = "EGm3EYIbpQuFkneoqvMTSQZVVvUSJZn87OlQdQTpGAjK1Zz_B4z9Hdop2chIrHqBuugkUWljmJ6XWz20";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDetail orderDetail) throws PayPalRESTException {
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransactions = getTransactionInformation(orderDetail);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransactions);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
            }
        }
        return approvalLink;
    }

    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {
        Details details = new Details();
        details.setShipping(orderDetail.getShipping());
        details.setSubtotal(orderDetail.getSubtotal());
        details.setTax(orderDetail.getTax());

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(orderDetail.getTotal());
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(orderDetail.getProductName());

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("USD");
        item.setName(orderDetail.getProductName());
        item.setPrice(orderDetail.getSubtotal());
        item.setTax(orderDetail.getTax());
        item.setQuantity("1");

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

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
    
    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("Erik");
        payerInfo.setLastName("Russian");
        payerInfo.setEmail("demoShopp1@gmail.com");

        payer.setPayerInfo(payerInfo);
        return payer;
    }
}
