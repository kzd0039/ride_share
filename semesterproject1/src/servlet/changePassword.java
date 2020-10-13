package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "changePassword")
public class changePassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("cancel") != null){
            RequestDispatcher rd = request.getRequestDispatcher("/pMyAccount");
            rd.forward(request,response);
        }

        HttpSession session = request.getSession();
        int user_id = (int)session.getAttribute("id");
        String oldpassword = request.getParameter("oldpass");
        String newpassword = request.getParameter("newpass");
        if (oldpassword.equals((newpassword))){
            String errormessage1 = "new password should be different";
            request.setAttribute("errormessage",errormessage1);
            RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
            rd.forward(request,response);
        }

        String DB_URL = "jdbc:mysql://localhost:3306/pic_trade?serverTimezone=UTC";
        String USER = "root";
        String PASS = "jollyfish";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE password=\'" + oldpassword + "\'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String update = "UPDATE users SET password= \'" + newpassword + "\' WHERE id=\'" + user_id + "\'";
                Statement updateuser = conn.createStatement();
                updateuser.executeUpdate((update));
                conn.close();
                RequestDispatcher rd = request.getRequestDispatcher("/pMyAccount");
                rd.forward(request,response);

            } else {
                conn.close();
                String errormessage2 = "Incorrect password";
                request.setAttribute("errormessage",errormessage2);
                RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
                rd.forward(request,response);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
    }
}
