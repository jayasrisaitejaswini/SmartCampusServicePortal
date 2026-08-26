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

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("adminId") == null) {
            response.sendRedirect("admin-login.html");
            return;
        }

        response.setContentType("text/html");

        int total = 0;
        int pending = 0;
        int inProgress = 0;
        int resolved = 0;

        try {

            Connection con = DBConnection.getConnection();

            // TOTAL REQUESTS
            PreparedStatement ps1 =
                    con.prepareStatement("SELECT COUNT(*) FROM requests");

            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                total = rs1.getInt(1);
            }
         // PRIORITY COUNTS

            int urgent = 0;
            int high = 0;
            int medium = 0;
            int low = 0;

            PreparedStatement psPriority = con.prepareStatement(
                "SELECT priority, COUNT(*) FROM requests GROUP BY priority"
            );

            ResultSet rsPriority = psPriority.executeQuery();

            while (rsPriority.next()) {
                String p = rsPriority.getString(1);
                int count = rsPriority.getInt(2);

                if ("Urgent".equals(p)) {
                    urgent = count;
                } else if ("High".equals(p)) {
                    high = count;
                } else if ("Medium".equals(p)) {
                    medium = count;
                } else if ("Low".equals(p)) {
                    low = count;
                }
            }
         // FEEDBACK ANALYTICS

            double averageRating = 0;
            int totalFeedback = 0;
            int fiveStar = 0;
            int fourStar = 0;
            int threeStar = 0;
            int twoStar = 0;
            int oneStar = 0;

            PreparedStatement psFeedback = con.prepareStatement(
                    "SELECT COUNT(*), COALESCE(AVG(rating), 0) FROM feedback"
            );

            ResultSet rsFeedback = psFeedback.executeQuery();

            if (rsFeedback.next()) {
                totalFeedback = rsFeedback.getInt(1);
                averageRating = rsFeedback.getDouble(2);
            }

            PreparedStatement psRating = con.prepareStatement(
                    "SELECT rating, COUNT(*) FROM feedback GROUP BY rating"
            );

            ResultSet rsRating = psRating.executeQuery();

            while (rsRating.next()) {

                int rating = rsRating.getInt(1);
                int count = rsRating.getInt(2);

                if (rating == 5) {
                    fiveStar = count;
                } else if (rating == 4) {
                    fourStar = count;
                } else if (rating == 3) {
                    threeStar = count;
                } else if (rating == 2) {
                    twoStar = count;
                } else if (rating == 1) {
                    oneStar = count;
                }
            }


            // PENDING REQUESTS
            PreparedStatement ps2 =
                    con.prepareStatement(
                            "SELECT COUNT(*) FROM requests WHERE status = 'Pending'");

            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                pending = rs2.getInt(1);
            }


            // IN PROGRESS REQUESTS
            PreparedStatement ps3 =
                    con.prepareStatement(
                            "SELECT COUNT(*) FROM requests WHERE status = 'In Progress'");

            ResultSet rs3 = ps3.executeQuery();

            if (rs3.next()) {
                inProgress = rs3.getInt(1);
            }


            // RESOLVED REQUESTS
            PreparedStatement ps4 =
                    con.prepareStatement(
                            "SELECT COUNT(*) FROM requests WHERE status = 'Resolved'");

            ResultSet rs4 = ps4.executeQuery();

            if (rs4.next()) {
                resolved = rs4.getInt(1);
            }


            // HTML START

            response.getWriter().println("<!DOCTYPE html>");
            response.getWriter().println("<html>");
            response.getWriter().println("<head>");

            response.getWriter().println("<meta charset='UTF-8'>");

            response.getWriter().println(
                    "<meta name='viewport' content='width=device-width, initial-scale=1.0'>");

            response.getWriter().println(
                    "<title>Admin Dashboard | Smart Campus</title>");


            // CSS

            response.getWriter().println("<style>");

            response.getWriter().println(
                    "*{margin:0;padding:0;box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}");

            response.getWriter().println(
                    "body{background:#080808;color:white;min-height:100vh;}");

            response.getWriter().println(
                    "nav{height:75px;display:flex;align-items:center;justify-content:space-between;padding:0 8%;border-bottom:1px solid #222;}");

            response.getWriter().println(
                    ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;}");

            response.getWriter().println(
                    ".logo span{color:#00e5ff;}");

            response.getWriter().println(
                    "nav a{color:#aaa;text-decoration:none;}");

            response.getWriter().println(
                    "nav a:hover{color:#00e5ff;}");

            response.getWriter().println(
                    ".container{width:90%;max-width:1200px;margin:50px auto;}");

            response.getWriter().println(
                    ".tag{display:inline-block;border:1px solid #00e5ff;color:#00e5ff;padding:7px 14px;border-radius:20px;font-size:12px;margin-bottom:15px;}");

            response.getWriter().println(
                    "h1{font-size:40px;margin-bottom:10px;}");

            response.getWriter().println(
                    ".intro{color:#888;margin-bottom:35px;}");

            response.getWriter().println(
                    ".cards{display:grid;grid-template-columns:repeat(4,1fr);gap:20px;margin-bottom:35px;}");

            response.getWriter().println(
                    ".card{background:#111;border:1px solid #292929;border-radius:14px;padding:25px;}");

            response.getWriter().println(
                    ".card h3{color:#999;font-size:13px;margin-bottom:12px;}");

            response.getWriter().println(
                    ".number{font-size:32px;font-weight:bold;color:#00e5ff;}");

            response.getWriter().println(
                    ".table-card{background:#111;border:1px solid #292929;border-radius:14px;padding:25px;overflow-x:auto;}");

            response.getWriter().println(
                    ".table-title{font-size:20px;margin-bottom:20px;}");

            response.getWriter().println(
                    "table{width:100%;border-collapse:collapse;}");

            response.getWriter().println(
                    "th{text-align:left;color:#777;font-size:12px;padding:15px;border-bottom:1px solid #292929;}");

            response.getWriter().println(
                    "td{padding:15px;border-bottom:1px solid #222;font-size:13px;}");

            response.getWriter().println(
                    ".status{padding:6px 12px;border-radius:20px;background:#173b20;color:#63e681;font-size:11px;}");

            response.getWriter().println(
                    "select{background:#080808;color:white;border:1px solid #333;padding:7px;border-radius:6px;margin-right:5px;margin-bottom:5px;}");

            response.getWriter().println(
                    "button{background:#00e5ff;color:#001014;border:none;padding:7px 12px;border-radius:6px;font-weight:bold;cursor:pointer;}");

            response.getWriter().println(
                    "button:hover{opacity:0.85;}");

            response.getWriter().println(
                    "@media(max-width:800px){.cards{grid-template-columns:repeat(2,1fr);}}");

            response.getWriter().println(
                    "@media(max-width:500px){.cards{grid-template-columns:1fr;}h1{font-size:32px;}}");

            response.getWriter().println("</style>");

            response.getWriter().println("</head>");

            response.getWriter().println("<body>");


            // NAVIGATION

            response.getWriter().println("<nav>");

            response.getWriter().println(
                    "<div class='logo'>SMART<span>CAMPUS</span></div>");

            response.getWriter().println(
                    "<a href='index.html'>Logout</a>");

            response.getWriter().println("</nav>");


            // MAIN CONTAINER

            response.getWriter().println("<div class='container'>");

            response.getWriter().println(
                    "<div class='tag'>ADMIN PORTAL</div>");

            response.getWriter().println(
                    "<h1>Admin Dashboard</h1>");

            response.getWriter().println(
                    "<p class='intro'>Monitor and manage campus service requests from one place.</p>");


            // STATISTICS

            response.getWriter().println("<div class='cards'>");

            response.getWriter().println(
                    "<div class='card'><h3>Total Requests</h3><div class='number'>"
                            + total + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Pending</h3><div class='number'>"
                            + pending + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>In Progress</h3><div class='number'>"
                            + inProgress + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Resolved</h3><div class='number'>"
                            + resolved + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Urgent</h3><div class='number'>"
                            + urgent + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>High</h3><div class='number'>"
                            + high + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Medium</h3><div class='number'>"
                            + medium + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Low</h3><div class='number'>"
                            + low + "</div></div>");

            response.getWriter().println("</div>");
         // FEEDBACK ANALYTICS

            response.getWriter().println("<div class='cards'>");

            response.getWriter().println(
                    "<div class='card'><h3>Average Rating</h3><div class='number'>"
                            + String.format("%.1f", averageRating)
                            + " / 5 &#9733;</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Total Feedback</h3><div class='number'>"
                            + totalFeedback + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>5 Star</h3><div class='number'>"
                            + fiveStar + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>4 Star</h3><div class='number'>"
                            + fourStar + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>3 Star</h3><div class='number'>"
                            + threeStar + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>2 Star</h3><div class='number'>"
                            + twoStar + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>1 Star</h3><div class='number'>"
                            + oneStar + "</div></div>");

            response.getWriter().println("</div>");
         // CATEGORY ANALYTICS

            int wifi = 0;
            int classroom = 0;
            int electrical = 0;
            int hostel = 0;
            int library = 0;
            int transport = 0;
            int canteen = 0;
            int other = 0;

            PreparedStatement psCategory = con.prepareStatement(
                    "SELECT category, COUNT(*) FROM requests GROUP BY category"
            );

            ResultSet rsCategory = psCategory.executeQuery();

            while (rsCategory.next()) {

                String categoryName = rsCategory.getString(1);
                int count = rsCategory.getInt(2);

                if ("Wi-Fi / Network".equals(categoryName)) {
                    wifi = count;
                } else if ("Classroom".equals(categoryName)) {
                    classroom = count;
                } else if ("Electrical".equals(categoryName)) {
                    electrical = count;
                } else if ("Hostel".equals(categoryName)) {
                    hostel = count;
                } else if ("Library".equals(categoryName)) {
                    library = count;
                } else if ("Transport".equals(categoryName)) {
                    transport = count;
                } else if ("Canteen".equals(categoryName)) {
                    canteen = count;
                } else {
                    other = count;
                }
            }
         // CATEGORY ANALYTICS DISPLAY

            response.getWriter().println(
                    "<div class='table-card'>");

            response.getWriter().println(
                    "<div class='table-title'>Requests by Category</div>");

            response.getWriter().println(
                    "<div style='display:grid;grid-template-columns:repeat(auto-fit,minmax(180px,1fr));gap:15px;'>");

            response.getWriter().println(
                    "<div class='card'><h3>Wi-Fi / Network</h3><div class='number'>"
                    + wifi + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Classroom</h3><div class='number'>"
                    + classroom + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Electrical</h3><div class='number'>"
                    + electrical + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Hostel</h3><div class='number'>"
                    + hostel + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Library</h3><div class='number'>"
                    + library + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Transport</h3><div class='number'>"
                    + transport + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Canteen</h3><div class='number'>"
                    + canteen + "</div></div>");

            response.getWriter().println(
                    "<div class='card'><h3>Other</h3><div class='number'>"
                    + other + "</div></div>");

            response.getWriter().println("</div>");
            response.getWriter().println("</div>");
         

         // PRIORITY ANALYTICS

            int totalPriority = urgent + high + medium + low;

            response.getWriter().println(
                    "<div class='table-card'>");

            response.getWriter().println(
                    "<div class='table-title'>Priority Distribution</div>");

            response.getWriter().println(
                    "<div style='margin-top:20px;'>");

            response.getWriter().println(
                    "<div style='margin-bottom:15px;'>"
                    + "<strong>Urgent</strong>"
                    + "<div style='background:#222;border-radius:10px;height:12px;margin-top:6px;'>"
                    + "<div style='background:#ff4d4d;width:"
                    + (totalPriority == 0 ? 0 : (urgent * 100 / totalPriority))
                    + "%;height:12px;border-radius:10px;'></div>"
                    + "</div>"
                    + "<small>" + urgent + " requests</small>"
                    + "</div>");

            response.getWriter().println(
                    "<div style='margin-bottom:15px;'>"
                    + "<strong>High</strong>"
                    + "<div style='background:#222;border-radius:10px;height:12px;margin-top:6px;'>"
                    + "<div style='background:#ff9800;width:"
                    + (totalPriority == 0 ? 0 : (high * 100 / totalPriority))
                    + "%;height:12px;border-radius:10px;'></div>"
                    + "</div>"
                    + "<small>" + high + " requests</small>"
                    + "</div>");

            response.getWriter().println(
                    "<div style='margin-bottom:15px;'>"
                    + "<strong>Medium</strong>"
                    + "<div style='background:#222;border-radius:10px;height:12px;margin-top:6px;'>"
                    + "<div style='background:#ffc107;width:"
                    + (totalPriority == 0 ? 0 : (medium * 100 / totalPriority))
                    + "%;height:12px;border-radius:10px;'></div>"
                    + "</div>"
                    + "<small>" + medium + " requests</small>"
                    + "</div>");

            response.getWriter().println(
                    "<div style='margin-bottom:15px;'>"
                    + "<strong>Low</strong>"
                    + "<div style='background:#222;border-radius:10px;height:12px;margin-top:6px;'>"
                    + "<div style='background:#4caf50;width:"
                    + (totalPriority == 0 ? 0 : (low * 100 / totalPriority))
                    + "%;height:12px;border-radius:10px;'></div>"
                    + "</div>"
                    + "<small>" + low + " requests</small>"
                    + "</div>");

            response.getWriter().println("</div>");
            response.getWriter().println("</div>");
            // REQUEST TABLE

            response.getWriter().println(
                    "<div class='table-card'>");

            response.getWriter().println(
                    "<div class='table-title'>Service Requests</div>");
            response.getWriter().println(
                    "<form method='get' action='AdminDashboardServlet' class='filters'>"
                    + "<input type='text' name='search' "
                    + "placeholder='Search Request ID or Student ID'>"
                    + "<select name='status'>"
                    + "<option value=''>All Status</option>"
                    + "<option value='Pending'>Pending</option>"
                    + "<option value='In Progress'>In Progress</option>"
                    + "<option value='Resolved'>Resolved</option>"
                    + "</select>"
                    + "<select name='priority'>"
                    + "<option value=''>All Priority</option>"
                    + "<option value='Urgent'>Urgent</option>"
                    + "<option value='High'>High</option>"
                    + "<option value='Medium'>Medium</option>"
                    + "<option value='Low'>Low</option>"
                    + "</select>"
                    + "<select name='category'>"
                    + "<option value=''>All Categories</option>"
                    + "<option value='Wi-Fi / Network'>Wi-Fi / Network</option>"
                    + "<option value='Classroom'>Classroom</option>"
                    + "<option value='Electrical'>Electrical</option>"
                    + "<option value='Hostel'>Hostel</option>"
                    + "<option value='Library'>Library</option>"
                    + "<option value='Transport'>Transport</option>"
                    + "<option value='Canteen'>Canteen</option>"
                    + "<option value='Other'>Other</option>"
                    + "</select>"
                    + "<select name='department'>"
                    + "<option value=''>All Departments</option>"
                    + "<option value='IT Support'>IT Support</option>"
                    + "<option value='Electrical'>Electrical</option>"
                    + "<option value='Hostel'>Hostel</option>"
                    + "<option value='Library'>Library</option>"
                    + "<option value='Transport'>Transport</option>"
                    + "<option value='Maintenance'>Maintenance</option>"
                    + "<option value='Canteen'>Canteen</option>"
                    + "</select>"
                    + "<button type='submit'>Search</button>"
                    + "</form>"
            );
            response.getWriter().println("<table>");

            response.getWriter().println("<tr>");

            response.getWriter().println("<th>REQUEST ID</th>");
            response.getWriter().println("<th>STUDENT ID</th>");
            response.getWriter().println("<th>CATEGORY</th>");
            response.getWriter().println("<th>LOCATION</th>");
            response.getWriter().println("<th>PRIORITY</th>");
            response.getWriter().println("<th>STATUS</th>");
            response.getWriter().println("<th>DEPARTMENT</th>");
            response.getWriter().println("<th>RESOLUTION NOTE</th>");
            response.getWriter().println("<th>ACTION</th>");

            response.getWriter().println("</tr>");


            // GET ALL REQUESTS

            String search = request.getParameter("search");
            String statusFilter = request.getParameter("status");
            String priorityFilter = request.getParameter("priority");
            String categoryFilter = request.getParameter("category");
            String departmentFilter = request.getParameter("department");

            StringBuilder sql = new StringBuilder(
                    "SELECT r.request_id, r.student_id, s.name, r.category, r.location, "
                    + "r.priority, r.status, r.department, r.resolution_note "
                    + "FROM requests r "
                    + "JOIN students s ON r.student_id = s.student_id "
                    + "WHERE 1=1");
            boolean exactRequestSearch = false;
            String cleanSearch = "";

            if (search != null && !search.trim().isEmpty()) {

                cleanSearch = search.trim().replace("#", "");

                if (search.trim().startsWith("#")) {
                    sql.append(" AND request_id = ?");
                    exactRequestSearch = true;
                } else {
                	sql.append(" AND (CAST(r.request_id AS CHAR) LIKE ? "
                	        + "OR CAST(r.student_id AS CHAR) LIKE ? "
                	        + "OR s.name LIKE ?)");
                  }
            }

            if (statusFilter != null && !statusFilter.isEmpty()) {
                sql.append(" AND status = ?");
            }

            if (priorityFilter != null && !priorityFilter.isEmpty()) {
                sql.append(" AND priority = ?");
            }

            if (categoryFilter != null && !categoryFilter.isEmpty()) {
                sql.append(" AND category = ?");
            }

            if (departmentFilter != null && !departmentFilter.isEmpty()) {
                sql.append(" AND department = ?");
            }

            sql.append(" ORDER BY CASE priority " +
                    "WHEN 'Urgent' THEN 1 " +
                    "WHEN 'High' THEN 2 " +
                    "WHEN 'Medium' THEN 3 " +
                    "WHEN 'Low' THEN 4 " +
                    "ELSE 5 END, created_at DESC");

            PreparedStatement ps5 = con.prepareStatement(sql.toString());

            int parameterIndex = 1;

            if (search != null && !search.trim().isEmpty()) {

                if (exactRequestSearch) {
                    ps5.setInt(parameterIndex++, Integer.parseInt(cleanSearch));
                } else {

                    ps5.setString(parameterIndex++, "%" + cleanSearch + "%");

                    ps5.setString(parameterIndex++, "%" + cleanSearch + "%");

                    ps5.setString(parameterIndex++, "%" + cleanSearch + "%");

                }
            }

            if (statusFilter != null && !statusFilter.isEmpty()) {
                ps5.setString(parameterIndex++, statusFilter);
            }

            if (priorityFilter != null && !priorityFilter.isEmpty()) {
                ps5.setString(parameterIndex++, priorityFilter);
            }

            if (categoryFilter != null && !categoryFilter.isEmpty()) {
                ps5.setString(parameterIndex++, categoryFilter);
            }

            if (departmentFilter != null && !departmentFilter.isEmpty()) {
                ps5.setString(parameterIndex++, departmentFilter);
            }

            ResultSet rs5 = ps5.executeQuery();

            while (rs5.next()) {

                int requestId = rs5.getInt("request_id");

                response.getWriter().println("<tr>");


                response.getWriter().println(
                        "<td>#"
                                + requestId
                                + "</td>");


                response.getWriter().println(
                        "<td>"
                                + rs5.getInt("student_id")
                                + "</td>");


                response.getWriter().println(
                        "<td>"
                                + rs5.getString("category")
                                + "</td>");


                response.getWriter().println(
                        "<td>"
                                + rs5.getString("location")
                                + "</td>");


                response.getWriter().println(
                        "<td>"
                                + rs5.getString("priority")
                                + "</td>");


                response.getWriter().println(
                        "<td><span class='status'>"
                                + rs5.getString("status")
                                + "</span></td>");


                response.getWriter().println(
                        "<td>"
                                + rs5.getString("department")
                                + "</td>");
                response.getWriter().println(
                        "<td>" + rs5.getString("resolution_note") + "</td>");

                // ACTION

                response.getWriter().println("<td>");

                response.getWriter().println(
                        "<form action='UpdateRequestServlet' method='post'>");


                // REQUEST ID

                response.getWriter().println(
                        "<input type='hidden' name='requestId' value='"
                                + requestId
                                + "'>");


                // STATUS DROPDOWN

                String currentStatus = rs5.getString("status");

                response.getWriter().println(
                        "<select name='status'>");

                response.getWriter().println(
                        "<option value='Pending' "
                        + (currentStatus.equals("Pending") ? "selected" : "")
                        + ">Pending</option>");

                response.getWriter().println(
                        "<option value='In Progress' "
                        + (currentStatus.equals("In Progress") ? "selected" : "")
                        + ">In Progress</option>");

                response.getWriter().println(
                        "<option value='Resolved' "
                        + (currentStatus.equals("Resolved") ? "selected" : "")
                        + ">Resolved</option>");

                response.getWriter().println("</select>");

                // DEPARTMENT DROPDOWN

                String currentDepartment = rs5.getString("department");

                response.getWriter().println(
                        "<select name='department'>");

                response.getWriter().println(
                        "<option value='IT Support' "
                        + (currentDepartment.equals("IT Support") ? "selected" : "")
                        + ">IT Support</option>");

                response.getWriter().println(
                        "<option value='Electrical' "
                        + (currentDepartment.equals("Electrical") ? "selected" : "")
                        + ">Electrical</option>");

                response.getWriter().println(
                        "<option value='Hostel' "
                        + (currentDepartment.equals("Hostel") ? "selected" : "")
                        + ">Hostel</option>");

                response.getWriter().println(
                        "<option value='Library' "
                        + (currentDepartment.equals("Library") ? "selected" : "")
                        + ">Library</option>");

                response.getWriter().println(
                        "<option value='Transport' "
                        + (currentDepartment.equals("Transport") ? "selected" : "")
                        + ">Transport</option>");

                response.getWriter().println(
                        "<option value='Maintenance' "
                        + (currentDepartment.equals("Maintenance") ? "selected" : "")
                        + ">Maintenance</option>");

                response.getWriter().println(
                        "<option value='Canteen' "
                        + (currentDepartment.equals("Canteen") ? "selected" : "")
                        + ">Canteen</option>");

                response.getWriter().println("</select>");

                // UPDATE BUTTON
                response.getWriter().println(
                        "<input type='text' name='resolutionNote' placeholder='Resolution note'>");
                response.getWriter().println(
                        "<button type='submit'>Update</button>");


                response.getWriter().println("</form>");

                response.getWriter().println("</td>");


                response.getWriter().println("</tr>");
            }


            response.getWriter().println("</table>");

            response.getWriter().println("</div>");

            response.getWriter().println("</div>");

            response.getWriter().println("</body>");

            response.getWriter().println("</html>");


            // CLOSE DATABASE RESOURCES

            rs1.close();
            rs2.close();
            rs3.close();
            rs4.close();
            rs5.close();

            ps1.close();
            ps2.close();
            ps3.close();
            ps4.close();
            ps5.close();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<h2>Unable to load Admin Dashboard.</h2>");
        }
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}