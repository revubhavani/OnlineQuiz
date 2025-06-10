<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quizapp.dao.QuestionDAO" %>
<%@ page import="com.quizapp.model.Question" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Quiz</title>
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
        <h2>Create New Quiz</h2>
        <form action="createQuiz" method="post">
            <label>Quiz Title:</label><input type="text" name="quizTitle" required><br>
            <label>Category:</label><input type="text" name="category" required><br>
            <label>Select Questions:</label><br>
            <%
                QuestionDAO questionDAO = new QuestionDAO();
                List<Question> questions = questionDAO.getAllQuestions();
                for (Question question : questions) {
            %>
                <input type="checkbox" name="questions" value="<%= question.getId() %>">
                <%= question.getQuestionText() %><br>
            <% } %>
            <input type="submit" value="Create Quiz">
        </form>
        <% if (request.getParameter("error") != null) { %>
            <p class="error"><%= request.getParameter("error") %></p>
        <% } %>
    </div>
</body>
</html>