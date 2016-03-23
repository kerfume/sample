package kei.webapp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import kei.webapp.beans.userbean;
import kei.webapp.module.DBconnector;

@ParentPackage("json-default")
@InterceptorRefs({
	  @InterceptorRef("basicStack"),
	  @InterceptorRef("json"),  // JSONインターセプタ
	})
@Results({
	  @Result(
	    name="success" ,
	    type="json"  ,
	    params={"root", "group"}
	  ), 
	})
public class groupjsonAction extends ActionSupport implements ServletRequestAware,SessionAware {

	private HashMap<String, String> group;
	
	@Action("groupjsonaction")
	public String execute() throws Exception{
		DBconnector db = new DBconnector();
		String group_id = request.getParameter("department");
		HashMap<String, String> map = db.getGroupList(((userbean)(session.get("ub"))).getCompany_id(), group_id, 1);

		setGroup(map);

		return SUCCESS;

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public HashMap<String, String> getGroup() {
		return group;
	}

	public void setGroup(HashMap<String, String> group) {
		this.group = group;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		
	}
	private HttpServletRequest request;
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	private Map<String, Object> session;


}
