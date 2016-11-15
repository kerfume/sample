package kei.webapp.action;

import java.io.*;
import java.util.*;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import kei.webapp.beans.manualclassbean;
import kei.webapp.beans.manualcontentbean;
import kei.webapp.beans.manualinbean;
import kei.webapp.beans.udatabean;
import kei.webapp.beans.userbean;
import kei.webapp.module.DBconnector;

@Results({ @Result(name = "sucsess", location = "/jsp/manualEdit.jsp"),
		@Result(name = "input", location = "/jsp/manualIn.jsp"), @Result(name = "back", location = "/jsp/menu.jsp") })
public class manualeditAction extends ActionSupport implements SessionAware {
	// startup

	// DB取得用
	userbean ub;
	udatabean ud;
	udatabean udedit;
	manualinbean mib;
	manualclassbean mcb;
	DBconnector db = new DBconnector();
	// form用リスト,map
	Map<String, String> dptlist;
	Map<String, String> grplist;
	Map<String, String> manulist;
	// form部品
	private String department;// group_id1
	private String group;// group_id2
	private String manualSyubetu;
	private String DPT;
	private String GRP;
	List<manualcontentbean> mbL;
	
	public Map<String, String> getDptlist() {
		ub = (userbean) session.get("ub");
		dptlist = db.getDepartmentList(ub.getCompany_id(), 0);

		return dptlist;
	}

	public Map<String, String> getGrplist() {
		ub = (userbean) session.get("ub");
		dptlist = db.getGroupList(ub.getCompany_id(), ub.getGroup1_id(), 1);

		return dptlist;
	}
	
	public Map<String, String> getManulist() {
		ub = (userbean) session.get("ub");
		manulist = db.getManuList(ub.getCompany_id(),department,group, 0);//change dbgetMethod!

		return manulist;
	}

	// rogic
	@Action("manualeditaction")
	@SkipValidation()
	public String execute() throws Exception {
		mbL = new ArrayList<>();
		
		return "sucsess";
	}
	
	public String serch() throws Exception {
		ub = (userbean) session.get("ub");
		mbL = db.getManuBeanList(ub.getCompany_id(), department, group, manualSyubetu);
		this.setDPT(department);
		this.setGRP(group);
		
		return "sucsess";
	}
	
	public List<manualcontentbean> getMbL() {
		return mbL;
	}

	public void setMbL(List<manualcontentbean> mbL) {
		this.mbL = mbL;
	}

	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getManualSyubetu() {
		return manualSyubetu;
	}

	public void setManualSyubetu(String manualSyubetu) {
		this.manualSyubetu = manualSyubetu;
	}

	

	public String getDPT() {
		return DPT;
	}

	public void setDPT(String dPT) {
		DPT = dPT;
	}

	public String getGRP() {
		return GRP;
	}

	public void setGRP(String gRP) {
		GRP = gRP;
	}



	// その他session
	private static final long serialVersionUID = 1L;

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	private Map<String, Object> session;

}
