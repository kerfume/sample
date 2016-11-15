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
	    params={"root", "manual"}
	  ), 
	})

public class manualjsonAction extends ActionSupport implements ServletRequestAware,SessionAware {

	private HashMap<String, String> manual;
	
	@Action("manualjsonaction")
	public String execute() throws Exception{
		DBconnector db = new DBconnector();
		String dpt_id = request.getParameter("department");
		String grp_id = request.getParameter("group");
		HashMap<String, String> map = db.getManuList(((userbean)(session.get("ub"))).getCompany_id(), dpt_id,grp_id,0);

		setManual(map);

		return SUCCESS;

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public HashMap<String, String> getManual() {
		return manual;
	}

	public void setManual(HashMap<String, String> manual) {
		this.manual = manual;
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