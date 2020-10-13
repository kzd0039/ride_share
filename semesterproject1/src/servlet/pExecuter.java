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
public class pExecuter extends HttpServlet {

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
            String cmd = request.getParameter("cmd");
            String redirect = request.getParameter("redirect");
            if (cmd == null) {
                out.println("NULL!");
            } else if (cmd.equals("logout")) {
                HttpSession session = request.getSession(false);
                session.invalidate();
                response.sendRedirect(redirect);
            } else if (cmd.equals("purchase")) {
                HttpSession session = request.getSession(false);
                if(session==null)
                {
                    response.sendRedirect("./");
                }
                else
                {
                    String pid=request.getParameter("pid");
                    Integer author_id=Integer.valueOf(request.getParameter("author_id"));
                    Integer buyer_id=(Integer)session.getAttribute("id");
                    String price=request.getParameter("price");
                    sqlExecuter exe=new sqlExecuter();
                    exe.cmd="select balance from users where id="+buyer_id.toString();
                    exe.exec();
                    exe.next();
                    Float buyer_balance=exe.getFloat(1);
                    exe.close();
                    out.println(pid);
                    out.println(author_id);
                    out.println(buyer_id);
                    out.println(price);
                    out.println(buyer_balance);
                    if(Float.valueOf(price)>buyer_balance)
                    {
                        response.sendRedirect("./pDetails?Pid="+pid+"&status=1");
                    }
                    else
                    {
                        exe.updateBalance(buyer_id, -1*Float.valueOf(price));
                        exe.updateBalance(author_id, Float.valueOf(price));
                        exe.purchase(buyer_id, author_id, Integer.valueOf(pid));
                        response.sendRedirect("./pDetails?Pid="+pid+"&status=2");
                    }
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
