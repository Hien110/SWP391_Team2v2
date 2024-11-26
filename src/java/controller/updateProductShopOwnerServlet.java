package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.ProductInfor;
import model.ProductShop;
import repository.ProductRepository;
import repository.ProductShopOwnerRepository;

@WebServlet("/updateProductShopOwnerServlet")
public class updateProductShopOwnerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productid = request.getParameter("productId");
        int productid_i = Integer.parseInt(productid);
        ProductRepository cb = new ProductRepository();
        Product p = cb.getProductById(productid);
        List<String> image = cb.getImage(productid_i);
        List<ProductInfor> info = cb.getinforProduct(productid);
        request.setAttribute("product", p);
        request.setAttribute("image", image);
        request.setAttribute("info", info);
        request.getRequestDispatcher("./updateproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int price = Integer.parseInt(request.getParameter("priceP"));
        response.setContentType("text/html;charset=UTF-8");
        ProductShopOwnerRepository pr = new ProductShopOwnerRepository();
        pr.updateProductShopOwner(productId, null, price, null, 0);
        String[] colors = request.getParameterValues("colorP");
        String[] sizes = request.getParameterValues("sizeP");
        String[] quantities = request.getParameterValues("quantityP");
        ProductRepository pro = new ProductRepository();
        PrintWriter out = response.getWriter();
        if (colors != null && sizes != null && quantities != null
                && colors.length == sizes.length && sizes.length == quantities.length) {
            List<ProductInfor> productInfos = new ArrayList<>();
            for (int i = 0; i < colors.length; i++) {
                String color = colors[i];
                String size = sizes[i];
                int quantity = Integer.parseInt(quantities[i]); // Chuyển đổi số lượng thành kiểu int
                productInfos.add(new ProductInfor(0, color, size, quantity, productId));
            }
            try {
                pro.updateProductInfor(productInfos);
            } catch (SQLException ex) {
                Logger.getLogger(updateProductShopOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String errorMessage = "Dữ liệu loại sản phẩm không hợp lệ.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("./updateP.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("ListProductShopOwner");
    }
}
