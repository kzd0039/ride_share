package servlet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zhang
 */
public class pCatalog extends HttpServlet {

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
        sqlExecuter exe = new sqlExecuter();
        int count = 0;
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            String nickName = null;
            Float balance = null;
            if (session != null) {
                nickName = (String) session.getAttribute("nickName");
                exe.cmd = "select balance from users where id=" + String.valueOf((int) session.getAttribute("id"));
                exe.exec();
                exe.next();
                balance = exe.getFloat(1);
                exe.close();
            }

            //------------------------------------------------------------------------------------------------------------------
            //Welcome Logan
            //------------------------------------------------------------------------------------------------------------------
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Catalog</title>");
            out.println("<LINK REL=\"StyleSheet\" HREF=\"./CSS/pCatalog.css\" TYPE=\"text/css\">");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body>");

            if (nickName == null) {
                out.println("<nav class=\"navbar navbar-inverse\">\n"
                        + "    <div ass=\"container-fluid\">\n"
                        + "    <div class=\"navbar-header\">");

                out.println("<a class=\"navbar-brand\" href=\"#\">PicXe</a>\n"
                        + "    </div>\n"
                        + "    <ul class=\"nav navbar-nav\">\n"
                        + "      <li><a href=\"./pMyAccount\">Account</a></li>");

                out.println("<li><a href=\"./pContact.xml\">Contact Us</a></li>\n"
                        + "    </ul>\n"
                        + "         <ul class=\"nav navbar-nav navbar-right\">\n"
                        + "        <li><a href=\"#\">Welcome! Tourist</a></li>\n"
                        + "        <li><a href=\"./pMyAccount\"><span class=\"glyphicon glyphicon-user\"></span> Login" + "</a></li>\n"
                        + "      </ul>\n"
                        + "  </div>\n"
                        + "</nav>");
            }
            //------------------------------------------------------------------------------------------------------------------
            //Status Bar
            //------------------------------------------------------------------------------------------------------------------

            if (nickName != null) {

                out.println("<nav class=\"navbar navbar-inverse\">\n"
                        + "    <div ass=\"container-fluid\">\n"
                        + "    <div class=\"navbar-header\">");

                out.println("<a class=\"navbar-brand\" href=\"#\">PicXe</a>\n"
                        + "    </div>\n"
                        + "    <ul class=\"nav navbar-nav\">\n"
                        + "      <li class=\"active\"><a href=\"./pMyAccount\">Home</a></li>\n");

                out.println("<li><a href=\"./pContact.xml\">Contact Us</a></li>\n"
                        + "    </ul>\n"
                        + "         <ul class=\"nav navbar-nav navbar-right\">\n"
                        + "<li><a href=\"pExecuter?cmd=logout&redirect=index.html\"></span>Welcome! " + nickName + "&nbsp<span class=\"glyphicon glyphicon-log-out\"></a></li>"
                        + "<li><a href=\"./pMyAccount\"><span class=\"glyphicon glyphicon-user\"></span>Account</a></li>"
                        + "        <li><a href=\"#\"><span class=\"glyphicon glyphicon-usd\"></span>Balance:" + String.valueOf(balance) + "</a></li>\n"
                        + "      </ul>\n"
                        + "  </div>\n"
                        + "</nav>");
                // out.println("<form action=\"pExecuter\" method=\"post\">");
                // out.println("<input type=\"hidden\" name=\"cmd\" value=\"logout\">");
                // out.println("<input type=\"hidden\" name=\"redirect\" value=\"./\">");
                // out.println("<input type=\"submit\" value=\"logout\"/>");
                //out.println("</form>");
            }
            //------------------------------------------------------------------------------------------------------------------
            //Filters
            //------------------------------------------------------------------------------------------------------------------
            out.println("<hr>");
            out.println("<form action=\"pCatalog\" method=\"get\">");
            out.println("Authors <select name=\"author_filter\" id=\"author_filter\">");
            out.println("<option value=\"0\">All</option>");
            exe.cmd = "SELECT distinct users.nickname from users join picture on users.id=picture.author_id order by users.id";
            exe.exec();
            while (exe.next()) {
                count += 1;
                out.println("<option value=\"" + String.valueOf(count) + "\">" + exe.getString(1) + "</option>");
            }
            exe.close();
            out.println("</select>");
            out.println("Categories <select name=\"topic_filter\" name=\"topic_filter\">");
            out.println("<option value=\"0\">All</option>");
            count = 0;
            exe.cmd = "select name from categories";
            exe.exec();
            while (exe.next()) {
                count += 1;
                out.println("<option value=\"" + String.valueOf(count) + "\">" + exe.getString(1) + "</option>");
            }
            exe.close();
            out.println("</select>");
            out.println("<input type=\"submit\" class=\"btn btn-info btn-xs\" value=\"Apply\"/>");
            out.println("</form>");
            //------------------------------------------------------------------------------------------------------------------
            //Display Catalog
            //------------------------------------------------------------------------------------------------------------------
            exe.cmd = "select * from picture where 1=1";
            String topic = request.getParameter("topic_filter");
            String author = request.getParameter("author_filter");
            if (topic != null && !topic.equals("0")) {
                exe.cmd = exe.cmd + " and category_id=" + topic;
            }
            if (author != null && !author.equals("0")) {
                exe.cmd = exe.cmd + " and author_id=" + author;
            }
            exe.exec();
            while (exe.next()) {
                out.println("<div class=\"gallery\">");
                out.println("    <a target=\"_blank\" href=\"./pDetails?Pid=" + exe.getInt(1).toString() + "\">");
                out.println("        <img src=\"./Pictures/" + exe.getString(6) + ".png\" alt=\"Trolltunga Norway\" width=\"640\" height=\"360\">");
                out.println("    </a>");
                out.println("    <div class=\"desc\">" + exe.getString(2) + "</div>");
                out.println("</div>");
            }
            exe.close();
            //------------------------------------------------------------------------------------------------------------------
            //End of File
            //------------------------------------------------------------------------------------------------------------------
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
