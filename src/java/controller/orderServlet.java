package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.User;
import model.InfoCustomer;
import model.walletHeartsteal;
import repository.OrderRepository;
import repository.WalletRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Promotion;
import repository.VoucherRepository;

@WebServlet(name = "orderServlet", urlPatterns = {"/order"})
public class orderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("listProduct");
            return;
        }

        int userId = user.getUserid();
        OrderRepository orderRepository = new OrderRepository();
        WalletRepository walletRepository = new WalletRepository();
        VoucherRepository voucher = new VoucherRepository();
        List<Promotion> vouchers = voucher.getAllVouchers();
        // Get user addresses
        List<InfoCustomer> userAddresses = orderRepository.getAllAddressesByUserId(userId);

        // Get wallet information
        walletHeartsteal wallet = walletRepository.getWalletByUserid(userId);

//        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("productName");
            String size = request.getParameter("size");
            String color = request.getParameter("color");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String image = request.getParameter("image");
            String description = request.getParameter("description");
            String shopName = request.getParameter("shopName");
            int shopId = Integer.parseInt(request.getParameter("shopId"));
            
            // Get the default address information
            InfoCustomer defaultAddress = userAddresses.get(0);
            String nameOfReceiver = defaultAddress.getCustomerName();
            String phoneNumber = defaultAddress.getPhoneCustomer();
            String address = defaultAddress.getAddressCustomer();

            Product product = new Product(productId, productName, price, description, quantity, image, color, size, shopId, shopName, nameOfReceiver, phoneNumber, address);
            request.setAttribute("voucher", vouchers);
            request.setAttribute("product", product);
            request.setAttribute("user", user);
            request.setAttribute("addresses", userAddresses);
            request.setAttribute("surplus", wallet.getSurplus()); 
            
            
            PrintWriter out = response.getWriter();
            out.println(voucher);
            out.println(productName);
            out.println(size);
            out.println(color);

            
//            RequestDispatcher dispatcher = request.getRequestDispatcher("orderForm.jsp");
//            dispatcher.forward(request, response);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
//        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
