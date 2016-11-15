package kei.beans;

import java.io.Serializable;
import java.util.Date;

public class Emp implements Serializable {

	private Integer empno;
	private String ename;
	private String job;
	private Date hiredate;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Emp() {}
	
	public Emp(int empno,String ename,String job,Date hiberdate) {
		this.setEmpno(empno);
		this.setEname(ename);
		this.setJob(job);
		this.setHiredate(hiberdate);
	}
	
	public Integer getEmpno() {
	    return this.empno;
	}
	public void setEmpno(Integer empno) {
	this.empno = empno;
	}
	public String getEname() {
	return this.ename;
	}
	public void setEname(String ename) {
	this.ename = ename;
	}
	public String getJob() {
	return this.job;
	}
	public void setJob(String job) {
	this.job = job;
	}
	public Date getHiredate() {
	return this.hiredate;
	}
	public void setHiredate(Date hiredate) {
	this.hiredate = hiredate;
	}

}
