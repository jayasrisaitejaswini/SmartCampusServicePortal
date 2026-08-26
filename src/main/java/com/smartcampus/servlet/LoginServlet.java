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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "SELECT student_id, name FROM students "
                   + "WHERE email = ? AND password = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int studentId = rs.getInt("student_id");
                String name = rs.getString("name");

                HttpSession session = request.getSession();

                session.setAttribute("studentId", studentId);
                session.setAttribute("studentName", name);
                session.setAttribute("studentEmail", email);

                response.sendRedirect("StudentDashboardServlet");

            } else {

                response.setContentType("text/html;charset=UTF-8");

                response.getWriter().println(
                    "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset='UTF-8'>"
                    + "<title>Login Failed | Smart Campus</title>"
                    + "<style>"
                    + "body{margin:0;background:#080808;color:white;font-family:Arial,sans-serif;"
                    + "display:flex;align-items:center;justify-content:center;min-height:100vh;}"
                    + ".box{background:#111;border:1px solid #292929;border-radius:16px;"
                    + "padding:40px;text-align:center;width:90%;max-width:500px;}"
                    + "h1{font-size:28px;}"
                    + "p{color:#999;}"
                    + ".btn{display:inline-block;margin-top:20px;padding:12px 22px;"
                    + "background:#00e5ff;color:#001014;text-decoration:none;"
                    + "border-radius:8px;font-weight:bold;}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='box'>"
                    + "<h1>Invalid Email or Password</h1>"
                    + "<p>The email or password you entered is incorrect.</p>"
                    + "<a class='btn' href='login.html'>Try Again</a>"
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

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                "<h1>Login Failed</h1>"
            );
        }
    }

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("login.html");
    }
}