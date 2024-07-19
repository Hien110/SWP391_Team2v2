package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
import java.util.List;
import model.Product;
import model.Shop;
import model.User;
import repository.ProductRepository;
import repository.UserRepository;
import repository.productShopListDAO;
import repository.shopDetailDAO;
import repository.shopFollowDAO;
import repository.viewEvaluateDAO;
import repository.viewHistoryOrdersDAO;
import model.ProductInfor;

@WebServlet(name = "ListDetailProductServlet", urlPatterns = {"/detailProduct"})
public class ListDetailProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productIdStr = request.getParameter("productId");
        viewHistoryOrdersDAO o = new viewHistoryOrdersDAO();
        int count = o.countDelivered(productIdStr);
        if (productIdStr == null || productIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is missing");
            return;
        }

        try {
            ProductRepository productRepository = new ProductRepository();
            Product product = productRepository.getProductById(productIdStr);
            shopDetailDAO s2 = new shopDetailDAO();
            shopFollowDAO s = new shopFollowDAO();
            int countWishList = s.countWishList(product.getShopId());
            Shop shop = s2.getShopByID(product.getShopId());
            viewEvaluateDAO cb1 = new viewEvaluateDAO();
            int countEva = cb1.countEvaluate(product.getShopId());
            int countOrderShop = o.countDeliveredShop(product.getShopId());
            productShopListDAO s1 = new productShopListDAO();
            List<Product> listP = s1.getAllProductByShopID(product.getShopId());
            UserRepository cb = new UserRepository();
            User user = cb.getAccountByShopid(product.getShopId());
            List<ProductInfor> inforPrduct = productRepository.getinforProduct(productIdStr);
            List<String> availableImages = productRepository.getAvailableImages(productIdStr);  
            List<String> availableSize = productRepository.getAllSizes(productIdStr);
            List<String> availableColor = productRepository.getAllColors(productIdStr);
            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }
//            PrintWriter out = response.getWriter();
            request.setAttribute("product", product);
            request.setAttribute("availablesize", availableSize);
            request.setAttribute("availablecolor", availableColor);
            request.setAttribute("inforP", inforPrduct);
            request.setAttribute("availableImages", availableImages);
            request.setAttribute("countOrder", count);
            request.setAttribute("countEva", countEva);
            request.setAttribute("user", user);
            request.setAttribute("listP", listP);
            request.setAttribute("countWishList", countWishList);
            request.setAttribute("countOrderShop", countOrderShop);
            request.setAttribute("shop", shop);
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving the product details");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Call the doGet method to handle POST requests
        doGet(request, response);
    }
}
