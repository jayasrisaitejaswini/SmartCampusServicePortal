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

/**
 * Servlet implementation class StudentDashboardServlet
 */
@WebServlet("/StudentDashboardServlet")
public class StudentDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
        String studentEmail = (String) session.getAttribute("studentEmail");
        
        int totalRequests = 0;
        int pendingRequests = 0;
        int inProgressRequests = 0;
        int resolvedRequests = 0;

        String sql = "SELECT status, COUNT(*) AS count FROM requests WHERE student_id = ? GROUP BY status";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String status = rs.getString("status");
                int count = rs.getInt("count");
                totalRequests = totalRequests + count;

                if (status.equals("Pending")) {
                    pendingRequests = count;
                } 
                else if (status.equals("In Progress")) {
                    inProgressRequests = count;
                } 
                else if (status.equals("Resolved")) {
                    resolvedRequests = count;
                }
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.getWriter().println(
                "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Student Dashboard | Smart Campus</title>"
                + "<style>"
                + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
                + "body{margin:0;background:#080808;color:white;min-height:100vh;}"
                + "nav{height:75px;display:flex;align-items:center;justify-content:space-between;padding:0 8%;border-bottom:1px solid #222;background:#080808;}"
                + ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;}"
                + ".logo span{color:#00e5ff;}"
                + "nav a{color:#aaa;text-decoration:none;margin-left:25px;}"
                + "nav a:hover{color:#00e5ff;}"
                + ".container{width:90%;max-width:1000px;margin:50px auto;}"
                + ".tag{display:inline-block;border:1px solid #00e5ff;color:#00e5ff;padding:7px 14px;border-radius:20px;font-size:12px;margin-bottom:15px;}"
                + "h1{font-size:40px;margin:0 0 10px;}"
                + ".welcome{color:#999;margin-bottom:35px;}"
                + ".profile{background:#111;border:1px solid #292929;border-radius:16px;padding:25px;margin-bottom:25px;}"
                + ".profile h2{margin-top:0;}"
                + ".profile p{color:#aaa;margin:8px 0;}"
                + ".cards{display:grid;grid-template-columns:repeat(4,1fr);gap:18px;}"
                + ".card{background:#111;border:1px solid #292929;border-radius:16px;padding:25px;}"
                + ".card-title{color:#888;font-size:13px;margin-bottom:12px;}"
                + ".number{font-size:32px;font-weight:bold;}"
                + ".actions{margin-top:30px;display:flex;gap:15px;flex-wrap:wrap;}"
                + ".btn{display:inline-block;padding:13px 22px;border-radius:8px;text-decoration:none;font-weight:bold;}"
                + ".primary{background:#00e5ff;color:#001014;}"
                + ".secondary{border:1px solid #333;color:white;}"
                + ".btn:hover{opacity:.85;}"
                + "@media(max-width:800px){.cards{grid-template-columns:1fr 1fr;}}"
                + "@media(max-width:500px){.cards{grid-template-columns:1fr;}h1{font-size:32px;}}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<nav>"
                + "<div class='logo'>SMART<span>CAMPUS</span></div>"
                + "<div>"
                + "<a href='request.html'>Report Issue</a>"
                + "<a href='track.html'>Track Request</a>"
                + "<a href='LogoutServlet'>Logout</a>"
                + "</div>"
                + "</nav>"
                + "<div class='container'>"
                + "<div class='tag'>STUDENT PORTAL</div>"
                + "<h1>Student Dashboard</h1>"
                + "<p class='welcome'>Welcome back, " + studentName + ". Manage and track your campus service requests.</p>"
                + "<div class='profile'>"
                + "<h2>" + studentName + "</h2>"
                + "<p>Student ID: " + studentId + "</p>"
                + "<p>Email: " + studentEmail + "</p>"
                + "</div>"
                + "<div class='cards'>"
                + "<div class='card'>"
                + "<div class='card-title'>TOTAL REQUESTS</div>"
                + "<div class='number'>" + totalRequests + "</div>"
                + "</div>"
                + "<div class='card'>"
                + "<div class='card-title'>PENDING</div>"
                + "<div class='number'>" + pendingRequests + "</div>"
                + "</div>"
                + "<div class='card'>"
                + "<div class='card-title'>IN PROGRESS</div>"
                + "<div class='number'>" + inProgressRequests + "</div>"
                + "</div>"
                + "<div class='card'>"
                + "<div class='card-title'>RESOLVED</div>"
                + "<div class='number'>" + resolvedRequests + "</div>"
                + "</div>"
                + "</div>"
                + "<div class='actions'>"

                + "<a class='btn primary' href='request.html'>REPORT AN ISSUE</a>"

                + "<a class='btn secondary' href='track.html'>TRACK REQUEST</a>"

                + "<a class='btn secondary' href='MyRequestsServlet'>MY REQUESTS</a>"

                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>"
        );
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
