package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.orders;
import model.walletHeartsteal;
import repository.WalletRepository;
import repository.cancelOrderDAO;
import repository.orderTrackingDAO;

/**
 *
 * @author TranHoangAnh
 */
@WebServlet(name = "cancelOrderServlet", urlPatterns = {"/cancelorder"})
public class cancelOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String orderid1 = request.getParameter("orderid");
        int orderid = Integer.parseInt(orderid1);
        orderTrackingDAO o = new orderTrackingDAO();
        orders order = o.getOrderByOrderId(orderid);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if ("Heasteal Points".equals(order.getPaymentmethods())) {
            WalletRepository cb = new WalletRepository();
            int totalPrice = order.getTotalprice();
            float price = (float) totalPrice;
            walletHeartsteal wallet = new walletHeartsteal(0, user.getUserid(), price/24000);
            cb.paymentHeartstealPay(wallet);
        }
        String productid1 = request.getParameter("productid");
        int productid = Integer.parseInt(productid1);
        String cancelReason = request.getParameter("cancelReason");
        String otherReason = request.getParameter("otherReason");

        // Kiểm tra nếu lý do là "Other", sử dụng lý do tùy chỉnh
        if ("other".equals(cancelReason)) {
            cancelReason = otherReason;
        }

        cancelOrderDAO c = new cancelOrderDAO();
        c.editOrder(orderid, cancelReason, productid);

        response.sendRedirect("ordertracking");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
