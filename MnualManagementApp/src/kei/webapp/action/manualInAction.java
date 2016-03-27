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

//poi関連
//import org.apache.poi.hssf.usermodel.*; // 旧excelの場合
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import com.opensymphony.xwork2.ActionSupport;

import kei.webapp.beans.manualclassbean;
import kei.webapp.beans.manualinbean;
import kei.webapp.beans.udatabean;
import kei.webapp.beans.userbean;
import kei.webapp.module.DBconnector;

@Results({ @Result(name = "sucsess", location = "/jsp/manualIn.jsp"),
		@Result(name = "input", location = "/jsp/manualIn.jsp"), @Result(name = "back", location = "/jsp/menu.jsp") })

public class manualInAction extends ActionSupport implements SessionAware {

	//err判定用
	int errnum;
	StringBuilder errmes;
	// xlsx用リスト
	List<ArrayList<String>> cellTable = new ArrayList<>();
	// DB取得用
	userbean ub;
	udatabean ud;
	udatabean udedit;
	manualinbean mib;
	manualclassbean mcb;
	DBconnector db = new DBconnector();
	
	ArrayList<ArrayList<manualinbean>> mibss;
	ArrayList<manualclassbean> mcbs;
	// form用リスト,map
	Map<String, String> dptlist;
	Map<String, String> grplist;
	// form部品
	private String department;// group_id1
	private String group;// group_id2
	private String message;
	private File xlsfile;
	private String xlsfileContentType;
	private String xlsfileFileName;

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

	@Action("manualinaction")
	@SkipValidation()
	public String execute() throws Exception {

		return "sucsess";
	}

	public String insert() throws Exception {
		errnum =0;
		errmes = new StringBuilder("======エラー内容=========\n");
		
		mibss = new ArrayList<>();
		mcbs = new ArrayList<>();
		
		System.out.println(this.getDepartment() + "," + this.getGroup() + "\n" + this.getXlsfileFileName() + ":"
				+ this.getXlsfileContentType());
		if (this.fileDecision(xlsfileFileName)) {
			// System.out.println("ok");
			try (FileInputStream fi = new FileInputStream(xlsfile); XSSFWorkbook book = new XSSFWorkbook(fi)) {
				Sheet sheet = book.getSheetAt(0); // 先頭シートのみ取得
				for (Row row : sheet) { // 全行をなめる
					if (String.valueOf(row.cellIterator().next()).trim().equals(""))//先頭cellが空ならデータなしとする
						break;
					ArrayList<String> mycell = new ArrayList<>();
					//Iterator<Cell> it = row.cellIterator();
					// for (Cell cell : row) { // 全セルをなめる
					for (int i = 0; i < 16; i++) {
						Cell cell = row.getCell(i);
						mycell.add(String.valueOf(cell));
						System.out.println(String.valueOf(cell) + " ");
					}
					cellTable.add(mycell);
					System.out.println("");
				}
				
			}
			this.makeInData(cellTable);
		} // else System.out.println("no");
		System.out.println(mcbs.size()+":"+mibss.size());
		this.uniqueCheck(mcbs);
		this.setMessage(errmes.toString());
		if(errnum == 0){
			//DB登録
			db.manualInsert(mcbs, mibss);
		}

		return "sucsess";
	}

	// 処理
	private boolean fileDecision(String filename) {
		boolean res = false;
		int ind;
		if (filename != null && (ind = filename.lastIndexOf(".")) != -1)
			res = filename.substring(ind).equals(".xlsx") ? true : false;
		return res;
	}
	
	private void makeInData(List<ArrayList<String>> cellTable){
		ub = (userbean) session.get("ub");
		for(int i=2;i<cellTable.size();i++){
			mcb = new manualclassbean();
			ArrayList<String> cells = cellTable.get(i);
			mcb.setCompany_id(ub.getCompany_id());
			mcb.setGroup1_id(this.getDepartment());
			mcb.setGroup2_id(this.getGroup());
			mcb.setManual_classification_id(cells.get(0));
			mcb.setManual_classification_name(cells.get(1));
			mcb.setColor_category(cells.get(2));
			//mib---------------------------------
			ArrayList<manualinbean> mibs = new ArrayList<>();
			for(int s=0;s <5;s++){
				mib = new manualinbean();
				mib.setCompany_id(ub.getCompany_id());
				mib.setGroup1_id(this.getDepartment());
				mib.setGroup2_id(this.getGroup());
				mib.setManual_classification_name(mcb.getManual_classification_name());
				if(s==0){
					mib.setManual_id(cells.get(10));
					mib.setManual_name(cells.get(3));
					mib.setManual_content1(cells.get(4));
					mib.setManual_content2(cells.get(5));
				}else{
					mib.setManual_id(cells.get(s+10));
					mib.setManual_name(cells.get(s+5));
				}
				mibs.add(mib);
				System.out.println("point1\n" + mib.getManual_classification_name()+"\n"+mib.getManual_id()+"\n"+mib.getManual_name()+"\nout");
			}
			//checkメソッド
			this.checkData(mcb, mibs, i-1);
			//登録用リストに追加
			System.out.println("point2\n" + mcb.getColor_category()+"\nout");
			mcbs.add(mcb);
			mibss.add(mibs);
		}
	}
	
