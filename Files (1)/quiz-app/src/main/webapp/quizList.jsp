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
    <header>
        <h1>ONLINE QUIZ</h1>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="quizList.jsp">Quiz</a>
            <a href="userlogin.jsp">Login/Signup</a>
        </nav>
    </header>
    <div class="container">
        <h2>Available Quizzes</h2>
        <table>
            <tr>
                <th>Sr No.</th>
                <th>Quiz Title</th>
                <th>Category</th>
                <th>Action</th>
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
                    <td><a href="takeQuiz?quizId=<%= quiz.getId() %>" class="btn">Start</a></td>
                </tr>
            <% } %>
        </table>
        <% if (request.getParameter("error") != null) { %>
            <p class="error"><%= request.getParameter("error") %></p>
        <% } %>
    </div>
</body>
</html>
 