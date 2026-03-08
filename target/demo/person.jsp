<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<html>
<head>
    <title>Insert Person Details</title>
</head>
<body>
    <h2>Insert Person Details</h2>
    
    <%
        // Check if the form is submitted using POST method.
        if(request.getMethod().equalsIgnoreCase("POST")) {
            // Retrieve form parameters
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            
            // Database connection details (update these with your database information)
            String url = "jdbc:mysql://localhost:3306/mydb"; // Replace 'your_database' with your DB name
            String dbUser = "root";      // Replace with your DB username
            String dbPass = "admin"; // Replace with your DB password
            
            Connection conn = null;
            PreparedStatement ps = null;
            
            try {
                // Load the MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establish the connection
                conn = DriverManager.getConnection(url, dbUser, dbPass);
                
                // Prepare SQL INSERT statement (excluding PersonID if it's auto-increment)
                String sql = "INSERT INTO persons (PersonID, LastName, FirstName, Address, City) VALUES (?,?, ?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, 1);
                ps.setString(2, lastName);
                ps.setString(3, firstName);
                ps.setString(4, address);
                ps.setString(5, city);
                
                // Execute the INSERT statement
                int result = ps.executeUpdate();
                if(result > 0) {
                    out.println("<p>Person details inserted successfully!</p>");
                } else {
                    out.println("<p>Failed to insert person details.</p>");
                }
            } catch(Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                // Close resources
                if(ps != null) {
                    try { ps.close(); } catch(Exception ignore) {}
                }
                if(conn != null) {
                    try { conn.close(); } catch(Exception ignore) {}
                }
            }
        }
    %>
    
    <!-- HTML form for capturing person details -->
    <form method="post" action="person.jsp">
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required /><br/><br/>
        
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required /><br/><br/>
        
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required /><br/><br/>
        
        <label for="city">City:</label>
        <input type="text" id="city" name="city" required /><br/><br/>
        
        <input type="submit" value="Insert Person" />
    </form>
</body>
</html>
