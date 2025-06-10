<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quizapp.dao.QuizDAO" %>
<%@ page import="com.quizapp.model.Leaderboard" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Leaderboard</title>
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
        <%
            QuizDAO quizDAO = new QuizDAO();
            int quizId = Integer.parseInt(request.getAttribute("quizId").toString());
            com.quizapp.model.Quiz quiz = quizDAO.getQuizById(quizId);
        %>
        <h2>Quiz Name: <%= quiz.getQuizTitle() %></h2>
        <h3>Category: <%= quiz.getCategory() %></h3>
        <table>
            <tr>
                <th>Sr No.</th>
                <th>User</th>
                <th>Score</th>
            </tr>
            <%
                List<Leaderboard> leaderboard = (List<Leaderboard>) request.getAttribute("leaderboard");
                int count = 1;
                for (Leaderboard entry : leaderboard) {
            %>
                <tr>
                    <td><%= count++ %></td>
                    <td><%= entry.getUserId() %></td>
                    <td><%= entry.getScore() %></td>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>