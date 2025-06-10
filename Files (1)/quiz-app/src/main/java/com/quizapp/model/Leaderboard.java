package com.quizapp.model;
 

public class Leaderboard {
    private int id;
    private int userId;
    private String username;
    public String getUsername() {
		return username;
	}
    
    
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	private int quizId;
    private int score;

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getQuizId() { return quizId; }
    public void setQuizId(int quizId) { this.quizId = quizId; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}