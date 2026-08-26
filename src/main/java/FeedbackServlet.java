import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.smartcampus.dao.DBConnection;
import java.sql.ResultSet;


@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String requestId = request.getParameter("requestId");

        String rating = request.getParameter("rating");

        String comment = request.getParameter("comment");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("studentId") == null) {
            response.getWriter().println(
                    "<h2>Please login before submitting feedback.</h2>");
            return;
        }

        int studentId = (Integer) session.getAttribute("studentId");
        if (requestId == null
                || rating == null || rating.trim().isEmpty()) {

            response.getWriter().println(
                    "<h2>Please provide a rating.</h2>");
            return;
        }
        String checkSql = "SELECT request_id FROM requests "
                + "WHERE request_id = ? AND student_id = ? "
                + "AND status = 'Resolved'";

        try {
            Connection checkCon = DBConnection.getConnection();

            PreparedStatement checkPs = checkCon.prepareStatement(checkSql);

            checkPs.setInt(1, Integer.parseInt(requestId));
            checkPs.setInt(2, studentId);

            ResultSet checkRs = checkPs.executeQuery();

            if (!checkRs.next()) {

            	response.getWriter().println(
            	        "<!DOCTYPE html>"
            	        + "<html>"
            	        + "<head>"
            	        + "<meta charset='UTF-8'>"
            	        + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            	        + "<title>Access Denied | Smart Campus</title>"
            	        + "<style>"
            	        + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
            	        + "body{margin:0;background:#080808;color:white;min-height:100vh;display:flex;align-items:center;justify-content:center;}"
            	        + ".box{width:90%;max-width:600px;background:#111;border:1px solid #292929;border-radius:16px;padding:45px;text-align:center;}"
            	        + ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;margin-bottom:30px;}"
            	        + ".logo span{color:#00e5ff;}"
            	        + ".icon{font-size:55px;color:#ff6b6b;margin-bottom:15px;}"
            	        + "h1{font-size:28px;margin-bottom:12px;}"
            	        + "p{color:#999;line-height:1.6;}"
            	        + ".btn{display:inline-block;margin-top:20px;padding:12px 22px;border-radius:8px;text-decoration:none;font-weight:bold;background:#00e5ff;color:#001014;}"
            	        + ".btn:hover{opacity:0.85;}"
            	        + "</style>"
            	        + "</head>"
            	        + "<body>"
            	        + "<div class='box'>"
            	        + "<div class='logo'>SMART<span>CAMPUS</span></div>"
            	        + "<div class='icon'>!</div>"
            	        + "<h1>Access Denied</h1>"
            	        + "<p>You are not authorized to give feedback for this request.</p>"
            	        + "<p>Please submit feedback only for your own resolved requests.</p>"
            	        + "<a class='btn' href='track.html'>Track My Request</a>"
            	        + "</div>"
            	        + "</body>"
            	        + "</html>"
            	);

                checkRs.close();
                checkPs.close();
                checkCon.close();

                return;
            }

            checkRs.close();
            checkPs.close();
            checkCon.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<h2>Unable to verify the request.</h2>");

            return;
        }
        String duplicateSql =
                "SELECT feedback_id FROM feedback WHERE request_id = ? AND student_id = ?";

        try {

            Connection duplicateCon = DBConnection.getConnection();

            PreparedStatement duplicatePs =
                    duplicateCon.prepareStatement(duplicateSql);

            duplicatePs.setInt(1, Integer.parseInt(requestId));
            duplicatePs.setInt(2, studentId);

            ResultSet duplicateRs = duplicatePs.executeQuery();

            if (duplicateRs.next()) {

            	response.getWriter().println(
            	        "<!DOCTYPE html>"
            	        + "<html>"
            	        + "<head>"
            	        + "<meta charset='UTF-8'>"
            	        + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            	        + "<title>Feedback Already Submitted | Smart Campus</title>"
            	        + "<style>"
            	        + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
            	        + "body{margin:0;background:#080808;color:white;min-height:100vh;display:flex;align-items:center;justify-content:center;}"
            	        + ".box{width:90%;max-width:600px;background:#111;border:1px solid #292929;border-radius:16px;padding:45px;text-align:center;}"
            	        + ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;margin-bottom:30px;}"
            	        + ".logo span{color:#00e5ff;}"
            	        + ".icon{font-size:55px;color:#00e5ff;margin-bottom:15px;}"
            	        + "h1{font-size:28px;margin-bottom:12px;}"
            	        + "p{color:#999;line-height:1.6;}"
            	        + ".btn{display:inline-block;margin-top:20px;padding:12px 22px;border-radius:8px;text-decoration:none;font-weight:bold;background:#00e5ff;color:#001014;}"
            	        + ".btn:hover{opacity:.85;}"
            	        + "</style>"
            	        + "</head>"
            	        + "<body>"
            	        + "<div class='box'>"
            	        + "<div class='logo'>SMART<span>CAMPUS</span></div>"
            	        + "<div class='icon'>✓</div>"
            	        + "<h1>Feedback Already Submitted</h1>"
            	        + "<p>You have already submitted feedback for this request.</p>"
            	        + "<p>Each request can receive only one feedback response.</p>"
            	        + "<a class='btn' href='track.html'>Track Another Request</a>"
            	        + "</div>"
            	        + "</body>"
            	        + "</html>"
            	);

                duplicateRs.close();
                duplicatePs.close();
                duplicateCon.close();

                return;
            }

            duplicateRs.close();
            duplicatePs.close();
            duplicateCon.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<h2>Unable to check existing feedback.</h2>"
            );

            return;
        }

        String sql = "INSERT INTO feedback "
                + "(request_id, student_id, rating, comment) "
                + "VALUES (?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(requestId));
            ps.setInt(2, studentId);
            ps.setInt(3, Integer.parseInt(rating));
            ps.setString(4, comment);

            ps.executeUpdate();

            response.getWriter().println(
                    "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset='UTF-8'>"
                    + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                    + "<title>Feedback Submitted | Smart Campus</title>"
                    + "<style>"
                    + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
                    + "body{margin:0;background:#080808;color:white;min-height:100vh;display:flex;align-items:center;justify-content:center;}"
                    + ".box{width:90%;max-width:600px;background:#111;border:1px solid #292929;border-radius:16px;padding:45px;text-align:center;}"
                    + ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;margin-bottom:30px;}"
                    + ".logo span{color:#00e5ff;}"
                    + ".success{font-size:55px;color:#63e681;margin-bottom:15px;}"
                    + "h1{font-size:30px;margin-bottom:12px;}"
                    + "p{color:#999;line-height:1.6;}"
                    + ".btn{display:inline-block;margin:20px 8px 0;padding:12px 22px;border-radius:8px;text-decoration:none;font-weight:bold;}"
                    + ".primary{background:#00e5ff;color:#001014;}"
                    + ".secondary{border:1px solid #333;color:white;}"
                    + ".btn:hover{opacity:0.85;}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='box'>"
                    + "<div class='logo'>SMART<span>CAMPUS</span></div>"
                    + "<div class='success'>✓</div>"
                    + "<h1>Feedback Submitted Successfully!</h1>"
                    + "<p>Thank you for rating our service.</p>"
                    + "<p>Your feedback has been recorded successfully.</p>"
                    + "<a class='btn primary' href='track.html'>Track Another Request</a>"
                    + "<a class='btn secondary' href='index.html'>Back to Home</a>"
                    + "</div>"
                    + "</body>"
                    + "</html>"
            );

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset='UTF-8'>"
                    + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                    + "<title>Feedback Error | Smart Campus</title>"
                    + "<style>"
                    + "*{box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;}"
                    + "body{margin:0;background:#080808;color:white;min-height:100vh;display:flex;align-items:center;justify-content:center;}"
                    + ".box{width:90%;max-width:600px;background:#111;border:1px solid #292929;border-radius:16px;padding:45px;text-align:center;}"
                    + ".logo{font-size:22px;font-weight:bold;letter-spacing:1px;margin-bottom:30px;}"
                    + ".logo span{color:#00e5ff;}"
                    + ".icon{font-size:55px;color:#ff6b6b;margin-bottom:15px;}"
                    + "h1{font-size:28px;margin-bottom:12px;}"
                    + "p{color:#999;line-height:1.6;}"
                    + ".btn{display:inline-block;margin-top:20px;padding:12px 22px;border-radius:8px;text-decoration:none;font-weight:bold;background:#00e5ff;color:#001014;}"
                    + ".btn:hover{opacity:0.85;}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='box'>"
                    + "<div class='logo'>SMART<span>CAMPUS</span></div>"
                    + "<div class='icon'>!</div>"
                    + "<h1>Feedback Could Not Be Submitted</h1>"
                    + "<p>Something went wrong while saving your feedback.</p>"
                    + "<p>Please try again later.</p>"
                    + "<a class='btn' href='track.html'>Back to Request</a>"
                    + "</div>"
                    + "</body>"
                    + "</html>"
            );
        }
    }
}