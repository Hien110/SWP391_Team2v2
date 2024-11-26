package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import model.Product;
import model.InfoCustomer;
import model.Promotion;
import model.User;
import model.walletHeartsteal;
import repository.OrderRepository;
import repository.VoucherRepository;
import repository.WalletRepository;

@WebServlet(name = "CartToOrderServlet", urlPatterns = {"/cartToOrder"})
public class CartToOrderServlet extends HttpServlet {

    private OrderRepository cartService = new OrderRepository(); // Initialize your service

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("./listProduct");
            return;
        }

        int userId = user.getUserid();
        OrderRepository orderRepository = new OrderRepository();
        WalletRepository walletRepository = new WalletRepository();
        VoucherRepository voucherRepository = new VoucherRepository();
        List<InfoCustomer> userAddresses = orderRepository.getAllAddressesByUserId(userId);
        List<Promotion> vouchers = voucherRepository.getAllVouchers();
        walletHeartsteal wallet = walletRepository.getWalletByUserid(userId);
        
        try {
            String[] cartIds = request.getParameterValues("cartIds");
            if (cartIds == null || cartIds.length == 0) {
                request.setAttribute("message", "No products selected.");
                request.setAttribute("messageType", "danger");
                RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
                dispatcher.forward(request, response);
                return;
            }

            List<Product> products = new ArrayList<>();
            String cartIdStr = String.join(",", cartIds);
            List<Integer> cartIds1 = Arrays.stream(cartIdStr.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (String cartId : cartIds) {
                int cartIdInt = Integer.parseInt(cartId);
                Product product = cartService.getProductFromCart(cartIdInt, userId);

                if (product != null) {
                    int productId = product.getProductId();
                    String productName = product.getProductName();
                    String size = product.getSize();
                    String color = product.getColor();
                    double price = product.getPrice();
                    int quantity = product.getQuantityp();
                    String image = product.getImage();
                    String description = product.getDescription();
                    String shopname = product.getShopName();
                    int shopId = product.getShopId();

                    // Set product details as request attributes
                    request.setAttribute("productId_" + cartId, productId);
                    request.setAttribute("productName_" + cartId, productName);
                    request.setAttribute("size_" + cartId, size);
                    request.setAttribute("color_" + cartId, color);
                    request.setAttribute("price_" + cartId, price);
                    request.setAttribute("quantity_" + cartId, quantity);
                    request.setAttribute("image_" + cartId, image);
                    request.setAttribute("description_" + cartId, description);
                    request.setAttribute("shopname_" + cartId, shopname);
                    request.setAttribute("shopId_" + cartId, shopId);

                    InfoCustomer defaultAddress = userAddresses.get(0);
                    product.setNameOfReceiver(defaultAddress.getCustomerName());
                    product.setPhoneNumber(defaultAddress.getPhoneCustomer());
                    product.setAddress(defaultAddress.getAddressCustomer());

                    products.add(product);
                } else {
                    request.setAttribute("message", "Product not found in cart with ID: " + cartId);
                    request.setAttribute("messageType", "danger");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }
            request.setAttribute("voucher", vouchers);
            request.setAttribute("cartIds", cartIds1);
            request.setAttribute("products", products);
            request.setAttribute("user", user);
            request.setAttribute("addresses", userAddresses);
            request.setAttribute("surplus", wallet.getSurplus());
            request.setAttribute("shopIds", products.stream().map(Product::getShopId).distinct().collect(Collectors.toList()));
//            PrintWriter out = response.getWriter();
//            out.print(vouchers);
            // Forward to orderForm.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("orderCart.jsp");
              dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Cart to Order Servlet";
    }
}
