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
import model.Shop;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import repository.ShopOwnerRepository;
import repository.UserRepository;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateProfileShopServlet", urlPatterns = {"/updateprofileshop"})
public class UpdateProfileShopServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfileShopServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileShopServlet at " + request.getContextPath() + "</h1>");
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
        String shopname = request.getParameter("shopname");
        String shopdesc = request.getParameter("shopdesc");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String diachi = request.getParameter("diachi");
        String tinhId = request.getParameter("tinh");
        String huyenId = request.getParameter("quan");
        String xaId = request.getParameter("phuong");
        String tinhName = getNameById(tinhId, "https://esgoo.net/api-tinhthanh/1/0.htm");
        String huyenName = getNameById(huyenId, "https://esgoo.net/api-tinhthanh/2/" + tinhId + ".htm");
        String xaName = getNameById(xaId, "https://esgoo.net/api-tinhthanh/3/" + huyenId + ".htm");
        String fullAddress = diachi + ", " + xaName + ", " + huyenName + ", " + tinhName;
        if (tinhId.equals("0") || huyenId.equals("0") ||xaId.equals("0") || diachi.equals("")){
            fullAddress = address;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Shop shop = (Shop) session.getAttribute("shop");
        int shopid = shop.getShopId();
        int userid = user.getUserid();
        ShopOwnerRepository cbshop = new ShopOwnerRepository();
        UserRepository cbuser = new UserRepository();
        Shop shop1 = new Shop(shopid, shopname, fullAddress, shopdesc, 0);
        User user1 = new User(userid, null, phonenumber, null, null);
        cbshop.updateProfileShop(shop1);
        cbuser.updatePhoneNumber(user1);
        Shop shop2 = cbshop.getShopByUserid(userid);
        User user2 = cbuser.getAccountByUsername(user.getUsername());
        session.setAttribute("shop", shop2);
        session.setAttribute("user", user2);
        String ms = "Câp nhập hồ sơ thành công";
        request.setAttribute("success", ms);
        request.getRequestDispatcher("./profileShop.jsp").forward(request, response);
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
