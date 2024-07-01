package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.ProductShop;
import model.productColors;
import model.productSize;
import repository.ProductShopOwnerRepository;

@WebServlet(name = "ListProductShopOwnerServlet", urlPatterns = {"/ListProductShopOwner"})
public class listProductShopOwnerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProductShopOwnerRepository pr = new ProductShopOwnerRepository();
            List<productSize> listS = pr.getAllProductSize(4);
            List<productColors> listC = pr.getAllProductColors(4);
            List<ProductShop> list = pr.getAllProductShopOwner(3);
            request.setAttribute("lc", listC);
            request.setAttribute("ls", listS);
            request.setAttribute("l", list);  // Đặt danh sách sản phẩm vào request scope với tên "l"
            request.getRequestDispatcher("listProductShopOwner.jsp").forward(request, response);  // Chuyển tiếp đến listProductShopOwner.jsp

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}