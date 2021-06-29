package com.kopo.oaiaGamLab;

public class Admin {
	int idx;
	String admin_id;
	String admin_pwd;
	String admin_name;
	
	public Admin() {
		
	}
	
	public Admin(String admin_id, String admin_pwd, String admin_name) {
		this.admin_id = admin_id;
		this.admin_pwd = admin_pwd;
		this.admin_name = admin_name;
	}
	
	public Admin(String admin_id, String admin_pwd) {
		this.admin_id = admin_id;
		this.admin_pwd = admin_pwd;
	}
	
}
