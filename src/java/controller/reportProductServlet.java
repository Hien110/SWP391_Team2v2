package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.reportProductDAO;

@WebServlet(name = "reportProductServlet", urlPatterns = {"/reportproduct"})

/**
 *
 * @author TranHoangAnh
 */
public class reportProductServlet extends HttpServlet {

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
        String customReason = request.getParameter("customReason");

//        String userID1 = request.getParameter("userid");
//        int userID = Integer.parseInt("userID1");
//        String productID1 = request.getParameter("productID");
//        int productID = Integer.parseInt("productID1");
        // Kiểm tra nếu lý do là "Other", sử dụng lý do tùy chỉnh
        if ("Khác".equals(reason)) {
            reason = customReason;
        }

        //test
        int userID = 2;
        int productID = 2;
        //test

        request.setAttribute("reason", reason);
        
        reportProductDAO rp = new reportProductDAO();
        rp.insertReportProduct(userID, productID, reason);

//        // Chuyển hướng hoặc trả lời người dùng
        response.sendRedirect("reportSuccessful.jsp");
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
