package com.quizapp.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quizapp.model.Leaderboard;
import com.quizapp.util.DBConnection;
 
public class LeaderboardDAO {
    public void saveScore(int userId, int quizId, int score) throws SQLException {
        String sql = "INSERT INTO leaderboard (user_id, quiz_id, score) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection()){
        	PreparedStatement stmt = conn.prepareStatement(sql);
        
            stmt.setInt(1, userId);
            stmt.setInt(2, quizId);
            stmt.setInt(3, score);
            stmt.executeUpdate();
        }
    }
 
    public List<Leaderboard> getLeaderboardByQuizId(int quizId) throws SQLException {
        List<Leaderboard> leaderboard = new ArrayList<>();
        String sql = "SELECT l.*, u.username FROM leaderboard l JOIN users u ON l.user_id = u.id WHERE l.quiz_id = ? ORDER BY l.score DESC";
        try (Connection conn = DBConnection.getConnection()){
        	PreparedStatement stmt = conn.prepareStatement(sql);
        
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Leaderboard entry = new Leaderboard();
                entry.setId(rs.getInt("id"));
                entry.setUserId(rs.getInt("user_id"));
                entry.setQuizId(rs.getInt("quiz_id"));
                entry.setScore(rs.getInt("score"));
                leaderboard.add(entry);
            }
        }
        return leaderboard;
    }
}