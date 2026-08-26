package com.smartcampus.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smartcampus.dao.DBConnection;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "SELECT admin_id, email FROM admins WHERE email = ? AND password = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                HttpSession session = request.getSession();

                session.setAttribute("adminId", rs.getInt("admin_id"));
                session.setAttribute("adminEmail", rs.getString("email"));

                response.sendRedirect("AdminDashboardServlet");

            } else {

            	response.getWriter().println(
            	        "<!DOCTYPE html>"
            	        + "<html>"
            	        + "<head>"
            	        + "<meta charset='UTF-8'>"
            	        + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            	        + "<title>Login Failed | Smart Campus</title>"
            	        + "<style>"
            	        + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
            	        + "body{margin:0;background:#080808;color:white;min-height:100vh;display:flex;align-items:center;justify-content:center;}"
            	        + ".box{width:90%;max-width:600px;background:#111;border:1px solid #292929;border-radius:16px;padding:45px;text-align:center;}"
            	        + ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;margin-bottom:30px;}"
            	        + ".logo span{color:#00e5ff;}"
            	        + ".icon{font-size:50px;color:#ff6b6b;margin-bottom:15px;}"
            	        + "h1{font-size:28px;margin-bottom:12px;}"
            	        + "p{color:#999;line-height:1.6;}"
            	        + ".btn{display:inline-block;margin-top:20px;padding:12px 22px;border-radius:8px;text-decoration:none;font-weight:bold;background:#00e5ff;color:#001014;}"
            	        + "</style>"
            	        + "</head>"
            	        + "<body>"
            	        + "<div class='box'>"
            	        + "<div class='logo'>SMART<span>CAMPUS</span></div>"
            	        + "<div class='icon'>!</div>"
            	        + "<h1>Invalid Admin Login</h1>"
            	        + "<p>The admin email or password you entered is incorrect.</p>"
            	        + "<a class='btn' href='admin-login.html'>Try Again</a>"
            	        + "</div>"
            	        + "</body>"
            	        + "</html>"
            	);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<h2>Something went wrong during admin login.</h2>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("admin-login.html");
    }
}