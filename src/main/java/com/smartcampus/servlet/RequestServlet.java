package com.smartcampus.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smartcampus.dao.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/submitRequest")
public class RequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String studentName = request.getParameter("studentName");
        String studentId = request.getParameter("studentId");
        String email = request.getParameter("email");
        String category = request.getParameter("category");
        String location = request.getParameter("location");
        String priority = request.getParameter("priority");
        String description = request.getParameter("description");

        /* ==============================
           CHECK LOGIN SESSION
           ============================== */

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("studentId") == null) {

            response.getWriter().println(
                "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Login Required | Smart Campus</title>"
                + "<style>"
                + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
                + "body{margin:0;background:#080808;color:white;min-height:100vh;"
                + "display:flex;align-items:center;justify-content:center;}"
                + ".box{width:90%;max-width:600px;background:#111;"
                + "border:1px solid #292929;border-radius:16px;"
                + "padding:45px;text-align:center;}"
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
                + "<h1>Login Required</h1>"
                + "<p>Please login before submitting a service request.</p>"
                + "<a class='btn' href='login.html'>Go to Login</a>"
                + "</div>"
                + "</body>"
                + "</html>"
            );

            return;
        }

        /* ==============================
           GET LOGGED-IN STUDENT ID
           ============================== */

        int loggedInStudentId =
                (Integer) session.getAttribute("studentId");

        int enteredStudentId;

        /* ==============================
           CHECK ENTERED STUDENT ID
           ============================== */

        try {

            enteredStudentId = Integer.parseInt(studentId);

        } catch (Exception e) {

            response.getWriter().println(
                "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Invalid Student ID | Smart Campus</title>"
                + "<style>"
                + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
                + "body{margin:0;background:#080808;color:white;min-height:100vh;"
                + "display:flex;align-items:center;justify-content:center;}"
                + ".box{width:90%;max-width:600px;background:#111;"
                + "border:1px solid #292929;border-radius:16px;"
                + "padding:45px;text-align:center;}"
                + ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;margin-bottom:30px;}"
                + ".logo span{color:#00e5ff;}"
                + ".icon{font-size:55px;color:#ff6b6b;margin-bottom:15px;}"
                + "h1{font-size:28px;margin-bottom:12px;}"
                + "p{color:#999;line-height:1.6;}"
                + ".btn{display:inline-block;margin-top:20px;padding:12px 22px;"
                + "border-radius:8px;text-decoration:none;font-weight:bold;"
                + "background:#00e5ff;color:#001014;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='box'>"
                + "<div class='logo'>SMART<span>CAMPUS</span></div>"
                + "<div class='icon'>!</div>"
                + "<h1>Invalid Student ID</h1>"
                + "<p>Please enter a valid Student ID.</p>"
                + "<a class='btn' href='request.html'>Try Again</a>"
                + "</div>"
                + "</body>"
                + "</html>"
            );

            return;
        }

        /* ==============================
           CHECK ID BELONGS TO LOGGED-IN STUDENT
           ============================== */

        if (enteredStudentId != loggedInStudentId) {

            response.getWriter().println(
                "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Student ID Mismatch | Smart Campus</title>"
                + "<style>"
                + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
                + "body{margin:0;background:#080808;color:white;min-height:100vh;"
                + "display:flex;align-items:center;justify-content:center;}"
                + ".box{width:90%;max-width:600px;background:#111;"
                + "border:1px solid #292929;border-radius:16px;"
                + "padding:45px;text-align:center;}"
                + ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;margin-bottom:30px;}"
                + ".logo span{color:#00e5ff;}"
                + ".icon{font-size:55px;color:#ff6b6b;margin-bottom:15px;}"
                + "h1{font-size:28px;margin-bottom:12px;}"
                + "p{color:#999;line-height:1.6;}"
                + ".highlight{color:#00e5ff;font-weight:bold;}"
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
                + "<h1>Student ID Mismatch</h1>"
                + "<p>The Student ID you entered does not belong to your logged-in account.</p>"
                + "<p>Please enter your own Student ID: "
                + "<span class='highlight'>" + loggedInStudentId + "</span></p>"
                + "<a class='btn' href='request.html'>Try Again</a>"
                + "</div>"
                + "</body>"
                + "</html>"
            );

            return;
        }

        /* ==============================
           USE SESSION STUDENT ID
           ============================== */

        int actualStudentId = loggedInStudentId;

        /* ==============================
           AUTOMATIC DEPARTMENT MAPPING
           ============================== */

        String department;

        if (category.equals("Wi-Fi / Network")) {

            department = "IT Support";

        } else if (category.equals("Electrical")) {

            department = "Electrical";

        } else if (category.equals("Hostel")) {

            department = "Hostel";

        } else if (category.equals("Library")) {

            department = "Library";

        } else if (category.equals("Transport")) {

            department = "Transport";

        } else if (category.equals("Canteen")) {

            department = "Canteen";

        } else if (category.equals("Classroom")) {

            department = "Maintenance";

        } else {

            department = "Maintenance";
        }

        /* ==============================
           INSERT REQUEST
           ============================== */

        String sql =
                "INSERT INTO requests "
                + "(student_id, category, location, description, priority, status, department) "
                + "VALUES (?, ?, ?, ?, ?, 'Pending', ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setInt(1, actualStudentId);
            ps.setString(2, category);
            ps.setString(3, location);
            ps.setString(4, description);
            ps.setString(5, priority);
            ps.setString(6, department);

            ps.executeUpdate();

            /* ==============================
               GET GENERATED REQUEST ID
               ============================== */

            ResultSet generatedKeys = ps.getGeneratedKeys();

            int generatedRequestId = 0;

            if (generatedKeys.next()) {

                generatedRequestId =
                        generatedKeys.getInt(1);
            }

            generatedKeys.close();
            ps.close();
            con.close();

            /* ==============================
               SUCCESS PAGE
               ============================== */

            response.getWriter().println(
                "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Request Submitted | Smart Campus</title>"
                + "<style>"
                + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
                + "body{margin:0;background:#080808;color:white;min-height:100vh;"
                + "display:flex;align-items:center;justify-content:center;}"
                + ".box{width:90%;max-width:650px;background:#111;"
                + "border:1px solid #292929;border-radius:16px;"
                + "padding:45px;text-align:center;}"
                + ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;margin-bottom:30px;}"
                + ".logo span{color:#00e5ff;}"
                + ".success{font-size:55px;color:#63e681;margin-bottom:15px;}"
                + "h1{font-size:30px;margin-bottom:12px;}"
                + "p{color:#999;line-height:1.6;}"
                + ".id{font-size:22px;font-weight:bold;color:#00e5ff;}"
                + ".buttons{margin-top:25px;}"
                + "a{display:inline-block;margin:8px;padding:12px 22px;"
                + "background:#00e5ff;color:#001014;text-decoration:none;"
                + "border-radius:8px;font-weight:bold;}"
                + "a:hover{opacity:.85;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='box'>"
                + "<div class='logo'>SMART<span>CAMPUS</span></div>"
                + "<div class='success'>✓</div>"
                + "<h1>Request Submitted Successfully!</h1>"
                + "<p>Your campus service request has been recorded successfully.</p>"
                + "<p class='id'>Your Request ID: #"
                + generatedRequestId
                + "</p>"
                + "<div class='buttons'>"
                + "<a href='StudentDashboardServlet'>Student Dashboard</a>"
                + "<a href='MyRequestsServlet'>My Requests</a>"
                + "<a href='TrackRequestServlet?requestId="
                + generatedRequestId
                + "'>Track This Request</a>"
                + "<a href='request.html'>Create Another Request</a>"
                + "<a href='index.html'>Back to Home</a>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>"
            );

        } catch (Exception e) {

            e.printStackTrace();

            /* ==============================
               ERROR PAGE
               ============================== */

            response.getWriter().println(
                "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Request Failed | Smart Campus</title>"
                + "<style>"
                + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
                + "body{margin:0;background:#080808;color:white;min-height:100vh;"
                + "display:flex;align-items:center;justify-content:center;}"
                + ".box{width:90%;max-width:600px;background:#111;"
                + "border:1px solid #292929;border-radius:16px;"
                + "padding:45px;text-align:center;}"
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
                + "<h1>Request Submission Failed</h1>"
                + "<p>Something went wrong while submitting your request.</p>"
                + "<p>Please try again.</p>"
                + "<a class='btn' href='request.html'>Try Again</a>"
                + "</div>"
                + "</body>"
                + "</html>"
            );
        }
    }
}