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

@WebServlet(name = "HeartstealPayServlet", urlPatterns = {"/heartstealpay"})
public class HeartstealPayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

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
                String ms = check.equals("1") ? "Số tiền nộp vào không hợp lệ" : "Số tiền rút ra không hợp lệ";
                request.setAttribute("error", ms);
                request.getRequestDispatcher("./walletHeartsteal.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "HeartstealPayServlet handles wallet operations.";
    }
}
