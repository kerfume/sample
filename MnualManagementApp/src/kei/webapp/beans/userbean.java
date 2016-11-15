package kei.webapp.beans;

import java.io.Serializable;

public class userbean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_id;
	private String user_name;
	private String company_id;
	private String company_name;
	private String group1_id;
	private String user_authority;//権限有無
	//他必要分があれば随時追加予定
	
	public userbean(){
		user_id="";
		user_name="";
		company_id="";
		company_name="";
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getUser_authority() {
		return user_authority;
	}

	public void setUser_authority(String user_authority) {
		this.user_authority = user_authority;
	}

	public String getGroup1_id() {
		return group1_id;
	}

	public void setGroup1_id(String group1_id) {
		this.group1_id = group1_id;
	}
	

}
