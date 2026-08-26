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
# 🔗 Repository

Source Code:

https://github.com/jayasrisaitejaswini/SmartCampusServicePortal

## 🎯 Demo Access

The following account can be used to explore the student module:

**Student Demo Account**

- Email: demo@student.com
- Password: demo123

> This is a dedicated demo account created specifically for project demonstration.
> Please do not use these credentials for any personal account.

## 🎯 Demo Access

The following account can be used to explore the student module:

**Admin Demo Account**

- Email: admin@smartcampus.com
- Password: admin123
 or
- Email: support@smartcampus.com
- Password: support123


> This is a dedicated demo account created specifically for project demonstration.
> Please do not use these credentials for any personal account.
## 📌 Overview

**SmartCampus Service Portal** is a web-based campus service management system developed using **Java Servlets, JDBC, MySQL, HTML, and CSS**.

The platform provides a centralized system for students to report campus-related issues and monitor their resolution status, while administrators can manage requests, assign departments, update statuses, and record resolution details.

The project demonstrates the integration of a **frontend interface, Java backend, relational database, authentication, session management, and role-based functionality** into a complete web application.

---

## 🎯 Problem Statement

Students commonly report campus issues such as:

- Wi-Fi and network problems
- Electrical issues
- Hostel maintenance
- Classroom problems
- Library-related issues
- Transportation concerns
- Canteen issues
- General maintenance problems
This can make it difficult to:

Track requests
Assign requests to the correct department
Monitor unresolved issues
Maintain request history
Collect student feedback
When these issues are handled through informal or manual communication, it can become difficult to track requests, identify responsible departments, monitor progress, and maintain service history.

### 💡 Proposed Solution

SmartCampus provides a centralized digital platform where students can submit and track service requests while administrators can efficiently manage the complete request lifecycle.

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
# ✨ Key Features

## 👨‍🎓 Student Module

### 🔐 Student Authentication

- Student login using email and password
- Session-based authentication
- Unauthorized access protection
- Logout functionality

### 📝 Service Request Submission

Students can report campus issues by entering:

- Category
- Location
- Description
- Priority

Each submitted request receives a unique **Request ID**.

### 📊 Student Dashboard

The dashboard provides an overview of:

- Total Requests
- Pending Requests
- In Progress Requests
- Resolved Requests

### 🔎 Request Tracking

Students can track individual requests using their Request ID.

The request lifecycle is displayed as:

