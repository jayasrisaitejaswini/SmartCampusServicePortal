# 🏫 SmartCampus Service Portal

### A Java-based campus service management platform for reporting, tracking, and resolving student service requests.

![Java](https://img.shields.io/badge/Java-Servlets-orange)
![JDBC](https://img.shields.io/badge/Database%20Connectivity-JDBC-blue)
![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1)
![HTML5](https://img.shields.io/badge/Frontend-HTML5-E34F26)
![CSS3](https://img.shields.io/badge/Styling-CSS3-1572B6)
![Apache Tomcat](https://img.shields.io/badge/Server-Apache%20Tomcat-F8DC75)
![Git](https://img.shields.io/badge/Version%20Control-Git-F05032)
![GitHub](https://img.shields.io/badge/Repository-GitHub-181717)

---

## 🔗 Repository

**Source Code:**  
https://github.com/jayasrisaitejaswini/SmartCampusServicePortal

---
# 🎯 Demo Access

Use the following dedicated demo accounts to explore the SmartCampus Service Portal.

## 👨‍🎓 Student Demo Account

```text
Email: demo@student.com
Password: demo123

The student demo account can be used to explore:

Student login
Student dashboard
Service request submission
Request tracking
Request history
Student feedback
Logout
👨‍💼 Admin Demo Account
Email: admin@smartcampus.com
Password: admin123
or 
Email: support@smartcampus.com
Password: support123

The admin demo account can be used to explore:

Admin login
Admin dashboard
Request management
Department assignment
Request status updates
Resolution notes
Request monitoring

These credentials are dedicated demo accounts created specifically for project demonstration.

They are not personal accounts.

Please do not use these credentials for any personal or external service.

No real database passwords, API keys, GitHub tokens, or personal credentials are included in this README.

# 📌 Overview

**SmartCampus Service Portal** is a web-based campus service management system developed using **Java Servlets, JDBC, MySQL, HTML5, and CSS3**.

The platform provides a centralized system where students can report campus-related issues, track their requests, view request history, and provide feedback after resolution.

Administrators can manage submitted requests, assign departments, update request statuses, add resolution notes, and monitor overall service activity.

The project demonstrates the integration of:

- Frontend development
- Java backend development
- Servlet-based request processing
- JDBC database connectivity
- MySQL database management
- Authentication
- Session management
- Authorization
- Request lifecycle management
- Git and GitHub version control

---

# 🎯 Problem Statement

Students may face different campus-related problems such as:

- Wi-Fi and network issues
- Electrical problems
- Hostel maintenance
- Classroom issues
- Library-related problems
- Transportation concerns
- Canteen issues
- General maintenance problems

When these issues are handled through informal communication, it can become difficult to:

- Track submitted requests
- Identify responsible departments
- Monitor unresolved issues
- Maintain request history
- Follow request progress
- Collect student feedback

### 💡 Proposed Solution

SmartCampus provides a centralized digital platform where students can submit service requests and track their progress while administrators can manage the complete request lifecycle.

```text
Student
   │
   ▼
Submit Service Request
   │
   ▼
Department Assignment
   │
   ▼
Admin Review
   │
   ▼
Work Started
   │
   ▼
Resolution
   │
   ▼
Student Feedback
✨ Key Features
👨‍🎓 Student Module
🔐 Student Authentication
Student login using email and password
Session-based authentication
Unauthorized-access protection
Logout functionality
📝 Service Request Submission

Students can report campus issues by entering:

Category
Location
Description
Priority

Each submitted request receives a unique Request ID.

📊 Student Dashboard

The dashboard provides an overview of:

Total Requests
Pending Requests
In Progress Requests
Resolved Requests
🔎 Request Tracking

Students can track individual requests using their Request ID.

The request lifecycle is displayed through different stages:

✓ Request Submitted
        ↓
✓ Assigned to Department
        ↓
✓ Work Started
        ↓
● Waiting for Resolution
        ↓
○ Resolved
📋 Request History

Students can view their previously submitted requests along with:

Request ID
Category
Location
Description
Priority
Status
Department
Request details
⭐ Student Feedback

Students can submit feedback after their requests are resolved.

The system validates that feedback belongs to the logged-in student and is associated with an eligible request.

👨‍💼 Admin Module
🔐 Admin Authentication

A separate administrator login provides access to request-management functionality.

📊 Admin Dashboard

Administrators can monitor:

Total Requests
Pending Requests
In Progress Requests
Resolved Requests
Request priorities
Department assignments
🏢 Department Management

Requests can be assigned to appropriate departments such as:

IT Support
Electrical
Hostel
Library
Transport
Canteen
Maintenance
🔄 Request Status Management

Administrators can update request status.

Available statuses:

Pending
In Progress
Resolved

This allows administrators to reflect the current stage of each service request.

📝 Resolution Notes

Administrators can record resolution notes explaining how a reported issue was handled.

Example:

Network issue assigned to the IT Support team.
🔍 Request Management

The admin dashboard provides a centralized view of submitted requests.

Administrators can:

View submitted requests
View student information
View request category
View location
View priority
View assigned department
Update request status
Add resolution notes
🧠 Smart Request Routing

The application supports department assignment based on the type of campus service request.

Request Category	Assigned Department
Wi-Fi / Network	IT Support
Electrical	Electrical
Hostel	Hostel
Library	Library
Transport	Transport
Canteen	Canteen
Other	Maintenance

This helps organize requests and direct them toward the appropriate campus service department.

⚡ Priority-Based Handling

Service requests can be categorized according to their priority:

Priority	Purpose
Urgent	Requires immediate attention
High	Requires prompt attention
Medium	Normal priority request
Low	Lower-priority request

This helps administrators identify requests that require faster attention.

🏗️ System Architecture
                 SMARTCAMPUS
                      │
                      ▼
              HTML / CSS Interface
                      │
                      ▼
                Java Servlets
                      │
                      ▼
                    JDBC
                      │
                      ▼
                   MySQL
Architecture Layers
Presentation Layer
HTML5
CSS3
Browser-based user interface
Application Layer
Java Servlets
Request processing
Authentication
Session management
Business logic
Authorization
Data Access Layer
JDBC
PreparedStatement
ResultSet
SQL queries
Database Layer
MySQL
Relational tables
Primary keys
Foreign-key relationships
Application Server
Apache Tomcat 9
🛠️ Technologies Used
Technology	Purpose
Java	Backend programming
Java Servlets	Server-side application logic
JDBC	Database connectivity
MySQL	Relational database
HTML5	Frontend structure
CSS3	User interface styling
Apache Tomcat 9	Web application server
Eclipse IDE	Development environment
Git	Version control
GitHub	Source code hosting
🗄️ Database Design

The application uses a MySQL database named:

smartcampus
Students Table

Stores student authentication and profile information.

student_id
name
email
password
roll_number
Requests Table

Stores campus service requests.

request_id
student_id
category
location
description
priority
status
department
created_at
resolution_note
Feedback Table

Stores feedback submitted by students.

feedback_id
request_id
student_id
rating
comment
created_at
Admins Table

Stores administrator authentication information.

admin_id
email
password

Relationships between tables are maintained using relational keys and foreign-key relationships.

🔒 Security & Access Control

The project implements several security-focused practices:

Session-based authentication
Login validation
Unauthorized-access protection
Student-specific request access
Student-specific feedback validation
PreparedStatement for SQL queries
Database credentials removed from source code
Environment variable used for the database password

The database password is loaded using:

SMARTCAMPUS_DB_PASSWORD

Real database passwords, API keys, and personal credentials should never be committed to GitHub.

🎯 Demo Access

The following accounts are dedicated demonstration accounts for the project.

👨‍🎓 Student Demo Account
Email: demo@student.com
Password: demo123
👨‍💼 Admin Demo Account
Email: admin@smartcampus.com
Password: admin123
Alternative Admin Demo Account
Email: support@smartcampus.com
Password: support123

These accounts are intended only for project demonstration. Do not use these credentials for personal accounts.

🚀 Running the Project Locally
Prerequisites

Install the following:

Java
Eclipse IDE
Apache Tomcat 9
MySQL Server
MySQL Workbench
MySQL Connector/J
Git
1. Clone the Repository
git clone https://github.com/jayasrisaitejaswini/SmartCampusServicePortal.git
2. Create the Database

Create the MySQL database:

CREATE DATABASE smartcampus;

Create the required tables according to the database structure used by the application.

3. Configure Database Credentials

Set the following environment variable:

SMARTCAMPUS_DB_PASSWORD

The application reads the MySQL password from the environment instead of storing the password directly in the Java source code.

4. Import into Eclipse

Import the project into Eclipse and configure:

Apache Tomcat 9

as the application server.

5. Start the Application

Start the Tomcat server and open:

http://localhost:8080/SmartCampusServicePortal/
📸 Application Screenshots

The following screenshots demonstrate the major modules of the application.

🏠 Home Page

🔐 Student Login

📊 Student Dashboard

📝 Request Submission

🔎 Request Tracking

📋 Request History

👨‍💼 Admin Login

📊 Admin Dashboard

📊 Admin Dashboard - Detailed View

📁 Project Structure
SmartCampusServicePortal/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── com/
│       │   │   └── smartcampus/
│       │   │       ├── dao/
│       │   │       │   ├── DBConnection.java
│       │   │       │   └── ConnectionTest.java
│       │   │       │
│       │   │       └── servlet/
│       │   │           ├── LoginServlet.java
│       │   │           ├── LogoutServlet.java
│       │   │           ├── StudentDashboardServlet.java
│       │   │           ├── RequestServlet.java
│       │   │           ├── MyRequestsServlet.java
│       │   │           ├── TrackRequestServlet.java
│       │   │           ├── AdminLoginServlet.java
│       │   │           ├── AdminDashboardServlet.java
│       │   │           └── UpdateRequestServlet.java
│       │   │
│       │   └── FeedbackServlet.java
│       │
│       └── webapp/
│           ├── index.html
│           ├── login.html
│           ├── request.html
│           ├── track.html
│           ├── admin-login.html
│           ├── admin-dashboard.html
│           └── WEB-INF/
│               ├── lib/
│               │   └── mysql-connector-j-26.7.0.jar
│               └── web.xml
│
└── .gitignore
🔄 Request Lifecycle

The complete service-request workflow is:

┌─────────────────────┐
│   Student Login     │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Submit Service      │
│ Request             │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Generate Request ID │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Department Assigned │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Work In Progress    │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Request Resolved    │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Student Feedback    │
└─────────────────────┘
🧪 Testing

The application was tested across the major workflows.

Student Workflow
Student login
Dashboard access
Service request submission
Request tracking
Request history
Resolved-request feedback
Logout
Admin Workflow
Admin login
Request viewing
Department assignment
Status updates
Resolution notes
Request management
Access Control
Unauthenticated access protection
Student-specific request validation
Feedback authorization validation
Session-based access control
📈 Future Enhancements

Potential improvements include:

Email notifications for request updates
OTP-based authentication
Advanced analytics and charts
Automatic priority classification
Department performance analytics
Advanced search and filtering
REST API integration
Cloud deployment
Docker containerization
Mobile-friendly Progressive Web App
Automated email notifications
Multiple administrative roles
AI-assisted request classification
💡 Learning Outcomes

This project provided practical experience in:

Java web application development
Java Servlet architecture
JDBC database connectivity
MySQL database design
SQL queries
CRUD operations
Authentication and session management
Authorization and access control
HTML and CSS UI development
Apache Tomcat deployment
Debugging web applications
Git version control
GitHub repository management
🎓 Project Highlights
🏫 Centralized Service Management

A single platform for managing multiple campus service categories.

🔄 End-to-End Request Tracking

Students can follow a request from submission through resolution.

👥 Role-Based Functionality

Separate workflows are provided for students and administrators.

🏢 Department-Based Routing

Requests can be directed to the appropriate campus service department.

⚡ Priority-Based Handling

Requests can be categorized based on priority to help administrators identify important issues.

📝 Resolution Tracking

Administrators can update request status and record resolution notes.

⭐ Feedback System

Students can provide feedback after eligible requests are resolved.

🗄️ Database-Driven Architecture

Student, request, administrator, and feedback data are stored and managed using MySQL.

🔮 Future Vision

SmartCampus can be extended into a larger campus service management platform with:

Mobile Application
       +
REST API
       +
Cloud Deployment
       +
Email / OTP Notifications
       +
Advanced Analytics
       +
AI-assisted Request Classification
👩‍💻 Project Information
Category	Details
Project Name	SmartCampus Service Portal
Project Type	Java Web Application
Backend	Java Servlets
Database	MySQL
Database Connectivity	JDBC
Frontend	HTML5 + CSS3
Application Server	Apache Tomcat 9
IDE	Eclipse
Version Control	Git
Source Code Hosting	GitHub
📌 Project Status
⭐ Completed

Core student and administrator workflows have been implemented and tested.

The project demonstrates:

Frontend
   +
Java Backend
   +
Servlets
   +
JDBC
   +
MySQL
   +
Authentication
   +
Request Management
   +
Admin Dashboard
   +
Feedback
   +
Git / GitHub
👩‍💻 Author

Jayasri Sai Tejaswini

B.Tech — Information Technology

⭐ Conclusion

SmartCampus Service Portal demonstrates how a real-world campus service problem can be transformed into a structured web application using Java, Servlets, JDBC, MySQL, HTML5, and CSS3.

The project provides an end-to-end workflow from student issue reporting to administrative resolution and student feedback, while demonstrating practical skills in backend development, database management, authentication, authorization, and version control.
