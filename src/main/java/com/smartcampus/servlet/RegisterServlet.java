package com.smartcampus.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartcampus.dao.DBConnection;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String rollNumber = request.getParameter("roll_number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "INSERT INTO students "
                   + "(name, roll_number, email, password) "
                   + "VALUES (?, ?, ?, ?)";

        response.setContentType("text/html;charset=UTF-8");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, rollNumber);
            ps.setString(3, email);
            ps.setString(4, password);

            int rows = ps.executeUpdate();

            ps.close();
            con.close();

            if (rows > 0) {

                response.getWriter().println(
                    "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset='UTF-8'>"
                    + "<title>Account Created | Smart Campus</title>"
                    + "<style>"
                    + "body{margin:0;background:#080808;color:white;"
                    + "font-family:Arial,sans-serif;display:flex;"
                    + "align-items:center;justify-content:center;"
                    + "min-height:100vh;}"
                    + ".box{background:#111;border:1px solid #292929;"
                    + "border-radius:16px;padding:40px;text-align:center;"
                    + "width:90%;max-width:500px;}"
                    + "h1{color:#00e5ff;}"
                    + "p{color:#999;}"
                    + ".btn{display:inline-block;margin-top:20px;"
                    + "padding:12px 22px;background:#00e5ff;"
                    + "color:#001014;text-decoration:none;"
                    + "border-radius:8px;font-weight:bold;}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='box'>"
                    + "<h1>Account Created Successfully!</h1>"
                    + "<p>Your student account has been created.</p>"
                    + "<p>You can now login using your email and password.</p>"
                    + "<a class='btn' href='login.html'>Go to Login</a>"
                    + "</div>"
                    + "</body>"
                    + "</html>"
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Registration Failed | Smart Campus</title>"
                + "<style>"
                + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
                + "body{margin:0;background:#080808;color:white;min-height:100vh;"
                + "display:flex;align-items:center;justify-content:center;}"
                + ".box{width:90%;max-width:600px;background:#111;"
                + "border:1px solid #292929;border-radius:16px;padding:45px;text-align:center;}"
                + ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;margin-bottom:30px;}"
                + ".logo span{color:#00e5ff;}"
                + ".icon{font-size:55px;color:#ff6b6b;margin-bottom:15px;}"
                + "h1{font-size:28px;margin-bottom:12px;}"
                + "p{color:#999;line-height:1.6;}"
                + ".btn{display:inline-block;margin-top:20px;padding:12px 22px;"
                + "border-radius:8px;text-decoration:none;font-weight:bold;"
                + "background:#00e5ff;color:#001014;}"
                + ".btn:hover{opacity:.85;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='box'>"
                + "<div class='logo'>SMART<span>CAMPUS</span></div>"
                + "<div class='icon'>!</div>"
                + "<h1>Registration Failed</h1>"
                + "<p>Unable to create your account.</p>"
                + "<p>Please check your details and try again.</p>"
                + "<a class='btn' href='register.html'>Go Back</a>"
                + "</div>"
                + "</body>"
                + "</html>"
            );
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("register.html");
    }
}