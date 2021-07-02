package com.kopo.oaiaGamLab;

public class Question {
	int idx;
	String question_title;
	String user_id;
	String question_content;
	String created;
	String answer;
	
	Question() {
	
	}
	
	Question(String question_title, String user_id, String question_content, String created) {
		this.question_title = question_title;
		this.user_id = user_id;
		this.question_content = question_content;
		this.created = created;
	}
	
	Question(int idx, String question_title, String user_id, String question_content, String created) {
		this.idx = idx;
		this.question_title = question_title;
		this.user_id = user_id;
		this.question_content = question_content;
		this.created = created;
	}
	
	Question(int idx, String question_title, String user_id, String question_content, String created, String answer) {
		this.idx = idx;
		this.question_title = question_title;
		this.user_id = user_id;
		this.question_content = question_content;
		this.created = created;
		this.answer = answer;
	}

}
