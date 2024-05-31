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
import repository.UserRepository;
import static service.RandomCodeGenerator.generateRandomCode;
import static service.sendEmail.sendEmail;

@WebServlet(name = "ResendEmailServlet", urlPatterns = {"/resendemail"})
public class ResendEmailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResendEmailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResendEmailServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = null;
        String username = null;
        email = request.getParameter("email");
        UserRepository cdb = new UserRepository();
        User c = cdb.getAccountByEmail(email);
        String check = (String) session.getAttribute("email");
        if (c == null && check == null) {
            String ms = "Cannot find your email";
            request.setAttribute("error", ms);
            request.getRequestDispatcher("./searchuser.jsp").forward(request, response);
        } else {
            username = c.getUsername();
            String newRandomCode = generateRandomCode();
            sendEmail(username, email, newRandomCode);
            session.setAttribute("randomCode", newRandomCode);
            session.setAttribute("email", email);
            response.sendRedirect("./verify");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");

        if (username != null || email != null) {
            String newRandomCode = generateRandomCode();
            sendEmail(username, email, newRandomCode);
            session.setAttribute("randomCode", newRandomCode);
            response.sendRedirect("./verify");
        } else {
            response.sendRedirect("./signup.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
