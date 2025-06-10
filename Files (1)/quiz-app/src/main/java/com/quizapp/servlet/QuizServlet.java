package com.quizapp.servlet;
 
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quizapp.dao.LeaderboardDAO;
import com.quizapp.dao.QuestionDAO;
import com.quizapp.model.Question;
import com.quizapp.model.User;
 
@WebServlet("/takeQuiz")
public class QuizServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        QuestionDAO questionDAO = new QuestionDAO();
        try {
            List<Question> questions = questionDAO.getQuestionsByQuizId(quizId);
            request.setAttribute("questions", questions);
            request.setAttribute("quizId", quizId);
            request.getRequestDispatcher("takeQuiz.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("quizList.jsp?error=Failed to load quiz");
        }
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
 
        QuestionDAO questionDAO = new QuestionDAO();
        try {
            List<Question> questions = questionDAO.getQuestionsByQuizId(quizId);
            int score = 0;
            for (Question question : questions) {
                String userAnswer = request.getParameter("answer_" + question.getId());
                if (userAnswer != null && userAnswer.equals(question.getCorrectOption())) {
                    score++;
                }
            }
 
            LeaderboardDAO leaderboardDAO = new LeaderboardDAO();
            leaderboardDAO.saveScore(user.getId(), quizId, score);
            response.sendRedirect("leaderboard?quizId=" + quizId);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("quizList.jsp?error=Failed to submit quiz");
        }
    }
}