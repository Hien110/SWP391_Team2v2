package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.OrderRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

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
    private static final String PARAM_TOTAL_PRICE = "amount";
    private static final String PARAM_DATE_ORDER = "dateOrder";
    private static final String PARAM_PROMOTION_ID = "promotionId";
    private static final String PARAM_COLOR = "color";
    private static final String PARAM_SIZE = "size";
    private static final String PARAM_PAYMENT_METHODS = "paymentMethods";

    // Payment methods
    private static final String PAYMENT_METHOD_COD = "cod";
    private static final String PAYMENT_METHOD_HEASTEAL = "heasteal";
    OrderRepository wallet = new OrderRepository();

    @Override
    public void init() throws ServletException {
        orderRepository = new OrderRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String checkStr = request.getParameter("checkstr");
            String paymentMethods = request.getParameter(PARAM_PAYMENT_METHODS);

            switch (paymentMethods) {
                case PAYMENT_METHOD_COD:
                    paymentMethods = "Thanh toán khi nhận hàng";
                    break;
                case PAYMENT_METHOD_HEASTEAL:
                    int userId = Integer.parseInt(request.getParameter(PARAM_USER_ID));
                    double totalPrice = Double.parseDouble(request.getParameter(PARAM_TOTAL_PRICE));
                    paymentMethods = "Heasteal Points";
                    wallet.subtractSurplus(userId, totalPrice / 24000);

                    break;
                default:
                    throw new ServletException("Unknown payment method: " + paymentMethods);
            }

            if ("1".equals(checkStr)) {
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
                String statusOrder = "Đang xử lí";

                // Insert the order
                orderRepository.insertOrder(productId, userId, quantity, nameOfReceiver, phoneNumber, address, statusOrder, totalPrice, dateOrder, promotionId, color, size, paymentMethods);
                orderRepository.editOrder(productId, quantity,size,color);
                orderRepository.editPromotion(promotionId);
//                PrintWriter out = response.getWriter();
//            out.println(productId);
                // Update the product quantity


                request.setAttribute("orderSuccess", "Order placed successfully!");
                response.sendRedirect("ordertracking");
                //2 la cart               
            } else {
                int userId = Integer.parseInt(request.getParameter(PARAM_USER_ID));
                String nameOfReceiver = request.getParameter(PARAM_NAME_OF_RECEIVER);
                String phoneNumber = request.getParameter(PARAM_PHONE_NUMBER);
                String address = request.getParameter(PARAM_ADDRESS);
                String currentDate = request.getParameter(PARAM_DATE_ORDER);
                String amount = request.getParameter("calculatedTotal");
                int voucherIds = Integer.parseInt(request.getParameter("voucherId"));
                // Lấy thông tin sản phẩm từ request
                String[] productIds = request.getParameterValues(PARAM_PRODUCT_ID);
                String[] shopNames = request.getParameterValues(PARAM_SHOPNAME);
                String[] productNames = request.getParameterValues("productName");
                String[] descriptions = request.getParameterValues("description");
                String[] sizes = request.getParameterValues(PARAM_SIZE);
                String[] colors = request.getParameterValues(PARAM_COLOR);
                String[] images = request.getParameterValues("image");
                String[] quantities = request.getParameterValues(PARAM_QUANTITY);
                String[] prices = request.getParameterValues("price");

                List<Product> products = new ArrayList<>();

                for (int i = 0; i < productIds.length; i++) {
                    Product product = new Product();
                    product.setProductId(Integer.parseInt(productIds[i]));
                    product.setShopName(shopNames[i]);
                    product.setProductName(productNames[i]);
                    product.setDescription(descriptions[i]);
                    product.setSize(sizes[i]);
                    product.setColor(colors[i]);
                    product.setImage(images[i]);
                    product.setQuantity(Integer.parseInt(quantities[i]));
                    product.setPrice(Double.parseDouble(prices[i]));
                    products.add(product);
                }

//                PrintWriter out = response.getWriter();
////            out.print(amount);
//            out.print(voucherIds);
                // Process each product and insert order
                for (Product product : products) {
                    orderRepository.insertOrder(product.getProductId(), userId, product.getQuantityp(), nameOfReceiver, phoneNumber, address, "Đang xử lí", 1000, currentDate, voucherIds, product.getColor(), product.getSize(), paymentMethods);
                    orderRepository.editOrder(product.getProductId(), product.getQuantityp(), product.getSize(), product.getColor());
                    orderRepository.editPromotion(voucherIds);
                }

                // Set success message and redirect after all orders processed
                request.setAttribute("orderSuccess", "Order placed successfully!");
                response.sendRedirect("ordertracking");
            }
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
