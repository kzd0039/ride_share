package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

/**
 *
 * @author Zhang
 */
public class pUpload extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        String nickName = null;
        Integer id = null;
        try (PrintWriter out = response.getWriter()) {
            if (session == null) {
                response.sendRedirect("./");
            } else {
                nickName = (String) session.getAttribute("nickName");
                id = (Integer) session.getAttribute("id");
            }
            String title = "";
            String category = "";
            Float price = 0.0f;
            String watermarked = "";
            String fileName = "";
            String thumbnail = "";
            fileName = UUID.randomUUID().toString();
            thumbnail = UUID.randomUUID().toString();
            watermarked = UUID.randomUUID().toString();
            try {
                ServletFileUpload upload = new ServletFileUpload();
                FileItemIterator iter = upload.getItemIterator(request);
                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                    String name = item.getFieldName();
                    InputStream stream = item.openStream();
                    if (!item.isFormField()) {
                        String root = getServletContext().getRealPath("/");
                        File path = new File(root + "/Pictures");
                        if (!path.exists()) {
                            boolean status = path.mkdirs();
                        }
                        File uploadedFile = new File(path + "/" + fileName + ".png");
                        File watermarkedPic = new File(path + "/" + watermarked + ".png");
                        File thumbnailPic = new File(path + "/" + thumbnail + ".png");
                        OutputStream os = new FileOutputStream(uploadedFile);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = stream.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                        }
                        os.close();
                        stream.close();
                        ImageProcessor.addImageWatermark(new File(path + "/download.png"), uploadedFile, watermarkedPic);
                        ImageProcessor.resize(uploadedFile, thumbnailPic, 640, 360);
                    } else {
                        String value=Streams.asString(stream);
                        if (name.equals("title")) {
                            title = value;
                        } else if (name.equals("price")) {
                            price = Float.valueOf(value);
                        } else if (name.equals("topic_filter")) {
                            category = value;
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            sqlExecuter exe = new sqlExecuter();
            exe.newPicture(title, id, Integer.valueOf(category), price, thumbnail, fileName, watermarked);
            response.sendRedirect("./pMyAccount?success=1");
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
//        processRequest(request, response);
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
//        processRequest(request, response);
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
