<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quizapp.dao.QuizDAO" %>
<%@ page import="com.quizapp.model.Quiz" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz List</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="sidebar">
        <h2>Quiz App</h2>
        <a href="adminDashboard.jsp">Dashboard</a>
        <a href="addQuestion.jsp">Question Manager</a>
        <a href="createQuiz.jsp">Quiz Manager</a>
        <a href="adminquizList.jsp">Quiz List</a>
    </div>
    <div class="content">
        <h2>Quiz List</h2>
        <table>
            <tr>
                <th>Sr No.</th>
                <th>Quiz Title</th>
                <th>Category</th>
            </tr>
            <%
                QuizDAO quizDAO = new QuizDAO();
                List<Quiz> quizzes = quizDAO.getAllQuizzes();
                int count = 1;
                for (Quiz quiz : quizzes) {
            %>
                <tr>
                    <td><%= count++ %></td>
                    <td><%= quiz.getQuizTitle() %></td>
                    <td><%= quiz.getCategory() %></td>
                </tr>
            <% } %>
        </table>
        <% if (request.getParameter("success") != null) { %>
            <p class="success"><%= request.getParameter("success") %></p>
        <% } %>
    </div>
</body>
</html>