package jp.kerfume.app.bean;

import java.util.Date;

public class InDataBean {
	private Integer id;
	private String name;
	private int age;
	private int sex;
	private Date registdate;
	
	public InDataBean(){
		this.setId(null);
		this.setName("");
		this.setAge(0);
		this.setSex(0);
		this.setRegistdate(null);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRegistdate() {
		return registdate;
	}

	public void setRegistdate(Date registdate) {
		this.registdate = registdate;
	}


}
