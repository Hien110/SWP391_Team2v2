/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import model.InfoCustomer;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import repository.InfoCustomerRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "InfoCustomerServlet", urlPatterns = {"/infocustomer"})
public class InfoCustomerServlet extends HttpServlet {

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
            out.println("<title>Servlet InfoCustomerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InfoCustomerServlet at " + request.getContextPath() + "</h1>");
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
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            int userid = user.getUserid();
            InfoCustomerRepository pr = new InfoCustomerRepository();
            List<InfoCustomer> info = pr.getInfoByUserid(userid);
            PrintWriter out = response.getWriter();
            out.print(user);
            request.setAttribute("info", info);
            request.getRequestDispatcher("infoOfUser.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userid = user.getUserid();
        String cusname = request.getParameter("cusname");
        String cusphone = request.getParameter("cusphone");
        String diachi = request.getParameter("diachi");
        String tinhId = request.getParameter("tinh");
        String huyenId = request.getParameter("quan");
        String xaId = request.getParameter("phuong");
        PrintWriter out = response.getWriter();
        String tinhName = getNameById(tinhId, "https://esgoo.net/api-tinhthanh/1/0.htm");
        String huyenName = getNameById(huyenId, "https://esgoo.net/api-tinhthanh/2/" + tinhId + ".htm");
        String xaName = getNameById(xaId, "https://esgoo.net/api-tinhthanh/3/" + huyenId + ".htm");
        String fullAddress = diachi + ", " + xaName + ", " + huyenName + ", " + tinhName;
        InfoCustomerRepository cdb = new InfoCustomerRepository();
        InfoCustomer c = new InfoCustomer(cusname, cusphone, fullAddress, userid);
        cdb.newAddress(c);
        out.print(userid);
        response.sendRedirect("./infocustomer");
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
