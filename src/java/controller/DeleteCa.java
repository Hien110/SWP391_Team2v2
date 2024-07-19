package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.CartItem;
import model.User;
import repository.OrderRepository;

@WebServlet(name = "DeleteCa", urlPatterns = {"/deleteCa"})
public class DeleteCa extends HttpServlet {

    private final OrderRepository orderRepository = new OrderRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("home.jsp"); // Redirect to home if user is not logged in
            return;
        }

        String cartIdStr = request.getParameter("cartId");

        if (cartIdStr != null && !cartIdStr.isEmpty()) {
            try {
                int cartId = Integer.parseInt(cartIdStr);
                boolean success = orderRepository.deleteByCartId(cartId);
                if (success) {
                    OrderRepository cb1 = new OrderRepository();
                    List<CartItem> cart = cb1.getCartItemsByUserId(user.getUserid());
                    int cartsize = cart.size();
                    session.setAttribute("cartsize", cartsize);
                    request.setAttribute("message", "Item removed successfully!");
                    request.setAttribute("messageType", "success");
                } else {
                    request.setAttribute("message", "Failed to remove item.");
                    request.setAttribute("messageType", "error");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid cart ID.");
                request.setAttribute("messageType", "error");
            }
        } else {
            request.setAttribute("message", "No cart ID provided.");
            request.setAttribute("messageType", "error");
        }

        // Forward back to the cart page
        request.getRequestDispatcher("ListCard").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
