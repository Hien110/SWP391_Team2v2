package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;
import repository.ProductRepository;

@WebServlet(name = "ListProductShopOwnerServlet", urlPatterns = {"/ListProductShopOwnerServlet"})
public class listProductShopOwnerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProductRepository pr = new ProductRepository();
            List<Product> list = pr.getAllProductShopOwner(1);

            request.setAttribute("l", list);  // Đặt danh sách sản phẩm vào request scope với tên "l"
            request.getRequestDispatcher("listProductShopOwner.jsp").forward(request, response);  // Chuyển tiếp đến listProductShopOwner.jsp

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
