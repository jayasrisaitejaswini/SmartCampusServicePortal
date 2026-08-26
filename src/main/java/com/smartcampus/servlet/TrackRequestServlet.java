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

import com.smartcampus.dao.DBConnection;

@WebServlet("/TrackRequestServlet")
public class TrackRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String requestId = request.getParameter("requestId");

        if (requestId == null || requestId.trim().isEmpty()) {
            response.getWriter().println("<h2>Please enter a Request ID.</h2>");
            return;
        }

        String sql = "SELECT request_id, student_id, category, location, description, "
                   + "priority, status, department, created_at "
                   + "FROM requests WHERE request_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(requestId));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	int studentId = rs.getInt("student_id");
                String category = rs.getString("category");
                String location = rs.getString("location");
                String description = rs.getString("description");
                String priority = rs.getString("priority");
                String status = rs.getString("status");
                String department = rs.getString("department");
                String createdAt = String.valueOf(rs.getTimestamp("created_at"));

                response.getWriter().println("<!DOCTYPE html>");
                response.getWriter().println("<html>");
                response.getWriter().println("<head>");
                response.getWriter().println("<meta charset='UTF-8'>");
                response.getWriter().println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                response.getWriter().println("<title>Request Details | Smart Campus</title>");

                response.getWriter().println("<style>");
                response.getWriter().println("*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}");
                response.getWriter().println("body{margin:0;background:#080808;color:white;}");
                response.getWriter().println("nav{height:75px;display:flex;align-items:center;justify-content:space-between;padding:0 8%;border-bottom:1px solid #222;}");
                response.getWriter().println(".logo{font-size:22px;font-weight:bold;letter-spacing:1px;}");
                response.getWriter().println(".logo span{color:#00e5ff;}");
                response.getWriter().println("nav a{color:#aaa;text-decoration:none;}");
                response.getWriter().println("nav a:hover{color:#00e5ff;}");
                response.getWriter().println(".container{width:90%;max-width:850px;margin:60px auto;}");
                response.getWriter().println(".tag{display:inline-block;border:1px solid #00e5ff;color:#00e5ff;padding:7px 14px;border-radius:20px;font-size:12px;margin-bottom:15px;}");
                response.getWriter().println("h1{font-size:40px;margin-bottom:10px;}");
                response.getWriter().println(".intro{color:#999;margin-bottom:30px;}");
                response.getWriter().println(".card{background:#111;border:1px solid #292929;border-radius:16px;padding:30px;}");
                response.getWriter().println(".header{display:flex;justify-content:space-between;align-items:center;margin-bottom:25px;}");
                response.getWriter().println(".request-id{color:#888;font-size:13px;}");
                response.getWriter().println(".status{padding:8px 15px;border-radius:20px;background:#173b20;color:#63e681;font-size:12px;font-weight:bold;}");
                response.getWriter().println(".grid{display:grid;grid-template-columns:1fr 1fr;gap:18px;}");
                response.getWriter().println(".item{background:#080808;border:1px solid #222;border-radius:10px;padding:16px;}");
                response.getWriter().println(".label{color:#777;font-size:12px;margin-bottom:7px;}");
                response.getWriter().println(".value{color:#eee;font-size:14px;}");
                response.getWriter().println(".description{margin-top:18px;background:#080808;border:1px solid #222;border-radius:10px;padding:18px;}");
                response.getWriter().println(".timeline{margin-top:30px;background:#111;border:1px solid #292929;border-radius:16px;padding:25px;}");
                response.getWriter().println(".timeline h2{margin-top:0;font-size:20px;}");
                response.getWriter().println(".step{padding:12px 0;border-bottom:1px solid #222;color:#777;}");
                response.getWriter().println(".step:last-child{border-bottom:none;}");
                response.getWriter().println(".step.done{color:#63e681;}");
                response.getWriter().println(".step.current{color:#00e5ff;font-weight:bold;}");
                response.getWriter().println(".back{display:inline-block;margin-top:25px;color:#00e5ff;text-decoration:none;}");
                response.getWriter().println("@media(max-width:600px){.grid{grid-template-columns:1fr;}h1{font-size:32px;}}");
                response.getWriter().println("</style>");

                response.getWriter().println("</head>");
                response.getWriter().println("<body>");

                response.getWriter().println("<nav>");
                response.getWriter().println("<div class='logo'>SMART<span>CAMPUS</span></div>");
                response.getWriter().println("<a href='track.html'>← Track Another Request</a>");
                response.getWriter().println("</nav>");

                response.getWriter().println("<div class='container'>");

                response.getWriter().println("<div class='tag'>REQUEST DETAILS</div>");
                response.getWriter().println("<h1>Request #" + requestId + "</h1>");
                response.getWriter().println("<p class='intro'>Live information retrieved from the Smart Campus service database.</p>");

                response.getWriter().println("<div class='card'>");

                response.getWriter().println("<div class='header'>");
                response.getWriter().println("<div>");
                response.getWriter().println("<div class='request-id'>REQUEST ID</div>");
                response.getWriter().println("<strong>#" + requestId + "</strong>");
                response.getWriter().println("</div>");
                response.getWriter().println("<div class='status'>" + status.toUpperCase() + "</div>");
                response.getWriter().println("</div>");

                response.getWriter().println("<div class='grid'>");

                response.getWriter().println("<div class='item'>");
                response.getWriter().println("<div class='label'>CATEGORY</div>");
                response.getWriter().println("<div class='value'>" + category + "</div>");
                response.getWriter().println("</div>");

                response.getWriter().println("<div class='item'>");
                response.getWriter().println("<div class='label'>LOCATION</div>");
                response.getWriter().println("<div class='value'>" + location + "</div>");
                response.getWriter().println("</div>");

                response.getWriter().println("<div class='item'>");
                response.getWriter().println("<div class='label'>PRIORITY</div>");
                response.getWriter().println("<div class='value'>" + priority + "</div>");
                response.getWriter().println("</div>");

                response.getWriter().println("<div class='item'>");
                response.getWriter().println("<div class='label'>DEPARTMENT</div>");
                response.getWriter().println("<div class='value'>" + department + "</div>");
                response.getWriter().println("</div>");

                response.getWriter().println("</div>");

                response.getWriter().println("<div class='description'>");
                response.getWriter().println("<div class='label'>DESCRIPTION</div>");
                response.getWriter().println("<div class='value'>" + description + "</div>");
                response.getWriter().println("</div>");

                response.getWriter().println("<div class='description'>");
                response.getWriter().println("<div class='label'>SUBMITTED AT</div>");
                response.getWriter().println("<div class='value'>" + createdAt + "</div>");
                response.getWriter().println("</div>");

                response.getWriter().println("</div>");
                
             // REQUEST TIMELINE

                boolean assigned = department != null && !department.isEmpty();
                boolean workStarted = "In Progress".equalsIgnoreCase(status)
                        || "Resolved".equalsIgnoreCase(status);
                boolean waiting = "In Progress".equalsIgnoreCase(status)
                        || "Resolved".equalsIgnoreCase(status);
                boolean resolvedStatus = "Resolved".equalsIgnoreCase(status);

                response.getWriter().println("<div class='timeline'>");
                response.getWriter().println("<h2>Request Progress</h2>");

                response.getWriter().println(
                        "<div class='step done'>&#10003; Request Submitted</div>");

                if (assigned) {
                    response.getWriter().println(
                            "<div class='step done'>&#10003; Assigned to " + department + "</div>");
                } else {
                    response.getWriter().println(
                            "<div class='step'>&#9675; Assigned to Department</div>");
                }

                if (workStarted) {
                    response.getWriter().println(
                    		"<div class='step done'>&#10003; Work Started</div>");
                } else {
                    response.getWriter().println(
                "<div class='step current'>&#9679; Work Started</div>");
                }

                if (waiting) {
                    response.getWriter().println(
                            "<div class='step current'>&#9679; Waiting for Resolution</div>");
                } else {
                    response.getWriter().println(
                            "<div class='step'>&#9675; Waiting for Resolution</div>");
                }

                if (resolvedStatus) {
                    response.getWriter().println(
                            "<div class='step done'>&#10003; Resolved</div>");
                } else {
                    response.getWriter().println(
                            "<div class='step'>&#9675; Resolved</div>");
                }

                response.getWriter().println("</div>");
             // STUDENT FEEDBACK

                if (resolvedStatus) {

                    response.getWriter().println("<div class='feedback-card'>");

                    response.getWriter().println("<h2>Rate Your Service</h2>");

                    response.getWriter().println(
                            "<p>How was your experience with this request?</p>");

                    response.getWriter().println(
                            "<form method='post' action='FeedbackServlet'>");

                    response.getWriter().println(
                            "<input type='hidden' name='requestId' value='" + requestId + "'>");

                    response.getWriter().println(
                            "<input type='hidden' name='studentId' value='" + studentId + "'>");

                    response.getWriter().println(
                            "<select name='rating' required>");

                    response.getWriter().println(
                            "<option value=''>Select Rating</option>");

                    response.getWriter().println(
                            "<option value='5'>5 - Excellent</option>");

                    response.getWriter().println(
                            "<option value='4'>4 - Good</option>");

                    response.getWriter().println(
                            "<option value='3'>3 - Average</option>");

                    response.getWriter().println(
                            "<option value='2'>2 - Poor</option>");

                    response.getWriter().println(
                            "<option value='1'>1 - Very Poor</option>");
                    response.getWriter().println("</select>");

                    response.getWriter().println(
                            "<textarea name='comment' placeholder='Tell us about your experience'></textarea>");

                    response.getWriter().println(
                            "<button type='submit'>Submit Feedback</button>");

                    response.getWriter().println("</form>");

                    response.getWriter().println("</div>");
                }
                
                
                
                response.getWriter().println("<a class='back' href='track.html'>← Search another request</a>");

                response.getWriter().println("</div>");
                response.getWriter().println("</body>");
                response.getWriter().println("</html>");

            } else {

                response.setContentType("text/html;charset=UTF-8");

                response.getWriter().println(
                    "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<title>Request Not Found</title>"
                    + "<style>"
                    + "body {"
                    + "background:#080808;"
                    + "color:#ffffff;"
                    + "font-family:Arial,sans-serif;"
                    + "text-align:center;"
                    + "padding-top:120px;"
                    + "}"
                    + ".box {"
                    + "max-width:600px;"
                    + "margin:auto;"
                    + "padding:40px;"
                    + "border:1px solid #333;"
                    + "border-radius:12px;"
                    + "background:#111;"
                    + "}"
                    + "h2 { margin-bottom:15px; }"
                    + "p { color:#aaa; }"
                    + "a {"
                    + "display:inline-block;"
                    + "margin-top:20px;"
                    + "padding:12px 22px;"
                    + "background:#ffffff;"
                    + "color:#000000;"
                    + "text-decoration:none;"
                    + "border-radius:8px;"
                    + "}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='box'>"
                    + "<h2>Request Not Found</h2>"
                    + "<p>No request found with Request ID: "
                    + requestId + "</p>"
                    + "<a href='track.html'>Search Another Request</a>"
                    + "</div>"
                    + "</body>"
                    + "</html>"
                );

            }

            rs.close();
            ps.close();
            con.close();

        } catch (NumberFormatException e) {

            response.getWriter().println("<h2>Request ID must be a number.</h2>");

        } catch (Exception e) {

            e.printStackTrace();
            response.getWriter().println("<h2>Something went wrong while tracking the request.</h2>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}