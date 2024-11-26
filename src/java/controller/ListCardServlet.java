package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.OrderRepository;
import model.CartItem;
import model.User;

@WebServlet(name = "ListCardServlet", urlPatterns = {"/ListCard"})
public class ListCardServlet extends HttpServlet {

    private final OrderRepository orderRepository = new OrderRepository();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("home.jsp");
            return;
        }
        int userId = user.getUserid();
        List<CartItem> cartItems = orderRepository.getCartItemsByUserId(userId);

        List<CartItem> validCartItems = new ArrayList<>();
        for (CartItem item : cartItems) {
            int availableQuantity = orderRepository.checkProductQuantity(item.getSize(), item.getColor(), item.getProductId());
            if (item.getQuantity() <= availableQuantity) {
                validCartItems.add(item);
            }
        }

        List<CartItem> aggregatedCartItems = aggregateCartItems(validCartItems);
        request.setAttribute("userId", user.getUserid());
        request.setAttribute("cartItems", aggregatedCartItems);

        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    private List<CartItem> aggregateCartItems(List<CartItem> cartItems) {
        Map<String, CartItem> aggregatedMap = new HashMap<>();
        
        for (CartItem item : cartItems) {
            String key = item.getShopname() + "|" + item.getProductName() + "|" + item.getSize() + "|" + item.getColor();
            if (aggregatedMap.containsKey(key)) {
                CartItem existingItem = aggregatedMap.get(key);
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            } else {
                aggregatedMap.put(key, item);
            }
        }

        return new ArrayList<>(aggregatedMap.values());
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
        return "ListCardServlet handles displaying the user's cart items.";
    }
}
