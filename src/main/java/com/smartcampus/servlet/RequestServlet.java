package com.smartcampus.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartcampus.dao.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/submitRequest")
public class RequestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String studentName = request.getParameter("studentName");
        String studentId = request.getParameter("studentId");
        String email = request.getParameter("email");
        String category = request.getParameter("category");
        String location = request.getParameter("location");
        String priority = request.getParameter("priority");
        String description = request.getParameter("description");
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

        String sql = "INSERT INTO requests " +
                     "(student_id, category, location, description, priority, status, department) " +
                     "VALUES (?, ?, ?, ?, ?, 'Pending', ?)";

        try {
        	Connection con = DBConnection.getConnection();

        	// CHECK WHETHER STUDENT EXISTS
        	// CHECK WHETHER STUDENT EXISTS

        	PreparedStatement checkStudent = con.prepareStatement(
        	        "SELECT student_id FROM students WHERE student_id = ? OR roll_number = ?"
        	);

        	checkStudent.setString(1, studentId);
        	checkStudent.setString(2, studentId);

        	ResultSet studentResult = checkStudent.executeQuery();

        	int actualStudentId = 0;

        	if (studentResult.next()) {
        	    actualStudentId = studentResult.getInt("student_id");
        	} else {
        	    response.setContentType("text/html;charset=UTF-8");

        	    response.getWriter().println(
        	            "<h2 style='color:red;text-align:center;margin-top:100px;'>"
        	            + "Student ID / Roll Number " + studentId + " is not registered."
        	            + "</h2>"
        	    );

        	    response.getWriter().println(
        	            "<p style='text-align:center;'>"
        	            + "Please enter a valid Student ID or Roll Number."
        	            + "</p>"
        	    );

        	    studentResult.close();
        	    checkStudent.close();
        	    con.close();

        	    return;
        	}
        	

        	studentResult.close();
        	checkStudent.close();

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

            ResultSet generatedKeys = ps.getGeneratedKeys();

            int generatedRequestId = 0;

            if (generatedKeys.next()) {
                generatedRequestId = generatedKeys.getInt(1);
            }

            generatedKeys.close();

            ps.close();

            con.close();

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Request Submitted - SmartCampus</title>"
                + "<style>"
                + "body{"
                + "background:#080808;"
                + "color:#ffffff;"
                + "font-family:Arial,sans-serif;"
                + "text-align:center;"
                + "padding-top:100px;"
                + "}"
                + ".box{"
                + "max-width:650px;"
                + "margin:auto;"
                + "padding:40px;"
                + "background:#111;"
                + "border:1px solid #333;"
                + "border-radius:12px;"
                + "}"
                + "h1{margin-bottom:15px;}"
                + "p{color:#aaa;}"
                + ".id{"
                + "font-size:22px;"
                + "font-weight:bold;"
                + "color:#00e5ff;"
                + "}"
                + ".buttons{"
                + "margin-top:25px;"
                + "}"
                + "a{"
                + "display:inline-block;"
                + "margin:8px;"
                + "padding:12px 22px;"
                + "background:#ffffff;"
                + "color:#000000;"
                + "text-decoration:none;"
                + "border-radius:8px;"
                + "font-weight:bold;"
                + "}"
                + "a:hover{"
                + "background:#00e5ff;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='box'>"
                + "<h1>✓ Request Submitted Successfully!</h1>"
                + "<p>Your campus service request has been recorded successfully.</p>"
                + "<p class='id'>Your Request ID: #" + generatedRequestId + "</p>"
                + "<div class='buttons'>"
                + "<a href='StudentDashboardServlet'>Student Dashboard</a>"
                + "<a href='MyRequestsServlet'>My Requests</a>"
                + "<a href='TrackRequestServlet?requestId=" + generatedRequestId + "'>Track This Request</a>"
                + "<a href='request.html'>Create Another Request</a>"
                + "<a href='index.html'>Back to Home</a>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>"
            );
        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType("text/html");

            response.getWriter().println(
                "<h1>Request Submission Failed</h1>"
            );
        }
    }
}