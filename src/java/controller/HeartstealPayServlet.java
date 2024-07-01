/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import model.walletHeartsteal;
import repository.UserRepository;
import repository.WalletRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "HeartstealPayServlet", urlPatterns = {"/heartstealpay"})
public class HeartstealPayServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HeartstealPayServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HeartstealPayServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        HttpSession session = request.getSession();
        UserRepository cb = new UserRepository();
        User user = (User) session.getAttribute("user");
        WalletRepository cb1 = new WalletRepository();
        walletHeartsteal w = cb1.getWalletByUserid(user.getUserid());
        if (w == null) {
            walletHeartsteal newWallet = new walletHeartsteal(0, user.getUserid(), 0);
            cb1.newHeartstealPay(newWallet);
            walletHeartsteal walletUser = cb1.getWalletByUserid(user.getUserid());
            session.setAttribute("wallet", walletUser);
            response.sendRedirect("./walletHeartsteal.jsp");
        } else {
            walletHeartsteal walletUser = cb1.getWalletByUserid(user.getUserid());
            session.setAttribute("wallet", walletUser);
            response.sendRedirect("./walletHeartsteal.jsp");
        }
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
        HttpSession session = request.getSession();
        UserRepository cb = new UserRepository();
        User user = (User) session.getAttribute("user");
        WalletRepository cb1 = new WalletRepository();
        String money = request.getParameter("money");
        String password = request.getParameter("password");
        String check = request.getParameter("check");
        walletHeartsteal walleto = cb1.getWalletByUserid(user.getUserid());
        if (!password.equals(user.getPassword())) {
            String ms = "Nộp tiền không thành công do sai mật khẩu";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./walletHeartsteal.jsp").forward(request, response);
        } else {
            try {
                int money1 = Integer.parseInt(money);
                walletHeartsteal wallet = new walletHeartsteal(0, user.getUserid(), money1);
                if (check.equals("1")) {
                    cb1.paymentHeartstealPay(wallet);
                    String ms = "Nộp tiền thành công";
                    request.setAttribute("success", ms);
                } else {
                    if (money1 > walleto.getSurplus()) {
                        String ms = "Số tiền rút vượt quá số dư tài khoản";
                        request.setAttribute("error", ms);
                    } else {
                        cb1.withdrawHeartstealPay(wallet);
                        String ms = "Rút tiền thành công";
                        request.setAttribute("success", ms);
                    }
                }
                walletHeartsteal walletUser = cb1.getWalletByUserid(user.getUserid());
                session.setAttribute("wallet", walletUser);
                request.getRequestDispatcher("./walletHeartsteal.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                if (check.equals("1")) {
                    String ms = "Số tiền nộp vào không hợp lệ";
                    request.setAttribute("error", ms);
                } else {
                    String ms = "Số tiền rút ra không hợp lệ";
                    request.setAttribute("error", ms);
                }
                request.getRequestDispatcher("./walletHeartsteal.jsp").forward(request, response);

            }
        }
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
