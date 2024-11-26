package controller;

import repository.OrderRepository;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.CartItem;
import model.User;

@WebServlet(name = "CartServlet", urlPatterns = {"/Cart"})
public class CartServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CartServlet.class.getName());
    private final OrderRepository orderRepository = new OrderRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Call doPost method to handle the request
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Extract item details from the request
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            int userId = user.getUserid();
            String productIdStr = request.getParameter("productId");
            String size = request.getParameter("size");
            String color = request.getParameter("color");
            String quantityStr = request.getParameter("quantity");
            OrderRepository cb1 = new OrderRepository();

           
            if (isNullOrEmpty(productIdStr, size, color, quantityStr)) {
                throw new IllegalArgumentException("One or more parameters are missing or empty.");
            }

            int productId = Integer.parseInt(productIdStr);
            int quantity = Integer.parseInt(quantityStr);
            
//                        PrintWriter out = response.getWriter();
////            out.println(productIdStr);
////            out.println(userId);
////            out.println(quantity);
//            out.println(size);
//            out.println(color);

//       
            orderRepository.addItemToCart(productId, userId, quantity, size, color);
            List<CartItem> cartItems = orderRepository.getCartItemsByUserId(userId);

                List<CartItem> validCartItems = new ArrayList<>();
                for (CartItem item : cartItems) {
                    int availableQuantity = orderRepository.checkProductQuantity(item.getSize(), item.getColor(), item.getProductId());
                    if (item.getQuantity() <= availableQuantity) {
                        validCartItems.add(item);
                    }
                }

                List<CartItem> aggregatedCartItems = aggregateCartItems(validCartItems);
                int cartsize = aggregatedCartItems.size();
                session.setAttribute("cartsize", cartsize);
            // Set success message
            request.setAttribute("message", "Sản phẩm đã được thêm vào giỏ hàng thành công!");
            request.setAttribute("messageType", "success");

        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Invalid input: {0}", e.getMessage());
            request.setAttribute("message", "Thêm vào giỏ hàng không thành công. Làm ơn hãy thử lại");
            request.setAttribute("messageType", "error");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to add product to cart", e);
            request.setAttribute("message", "Thêm vào giỏ hàng không thành công. Làm ơn hãy thử lại.");
            request.setAttribute("messageType", "error");
        }

       
        request.getRequestDispatcher("/detailProduct").forward(request, response);
    }

    private boolean isNullOrEmpty(String... values) {
        for (String value : values) {
            if (value == null || value.isEmpty()) {
               return true;
            }
        }
        return false;
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
    public String getServletInfo() {
        return "Handles adding items to the cart.";
    }
}
