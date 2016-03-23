package kei.webapp.action;

import kei.webapp.beans.userbean;
import kei.webapp.module.*;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/jsp")
@ParentPackage("struts-default")
@Results({
	@Result(name="sucsess",location="/jsp/login.jsp"),
	@Result(name="login",location="/jsp/menu.jsp"),
	@Result(name="input",location="/jsp/login.jsp")
})

public class loginAction extends ActionSupport implements SessionAware{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_id;
	private String password;
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Action("loginaction")
	public String execute() throws Exception{
		try{
			DBconnector dbc = new DBconnector();
			userbean ub = dbc.login_user_saerch(user_id, password);
		if(ub.getUser_name().equals("")){
			addActionError("ユーザ名及び/またはパスワードが正しくありません。");
			return "input";
		}else if(ub.getUser_authority().equals("0")){
			addActionError("このユーザーは管理権限を所持していません。");
			return "input";
		}
		else{
			session.put("ub",ub);
			dbc.updateLogin(ub.getUser_id());
			
			return "login";
		}
		}catch(Exception e){
			return "input";
		}
		
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	private Map<String, Object> session;
	
}
