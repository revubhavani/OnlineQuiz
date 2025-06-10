<%@ page import="java.sql.*" %>
<%
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // JDBC variables
    String url = "jdbc:mysql://localhost:3306/quiz_app";
    String dbUser = "root"; // change as needed
    String dbPassword = "Password@12"; // change as needed

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // for MySQL 8+
        conn = DriverManager.getConnection(url, dbUser, dbPassword);

        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, email);
        stmt.setString(3, password); // For production, you should hash the password!

        int rows = stmt.executeUpdate();

        if (rows > 0) {
            response.sendRedirect("userlogin.jsp?message=Registration successful. Please login.");
        } else {
            response.sendRedirect("register.jsp?error=Registration failed. Try again.");
        }

    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("register.jsp?error=" + e.getMessage());
    } finally {
        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
%>