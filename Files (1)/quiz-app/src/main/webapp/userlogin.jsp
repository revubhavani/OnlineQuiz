 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <header>
        <h1>ONLINE QUIZ</h1>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="quizList.jsp">Quiz</a>
            <a href="userlogin.jsp">Login/Signup</a>
        </nav>
    </header>
    <div class="container">
        <h2>Login</h2>
        <% if (request.getParameter("success") != null) { %>
            <p class="success"><%= request.getParameter("success") %></p>
        <% } %>
        <form action="userlogin" method="post">
            <label>Email:</label><input type="email" name="email" required><br>
            <label>Password:</label><input type="password" name="password" required><br>
            <input type="submit" value="Login">
        </form>
        <p>Don't have an account? <a href="register.jsp">Register here</a></p>
        <% if (request.getParameter("error") != null) { %>
            <p class="error"><%= request.getParameter("error") %></p>
        <% } %>
    </div>
</body>
</html>
 