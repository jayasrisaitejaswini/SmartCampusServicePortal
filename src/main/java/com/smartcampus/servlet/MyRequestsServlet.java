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

@WebServlet("/MyRequestsServlet")
public class MyRequestsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect("login.html");
            return;
        }

        int studentId = (Integer) session.getAttribute("studentId");
        String studentName = (String) session.getAttribute("studentName");

        String sql = "SELECT request_id, category, location, priority, status, department, created_at "
                   + "FROM requests WHERE student_id = ? ORDER BY created_at DESC";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            response.getWriter().println("<!DOCTYPE html>");
            response.getWriter().println("<html>");
            response.getWriter().println("<head>");
            response.getWriter().println("<meta charset='UTF-8'>");
            response.getWriter().println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            response.getWriter().println("<title>My Requests | Smart Campus</title>");

            response.getWriter().println("<style>");
            response.getWriter().println("*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}");
            response.getWriter().println("body{margin:0;background:#080808;color:white;}");
            response.getWriter().println("nav{height:75px;display:flex;align-items:center;justify-content:space-between;padding:0 8%;border-bottom:1px solid #222;}");
            response.getWriter().println(".logo{font-size:22px;font-weight:bold;letter-spacing:1px;}");
            response.getWriter().println(".logo span{color:#00e5ff;}");
            response.getWriter().println("nav a{color:#aaa;text-decoration:none;}");
            response.getWriter().println("nav a:hover{color:#00e5ff;}");
            response.getWriter().println(".container{width:90%;max-width:1100px;margin:55px auto;}");
            response.getWriter().println(".tag{display:inline-block;border:1px solid #00e5ff;color:#00e5ff;padding:7px 14px;border-radius:20px;font-size:12px;margin-bottom:15px;}");
            response.getWriter().println("h1{font-size:40px;margin-bottom:8px;}");
            response.getWriter().println(".intro{color:#999;margin-bottom:30px;}");
            response.getWriter().println(".table-card{background:#111;border:1px solid #292929;border-radius:16px;padding:20px;overflow-x:auto;}");
            response.getWriter().println("table{width:100%;border-collapse:collapse;}");
            response.getWriter().println("th{text-align:left;color:#777;font-size:12px;padding:15px;border-bottom:1px solid #292929;}");
            response.getWriter().println("td{padding:16px 15px;border-bottom:1px solid #222;font-size:14px;}");
            response.getWriter().println("tr:last-child td{border-bottom:none;}");
            response.getWriter().println(".status{padding:6px 12px;border-radius:20px;font-size:11px;font-weight:bold;background:#173b20;color:#63e681;}");
            response.getWriter().println(".view{color:#00e5ff;text-decoration:none;}");
            response.getWriter().println(".view:hover{text-decoration:underline;}");
            response.getWriter().println(".back{display:inline-block;margin-top:25px;color:#00e5ff;text-decoration:none;}");
            response.getWriter().println("</style>");

            response.getWriter().println("</head>");
            response.getWriter().println("<body>");

            response.getWriter().println("<nav>");
            response.getWriter().println("<div class='logo'>SMART<span>CAMPUS</span></div>");
            response.getWriter().println("<a href='StudentDashboardServlet'>← Dashboard</a>");
            response.getWriter().println("</nav>");

            response.getWriter().println("<div class='container'>");

            response.getWriter().println("<div class='tag'>REQUEST HISTORY</div>");
            response.getWriter().println("<h1>My Requests</h1>");
            response.getWriter().println("<p class='intro'>Welcome, " + studentName
                    + ". Here are all your campus service requests.</p>");

            response.getWriter().println("<div class='table-card'>");

            response.getWriter().println("<table>");
            response.getWriter().println("<tr>");
            response.getWriter().println("<th>REQUEST ID</th>");
            response.getWriter().println("<th>CATEGORY</th>");
            response.getWriter().println("<th>LOCATION</th>");
            response.getWriter().println("<th>PRIORITY</th>");
            response.getWriter().println("<th>STATUS</th>");
            response.getWriter().println("<th>DEPARTMENT</th>");
            response.getWriter().println("<th>ACTION</th>");
            response.getWriter().println("</tr>");

            boolean hasRequests = false;

            while (rs.next()) {

                hasRequests = true;

                int requestId = rs.getInt("request_id");
                String category = rs.getString("category");
                String location = rs.getString("location");
                String priority = rs.getString("priority");
                String status = rs.getString("status");
                String department = rs.getString("department");

                response.getWriter().println("<tr>");

                response.getWriter().println("<td>#" + requestId + "</td>");
                response.getWriter().println("<td>" + category + "</td>");
                response.getWriter().println("<td>" + location + "</td>");
                response.getWriter().println("<td>" + priority + "</td>");
                response.getWriter().println("<td><span class='status'>" + status + "</span></td>");
                response.getWriter().println("<td>" + department + "</td>");

                response.getWriter().println("<td>");
                response.getWriter().println("<a class='view' href='TrackRequestServlet?requestId="
                        + requestId + "'>View</a>");
                response.getWriter().println("</td>");

                response.getWriter().println("</tr>");
            }

            if (!hasRequests) {

                response.getWriter().println("<tr>");
                response.getWriter().println("<td colspan='7'>You have no requests yet.</td>");
                response.getWriter().println("</tr>");
            }

            response.getWriter().println("</table>");
            response.getWriter().println("</div>");

            response.getWriter().println("<a class='back' href='StudentDashboardServlet'>← Back to Dashboard</a>");

            response.getWriter().println("</div>");
            response.getWriter().println("</body>");
            response.getWriter().println("</html>");

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println("<h2>Unable to load your requests.</h2>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}