package servlet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zhang
 */
public class sqlExecuter {

    public String cmd = "";
    Connection conn;
    String DB_URL = "";
    String USER = "";
    String PASS = "";

    public sqlExecuter() {
        USER = "root";
        PASS = "jollyfish";
        DB_URL = "jdbc:mysql://localhost:3306/pic_trade?serverTimezone=UTC";
    }

    public sqlExecuter(String sqlScript) {
        USER = "root";
        PASS = "jollyfish";
        DB_URL = "jdbc:mysql://localhost:3306/pic_trade";
        cmd = sqlScript;
    }
    ResultSet rs;

    public Boolean next() {
        try {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public String getString(int i) {
        try {
            return rs.getString(i);
        } catch (SQLException e) {
            return null;
        }
    }

    public Integer getInt(int i) {
        try {
            return rs.getInt(i);
        } catch (SQLException e) {
            return -1;
        }
    }

    public Float getFloat(int i) {
        try {
            return rs.getFloat(i);
        } catch (SQLException e) {
            return -1.0f;
        }
    }

    public int purchase(int buyer_id, int author_id, int pid) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT count(*) FROM purchase";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int count = rs.getInt(1) + 1;
            sql = "INSERT INTO purchase(transaction_id,buyer_id,seller_id,picture_id)"
                    + "VALUES ('" + count + "',"
                    + "'" + buyer_id + "',"
                    + "'" + author_id + "',"
                    + "'" + pid + "')";
            stmt.executeUpdate(sql);
            conn.close();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
    }

    public int updateBalance(int user_id, Float amount) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "update users set balance=balance+ " + String.valueOf(amount) + " where id=" + String.valueOf(user_id);
            stmt.executeUpdate(sql);
            conn.close();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
    }

    public int newPicture(String title, int authorid, int categoryid, Float price, String thumbnail, String original, String watermarked) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT count(*) FROM picture";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int count = rs.getInt(1) + 1;
            sql = "INSERT INTO picture(id,name,author_id,category_id,price,thumbnail,original,watermarked)"
                    + "VALUES ('" + count + "',"
                    + "'" + title + "',"
                    + "'" + authorid + "',"
                    + "'" + categoryid + "',"
                    + "'" + price + "',"
                    + "'" + thumbnail + "',"
                    + "'" + original + "',"
                    + "'" + watermarked + "')";
            stmt.executeUpdate(sql);
            conn.close();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
    }

    public void update() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exec() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
