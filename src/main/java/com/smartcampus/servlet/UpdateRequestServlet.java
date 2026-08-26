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

@WebServlet("/UpdateRequestServlet")
public class UpdateRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String requestId = request.getParameter("requestId");
        String status = request.getParameter("status");
        String department = request.getParameter("department");
        String resolutionNote = request.getParameter("resolutionNote");
        
        if ("Resolved".equalsIgnoreCase(status)
                && (resolutionNote == null || resolutionNote.trim().isEmpty())) {

        	response.setContentType("text/html;charset=UTF-8");

        	response.getWriter().println("<!DOCTYPE html>");
        	response.getWriter().println("<html><head>");
        	response.getWriter().println("<meta charset='UTF-8'>");
        	response.getWriter().println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        	response.getWriter().println("<title>Resolution Required | Smart Campus</title>");

        	response.getWriter().println("<style>");
        	response.getWriter().println(
        	        "body{margin:0;background:#080808;color:white;font-family:Arial,sans-serif;"
        	        + "display:flex;align-items:center;justify-content:center;min-height:100vh;}");
        	response.getWriter().println(
        	        ".card{width:90%;max-width:500px;background:#111;border:1px solid #292929;"
        	        + "border-radius:16px;padding:40px;text-align:center;}");
        	response.getWriter().println(
        	        "h1{color:#00e5ff;margin-bottom:15px;}");
        	response.getWriter().println(
        	        "p{color:#aaa;line-height:1.6;}");
        	response.getWriter().println(
        	        "a{display:inline-block;margin-top:20px;padding:12px 22px;background:#00e5ff;"
        	        + "color:#000;text-decoration:none;border-radius:8px;font-weight:bold;}");
        	response.getWriter().println("</style></head><body>");

        	response.getWriter().println("<div class='card'>");
        	response.getWriter().println("<h1>Resolution Note Required</h1>");
        	response.getWriter().println(
        	        "<p>Please enter a resolution note before marking this request as Resolved.</p>");
        	response.getWriter().println(
        	        "<a href='AdminDashboardServlet'>Back to Admin Dashboard</a>");
        	response.getWriter().println("</div>");

        	response.getWriter().println("</body></html>");

        	return;
        }
        String sql =
                "UPDATE requests SET status = ?, department = ?, resolution_note = ? "
                + "WHERE request_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setString(2, department);
            ps.setString(3, resolutionNote);
            ps.setInt(4, Integer.parseInt(requestId));

            int rowsUpdated = ps.executeUpdate();

            ps.close();
            con.close();

            if (rowsUpdated > 0) {

                response.sendRedirect("AdminDashboardServlet");

            } else {

                response.getWriter().println(
                        "<h2>Request not found.</h2>");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<h2>Unable to update request.</h2>");
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("AdminDashboardServlet");
    }
}