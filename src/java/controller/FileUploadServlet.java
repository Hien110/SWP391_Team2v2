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
import java.util.Map;
import model.User;
import repository.UserRepository;
import service.RandomCodeGenerator;

/**
 *
 * @author duyqu
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/fileuploadservlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class FileUploadServlet extends HttpServlet {

    private Cloudinary cloudinary;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init();
        ServletContext context = config.getServletContext();
        String envFilePath = context.getRealPath("/WEB-INF/.env");
        Dotenv dotenv = Dotenv.configure().directory(envFilePath).load();

        cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReadFromJSP</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReadFromJSP at " + request.getContextPath() + "</h1>");
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
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        File tempFile = File.createTempFile("upload_", "_" + fileName);
        filePart.write(tempFile.getAbsolutePath());
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        RandomCodeGenerator random = new RandomCodeGenerator();
        String randomid = random.generateRandomCode();
        try {
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
            UserRepository cdb = new UserRepository();
            cdb.updateAvata(user.getUsername(), uploadedImageUrl);
             User c1 = cdb.getAccountByUsername(user.getUsername());
            session.setAttribute("user", c1);
            response.sendRedirect("./profileUser.jsp");
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        } finally {
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
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
