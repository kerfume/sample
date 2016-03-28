package kei.webapp.beans;

public class manualinbean {
	private String company_id;
	private String group1_id;
	private String group2_id;
	private String manual_id;
	private int update_count;
	private String manual_name;
	private String manual_content1;
	private String manual_content2;
	private String manual_picture;
	private int delete_flag;
	private String manual_classification_name;
	//階層1
	private String[] dir_id = new String[4];
	private String[] dir_name = new String[4];
	
	public String[] getDir_id_full(){
		return dir_id;
	}
	public String[] getDir_name_full(){
		return dir_name;
	}
	public String getDir_id(int index){
		return dir_id[index];
	}
	public void setDir_id(String dir_id,int index){
		this.dir_id[index] = dir_id;
	}
	public String getDir_name(int index){
		return dir_name[index];
	}
	public void setDir_name(String dir_name,int index){
		this.dir_name[index] = dir_name;
	}
	public String getManual_classification_name() {
		return manual_classification_name;
	}
	public void setManual_classification_name(String manual_classification_name) {
		this.manual_classification_name = manual_classification_name;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getGroup1_id() {
		return group1_id;
	}
	public void setGroup1_id(String group1_id) {
		this.group1_id = group1_id;
	}
	public String getGroup2_id() {
		return group2_id;
	}
	public void setGroup2_id(String group2_id) {
		this.group2_id = group2_id;
	}
	public String getManual_id() {
		return manual_id;
	}
	public void setManual_id(String manual_id) {
		this.manual_id = manual_id;
	}
	public int getUpdate_count() {
		return update_count;
	}
	public void setUpdate_count(int update_count) {
		this.update_count = update_count;
	}
	public String getManual_name() {
		return manual_name;
	}
	public void setManual_name(String manual_name) {
		this.manual_name = manual_name;
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
	public String getManual_picture() {
		return manual_picture;
	}
	public void setManual_picture(String manual_picture) {
		this.manual_picture = manual_picture;
	}
	public int getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
}
