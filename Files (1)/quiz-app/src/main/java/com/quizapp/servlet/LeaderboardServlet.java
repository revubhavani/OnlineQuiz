package com.quizapp.servlet;
 
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizapp.dao.LeaderboardDAO;
import com.quizapp.model.Leaderboard;
 
@WebServlet("/leaderboard")
public class LeaderboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        LeaderboardDAO leaderboardDAO = new LeaderboardDAO();
        try {
            List<Leaderboard> leaderboard = leaderboardDAO.getLeaderboardByQuizId(quizId);
            request.setAttribute("leaderboard", leaderboard);
            request.setAttribute("quizId", quizId);
            request.getRequestDispatcher("leaderboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("quizList.jsp?error=Failed to load leaderboard");
        }
    }
}

 