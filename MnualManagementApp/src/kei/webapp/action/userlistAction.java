package kei.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import kei.webapp.beans.udatabean;
import kei.webapp.beans.userbean;
import kei.webapp.module.DBconnector;

@Results({ @Result(name = "sucsess", location = "/jsp/userlist.jsp"),
	@Result(name = "input", location = "/jsp/userlist.jsp")})
public class userlistAction extends ActionSupport implements SessionAware {
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
			.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	DBconnector db= new DBconnector(); 
	List<udatabean> udL;
	int pagenum = 1;
	int begin;
	int end;
	int unum = 0;
	
	public int getUnum() {
		return unum;
	}

	public void setUnum(int unum) {
		this.unum = unum;
	}

	

	public int getPagenum() {
		return pagenum;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begen) {
		this.begin = begen;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public List<udatabean> getUdL() {
		return udL;
	}

	public void setUdL(List<udatabean> udL) {
		this.udL = udL;
	}

	@Action("userlistaction")
	public String execute() throws Exception {
		if(request.getParameter("unum") == null){
		udL =  new ArrayList<>();
		
		//session.put("ulcursor", "1");
		udL = db.getUsers(((userbean) session.get("ub")).getCompany_id());
		if(udL.size() % 15 == 0) pagenum = (int)(udL.size() / 15);
		else pagenum = (int)(udL.size() / 15 + 1);
		begin = 0;
		end = 14;
		session.put("udL", udL);
		session.put("pagenum", pagenum);
		}else{
			udL = (List<udatabean>)session.get("udL");
			pagenum = (int)session.get("pagenum");
			unum = Integer.parseInt(request.getParameter("unum"));
			begin = unum * 15;
			end = begin + 14;
			if(udL.size()-1 < end) end = udL.size() - 1;
				
		}
		
		return "sucsess";
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	private Map<String, Object> session;


}