```text
✓ Request Submitted
      ↓
✓ Assigned to Department
      ↓
✓ Work Started
      ↓
● Waiting for Resolution
      ↓
○ Resolved
### 📋 Request History

Students can view their previously submitted requests along with:

- Request ID
- Category
- Location
- Priority
- Status
- Department
- Request details

### ⭐ Student Feedback

Students can submit feedback after their requests are resolved.

The system validates that feedback can only be submitted for eligible requests belonging to the logged-in student.
# 👨‍💼 Admin Module

### 🔐 Admin Authentication

A separate admin login provides access to request-management functionality.

### 📊 Admin Dashboard

Administrators can monitor:

- Total Requests
- Pending Requests
- In Progress Requests
- Resolved Requests
- Request Priorities
- Department Assignments

### 🏢 Department Management

Requests can be assigned to appropriate departments such as:
IT Support
Electrical
Hostel
Library
Transport
Maintenance
Canteen
### 🔄 Request Status Management

Administrators can update request status:
Pending
In Progress
Resolved

### 📝 Resolution Notes

Administrators can record resolution notes explaining how a reported issue was handled.

### 🔍 Request Management

The admin dashboard provides a centralized view of submitted requests and allows administrators to update request information.

# 🧠 Smart Request Routing

The application supports department assignment based on the type of campus service request.

|Request CategoryAssigned Department|
| Wi-Fi / Network                     | IT Support  |
| Electrical                          | Electrical  |
| Hostel                              | Hostel      |
| Library                             | Library     |
| Transport                           | Transport   |
| Canteen                             | Canteen     |
| Other                               | Maintenance |

This helps organize requests and route them toward the appropriate service department

# 🏗️ System Architecture
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
### Architecture Layers

**Presentation Layer**

-  HTML 
-  CSS 
-  Browser-based user interface 

**Application Layer**

-  Java Servlets 
-  Request processing 
-  Authentication 
-  Session management 
-  Business logic 

**Data Access Layer**

-  JDBC 
-  PreparedStatement 
-  ResultSet 
-  SQL queries 

**Database Layer**

-  MySQL 
-  Relational tables 
-  Foreign-key relationships 

**Application Server**

-  Apache Tomcat 

# 🛠️ Technologies Used

| TechnologyPurpose |                            
| Java              | Backend programming           |
| Java Servlets     | Server-side application logic |
| JDBC              | Database connectivity         |
| MySQL             | Relational database           |
| HTML5             | Frontend structure            |
| CSS3              | UI styling                    |
| Apache Tomcat     | Web application server        |
| Eclipse IDE       | Development environment       |
| Git               | Version control               |
| GitHub            | Source code hosting           |

# 🗄️ Database Design

The application uses a MySQL database named:
smartcampus

### Students Table

Stores student authentication and profile information.
student_id
name
email
password
roll_number

### Requests Table

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

### Feedback Table

Stores feedback submitted by students.
feedback_id
request_id
student_id
rating
comment
created_at

### Admins Table

Stores administrator authentication information
admin_id
email
password

Relationships between tables are maintained using relational keys.
# 🔒 Security & Access Control

The project implements several security-focused practices:

-  Session-based authentication 
-  Login validation 
-  Unauthorized-access protection 
-  Student-specific request access 
-  Student-specific feedback validation 
-  PreparedStatement for SQL queries 
-  Database credentials removed from source code 
-  Environment variable used for the database password 

The database password is loaded using:
SMARTCAMPUS_DB_PASSWORD
> **Real database passwords and personal credentials should never be committed to GitHub.**

# 🚀 Running the Project Locally

## Prerequisites

Install the following:

-  Java 
-  Eclipse IDE 
-  Apache Tomcat 9 
-  MySQL Server 
-  MySQL Workbench 
-  MySQL Connector/J 
-  Git 

## 1. Clone the Repository
git clone https://github.com/jayasrisaitejaswini/SmartCampusServicePortal.git
## 2. Create the Database
Create a MySQL database:
CREATE DATABASE smartcampus;
Create the required tables according to the database structure used by the application.

## 3. Configure Database Credentials

Set the following environment variable:
SMARTCAMPUS_DB_PASSWORD
```

The application reads the MySQL password from the environment rather than storing it directly in the source code.

## 4. Import into Eclipse

Import the project into Eclipse and configure:
Apache Tomcat 9
as the application server.

## 5. Start the Application

Start the Tomcat server and open:
http://localhost:8080/SmartCampusServicePortal/

# 📸 Application Screenshots

Screenshots demonstrating the major modules of the application.

### 🏠 Home Page



### 🔐 Student Login

*Add screenshot here*

### 📊 Student Dashboard

*Add screenshot here*

### 📝 Request Submission

*Add screenshot here*

### 🔎 Request Tracking

*Add screenshot here*

### 👨‍💼 Admin Login

*Add screenshot here*

### 📊 Admin Dashboard

*Add screenshot here*

### ⭐ Student Feedback

