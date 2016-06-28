package kei.webapp.action;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import kei.webapp.beans.manualclassbean;
import kei.webapp.beans.manualcontentbean;
import kei.webapp.beans.manualinbean;
import kei.webapp.beans.udatabean;
import kei.webapp.beans.userbean;
import kei.webapp.module.DBconnector;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "sucsess", location = "/jsp/manualMdifi.jsp"),
		@Result(name = "input", location = "/jsp/manualIn.jsp"), @Result(name = "back", location = "/jsp/menu.jsp") })
public class manualmodifiAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private String manu_id;
	private String updcnt;
	private String department;
	private String group;

	private String manual_title;
	private String manual1;
	private String manual2;
	private String manual3;
	private String manual4;
	private String detail_info;
	private String require_matter;
	private File movie_picture;
	private String movie_pictureContentType;
	private String movie_pictureFileName;

	// rogic
	@Action("manualmodifiaction")
	@SkipValidation()
	public String execute() throws Exception {

		return "sucsess";
	}

	public String edit() throws Exception {
		/*
		 * System.out.println(request.getParameter("manu_ID"));
		 * System.out.println(request.getParameter("updcnt"));
		 */
		// hiddenにセットしたほうがいい
		userbean ub = (userbean) session.get("ub");
		this.setManu_id(request.getParameter("manu_ID"));
		this.setUpdcnt(request.getParameter("updcnt"));
		this.setDepartment(request.getParameter("DPT"));
		this.setGroup(request.getParameter("GRP"));
		DBconnector db = new DBconnector();
		manualcontentbean mcb = db.getManuBean(ub.getCompany_id(),department,group,manu_id.substring(7,9), Integer.parseInt(updcnt));
		
		this.setManual1(mcb.getKaisou1());
		this.setManual2(mcb.getKaisou2());
		this.setManual3(mcb.getKaisou3());
		this.setManual4(mcb.getKaisou4());
		this.setDetail_info(mcb.getManual_content1());
		this.setRequire_matter(mcb.getManual_content2());
		
		return "sucsess";
	}

	public String getManual_title() {
		return manual_title;
	}

	public void setManual_title(String manual_title) {
		this.manual_title = manual_title;
	}

	public String getManual1() {
		return manual1;
	}

	public void setManual1(String manual1) {
		this.manual1 = manual1;
	}

	public String getManual2() {
		return manual2;
	}

	public void setManual2(String manual2) {
		this.manual2 = manual2;
	}

	public String getManual3() {
		return manual3;
	}

	public void setManual3(String manual3) {
		this.manual3 = manual3;
	}

	public String getManual4() {
		return manual4;
	}

	public void setManual4(String manual4) {
		this.manual4 = manual4;
	}

	public String getDetail_info() {
		return detail_info;
	}

	public void setDetail_info(String detail_info) {
		this.detail_info = detail_info;
	}

	public String getRequire_matter() {
		return require_matter;
	}

	public void setRequire_matter(String require_matter) {
		this.require_matter = require_matter;
	}

	public File getMovie_picture() {
		return movie_picture;
	}

	public void setMovie_picture(File movie_picture) {
		this.movie_picture = movie_picture;
	}

	public String getMovie_pictureContentType() {
		return movie_pictureContentType;
	}

	public void setMovie_pictureContentType(String movie_pictureContentType) {
		this.movie_pictureContentType = movie_pictureContentType;
	}

	public String getMovie_pictureFileName() {
		return movie_pictureFileName;
	}

	public void setMovie_pictureFileName(String movie_pictureFileName) {
		this.movie_pictureFileName = movie_pictureFileName;
	}

	public String getManu_id() {
		return manu_id;
	}

	public void setManu_id(String manu_id) {
		this.manu_id = manu_id;
	}

	public String getUpdcnt() {
		return updcnt;
	}

	public void setUpdcnt(String updcnt) {
		this.updcnt = updcnt;
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

	// その他sessionとか
	private static final long serialVersionUID = 1L;

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
