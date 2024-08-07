package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import repository.reportShopDAO;

@WebServlet(name = "reportShopServlet", urlPatterns = {"/reportshop"})

/**
 *
 * @author TranHoangAnh
 */
public class reportShopServlet extends HttpServlet {

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
        String reason = request.getParameter("reason");
        String customReason = request.getParameter("otherReason");
        String shopid1 = request.getParameter("shopid");
        int shopid = Integer.parseInt(shopid1);
        
        HttpSession session = request.getSession(false);
        User u = (User) session.getAttribute("user");
        int userid = u.getUserid();

        // Kiểm tra nếu lý do là "Other", sử dụng lý do tùy chỉnh
        if ("Khác".equals(reason)) {
            reason = customReason;
        }
        
        //successful
        // Đặt giá trị cho biến successful
        session.setAttribute("successful", true);
//        
//        // Thiết lập thời gian hết hạn của session là 5 giây
//        session.setMaxInactiveInterval(5);

        
        request.setAttribute("reason", reason);
        
        reportShopDAO rp = new reportShopDAO();
        rp.insertReportShop(userid, shopid, reason);
        
        // Chuyển hướng hoặc trả lời người dùng
         response.sendRedirect(request.getContextPath() + "/shopdetail?shopid=" + shopid);
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
