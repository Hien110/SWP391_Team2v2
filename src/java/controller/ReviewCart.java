package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import model.Product;
import model.User;
import repository.OrderRepository;

@WebServlet(name = "ReviewCartServlet", urlPatterns = {"/ReviewCart"})
public class ReviewCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String paymentMethods = request.getParameter("paymentMethods");
        String nameOfReceiver = request.getParameter("nameOfReceiver");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        if (user == null) {
            response.sendRedirect("home.jsp");
            return;
        }

        int userId = user.getUserid();
        String[] cartIdsStrArray = request.getParameterValues("cartIds");

        if (cartIdsStrArray == null || cartIdsStrArray.length == 0) {
            response.sendRedirect("cart.jsp");
            return;
        }

        int[] cartIds = Stream.of(cartIdsStrArray).mapToInt(Integer::parseInt).toArray();

        OrderRepository itemc = new OrderRepository();

        List<Product> products = new ArrayList<>();
        for (int cartId : cartIds) {
            Product product = itemc.getProductFromCart(cartId, userId);
            if (product != null) {
                products.add(product);
            }
        }

        request.setAttribute("products", products);
        request.setAttribute("userId", userId);
        request.setAttribute("nameOfReceiver", nameOfReceiver);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("address", address);
        request.setAttribute("paymentMethods", paymentMethods);
        RequestDispatcher dispatcher = request.getRequestDispatcher("reviewCart.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to review and process cart";
    }
}
