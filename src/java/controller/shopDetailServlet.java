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
import model.Product;
import model.Shop;
import model.User;
import model.wishlist;
import repository.getWishListDAO;
import repository.productShopListDAO;
import repository.shopDetailDAO;
import repository.shopFollowDAO;

/**
 *
 * @author TranHoangAnh
 */
@WebServlet(name = "shopDetailServlet", urlPatterns = {"/shopdetail"})
public class shopDetailServlet extends HttpServlet {

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
        //test
        int shopid = 1;
        //test
        
        HttpSession session = request.getSession(false);
        User u = (User) session.getAttribute("user");
        int userid = u.getUserid();
        
        wishlist w = new wishlist();
        getWishListDAO get = new getWishListDAO();
        w = get.getAllOrderByUID(shopid, userid);
        request.setAttribute("isFollow", w);
        
        shopDetailDAO s = new shopDetailDAO();
        productShopListDAO s1 = new productShopListDAO();
        Shop shop = new Shop();
        List<Product> listP = s1.getAllProductByShopID(shopid);
        
        shop = s.getShopByID(shopid);
        request.setAttribute("listP", listP);
        request.setAttribute("shop", shop);
        request.getRequestDispatcher("shopDetail.jsp").forward(request, response);
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
