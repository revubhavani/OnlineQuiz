<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
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
        <h2>User Registration</h2>
        <form action="registrationProcess.jsp" method="post">
            <label>Username:</label><input type="text" name="username" required><br>
            <label>Email:</label><input type="email" name="email" required><br>
            <label>Password:</label><input type="password" name="password" required><br>
            <input type="submit" value="Register">
        </form>
        <% if (request.getParameter("error") != null) { %>
            <p class="error"><%= request.getParameter("error") %></p>
        <% } %>
    </div>
</body>
</html>
 
 