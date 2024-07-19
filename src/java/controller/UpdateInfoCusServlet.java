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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import model.InfoCustomer;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import repository.InfoCustomerRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateInfoCusServlet", urlPatterns = {"/updateinfocus"})
public class UpdateInfoCusServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateInfoCusServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateInfoCusServlet at " + request.getContextPath() + "</h1>");
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
        String cusid = request.getParameter("cusid");
        InfoCustomerRepository cb = new InfoCustomerRepository();
        InfoCustomer in = cb.getInfoByCusid(cusid);
        String address = in.getAddressCustomer();
        String shortAddress = address.contains(",") ? address.substring(0, address.indexOf(",")) : address;
        request.setAttribute("shortAddress", shortAddress);
        request.setAttribute("cusinfo", in);
        request.getRequestDispatcher("./updateInfoCus.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String cusid = request.getParameter("cusid");
        int cusid1 = Integer.parseInt(cusid);
        String cusname = request.getParameter("cusname");
        String cusphone = request.getParameter("cusphone");
        String diachi = request.getParameter("diachi");
        String tinhId = request.getParameter("tinh");
        String huyenId = request.getParameter("quan");
        String xaId = request.getParameter("phuong");
        if (cusphone.length() != 10 || cusphone.charAt(0) != '0') {
            request.setAttribute("error", "Số điện thoại không hợp lệ");
            request.getRequestDispatcher("./updateInfoCus.jsp").forward(request, response);
        } else if (tinhId.equals("0") || huyenId.equals("0") || xaId.equals("0") || diachi.equals("")) {
            request.setAttribute("error", "Đại chỉ không hợp lệ");
            request.getRequestDispatcher("./updateInfoCus.jsp").forward(request, response);
        } else {
            String tinhName = getNameById(tinhId, "https://esgoo.net/api-tinhthanh/1/0.htm");
            String huyenName = getNameById(huyenId, "https://esgoo.net/api-tinhthanh/2/" + tinhId + ".htm");
            String xaName = getNameById(xaId, "https://esgoo.net/api-tinhthanh/3/" + huyenId + ".htm");
            String fullAddress = diachi + ", " + xaName + ", " + huyenName + ", " + tinhName;
            InfoCustomerRepository cdb = new InfoCustomerRepository();
            InfoCustomer c = new InfoCustomer(cusid1, cusname, cusphone, fullAddress, cusid1);
            cdb.updateInforReceiver(c);
            session.removeAttribute("error");
            response.sendRedirect("./infocustomer");
        }
    }

    private String getNameById(String id, String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        JSONObject jsonResponse = new JSONObject(content.toString());
        if (jsonResponse.getInt("error") == 0) {
            JSONArray array = jsonResponse.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                // Chuyển đổi giá trị id thành chuỗi
                String objectId = String.valueOf(object.get("id"));
                if (objectId.equals(id)) {
                    return object.getString("full_name");
                }
            }
        }
        return "Unknown";
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
