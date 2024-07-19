package controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.ProductInfor;
import model.Shop;
import repository.ProductRepository;
import repository.ShopOwnerRepository;
import service.RandomCodeGenerator;

@WebServlet(name = "CreateProductServlet", urlPatterns = {"/createproduct"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class CreateProductServlet extends HttpServlet {

    private Cloudinary cloudinary;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        ServletContext context = config.getServletContext();
        String envFilePath = context.getRealPath("/WEB-INF/.env");
        Dotenv dotenv = Dotenv.configure().directory(envFilePath).load();
        cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nameP = request.getParameter("nameP");
        String descP = request.getParameter("descP");
        String priceP = request.getParameter("priceP");
        String typeP = request.getParameter("categoryP");

        String[] colors = request.getParameterValues("colorP");
        String[] sizes = request.getParameterValues("sizeP");
        String[] quantities = request.getParameterValues("quantityP");

        List<String> imageUrls = new ArrayList<>();
        HttpSession session = request.getSession();
        Shop shop = (Shop) session.getAttribute("shop");
        int shopid = shop.getShopId();
        RandomCodeGenerator random = new RandomCodeGenerator();
        int maxFiles = 9; // Số lượng file tối đa
        ShopOwnerRepository son = new ShopOwnerRepository();
        ProductRepository pro = new ProductRepository();

        try {
            int uploadedCount = 0;
            for (Part filePart : request.getParts()) {
                if (filePart.getName().equals("file")) {
                    String fileName = filePart.getSubmittedFileName();
                    if (fileName != null && !fileName.isEmpty()) {
                        uploadedCount++;
                        if (uploadedCount > maxFiles) {
                            break; // Vượt quá số lượng file tối đa
                        }

                        File tempFile = File.createTempFile("upload_", "_" + fileName);
                        filePart.write(tempFile.getAbsolutePath());

                        // Generate random id for each file
                        String randomid = random.generateRandomCode();

                        // Upload the file to Cloudinary
                        Map<String, Object> uploadParams = ObjectUtils.asMap(
                                "use_filename", true,
                                "unique_filename", false,
                                "overwrite", true,
                                "public_id", randomid, // Set the desired name for the uploaded image
                                "folder", "user" // Specify the nested folder path
                        );

                        Map uploadResult = cloudinary.uploader().upload(tempFile, uploadParams);
                        String uploadedImageUrl = (String) uploadResult.get("secure_url");
                        imageUrls.add(uploadedImageUrl);

                        // Clean up the temporary file
                        if (tempFile.exists()) {
                            tempFile.delete();
                        }
                    }
                }
            }

            if (uploadedCount == 0 || imageUrls.isEmpty()) {
                // Không có file nào được tải lên
                String ms = "Bạn chưa thêm hình ảnh sản phẩm";
                request.setAttribute("error", ms);
                request.getRequestDispatcher("./createProductShop.jsp").forward(request, response);
                return;
            }

            // Store all image URLs in session
            for (int i = 0; i < maxFiles; i++) {
                if (i < imageUrls.size()) {
                    session.setAttribute("img" + (i + 1), imageUrls.get(i));
                } else {
                    session.setAttribute("img" + (i + 1), null); // Nếu không đủ file, set giá trị null
                }
            }

            int productId = pro.addProduct(nameP, descP, priceP, "0", shopid, typeP);
            pro.addImageUrls(productId, imageUrls);

            // Thêm thông tin loại sản phẩm
            if (colors != null && sizes != null && quantities != null
                    && colors.length == sizes.length && sizes.length == quantities.length) {
                List<ProductInfor> productInfos = new ArrayList<>();
                for (int i = 0; i < colors.length; i++) {
                    String color = colors[i];
                    String size = sizes[i];
                    int quantity = Integer.parseInt(quantities[i]); // Chuyển đổi số lượng thành kiểu int
                    productInfos.add(new ProductInfor(0, color, size, quantity, productId));
                }
                pro.updateOrInsertProductInfos(productInfos);
            } else {
                String errorMessage = "Dữ liệu loại sản phẩm không hợp lệ.";
                request.setAttribute("error", errorMessage);
                request.getRequestDispatcher("./createProductShop.jsp").forward(request, response);
                return;
            }

            response.sendRedirect("ListProductShopOwner");
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}