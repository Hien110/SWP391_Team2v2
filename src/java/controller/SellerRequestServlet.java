package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import repository.SellerRequestRepository;
import repository.UserRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SellerRequestServlet", urlPatterns = {"/processApproval"})
public class SellerRequestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         SellerRequestRepository sellerRequestRepository = new SellerRequestRepository();
        List<User> userList = sellerRequestRepository.getUsersWithRoleId(4);

        // Set users as an attribute to be displayed in JSP
        request.setAttribute("users", userList);
        request.getRequestDispatcher("processApproval.jsp").forward(request, response);
      
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 String action = request.getParameter("action");
        String userIdStr = request.getParameter("userId");

        if (action != null && userIdStr != null) {
            int userId = Integer.parseInt(userIdStr);

            if (action.equals("accept")) {
                // Update roleId to 2 for the user
                UserRepository userRepository = new UserRepository();
                userRepository.updateRoleIdAccept(userId);
            } else  {
                // Update roleId to 3 for the user
                UserRepository userRepository = new UserRepository();
                userRepository.updateRoleIdReject(userId);
                userRepository.DeleteReject(userId);
            }

            // Remove user from SellerRequest
            SellerRequestRepository sellerRequestRepository = new SellerRequestRepository();
            sellerRequestRepository.deleteUserRequest(userId);
        }
              response.sendRedirect("./processApproval");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       

        // Get updated list of users from SellerRequest with roleId 4
       
    }
}
