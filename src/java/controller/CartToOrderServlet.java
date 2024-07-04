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
import static java.lang.System.out;
import model.Product;
import model.InfoCustomer;
import model.User;
import model.walletHeartsteal;
import repository.OrderRepository;
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
            response.sendRedirect("home.jsp");
            return;
        }

        int userId = user.getUserid();
        OrderRepository orderRepository = new OrderRepository();
        WalletRepository walletRepository = new WalletRepository();

        List<InfoCustomer> userAddresses = orderRepository.getAllAddressesByUserId(userId);
        walletHeartsteal wallet = walletRepository.getWalletByUserid(userId);



        try {
            String[] cartIds = request.getParameterValues("cartId");
            List<Product> products = new ArrayList<>();

            for (String cartIdStr : cartIds) {
                int cartId = Integer.parseInt(cartIdStr);
                Product product = cartService.getProductFromCart(cartId, userId);

                if (product != null) {
                    products.add(product);
                } else {
                    request.setAttribute("message", "Product not found in cart with ID: " + cartId);
                    request.setAttribute("messageType", "danger");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }

           
            InfoCustomer defaultAddress = userAddresses.get(0);
            String nameOfReceiver = defaultAddress.getCustomerName();
            String phoneNumber = defaultAddress.getPhoneCustomer();
            String address = defaultAddress.getAddressCustomer();


            for (Product product : products) {
                product.setNameOfReceiver(nameOfReceiver);
                product.setPhoneNumber(phoneNumber);
                product.setAddress(address);
            }

            request.setAttribute("products", products);
            request.setAttribute("user", user);
            request.setAttribute("addresses", userAddresses);
            request.setAttribute("surplus", wallet.getSurplus()); 

            // Forward to orderForm.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("orderForm.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        } finally {
            out.close(); // Close the PrintWriter
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
