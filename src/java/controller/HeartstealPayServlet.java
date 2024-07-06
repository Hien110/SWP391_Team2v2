package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.walletHeartsteal;
import repository.WalletRepository;

@WebServlet(name = "HeartstealPayServlet", urlPatterns = {"/heartstealpay"})
public class HeartstealPayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        final WalletRepository walletRepo = new WalletRepository();
        walletHeartsteal wallet = walletRepo.getWalletByUserid(user.getUserid());

        if (wallet == null) {
            wallet = new walletHeartsteal(0, user.getUserid(), 0);
            walletRepo.newHeartstealPay(wallet);
            wallet = walletRepo.getWalletByUserid(user.getUserid());
        }

        session.setAttribute("wallet", wallet);
        response.sendRedirect("./walletHeartsteal.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        final String moneyStr = request.getParameter("money");
        final String password = request.getParameter("password");
        final String check = request.getParameter("check");

        if (moneyStr == null || password == null || check == null) {
            request.setAttribute("error", "Dữ liệu không hợp lệ.");
            request.getRequestDispatcher("./walletHeartsteal.jsp").forward(request, response);
            return;
        }

        if (!password.equals(user.getPassword())) {
            request.setAttribute("error", "Nộp tiền không thành công do sai mật khẩu");
            request.getRequestDispatcher("./walletHeartsteal.jsp").forward(request, response);
            return;
        }

        try {
            final float money = Float.parseFloat(moneyStr);
            final WalletRepository walletRepo = new WalletRepository();
            final walletHeartsteal wallet = new walletHeartsteal(0, user.getUserid(), money);

            if ("1".equals(check)) {
                walletRepo.paymentHeartstealPay(wallet);
                request.setAttribute("success", "Nộp tiền thành công");
            } else {
                walletHeartsteal currentWallet = walletRepo.getWalletByUserid(user.getUserid());
                if (money > currentWallet.getSurplus()) {
                    request.setAttribute("error", "Số tiền rút vượt quá số dư tài khoản");
                } else {
                    walletRepo.withdrawHeartstealPay(wallet);
                    request.setAttribute("success", "Rút tiền thành công");
                }
            }

            final walletHeartsteal updatedWallet = walletRepo.getWalletByUserid(user.getUserid());
            session.setAttribute("wallet", updatedWallet);
            request.getRequestDispatcher("./walletHeartsteal.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            final String errorMsg = "1".equals(check) ? "Số tiền nộp vào không hợp lệ" : "Số tiền rút ra không hợp lệ";
            request.setAttribute("error", errorMsg);
            request.getRequestDispatcher("./walletHeartsteal.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "HeartstealPayServlet handles wallet operations.";
    }
}
