package servlet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zhang
 */

public class pLogin extends HttpServlet {

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
        if (request.getParameter("ok") != null) {
            String n = request.getParameter("username");
            String p = request.getParameter("userpass");
            account_validate validater = new account_validate("pic_trade");
            int result = validater.validate(n, p);
            try (PrintWriter out = response.getWriter()) {
                if (result == 1) {
                    HttpSession session = request.getSession();
                    session.setAttribute("id", validater.userid);
                    session.setAttribute("nickName", validater.nickName);
                    out.println("Success");
                    response.sendRedirect("./pCatalog");
                } else if (result == 0) {
                    out.println("Failed");
                    response.sendRedirect("./");
                } else {
                    out.println(result);
                }
            }
        } else if (request.getParameter("cancel") != null) {
            response.sendRedirect("./pCatalog");

        } //--------------------------------------------------------------------------------
        //Sign Up Button on the Login Page
        //--------------------------------------------------------------------------------
        else if (request.getParameter("signup") != null) {
            response.sendRedirect("./pSignup");
        } //--------------------------------------------------------------------------------
        //Login Button on the Sign up Page
        //--------------------------------------------------------------------------------
        else if (request.getParameter("signup_submmit") != null) {
            String p1 = request.getParameter("userpass1");
            String p2 = request.getParameter("userpass2");
            String nickName = request.getParameter("nickName");
            String username = request.getParameter("username");
            if (!p1.equals(p2)) {
                response.sendRedirect("./pSignup?error=1");
            } else if (p1.equals("") || p2.equals("") || nickName.equals("") || username.equals("")) {
                response.sendRedirect("./pSignup?error=2");
            } else {
                account_validate validater = new account_validate("pic_trade");
                int re = validater.newUser(username, p1, nickName);
                if (re == -2) {
                    response.sendRedirect("./pSignup?error=3");
                } else if (re == -1 || re == -3) {
                    response.sendRedirect("./pSignup?error=4");
                } else {
                    response.sendRedirect("./");
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
