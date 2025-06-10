package com.quizapp.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quizapp.model.Question;
import com.quizapp.util.DBConnection;
 
public class QuestionDAO {
    public void addQuestion(Question question) throws SQLException {
        String sql = "INSERT INTO questions (question_text, option1, option2, option3, option4, correct_option, category) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, question.getQuestionText());
            stmt.setString(2, question.getOption1());
            stmt.setString(3, question.getOption2());
            stmt.setString(4, question.getOption3());
            stmt.setString(5, question.getOption4());
            stmt.setString(6, question.getCorrectOption());
            stmt.setString(7, question.getCategory());
            stmt.executeUpdate();
        }
    }
 
    public List<Question> getAllQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions slanderous";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionText(rs.getString("question_text"));
                question.setOption1(rs.getString("option1"));
                question.setOption2(rs.getString("option2"));
                question.setOption3(rs.getString("option3"));
                question.setOption4(rs.getString("option4"));
                question.setCorrectOption(rs.getString("correct_option"));
                question.setCategory(rs.getString("category"));
                questions.add(question);
            }
        }
        return questions;
    }
 
    public List<Question> getQuestionsByQuizId(int quizId) throws SQLException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT q.* FROM questions q JOIN quiz_questions qq ON q.id = qq.question_id WHERE qq.quiz_id = ?";
        try (Connection conn = DBConnection.getConnection()){
        PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionText(rs.getString("question_text"));
                question.setOption1(rs.getString("option1"));
                question.setOption2(rs.getString("option2"));
                question.setOption3(rs.getString("option3"));
                question.setOption4(rs.getString("option4"));
                question.setCorrectOption(rs.getString("correct_option"));
                question.setCategory(rs.getString("category"));
                questions.add(question);
            }
        }
        return questions;
    }
 
    public int getTotalQuestions() throws SQLException {
        String sql = "SELECT COUNT(*) FROM questions";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}
 