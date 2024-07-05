package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.ProductShop;
import model.Shop;
import model.Product;
import model.productColors;
import model.productSize;
import repository.ProductShopOwnerRepository;

@WebServlet(name = "ListProductShopOwnerServlet", urlPatterns = {"/ListProductShopOwner"})
public class listProductShopOwnerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Shop shop = (Shop) session.getAttribute("shop");
        int shopid = shop.getShopId();
        PrintWriter out = response.getWriter();
        try {
            ProductShopOwnerRepository pr = new ProductShopOwnerRepository();
            List<ProductShop> list = pr.getAllProductShopOwner(shopid);
            request.setAttribute("l", list);
            for (int i = 0; i < list.size(); i++) {
                ProductShop productShop = list.get(i);
                List<productColors> listC = pr.getAllProductColors(productShop.getProductId());
                List<productSize> listS = pr.getAllProductSize(productShop.getProductId());

                request.setAttribute("lc" + i, listC);
                 request.setAttribute("ls" + i, listS);
                request.setAttribute("listP" + i, productShop);
                out.print(listC);
                out.print(productShop);
            }
            request.setAttribute("count", list.size());
            request.getRequestDispatcher("listProductShopOwner.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
