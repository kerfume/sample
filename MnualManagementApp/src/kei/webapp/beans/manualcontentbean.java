package kei.webapp.beans;

import java.io.Serializable;

public class manualcontentbean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String manual_ID; //企業id+部署id+グループid+マニュアル種別id
	private String manual_title;
	private String colortype;
	private int updcnt;
	private String kaisou1;
	private String kaisou2;
	private String kaisou3;
	private String kaisou4;
	private String manual_content1;
	private String manual_content2;
	private String imgURI;
	
	public manualcontentbean(){
		this.manual_ID = "";
		this.manual_title = "";
		this.colortype = "";
		this.kaisou1 = "";
		this.kaisou2 = "";
		this.kaisou3 = "";
		this.kaisou4 = "";
		this.manual_content1 = "";
		this.manual_content2 = "";
	}
	
	public String getManual_ID() {
		return manual_ID;
	}
	public void setManual_ID(String manual_ID) {
		this.manual_ID = manual_ID;
	}
	public int getUpdcnt() {
		return updcnt;
	}
	public void setUpdcnt(int updcnt) {
		this.updcnt = updcnt;
	}
	public String getKaisou1() {
		return kaisou1;
	}
	public void setKaisou1(String kaisou1) {
		this.kaisou1 = kaisou1;
	}
	public String getKaisou2() {
		return kaisou2;
	}
	public void setKaisou2(String kaisou2) {
		this.kaisou2 = kaisou2;
	}
	public String getKaisou3() {
		return kaisou3;
	}
	public void setKaisou3(String kaisou3) {
		this.kaisou3 = kaisou3;
	}
	public String getKaisou4() {
		return kaisou4;
	}
	public void setKaisou4(String kaisou4) {
		this.kaisou4 = kaisou4;
	}
	public String getManual_content1() {
		return manual_content1;
	}
	public void setManual_content1(String manual_content1) {
		this.manual_content1 = manual_content1;
	}
	public String getManual_content2() {
		return manual_content2;
	}
	public void setManual_content2(String manual_content2) {
		this.manual_content2 = manual_content2;
	}
	public String getManual_title() {
		return manual_title;
	}
	public void setManual_title(String manual_title) {
		this.manual_title = manual_title;
	}
	public String getColortype() {
		return colortype;
	}
	public void setColortype(String colortype) {
		this.colortype = colortype;
	}
	public String getImgURI() {
		return imgURI;
	}
	public void setImgURI(String imgURI) {
		this.imgURI = imgURI;
	}
	
	
	
	

}
