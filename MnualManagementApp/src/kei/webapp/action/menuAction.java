package kei.webapp.action;

import java.util.HashMap;
import java.util.Map;

import kei.webapp.module.DateGetter;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@Results({
	@Result(name="logoff",location="/jsp/login.jsp"),
	@Result(name="useredit",location="/jsp/useredit.jsp"),
	@Result(name="input",location="/jsp/menu.jsp")
})


public class menuAction extends ActionSupport implements SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Action("menuaction")
	public String execute() throws Exception {
		System.out.println("excecuteメソッド");
        return "input";
    }
	
	public String gouseredit() throws Exception {
		
        return "useredit";
    }
	
	
	public String logoff() throws Exception{
		System.out.println("logoffメソッド");
		((SessionMap)session).invalidate();
		return("logoff");
	}
	

	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	private Map<String, Object> session;
}
