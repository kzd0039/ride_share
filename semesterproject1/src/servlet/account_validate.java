package servlet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Zhang
 */
public class account_validate {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "";
    String USER = "";
    String PASS = "";
    String DB_NAME = "";
    public int userid = -1;
    public String nickName = "";
    public float balance = 0;

    public account_validate(String database_url, String database_user, String database_password, String database_name) {
        DB_URL = database_url;
        USER = database_user;
        PASS = database_password;
        DB_NAME = database_name;
    }

    public account_validate(String database_name) {
        USER = "root";
        PASS = "jollyfish";
        DB_URL = "jdbc:mysql://localhost:3306/pic_trade?serverTimezone=UTC";
        DB_NAME = database_name;
    }

    public int newUser(String username, String password, String nickName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE username=\'" + username + "\'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return -2;
            }
            sql = "SELECT count(*) FROM users";
            rs = stmt.executeQuery(sql);
            rs.next();
            int count = rs.getInt(1) + 1;
            sql = "INSERT INTO users(username,password,nickname,balance,id)"
                    + "VALUES ('" + username + "',"
                    + "'" + password + "',"
                    + "'" + nickName + "',"
                    + "'" + "0" + "',"
                    + "'" + count + "')";
            stmt.executeUpdate(sql);
            conn.close();
            this.userid = count;
            this.nickName = nickName;
            this.balance = 0;
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
    }

    public int validate(String id, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE username=\'" + id + "\' AND password=\'" + pass + "\'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                userid = rs.getInt(5);
                nickName = rs.getString(3);
                balance = rs.getFloat(4);
                conn.close();
                return 1;
            } else {
                conn.close();
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

}
