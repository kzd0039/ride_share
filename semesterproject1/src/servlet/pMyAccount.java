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
public class pMyAccount extends HttpServlet {

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
            //------------------------------------------------------------------
            //Verify Login Status
            //------------------------------------------------------------------
            Float balance = null;
            String nickName = null;
            Integer id = -1;
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect("./");
            } else {
                nickName = (String) session.getAttribute("nickName");
                balance = (Float) session.getAttribute("balance");
                id = (Integer) session.getAttribute("id");
            }
            sqlExecuter exe = new sqlExecuter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MyAccount</title>");
            out.println("<LINK REL=\"StyleSheet\" HREF=\"./CSS/MyAccount.css\" TYPE=\"text/css\">");
            out.println("<LINK REL=\"StyleSheet\" HREF=\"./CSS/style.css\" TYPE=\"text/css\">");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            String success = request.getParameter("success");

            out.println("<nav class=\"navbar navbar-inverse\">\n"
                    + "    <div ass=\"container-fluid\">\n"
                    + "    <div class=\"navbar-header\">");

            out.println("<a class=\"navbar-brand\" href=\"./pCatalog\">PicXe</a>\n"
                    + "    </div>\n"
                    + "    <ul class=\"nav navbar-nav\">\n"
                    + "      <li><a href=\"./pCatalog\">Catalog</a></li>");

            out.println("<li><a href=\"./pContact.xml\">Contact Us</a></li>\n" + "    </ul>\n"
                    + "<ul class=\"nav navbar-nav navbar-right\">\n"
                    + "<li><a href=\"pExecuter?cmd=logout&redirect=index.html\"><span class=\"glyphicon glyphicon-log-out\"></span> logout</a></li>"
                    + "      </ul>\n"
                    + "  </div>\n"
                    + "</nav>");
            out.println("<h1>MyAccount</h1>");
            if (success != null) {
                out.println("<div class=\"alert alert-success\">\n"
                        + " <strong>Success!</strong> Upload success.\n"
                        + "  </div>");
                out.println("<script>$(\".alert\").delay(4000).slideUp(200, function() {\n"
                        + "    $(this).alert('close');\n"
                        + "});</script>");
            }
            //------------------------------------------------------------------------------------------------------
            //Tabs
            //------------------------------------------------------------------------------------------------------
            out.println("<div class=\"tab\">");
            out.println("    <button class=\"tablinks\" onclick=\"openCity(event, 'myWorks')\">My Works</button>");
            out.println("    <button class=\"tablinks\" onclick=\"openCity(event, 'purchased')\">Purchased Items</button>");
            out.println("    <button class=\"tablinks\" onclick=\"openCity(event, 'transaction')\">Transaction History</button>");
            out.println("    <button class=\"tablinks\" onclick=\"openCity(event, 'upload')\">Upload</button>");
            out.println("    <button class=\"tablinks\" onclick=\"location.href = \'http://localhost:8080/semesterproject1_war_exploded/changePassword.jsp\'\">Change Password</button>");
            out.println("</div>");
            //------------------------------------------------------------------------------------------------------
            //My Works
            //------------------------------------------------------------------------------------------------------
            out.println("<div id=\"myWorks\" class=\"tabcontent\">");
            exe.cmd = "select * from picture where author_id=" + id.toString();
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
            out.println("</div>");
            //------------------------------------------------------------------------------------------------------
            //Purchased Items
            //------------------------------------------------------------------------------------------------------
            out.println("<div id=\"purchased\" class=\"tabcontent\">");
            exe.cmd = "select * from picture join purchase on picture.id=purchase.picture_id where buyer_id=" + id.toString();
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
            out.println("</div>");
            //------------------------------------------------------------------------------------------------------
            //Transaction History
            //------------------------------------------------------------------------------------------------------
            out.println("<div id=\"transaction\" class=\"tabcontent\">");
            out.println("    <h3>Transaction History</h3>");
            out.println("<input class=\"form-control\" id=\"myInput\" type=\"text\" placeholder=\"Search..\">");
            out.println("<table id=\"transaction_table\" class=\"table table-hover\">");
            out.println("<tr>");
            out.println("<th>Transaction ID</th>");
            out.println("<th>Buyer</th>");
            out.println("<th>Sellor</th>");
            out.println("<th>Item</th>");
            out.println("<th>Price</th>");
            out.println("</tr>");

            out.println("<script>\n"
                    + "$(document).ready(function(){\n"
                    + "  $(\"#myInput\").on(\"keyup\", function() {\n"
                    + "    var value = $(this).val().toLowerCase();\n"
                    + "    $(\"#transaction_table tr\").filter(function() {\n"
                    + "      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)\n"
                    + "    });\n"
                    + "  });\n"
                    + "});\n"
                    + "</script>");
            exe.cmd = "select transaction_id,buyer_id,seller_id,picture_id,name,price,A.nickname,B.nickName from purchase join picture on purchase.picture_id=picture.id "
                    + " join users as A on seller_id=A.id join users as B on buyer_id=B.id"
                    + " where buyer_id=" + id.toString() + " or seller_id=" + id.toString();
            exe.exec();
            while (exe.next()) {
                out.println("<tr>");
                out.println("<td>" + exe.getInt(1).toString() + "</td>");
                out.println("<td>" + exe.getString(8) + "</td>");
                out.println("<td>" + exe.getString(7) + "</td>");
                out.println("<td><a href=\"./pDetails?Pid=" + exe.getInt(4).toString() + "\">" + exe.getString(5) + "</a></td>");
                out.println("<td>" + exe.getFloat(6).toString() + " Gallery Coins</td>");
                out.println("</tr>");
            }
            out.print("</table>");
            exe.close();
            out.println("</div>");

            //------------------------------------------------------------------------------------------------------
            //Upload
            //------------------------------------------------------------------------------------------------------
            out.println("<div id=\"upload\" class=\"tabcontent\">");
            out.println("<center>");
            out.println("<h3 style=\"padding-left:66px\">Upload Picture</h3><br>");
            out.println("<form action=\"pUpload\" enctype=\"multipart/form-data\" method=\"post\">");
            out.println("<input type=\"file\" name=\"uppic\" style=\"padding-left:185px\"><br>");
            out.println("<label>Title:</label><input type=\"text\" name=\"title\"><br/>");
            out.println("<label>Price:</label><input type=\"text\" name=\"price\"><br/>");
            out.println("<div class=\"block center\" style=\"padding-left:43px\">");
            out.println("<label>Categories:</label><select name=\"topic_filter\" name=\"topic_filter\">");
            int count = 0;
            exe.cmd = "select name from categories";
            exe.exec();
            while (exe.next()) {
                count += 1;
                out.println("<option value=\"" + String.valueOf(count) + "\">" + exe.getString(1) + "</option>");
            }
            exe.close();
            out.println("</select><br>");
            out.println("</div>");
            out.println("<div class=\"block center\">");
            out.println("<input name=\"upload\" class=\"btn btn-primary\" type=\"submit\" value=\"Upload\"/>");
            out.println("</div>");
            out.println("</form>");
            out.println("</center>");
            out.println("</div>");


            //------------------------------------------------------------------------------------------------------
            //Tab Control
            //------------------------------------------------------------------------------------------------------
            out.println("<script>");
            out.println("    function openCity(evt, cityName) {");
            out.println("        var i, tabcontent, tablinks;");
            out.println("        tabcontent = document.getElementsByClassName(\"tabcontent\");");
            out.println("        for (i = 0; i < tabcontent.length; i++) {");
            out.println("            tabcontent[i].style.display = \"none\";");
            out.println("        }");
            out.println("        tablinks = document.getElementsByClassName(\"tablinks\");");
            out.println("        for (i = 0; i < tablinks.length; i++) {");
            out.println("            tablinks[i].className = tablinks[i].className.replace(\" active\", \"\");");
            out.println("        }");
            out.println("        document.getElementById(cityName).style.display = \"block\";");
            out.println("        evt.currentTarget.className += \" active\";");
            out.println("    }");
            out.println("</script>");
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
