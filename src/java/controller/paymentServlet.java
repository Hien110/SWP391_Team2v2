package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.OrderRepository;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/payments"})
public class paymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(paymentServlet.class.getName());

    private OrderRepository orderRepository;

    // Parameter names
    private static final String PARAM_PRODUCT_ID = "productId";
    private static final String PARAM_USER_ID = "userId";
    private static final String PARAM_QUANTITY = "quantity";
    private static final String PARAM_SHOPNAME = "shopName";
    private static final String PARAM_NAME_OF_RECEIVER = "nameOfReceiver";
    private static final String PARAM_PHONE_NUMBER = "phoneNumber";
    private static final String PARAM_ADDRESS = "address";
    private static final String PARAM_TOTAL_PRICE = "totalPrice";
    private static final String PARAM_DATE_ORDER = "dateOrder";
    private static final String PARAM_PROMOTION_ID = "promotionId";
    private static final String PARAM_COLOR = "color";
    private static final String PARAM_SIZE = "size";
    private static final String PARAM_PAYMENT_METHODS = "paymentMethods";

    // Payment methods
    private static final String PAYMENT_METHOD_COD = "cod";
    private static final String PAYMENT_METHOD_HEASTEAL = "heasteal";

    @Override
    public void init() throws ServletException {
        orderRepository = new OrderRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter(PARAM_PRODUCT_ID));
            int userId = Integer.parseInt(request.getParameter(PARAM_USER_ID));
            int quantity = Integer.parseInt(request.getParameter(PARAM_QUANTITY));
            String nameOfReceiver = request.getParameter(PARAM_NAME_OF_RECEIVER);
            String phoneNumber = request.getParameter(PARAM_PHONE_NUMBER);
            String shopName = request.getParameter(PARAM_SHOPNAME);
            String address = request.getParameter(PARAM_ADDRESS);
            double totalPrice = Double.parseDouble(request.getParameter(PARAM_TOTAL_PRICE));
            String dateOrder = request.getParameter(PARAM_DATE_ORDER);
            int promotionId = Integer.parseInt(request.getParameter(PARAM_PROMOTION_ID));
            String color = request.getParameter(PARAM_COLOR);
            String size = request.getParameter(PARAM_SIZE);
            String paymentMethods = request.getParameter(PARAM_PAYMENT_METHODS);

            String statusOrder = "Pending";
            String paymentMethod;

            switch (paymentMethods) {
                case PAYMENT_METHOD_COD:
                    paymentMethod = "Cash on Delivery";
                    break;
                case PAYMENT_METHOD_HEASTEAL:
                    paymentMethod = "Heasteal Points";
                    break;
                default:
                    throw new ServletException("Unknown payment method: " + paymentMethods);
            }

            // Insert the order
            orderRepository.insertOrder(productId, userId, quantity, nameOfReceiver, phoneNumber, address, statusOrder, totalPrice, dateOrder, promotionId, color, size, paymentMethod);

            // Update the product quantity
            orderRepository.editOrder(productId, quantity);

            // Set success message and forward to order tracking page
            request.setAttribute("orderSuccess", "Order placed successfully!");
            response.sendRedirect("ordertracking");
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid number format in request parameters", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format in request parameters");
        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, "Error processing payment", e);
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error processing payment", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error processing payment");
        }
    }
}
