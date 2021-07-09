package com.kopo.oaiaGamLab;

public class Comments {
	int idx;
	String comments;
	String user_id;
	String created;
	int board_idx;
	
	Comments(){
		
	}
	
	Comments(String user_id, String comments, String created, int board_idx){
		this.user_id = user_id;
		this.comments = comments;
		this.created = created;
		this.board_idx = board_idx;
	}
	
	Comments(int idx, String user_id, String comments, String created, int board_idx){
		this.idx = idx;
		this.user_id = user_id;
		this.comments = comments;
		this.created = created;
		this.board_idx = board_idx;
	}
	
}
