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

	// err判定用
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
		this.setMessage("======エラー内容=========\n");
		return "sucsess";
	}

	public String insert() throws Exception {
		errnum = 0;
		errmes = new StringBuilder("======エラー内容=========\n");

		System.out.println(this.getDepartment() + "," + this.getGroup() + "\n" + this.getXlsfileFileName() + ":"
				+ this.getXlsfileContentType());
		if (this.fileDecision(xlsfileFileName)) {
			// System.out.println("ok");
			ArrayList<String> cells = new ArrayList<>();

			try (FileInputStream fi = new FileInputStream(xlsfile); XSSFWorkbook book = new XSSFWorkbook(fi)) {
				Sheet sheet = book.getSheetAt(0); // 先頭シートのみ取得
				Row row = sheet.getRow(2);
				for (int i = 0; i < 9; i++) { // 3行目のA~I列

					Cell cell = row.getCell(i);
					cells.add(String.valueOf(cell));
					System.out.println(String.valueOf(cell) + " ");
				}

			}
			// 登録用beanセットメソッド+データチェック
			this.makeInData(cells);
		} // else System.out.println("no");
		if (errnum == 0) {
			// ID採番メソッド
			this.idMaker(mcb, mib);
			// DB登録
			if(mcb.getUpdate_count() == 0)
				db.manualInsert(mcb, mib);
			else
				db.manualUpdate(mcb, mib);
		}
		this.setMessage(errmes.toString());
		
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

	private void makeInData(List<String> cells) {
		ub = (userbean) session.get("ub");
		mcb = new manualclassbean();
		mcb.setCompany_id(ub.getCompany_id());
		mcb.setGroup1_id(this.getDepartment());
		mcb.setGroup2_id(this.getGroup());
		// mcb.setManual_classification_id("");// 後で追加
		mcb.setManual_classification_name(cells.get(0));
		mcb.setColor_category(cells.get(1));
		// mib---------------------------------
		mib = new manualinbean();
		mib.setCompany_id(ub.getCompany_id());
		mib.setGroup1_id(this.getDepartment());
		mib.setGroup2_id(this.getGroup());
		// mib.setManual_id("");//後で
		mib.setManual_name(cells.get(2));
		mib.setManual_content1(cells.get(3));
		mib.setManual_content2(cells.get(4));
		mib.setDir_name(cells.get(5), 0);
		mib.setDir_name(cells.get(6), 1);
		mib.setDir_name(cells.get(7), 2);
		mib.setDir_name(cells.get(8), 3);
		// checkメソッド
		this.checkData(mcb, mib);
	}

	private void checkData(manualclassbean mcb, manualinbean mib) {
		if (nullCheck(mcb.getManual_classification_name())) {
			errnum++;
			errmes.append("「マニュアル種別名」が未入力です。\n");
		}
		if (nullCheck(mcb.getColor_category())) {
			errnum++;
			errmes.append("「色区分」が未入力です。\n");
		} else {
			try {
				double t = Double.valueOf(mcb.getColor_category());
				int tmp = (int) t;
				if (0 > tmp && 14 < tmp) {
					errnum++;
					errmes.append("「色区分」は0~14のみ入力出来ます。\n");
				}
				mcb.setColor_category(String.valueOf(tmp));
			} catch (Exception e) {
				errnum++;
				errmes.append("「色区分」が不正な値です。\n");
			}
		}
		if (nullCheck(mib.getManual_name())) {
			errnum++;
			errmes.append("「マニュアル名」が未入力です。\n");
		}
		if (nullCheck(mib.getManual_content1())) {
			errnum++;
			errmes.append("「マニュアル内容1」が未入力です。\n");
		}
		if (nullCheck(mib.getManual_content2())) {
			errnum++;
			errmes.append("「マニュアル内容2」が未入力です。\n");
		}
		if (nullCheck(mib.getDir_name(0))) {
			errnum++;
			errmes.append("「階層1」が未入力です。\n");
		}
		// 階層整合性チェック
		int cnt = 1;
		for (String s : mib.getDir_name_full()) {
			// 階層1はスキップ
			if (s.equals(mib.getDir_name(0))) {
				continue;
			}

			cnt++;
			for (int i = cnt; i < mib.getDir_id_full().length; i++) {
				if (nullCheck(s) && !nullCheck(mib.getDir_name(i))) {
					errnum++;
					errmes.append("階層データが不正です。\n");
				}
			}
		}

	}

	// ID採番
	private void idMaker(manualclassbean mcb, manualinbean mib) {
		/**
		 * id作成用ローカルクラス
		 * @author kei
		 *
		 */
		class idm{
			void updateManu(){
				//更新回数をセット
				mcb.setUpdate_count(db.manualUpdcnt(mcb) + 1);
				mib.setUpdate_count(mcb.getUpdate_count());
				setIds();
				/*int cnt = 1;
				String str = mcb.getManual_classification_id();
				mib.setDir_id(str + "0001", 0);
				for(int i =1;i < 4;i++){
					if(!nullCheck(mib.getDir_name(i))){
						mib.setDir_id(str + "000"+(i+1), i);
						cnt++;
					}
				}
				switch(cnt){
				case 1:
					mib.setManual_id(str + "0001");	
					break;
				case 2:
					mib.setManual_id(str + "0001002");	
					break;
				case 3:
					mib.setManual_id(str + "0001002003");	
					break;
				case 4:
					mib.setManual_id(str + "0001002003004");	
					break;
				}*/
			}
			void insertManu(){
				mcb.setManual_classification_id(
						String.valueOf(Integer.parseInt(db.getManualclassid(mcb))+1)
						);
				setIds();
				//書く------------------
			}
			void setIds(){
				int cnt = 1;
				String str = mcb.getManual_classification_id();
				mib.setDir_id(str + "0001", 0);
				for(int i =1;i < 4;i++){
					if(!nullCheck(mib.getDir_name(i))){
						mib.setDir_id(str + "000"+(i+1), i);
						cnt++;
					}
				}
				switch(cnt){
				case 1:
					mib.setManual_id(str + "0001");	
					break;
				case 2:
					mib.setManual_id(str + "0001002");	
					break;
				case 3:
					mib.setManual_id(str + "0001002003");	
					break;
				case 4:
					mib.setManual_id(str + "0001002003004");	
					break;
				}
			}
		}
		
		try {
			int resnum =db.manualSelect(mcb);
			if (resnum > 0) {
				//マニュアルデータが既に登録されていた場合
				idm ins = new idm();
				switch(resnum){
				case 1:
					ins.insertManu();
					break;
				case 2:
					ins.updateManu();
					break;
				}
			}else{
				//マニュアルデータが既に登録されていなかった場合
				mcb.setManual_classification_id("01");
				int cnt = 1;
				mib.setDir_id("010001", 0);
				for(int i =1;i < 4;i++){
					if(!nullCheck(mib.getDir_name(i))){
						mib.setDir_id("01000"+(i+1), i);
						cnt++;
					}
				}
				switch(cnt){
				case 1:
					mib.setManual_id("010001");	
					break;
				case 2:
					mib.setManual_id("010001002");	
					break;
				case 3:
					mib.setManual_id("010001002003");	
					break;
				case 4:
					mib.setManual_id("010001002003004");	
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errnum++;
			errmes.append("DBエラーが発生しました。\n");
		}

	}

	private boolean nullCheck(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else
			return false;
	}

	// 旧チェックメソッド
	/*
	 * private void checkData(manualclassbean mcb, ArrayList<manualinbean> mibs,
	 * int rownum) { System.out.println("チェック開始"); if
	 * (mcb.getManual_classification_id() == null ||
	 * mcb.getManual_classification_id().trim().equals("")) { errnum++;
	 * errmes.append(rownum + "行目の「マニュアル種別IDが」未入力です。\n"); } if
	 * (mcb.getManual_classification_name() == null ||
	 * mcb.getManual_classification_name().trim().equals("")) { errnum++;
	 * errmes.append(rownum + "行目の「マニュアル種別名が」未入力です。\n"); } if
	 * (mcb.getColor_category() == null ||
	 * mcb.getColor_category().trim().equals("")) { errnum++;
	 * errmes.append(rownum + "行目の「色区分」未入力です。\n"); } else { try { double t =
	 * Double.valueOf(mcb.getColor_category()); int tmp = (int) t; if (0 > tmp
	 * && 14 < tmp) { errnum++; errmes.append(rownum +
	 * "行目の「色区分」は0~14のみ入力出来ます。\n"); }
	 * mcb.setColor_category(String.valueOf(tmp)); } catch (Exception e) {
	 * errnum++; errmes.append(rownum + "行目の「色区分」が不正な値です。\n"); } } int con = 0;
	 * for (manualinbean mib : mibs) { con++; switch (con) { case 1: if
	 * (mib.getManual_name() == null || mib.getManual_name().trim().equals(""))
	 * { errnum++; errmes.append(rownum + "行目の「マニュアル名」が未入力です。\n"); } if
	 * (mib.getManual_id() == null || mib.getManual_id().trim().equals("")) {
	 * errnum++; errmes.append(rownum + "行目の「マニュアルID」が未入力です。\n"); } if
	 * (mib.getManual_content1() == null ||
	 * mib.getManual_content1().trim().equals("")) { errnum++;
	 * errmes.append(rownum + "行目の「マニュアル内容1」が未入力です。\n"); } if
	 * (mib.getManual_content2() == null ||
	 * mib.getManual_content2().trim().equals("")) { errnum++;
	 * errmes.append(rownum + "行目の「マニュアル内容2」が未入力です。\n"); } break; case 2: if
	 * (mib.getManual_name() == null || mib.getManual_name().trim().equals(""))
	 * { errnum++; errmes.append(rownum + "行目の「階層1」が未入力です。\n"); } if
	 * (mib.getManual_id() == null || mib.getManual_id().trim().equals("")) {
	 * errnum++; errmes.append(rownum + "行目の階層1の「マニュアルID」が未入力です。\n"); } break;
	 * default: } } }
	 */

	/*
	 * public void uniqueCheck(ArrayList<manualclassbean> mcbs) { Set<String>
	 * checkHash = new HashSet<String>(); for (int i = 0; i < mcbs.size(); i++)
	 * { String str = mcbs.get(i).getManual_classification_id(); if
	 * (checkHash.contains(str)) { errnum++;
	 * errmes.append("マニュアル種別IDに重複があります。\n"); return; } else {
	 * checkHash.add(str); } } }
	 */

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
	
	/**
	 * ヴァリテーションチェック
	 */
	@Override
	public void validate() {
		if(xlsfile == null )
			addFieldError("files", "ファイルを選択してください。");
		else if(!this.fileDecision(xlsfileFileName))
				addFieldError("files", "サポートされていないファイルタイプです。");
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
