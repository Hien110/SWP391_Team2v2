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
import model.Shop;
import org.json.JSONArray;
import org.json.JSONObject;
import repository.InfoCustomerRepository;
import repository.ShopOwnerRepository;
import repository.UserRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "RegisterToSalesServlet", urlPatterns = {"/registertosales"})
public class RegisterToSalesServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterToSellServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterToSellServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String userid_raw = (String) session.getAttribute("uid");
        int userid = 0;
        if (userid_raw != null) {
            try {
                userid = Integer.parseInt(userid_raw);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        String shopname = request.getParameter("shopname");
        String shopdes = request.getParameter("shopdes");
        String specificaddress = request.getParameter("specificaddress");
        String shopprovince = request.getParameter("shopprovince");
        String shopdistrict = request.getParameter("shopdistrict");
        String shopward = request.getParameter("shopward");
        PrintWriter out = response.getWriter();
        String tinhName = getNameById(shopprovince, "https://esgoo.net/api-tinhthanh/1/0.htm");
        String huyenName = getNameById(shopdistrict, "https://esgoo.net/api-tinhthanh/2/" + shopprovince + ".htm");
        String xaName = getNameById(shopward, "https://esgoo.net/api-tinhthanh/3/" + shopdistrict + ".htm");
        String fullAddress = specificaddress + ", " + xaName + ", " + huyenName + ", " + tinhName;
        ShopOwnerRepository cdb = new ShopOwnerRepository();
        UserRepository cdb1 = new UserRepository();
        cdb1.updateRoleWaiting(userid);
        Shop s = new Shop(shopname, fullAddress, shopdes, userid);
        cdb.newShop(s);
        session.setAttribute("roleid", 4);
        response.sendRedirect("./registerToSales.jsp");
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
