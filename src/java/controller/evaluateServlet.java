package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.User;
import model.evaluate;
import repository.viewEvaluateDAO;

/**
 *
 * @author TranHoangAnh
 */
@WebServlet(name = "evaluateServlet", urlPatterns = {"/evaluate"})
public class evaluateServlet extends HttpServlet {

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

        // Lấy userID từ session
        HttpSession session = request.getSession(false);
        User u = (User) session.getAttribute("user");
        String username = u.getUsername();

        // Lấy productID từ request (nếu cần)
         String productID1 = request.getParameter("productid");
         int productID = Integer.parseInt(productID1);
//        // Test với productID cố định
//        int productID = 1;

        // Gọi DAO để lấy danh sách các đánh giá
        viewEvaluateDAO s = new viewEvaluateDAO();
        List<evaluate> list = s.getAllOrderByUID(productID);

        boolean isComment=true;
        // Duyệt danh sách và đặt isComment = true nếu userID trên session trùng với userID trong danh sách
        for (evaluate eval : list) {
            if (eval.getUserName().equals(username)) {
                isComment = false;
            }
        }

        // Đặt danh sách vào attribute để truyền sang evaluate.jsp
        request.setAttribute("productid", productID);
        request.setAttribute("isComment", isComment);
        request.setAttribute("listComment", list);
        request.getRequestDispatcher("evaluate.jsp").forward(request, response);

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