	private void checkData(manualclassbean mcb,ArrayList<manualinbean> mibs,int rownum){
		System.out.println("チェック開始");
		if(mcb.getManual_classification_id() == null || mcb.getManual_classification_id().trim().equals("")){
			errnum++;
			errmes.append(rownum + "行目の「マニュアル種別IDが」未入力です。\n");
		}
		if(mcb.getManual_classification_name() == null || mcb.getManual_classification_name().trim().equals("")){
			errnum++;
			errmes.append(rownum + "行目の「マニュアル種別名が」未入力です。\n");
		}
		if(mcb.getColor_category() == null || mcb.getColor_category().trim().equals("")){
			errnum++;
			errmes.append(rownum + "行目の「色区分」未入力です。\n");
		}else{
			try{
				double t = Double.valueOf(mcb.getColor_category());
				int tmp = (int) t;
				if(0 > tmp && 14 < tmp){
					errnum++;
					errmes.append(rownum + "行目の「色区分」は0~14のみ入力出来ます。\n");
				}mcb.setColor_category(String.valueOf(tmp));
			}catch(Exception e){
				errnum++;
				errmes.append(rownum + "行目の「色区分」が不正な値です。\n");
			}
		}
		int con=0;
		for(manualinbean mib:mibs){
			con++;
			switch (con){
			case 1:
				if(mib.getManual_name() == null || mib.getManual_name().trim().equals("")){
					errnum++;
					errmes.append(rownum + "行目の「マニュアル名」が未入力です。\n");
				}
				if(mib.getManual_id() == null || mib.getManual_id().trim().equals("")){
					errnum++;
					errmes.append(rownum + "行目の「マニュアルID」が未入力です。\n");
				}
				if(mib.getManual_content1() == null || mib.getManual_content1().trim().equals("")){
					errnum++;
					errmes.append(rownum + "行目の「マニュアル内容1」が未入力です。\n");
				}
				if(mib.getManual_content2() == null || mib.getManual_content2().trim().equals("")){
					errnum++;
					errmes.append(rownum + "行目の「マニュアル内容2」が未入力です。\n");
				}
				break;
			case 2:
				if(mib.getManual_name() == null || mib.getManual_name().trim().equals("")){
					errnum++;
					errmes.append(rownum + "行目の「階層1」が未入力です。\n");
				}
				if(mib.getManual_id() == null || mib.getManual_id().trim().equals("")){
					errnum++;
					errmes.append(rownum + "行目の階層1の「マニュアルID」が未入力です。\n");
				}
				break;
			default:
			}
		}
	}
	
	public void uniqueCheck(ArrayList<manualclassbean> mcbs){
		Set<String> checkHash = new HashSet<String>();
		for(int i=0;i<mcbs.size();i++){
			String str = mcbs.get(i).getManual_classification_id();
			if(checkHash.contains(str)){
				errnum++;
				errmes.append("マニュアル種別IDに重複があります。\n");
				return;
			}else{
				checkHash.add(str);
			}
		}
	}
	// get,set
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File getXlsfile() {
		return xlsfile;
	}

	public void setXlsfile(File xlsfile) {
		this.xlsfile = xlsfile;
	}

	public String getXlsfileContentType() {
		return xlsfileContentType;
	}

	public void setXlsfileContentType(String xlsfileContentType) {
		this.xlsfileContentType = xlsfileContentType;
	}

	public String getXlsfileFileName() {
		return xlsfileFileName;
	}

	public void setXlsfileFileName(String xlsfileFileName) {
		this.xlsfileFileName = xlsfileFileName;
	}

	public Map<String, Object> getSession() {
		return session;
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
