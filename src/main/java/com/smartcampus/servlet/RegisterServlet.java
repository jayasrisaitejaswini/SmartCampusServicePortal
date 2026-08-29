package com.smartcampus.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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

        response.setContentType("text/html;charset=UTF-8");

        // Remove extra spaces
        if (name != null) name = name.trim();
        if (rollNumber != null) rollNumber = rollNumber.trim();
        if (email != null) email = email.trim();
        if (password != null) password = password.trim();


        // SERVER-SIDE ROLL NUMBER VALIDATION
        if (rollNumber == null || !rollNumber.matches("[A-Za-z0-9]{10}")) {

            showMessage(
                response,
                "Invalid Roll Number",
                "Roll number must contain exactly 10 characters.",
                "Use only uppercase letters, lowercase letters, and numbers.",
                "register.html"
            );

            return;
        }


        // BASIC EMAIL VALIDATION
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            showMessage(
                response,
                "Invalid Email",
                "Please enter a valid email address.",
                "Example: student@gmail.com",
                "register.html"
            );

            return;
        }


        String sql = "INSERT INTO students "
                   + "(name, roll_number, email, password) "
                   + "VALUES (?, ?, ?, ?)";


        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, name);
            ps.setString(2, rollNumber);
            ps.setString(3, email);
            ps.setString(4, password);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                showMessage(
                    response,
                    "Account Created Successfully!",
                    "Your student account has been created.",
                    "You can now login using your email and password.",
                    "login.html"
                );
            }

        }
        catch (SQLIntegrityConstraintViolationException e) {

            String errorMessage = e.getMessage().toLowerCase();

            // EMAIL ALREADY EXISTS
            if (errorMessage.contains("email")) {

                showMessage(
                    response,
                    "Email Already Exists",
                    "This email is already registered.",
                    "Please use another email or login to your existing account.",
                    "login.html"
                );

            }

            // ROLL NUMBER ALREADY EXISTS
            else if (errorMessage.contains("roll_number")
                    || errorMessage.contains("roll number")) {

                showMessage(
                    response,
                    "Roll Number Already Exists",
                    "This roll number is already registered.",
                    "Please use another roll number or login to your existing account.",
                    "login.html"
                );

            }

            else {

                showMessage(
                    response,
                    "Registration Failed",
                    "This account information already exists.",
                    "Please check your details and try again.",
                    "register.html"
                );
            }

        }
        catch (SQLException e) {

            e.printStackTrace();

            showMessage(
                response,
                "Registration Failed",
                "Unable to create your account at the moment.",
                "Please try again later.",
                "register.html"
            );

        }
        catch (Exception e) {

            e.printStackTrace();

            showMessage(
                response,
                "Registration Failed",
                "Something went wrong.",
                "Please check your details and try again.",
                "register.html"
            );
        }
    }


    private void showMessage(
            HttpServletResponse response,
            String title,
            String message,
            String description,
            String link
    ) throws IOException {

        response.getWriter().println(

            "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<meta charset='UTF-8'>"
            + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            + "<title>" + title + " | Smart Campus</title>"

            + "<style>"

            + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"

            + "body{"
            + "margin:0;"
            + "background:#080808;"
            + "color:white;"
            + "min-height:100vh;"
            + "display:flex;"
            + "align-items:center;"
            + "justify-content:center;"
            + "}"

            + ".box{"
            + "width:90%;"
            + "max-width:550px;"
            + "background:#111;"
            + "border:1px solid #292929;"
            + "border-radius:16px;"
            + "padding:45px;"
            + "text-align:center;"
            + "}"

            + ".logo{"
            + "font-size:22px;"
            + "font-weight:bold;"
            + "letter-spacing:1px;"
            + "margin-bottom:30px;"
            + "}"

            + ".logo span{color:#00e5ff;}"

            + ".icon{"
            + "font-size:45px;"
            + "color:#00e5ff;"
            + "margin-bottom:15px;"
            + "}"

            + "h1{"
            + "font-size:28px;"
            + "margin-bottom:15px;"
            + "}"

            + "p{"
            + "color:#999;"
            + "line-height:1.6;"
            + "}"

            + ".btn{"
            + "display:inline-block;"
            + "margin-top:22px;"
            + "padding:12px 25px;"
            + "background:#00e5ff;"
            + "color:#001014;"
            + "text-decoration:none;"
            + "border-radius:8px;"
            + "font-weight:bold;"
            + "}"

            + ".btn:hover{opacity:.85;}"

            + "</style>"

            + "</head>"

            + "<body>"

            + "<div class='box'>"

            + "<div class='logo'>SMART<span>CAMPUS</span></div>"

            + "<div class='icon'>✓</div>"

            + "<h1>" + title + "</h1>"

            + "<p>" + message + "</p>"

            + "<p>" + description + "</p>"

            + "<a class='btn' href='" + link + "'>"

            + (link.equals("login.html") ? "Go to Login" : "Go Back")

            + "</a>"

            + "</div>"

            + "</body>"

            + "</html>"
        );
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("register.html");
    }
}