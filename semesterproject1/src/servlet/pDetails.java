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
public class pDetails extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String picloc = "";
            Boolean purchased = false;
            Integer user_id = -1;
            String pid = request.getParameter("Pid");
            String status = request.getParameter("status");

            if (pid == null) {
                response.sendRedirect("./pCatalog");
            } else {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    user_id = (Integer) session.getAttribute("id");
                }
                sqlExecuter exe = new sqlExecuter();
                exe.cmd = "select * from purchase where buyer_id=" + user_id + " and picture_id=" + pid;
                exe.exec();
                if (exe.next()) {
                    purchased = true;
                }
                exe.close();
                exe.cmd = "select * from picture where author_id=" + user_id + " and id=" + pid;
                exe.exec();
                if (exe.next()) {
                    purchased = true;
                }
                exe.close();
                exe.cmd = "select * from picture where id=" + pid;
                exe.exec();
                if (!exe.next()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Details</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>404 Not Found</h1>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    if (purchased) {
                        picloc = exe.getString(7);
                    } else {
                        picloc = exe.getString(8);
                    }
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Details</title>");
                    out.println("<LINK REL=\"StyleSheet\" HREF=\"./CSS/pDeatils.css\" TYPE=\"text/css\">");
                    out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
                    out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
                    out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<nav class=\"navbar navbar-inverse\">\n"
                            + "    <div ass=\"container-fluid\">\n"
                            + "    <div class=\"navbar-header\">");

                    out.println("<a class=\"navbar-brand\" href=\"./pCatalog\">PicXe</a>\n"
                            + "    </div>\n"
                            + "    <ul class=\"nav navbar-nav\">\n"
                            + "      <li><a href=\"./pCatalog\">Catalog</a></li>");

                    out.println("<li><a href=\"./pContact.xml\">Contact</a></li>\n" + "    </ul>\n"
                            + "<ul class=\"nav navbar-nav navbar-right\">\n"
                            + "<li><a href=\"./pMyAccount\"><span class=\"glyphicon glyphicon-user\"></span> Account</a></li>"
                            + "<li><a href=\"index.html\"><span class=\"glyphicon glyphicon-log-out\"></span> logout</a></li>"
                            + "      </ul>\n"
                            + "  </div>\n"
                            + "</nav>");
                    if (status != null) {
                        if (status.equals("1")) {
                            out.println(" <div class=\"alert alert-warning\">\n"
                                    + "    <strong>Warning!</strong> You don't have enough balance.\n"
                                    + "  </div>");
                        } else if (status.equals("2")) {
                            out.println("<div class=\"alert alert-success\">\n"
                                    + "    <strong>Success!</strong> Your purchase is complete.\n"
                                    + "  </div>");

                        }
                    }
                    if (!purchased) {
                        out.println("<center>");
                        out.println("<form action=\"pExecuter\" method=\"post\"><br>");
                        out.println("<input name=\"cmd\" type=\"hidden\" value=\"purchase\"/>");
                        out.println("<input name=\"pid\" type=\"hidden\" value=\"" + pid + "\"/>");
                        out.println("<input name=\"author_id\" type=\"hidden\" value=\"" + exe.getInt(3).toString() + "\"/>");
                        out.println("<input name=\"price\" type=\"hidden\" value=\"" + exe.getFloat(5).toString() + "\"/>");
                        out.println("<input name=\"upload\" class=\"btn btn-primary\" type=\"submit\" value=\"Purchase for " + exe.getFloat(5).toString() + " Gallery Coins\"/>");
                        out.println("</form>");
                        out.println("</center>");
                    }

                    out.println("<div class=\"gallery\">");
                    out.println("    <a target=\"_blank\" href=\"./pDetails?Pid=" + exe.getInt(1).toString() + "\">");
                    out.println("        <img src=\"./Pictures/" + picloc + ".png\" alt=\"Trolltunga Norway\" width=\"640\" height=\"360\">");
                    out.println("    </a>");
                    out.println("    <div class=\"desc\">" + exe.getString(2) + "</div>");
                    out.println("</div>");

                    

                    out.println("</body>");
                    out.println("</html>");
                    exe.close();
                }
            }
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
