package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promotion;
import model.orders;
import model.walletHeartsteal;
import repository.UserRepository;
import repository.WalletRepository;
import repository.cancelOrderDAO;
import repository.submitDeliveredDAO;

/**
 *
 * @author TranHoangAnh
 */
@WebServlet(name = "submitDeliveredServlet", urlPatterns = {"/submitdelivered"})
public class submitDeliveredServlet extends HttpServlet {

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
        
        submitDeliveredDAO c = new submitDeliveredDAO();
        c.editOrder(orderid);
        UserRepository cb = new UserRepository();
        orders o = cb.getOrderByOrderId(orderid);
        int userid = cb.getUserIdByOrderId(orderid);
        Promotion pro = cb.getPromotionByid(o.getPromotionid());
        WalletRepository cb1 = new WalletRepository();
        double surplus = (o.getTotalprice()/(1-(pro.getPercentPromotion()*0.01)))-10000;
        float surplus2 = (float) surplus/24000;

        walletHeartsteal w = new walletHeartsteal(0, userid, surplus2);
        cb1.paymentHeartstealPay(w);
        response.sendRedirect("orderhistory");
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
