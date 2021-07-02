package com.kopo.oaiaGamLab;

public class SignupUser {
	int idx;
	String id;
	String pwd;
	String name;
	String birth;
	String email;
	String nickname;
	String join_date;

	SignupUser() {
	}

	SignupUser(String id, String pwd, String name, String birth, String email, String nickname, String join_date) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.nickname = nickname;
		this.join_date = join_date;
	}

	SignupUser(int idx, String pwd, String name, String birth, String email, String nickname) {
		this.idx = idx;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.nickname = nickname;
	}

	SignupUser(int idx, String id, String pwd, String name, String birth, String email, String nickname,
			String join_date) {
		this.idx = idx;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.nickname = nickname;
		this.join_date = join_date;
	}
	
	SignupUser(String id){
		this.id=id;
	}

}
