package com.quizapp.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quizapp.model.Quiz;
import com.quizapp.util.DBConnection;
 
public class QuizDAO {
    public void createQuiz(Quiz quiz, List<Integer> questionIds) throws SQLException {
        Connection conn = DBConnection.getConnection();
        try {
            conn.setAutoCommit(false);
            // Insert quiz
            String sql = "INSERT INTO quizzes (quiz_title, category) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, quiz.getQuizTitle());
            stmt.setString(2, quiz.getCategory());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int quizId = 0;
            if (rs.next()) {
                quizId = rs.getInt(1);
            }
 
            // Map questions to quiz
            String mapSql = "INSERT INTO quiz_questions (quiz_id, question_id) VALUES (?, ?)";
            PreparedStatement mapStmt = conn.prepareStatement(mapSql);
            for (Integer questionId : questionIds) {
                mapStmt.setInt(1, quizId);
                mapStmt.setInt(2, questionId);
                mapStmt.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }
 
    public List<Quiz> getAllQuizzes() throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM quizzes";
        try (Connection conn = DBConnection.getConnection()){
        	Statement stmt = conn.createStatement(); 
        	ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setQuizTitle(rs.getString("quiz_title"));
                quiz.setCategory(rs.getString("category"));
                quizzes.add(quiz);
            }
        }
        return quizzes;
    }
 
    public Quiz getQuizById(int quizId) throws SQLException {
        String sql = "SELECT * FROM quizzes WHERE id = ?";
        try (Connection conn = DBConnection.getConnection()){
        	PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setQuizTitle(rs.getString("quiz_title"));
                quiz.setCategory(rs.getString("category"));
                return quiz;
            }
        }
        return null;
    }
}
 
