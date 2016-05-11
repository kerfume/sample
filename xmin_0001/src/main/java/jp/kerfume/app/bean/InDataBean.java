package jp.kerfume.app.bean;

public class InDataBean {
	private String name;
	private int age;
	private int sex;
	
	public InDataBean(){
		this.setName("");
		this.setAge(0);
		this.setSex(0);
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

}
