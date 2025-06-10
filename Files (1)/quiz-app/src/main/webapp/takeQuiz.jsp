<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.quizapp.model.Question" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Take Quiz</title>
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
        <h2>Take Quiz</h2>
        <form action="takeQuiz" method="post">
            <input type="hidden" name="quizId" value="<%= request.getAttribute("quizId") %>">
            <%
                List<Question> questions = (List<Question>) request.getAttribute("questions");
                int count = 1;
                for (Question question : questions) {
            %>
                <p><%= count++ %>. <%= question.getQuestionText() %></p>
                <input type="radio" name="answer_<%= question.getId() %>" value="<%= question.getOption1() %>"> <%= question.getOption1() %><br>
                <input type="radio" name="answer_<%= question.getId() %>" value="<%= question.getOption2() %>"> <%= question.getOption2() %><br>
                <input type="radio" name="answer_<%= question.getId() %>" value="<%= question.getOption3() %>"> <%= question.getOption3() %><br>
                <input type="radio" name="answer_<%= question.getId() %>" value="<%= question.getOption4() %>"> <%= question.getOption4() %><br>
            <% } %>
            <input type="submit" value="Submit Quiz">
        </form>
    </div>
</body>
</html>