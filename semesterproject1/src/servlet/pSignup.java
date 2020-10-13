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

/**
 *
 * @author Zhang
 */
public class pSignup extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Account Signup</title>");
            out.println("<LINK REL=\"StyleSheet\" HREF=\"./CSS/style.css\" TYPE=\"text/css\">");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body>");
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
            out.println("<center>");
            out.println("<h2>New to PicXe? Sign Up!</h2>");
            out.println("<br>");
            out.println("<form action=\"pLogin\" method=\"post\">");

            out.println("<div class=\"block\">");
            out.println("<label>Username:</label><input type=\"text\" name=\"username\"><br/><br/>");
            out.println("</div>");

            out.println("<div class=\"block\">");
            out.println("<label>Password:</label><input type=\"text\" name=\"userpass1\"><br/><br/>");
            out.println("</div>");

            out.println("<div class=\"block\">");
            out.println("<label>Type Again:</label><input type=\"text\" name=\"userpass2\"><br/><br/>");
            out.println("</div>");

            out.println("<div class=\"block\">");
            out.println("<label>Nickname:</label><input type=\"text\" name=\"nickName\"><br/><br/>");
            out.println("</div>");
            
            out.println("<div class=\"block center\" style=\"padding-left:55px\">");
            out.println("<input name=\"signup_submmit\" class=\"btn btn-primary\" type=\"submit\" value=\"Sign Up\"/>");
            out.println("</div>");
            out.println("</form>");
            out.println("</center>");
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
