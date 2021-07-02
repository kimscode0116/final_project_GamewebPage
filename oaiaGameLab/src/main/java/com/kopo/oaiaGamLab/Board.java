package com.kopo.oaiaGamLab;

public class Board {
	int idx;
	String user_title;
	String user_nickname;
	String user_content;
	int user_idx;
	String created;
	
	public Board() {
	
	}
	
	public Board(String user_title, String user_content, int user_idx, String created) {
		this.user_title = user_title;
		this.user_content = user_content;
		this.user_idx = user_idx;
		this.created = created;
	}
	
	public Board(String user_title, String user_nickname, String user_content, String created) {
		this.user_title = user_title;
		this.user_nickname = user_nickname;
		this.user_content = user_content;
		this.created = created;
	}

}