*Add screenshot here*
# 📁 Project Structure
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
│               └── web.xml
│
└── .gitignore
```

---

# 🔄 Request Lifecycle

The complete service-request workflow 
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

# 🧪 Testing

The application was tested across the major workflows.

### Student Workflow

-  Student login 
-  Dashboard access 
-  Request submission 
-  Request tracking 
-  Request history 
-  Resolved-request feedback 
-  Logout 

### Admin Workflow

-  Admin login 
-  Request viewing 
-  Department assignment 
-  Status updates 
-  Resolution notes 
-  Request management 

### Access Control

-  Unauthenticated access protection 
-  Student-specific request validation 
-  Feedback authorization validation 

# 📈 Future Enhancements

Potential improvements include:

-  Email notifications for request updates 
-  OTP-based authentication 
-  Advanced analytics and charts 
-  Automatic priority classification 
-  Department performance analytics 
-  Advanced search and filtering 
-  REST API integration 
-  Cloud deployment 
-  Docker containerization 
-  Mobile-friendly Progressive Web App 
-  Automated email notifications to students 
-  Role-based access control with multiple administrative roles 

# 💡 Learning Outcomes

This project provided practical experience in:

-  Java web application development 
-  Java Servlet architecture 
-  JDBC database connectivity 
-  MySQL database design 
-  SQL queries 
-  CRUD operations 
-  Authentication and session management 
-  Authorization and access control 
-  HTML and CSS UI development 
-  Apache Tomcat deployment 
-  Debugging web applications 
-  Git version control 
-  GitHub repository management 
# 🎓 Project Highlights

### Centralized Service Management

One platform for multiple campus service categories.

### End-to-End Request Tracking

Students can follow a request from submission through resolution.

### Role-Based Functionality

Different workflows are provided for students and administrators.

### Department-Based Routing

Requests can be directed to the appropriate campus service department.

### Resolution Tracking

Administrators can update statuses and record resolution notes.

### Feedback System

Students can provide feedback after successful resolution.

### Database-Driven Architecture

Student, request, administrative, and feedback data are stored and managed using MySQL.

# 🔮 Future Vision

SmartCampus can be extended into a complete campus service management platform with
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

# 👩‍💻 Project Information

**Project Name:** SmartCampus Service Portal

**Project Type:** Java Web Application

**Backend:** Java Servlets

**Database:** MySQL

**Database Connectivity:** JDBC

**Frontend:** HTML5 + CSS3

**Application Server:** Apache Tomcat

**IDE:** Eclipse

**Version Control:** Git

**Repository:** GitHub
# 🔗 Repository

Source Code:

https://github.com/jayasrisaitejaswini/SmartCampusServicePortal

## ⭐ Project Status

**Completed — Core student and admin workflows implemented and tested.**

**Built as a practical Java web application project demonstrating full-stack integration of frontend, backend, database, authentication, request management, and version
### 🔄 Request Status Management

Administrators can update the status of service requests through the admin dashboard.

Available statuses
Pending
In Progress
Resolved
This allows administrators to reflect the current stage of each campus service request.

### 📝 Resolution Notes

Administrators can add resolution notes to provide additional information about how an issue was handled.

For example:
Network issue assigned to maintenance team.
or
Electrical issue checked and resolved successfully.

### 🔍 Request Management

The admin dashboard provides a centralized view of submitted requests.

Administrators can:

-  View submitted requests 
-  View student information 
-  View request category 
-  View location 
-  View priority 
-  View assigned department 
-  Update request status 
-  Add resolution notes 
# 🧠 Smart Request Routing

The application supports department assignment based on the type of campus service request.

| Request CategoryAssigned Department |             
| Wi-Fi / Network                     | IT Support  |
| Electrical                          | Electrical  |
| Hostel                              | Hostel      |
| Library                             | Library     |
| Transport                           | Transport   |
| Canteen                             | Canteen     |
| Other                               | Maintenance |

This helps organize service requests and direct them toward the appropriate campus department.
# 🏗️ System Architecture
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
### Architecture Layers

**Presentation Layer**

-  HTML5 
-  CSS3 
-  Browser-based user interface 

**Application Layer**

-  Java Servlets 
-  Authentication 
-  Session management 
-  Request processing 
-  Business logic 

**Data Access Layer**

-  JDBC 
-  PreparedStatement 
-  ResultSet 
-  SQL queries 

**Database Layer**

-  MySQL 
-  Relational tables 
-  Primary keys 
-  Foreign-key relationships 

**Application Server**

-  Apache Tomcat 9 
# 🛠️ Technologies Used

| TechnologyPurpose |                               
| Java              | Backend programming           |
| Java Servlets     | Server-side application logic |
| JDBC              | Database connectivity         |
| MySQL             | Relational database           |
| HTML5             | Frontend structure            |
| CSS3              | User interface styling        |
| Apache Tomcat 9   | Web application server        |
| Eclipse IDE       | Development environment       |
| Git               | Version control               |
| GitHub            | Source code hosting           |

# 🗄️ Database Design

The application uses a MySQL database named:
smartcampus

### Students Table

Stores student authentication and profile information.
student_id
name
email
password
roll_number
### Requests Table

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
### Feedback Table

Stores feedback submitted by students.
feedback_id
request_id
student_id
rating
comment
created_at
### Admins Table

Stores administrator authentication information.
admin_id
email
password

Relationships between tables are maintained using relational keys.
# 🔒 Security & Access Control

The project implements several security-focused practices:

-  Session-based authentication 
-  Login validation 
-  Unauthorized-access protection 
-  Student-specific request access 
-  Student-specific feedback validation 
-  PreparedStatement for SQL queries 
-  Database credentials removed from source code 
-  Environment variable used for the database password 

The database password is loaded using:

SMARTCAMPUS_DB_PASSWORD

> **Never commit real database passwords, API keys, or personal credentials to GitHub.**
# 🚀 Running the Project Locally

## Prerequisites

Install the following:

-  Java 
-  Eclipse IDE 
-  Apache Tomcat 9 
-  MySQL Server 
-  MySQL Workbench 
-  MySQL Connector/J 
-  Git 

## 1. Clone the Repository

git clone https://github.com/jayasrisaitejaswini/SmartCampusServicePortal.git

## 2. Create the Database

Create the MySQL database:
CREATE DATABASE smartcampus;
Create the required tables according to the database structure used by the application.

## 3. Configure Database Credentials

Set the following environment variable
SMARTCAMPUS_DB_PASSWORD
The application reads the MySQL password from the environment instead of storing the password directly in the source code.

## 4. Import into Eclipse

Import the project into Eclipse and configure:
Apache Tomcat 9
as the application server.

## 5. Start the Application

Start the Tomcat server and open:
http://localhost:8080/SmartCampusServicePortal/


# 🔄 Request Lifecycle

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

# 🧪 Testing

The application was tested across the major workflows.

### Student Workflow

-  Student login 
-  Dashboard access 
-  Service request submission 
-  Request tracking 
-  Request history 
-  Resolved-request feedback 
-  Logout 

### Admin Workflow

-  Admin login 
-  Request viewing 
-  Department assignment 
-  Status updates 
-  Resolution notes 
-  Request management 

### Access Control

-  Unauthenticated access protection 
-  Student-specific request validation 
-  Feedback authorization validation 

# 📈 Future Enhancements

Potential improvements include:

-  Email notifications for request updates 
-  OTP-based authentication 
-  Advanced analytics and charts 
-  Automatic priority classification 
-  Department performance analytics 
-  Advanced search and filtering 
-  REST API integration 
-  Cloud deployment 
-  Docker containerization 
-  Mobile-friendly Progressive Web App 
-  Automated email notifications 
-  Role-based access control with multiple administrative roles 

# 💡 Learning Outcomes

This project provided practical experience in:

-  Java web application development 
-  Java Servlet architecture 
-  JDBC database connectivity 
-  MySQL database design 
-  SQL queries 
-  CRUD operations 
-  Authentication and session management 
-  Authorization and access control 
-  HTML and CSS UI development 
-  Apache Tomcat deployment 
-  Debugging web applications 
-  Git version control 
-  GitHub repository management 

# 🎓 Project Highlights

### 🏫 Centralized Service Management

A single platform for managing multiple campus service categories.

### 🔄 End-to-End Request Tracking

Students can follow a request from submission through resolution.

### 👥 Role-Based Functionality

Separate workflows are provided for students and administrators.

### 🏢 Department-Based Routing

Requests can be directed to the appropriate campus service department.

### ⚡ Priority-Based Handling
Requests can be categorized using:
Urgent
High
Medium
Low
This helps administrators identify higher-priority service requests.

### 📝 Resolution Tracking

Administrators can update request status and record resolution notes.

### ⭐ Feedback System

Students can provide feedback after eligible requests are resolved.

### 🗄️ Database-Driven Architecture

Student, request, administrator, and feedback data are stored and managed using MySQL.

# 🔮 Future Vision

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
```

---

# 👩‍💻 Project Information

**Project Name:** SmartCampus Service Portal

**Project Type:** Java Web Application

**Backend:** Java Servlets

**Database:** MySQL

**Database Connectivity:** JDBC

**Frontend:** HTML5 + CSS3

**Application Server:** Apache Tomcat 9

**IDE:** Eclipse

**Version Control:** Git

**Source Code Hosting:** GitHub

---

# 🔗 Repository

Source Code:

https://github.com/jayasrisaitejaswini/SmartCampusServicePortal

---

## ⭐ Project Status

**Completed — Core student and admin workflows implemented and tested.**
**Built as a practical Java web application demonstrating frontend-backend integration, database connectivity, authentication, request management, role-based functionality, and version control using Git and GitHub
