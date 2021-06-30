package com.kopo.oaiaGamLab;

public class Board {
	int idx;
	String user_title;
	String user_content;
	String created;
	String updated;
	int user_idx;
	
	public Board() {
	
	}
	
	public Board(String user_title, String user_content, int user_idx, String created, String updated) {
		this.user_title = user_title;
		this.user_content = user_content;
		this.user_idx = user_idx;
		this.created = created;
		this.updated = updated;
	}

}
