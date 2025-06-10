package com.quizapp.servlet;
 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizapp.dao.QuestionDAO;
import com.quizapp.model.Question;
 
@SuppressWarnings("serial")
@WebServlet("/addQuestion")
public class AddQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question question = new Question();
        question.setQuestionText(request.getParameter("question"));
        question.setOption1(request.getParameter("option1"));
        question.setOption2(request.getParameter("option2"));
        question.setOption3(request.getParameter("option3"));
        question.setOption4(request.getParameter("option4"));
        question.setCorrectOption(request.getParameter("correctOption"));
        question.setCategory(request.getParameter("category"));
 
        QuestionDAO questionDAO = new QuestionDAO();
        try {
            questionDAO.addQuestion(question);
            response.sendRedirect("adminDashboard.jsp?success=Question added");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("addQuestion.jsp?error=Failed to add question");
        }
    }
}