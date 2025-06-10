<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Question</title>
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
        <h2>Add New Question</h2>
        <form action="addQuestion" method="post">
            <label>Question:</label><input type="text" name="question" required><br>
            <label>Option 1:</label><input type="text" name="option1" required><br>
            <label>Option 2:</label><input type="text" name="option2" required><br>
            <label>Option 3:</label><input type="text" name="option3" required><br>
            <label>Option 4:</label><input type="text" name="option4" required><br>
            <label>Correct Option:</label><input type="text" name="correctOption" required><br>
            <label>Category:</label><input type="text" name="category" required><br>
            <input type="submit" value="Add Question">
        </form>
        <% if (request.getParameter("error") != null) { %>
            <p class="error"><%= request.getParameter("error") %></p>
        <% } %>
    </div>
</body>
</html>