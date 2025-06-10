<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quizapp.dao.UserDAO" %>
<%@ page import="com.quizapp.dao.QuestionDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="sidebar">
        <h2>Quiz App</h2>
        <a href="adminDashboard.jsp">Dashboard</a>
        <a href="addQuestion.jsp">Question Manager</a>
        <a href="createQuiz.jsp">Quiz Manager</a>
        <a href="quizList.jsp">Quiz List</a>
    </div>
    <div class="content">
        <h2>Admin Dashboard</h2>
        <%
            UserDAO userDAO = new UserDAO();
            QuestionDAO questionDAO = new QuestionDAO();
            int totalUsers = userDAO.getTotalUsers();
            int totalQuestions = questionDAO.getTotalQuestions();
        %>
        <p>Total Users: <%= totalUsers %></p>
        <p>Total Questions: <%= totalQuestions %></p>
        <% if (request.getParameter("success") != null) { %>
            <p class="success"><%= request.getParameter("success") %></p>
        <% } %>
    </div>
</body>
</html>