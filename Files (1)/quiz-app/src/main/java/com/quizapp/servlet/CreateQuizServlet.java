package com.quizapp.servlet;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizapp.dao.QuizDAO;
import com.quizapp.model.Quiz;
 
@WebServlet("/createQuiz")
public class CreateQuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Quiz quiz = new Quiz();
        quiz.setQuizTitle(request.getParameter("quizTitle"));
        quiz.setCategory(request.getParameter("category"));
 
        String[] questionIds = request.getParameterValues("questions");
        List<Integer> questionIdList = new ArrayList<>();
        if (questionIds != null) {
            for (String id : questionIds) {
                questionIdList.add(Integer.parseInt(id));
            }
        }
 
        QuizDAO quizDAO = new QuizDAO();
        try {
            quizDAO.createQuiz(quiz, questionIdList);
            response.sendRedirect("quizList.jsp?success=Quiz created");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("createQuiz.jsp?error=Failed to create quiz");
        }
    }
}