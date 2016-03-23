package kei.webapp.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

import kei.webapp.beans.udatabean;
import kei.webapp.beans.userbean;
import kei.webapp.module.DBconnector;
import kei.webapp.module.DateGetter;

@Results({ @Result(name = "sucsess", location = "/jsp/useredit.jsp"),
		@Result(name = "input", location = "/jsp/useredit.jsp"), @Result(name = "back", location = "/jsp/menu.jsp") })
public class usereditAction extends ActionSupport implements SessionAware {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
			.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	// DB取得用
	userbean ub;
	udatabean ud;
	udatabean udedit;
	DBconnector db = new DBconnector();
	// 制御用パラメータ
	boolean editflg = false;
	// form用リスト,map
	List<String> yearlist;
	List<String> monthlist;
	List<String> daylist;
	Map<String, String> dptlist;
	Map<String, String> grplist;
	// formメンバ
	private String name;
	private String password;
	private int sex;
	private String birthday_year;
	private String birthday_month;
	private String birthday_day;
	private String blood_type;
	private String enter_year;
	private String enter_month;
	private int authority;
	private String department;// group_id1
	private String group;// group_id2
	private String post;
	private String comment;
	private String business_type;
	private File image;
	private String imageContentType;
	private String imageFileName;
	private String userid;
	private String fimn;// 画像用ひでん

	public String getUserid() {
		return userid;
	}

	public String getFimn() {
		return fimn;
	}

	public void setFimn(String fimn) {
		this.fimn = fimn;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	// 画像ファイル保存用
	public String imn;
	public File imgFile;

	// list群の中身生成
	public List<String> getYearlist() {
		yearlist = new ArrayList<>();
		DateGetter dg = new DateGetter();
		for (int i = 1950; i <= Integer.parseInt(dg.getNowyear()); i++) {
			yearlist.add(String.valueOf(i));
		}

		return yearlist;
	}

	public List<String> getMonthlist() {
		monthlist = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			monthlist.add(String.valueOf(i));
		}

		return monthlist;
	}

	public List<String> getDaylist() {
		daylist = new ArrayList<>();
		for (int i = 1; i <= 31; i++) {
			daylist.add(String.valueOf(i));
		}

		return daylist;
	}

	public Map<String, String> getDptlist() {
		ub = (userbean) session.get("ub");
		dptlist = db.getDepartmentList(ub.getCompany_id(), 0);

		return dptlist;
	}

	public Map<String, String> getGrplist() {
		ub = (userbean) session.get("ub");
		if (editflg == true) {
			System.out.println("POINT1:" + udedit.getDepartment());
			dptlist = db.getGroupList(ub.getCompany_id(), this.getDepartment(), 1);
		} else {
			dptlist = db.getGroupList(ub.getCompany_id(), ub.getGroup1_id(), 1);
		}

		return dptlist;
	}

	@Action("useraction")
	@SkipValidation()
	public String execute() throws Exception {
		if (request.getParameter("userid") != null) {
			editflg = true;
			setUserid(request.getParameter("userid"));
			try {
				udedit = db.getOneuser(request.getParameter("userid"));
				// 画像以外一通りセット
				this.setName(udedit.getName());
				this.setPassword(udedit.getPassword());
				this.setSex(udedit.getSex());
				if (udedit.getBirthday() != null) {
					this.setBirthday_year(udedit.getBirthday().substring(0, 4));
					this.setBirthday_month(udedit.getBirthday().substring(4, 6));
					this.setBirthday_day(udedit.getBirthday().substring(6, 8));
				} else {// デフォルト値をセット
					this.setBirthday_year("1950");
					this.setBirthday_month("01");
					this.setBirthday_day("01");
				}
				this.setBlood_type(udedit.getBlood_type());
				if (udedit.getEnter_years() != null) {
					this.setEnter_year(udedit.getEnter_years().substring(0, 4));
					this.setEnter_month(udedit.getEnter_years().substring(4, 6));
				} else {// デフォルト値をセット
					this.setEnter_year("1950");
					this.setEnter_month("01");
				}
				this.setAuthority(udedit.getAuthority());
				this.setDepartment(udedit.getDepartment());
				this.setGroup(udedit.getGroup());
				this.setPost(udedit.getPost());
				this.setBusiness_type(udedit.getBusiness_type());
				this.setComment(udedit.getComment());
				if (udedit.getImn() != null && !udedit.getImn().equals(""))
					this.setFimn(udedit.getImn());
				else
					this.setFimn("");
			} catch (Exception e) {
				editflg = false;
				addFieldError("getuer", "ユーザーの取得に失敗しました。");
				setUserid("0000000000");
				formatForm();
			}
		} else {
			editflg = false;
			setUserid("0000000000");
			formatForm();
		}
		return "sucsess";
	}

	@SkipValidation()
	public String cancell() throws Exception {
		editflg = false;
		setUserid("0000000000");
		formatForm();

		return "sucsess";
	}

	public String back() throws Exception {
		// なにかクリーン処理あれば実装
		return "back";
	}

	public String edit() throws Exception {
		// System.out.println("point:edit");
		// boolean result = false;
		try {
			System.out.println("point:start");
			if (imageContentType != null && saveImage())
				System.out.println("アップロードに成功しました");
			else
				System.out.println("アップロードに失敗しました");
			System.out.println(name + "\n" + password + "\n" + sex + "\n" + birthday_year + "\n" + birthday_month + "\n"
					+ birthday_day + "\n" + blood_type + "\n" + enter_year + "\n" + enter_month + "\n" + authority
					+ "\n" + department + "\n" + group + "\n" + post + "\n" + business_type + "\n" + comment + "\n"
					+ imageFileName + "\n" + userid);
			ud = new udatabean();
			ud.setName(name);
			ud.setPassword(password);
			ud.setSex(sex);
			ud.setBirthday(birthday_year + "/" + birthday_month + "/" + birthday_day);
			ud.setBlood_type(blood_type);
			ud.setEnter_years(enter_year + "/" + enter_month + "/" + "00");
			// System.out.println(ud.getEnter_years());
			ud.setAuthority(authority);
			ud.setDepartment(department);
			ud.setGroup(group);
			ud.setPost(post);
			ud.setBusiness_type(business_type);
			ud.setComment(comment);
			ud.setCompany_id(((userbean) session.get("ub")).getCompany_id());
			if (imn != null)
				ud.setImn(imn);
			else if (fimn.equals(""))
				ud.setImn(null);
			else
				ud.setImn(fimn);

			if (userid.equals("0000000000")) {
				// System.out.println("point:newuser");
				// ud.setUserid(ud.getCompany_id() + String.format("%07d",
				// db.selectUsernum()));
				db.newUser(ud);
				db.autoInc();
				addActionMessage("ユーザーを新規登録しました。");
			} else {
				// System.out.println("point:update");
				boolean meflg = false;
				ud.setUserid(userid);
				if (ud.getUserid().equals(((userbean) session.get("ub")).getUser_id()))
					meflg = true;
				db.updateUser(ud);
				addActionMessage("ユーザー「" + ud.getUserid() + "」を更新しました。");
				if (imn != null)
					deleteImage(fimn);
				if (meflg == true) {
					userbean ub = db.login_user_saerch(ud.getUserid(), ud.getPassword());
					session.put("ub", ub);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			addFieldError("SQLERR", "登録/更新に失敗しました、管理者に連絡してください。");
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("SQLERR", "登録/更新に失敗しました、管理者に連絡してください。");
		}
		formatForm();
		return "sucsess";
	}

	@SkipValidation()
	public String delete() throws Exception {
		if (this.getUserid().equals("0000000000")) {
			formatForm();
		} else if (this.getUserid().equals(((userbean) session.get("ub")).getUser_id())) {
			addFieldError("DELETEERR", "自身で操作中のユーザーは削除できません。");
		} else {
			try {
				db.deleteUser(this.getUserid());
				addActionMessage("ユーザー「" + this.getUserid() + "」を削除しました。");
			} catch (Exception e) {
				addFieldError("SQLERR", "ユーザーの削除に失敗しました");
			}
		}
		formatForm();

		return "sucsess";

	}

	/*
	 * @SkipValidation() public String cangeuser() throws Exception {
	 * //System.out.println(hid_userid);
	 * 
	 * return "sucsess"; }
	 */

	// 処理用メソッド
	boolean saveImage() {
		SimpleDateFormat fom = new SimpleDateFormat("yyyyMMddHHmmss");
		boolean result = false;
		String itype = "jpg";
		if (!new File(request.getServletContext().getRealPath("/img")).exists()) {
			System.out.println("フォルダpathが存在しません！アップロードを中止します");
			result = false;
		} else {
			try {
				switch (imageContentType) {
				case "image/bmp":
					imn = fom.format(new Date()) + imageFileName.replace(".tmp", ".bmp");
					itype = "bmp";
					break;
				case "image/jpeg":
					imn = fom.format(new Date()) + imageFileName.replace(".tmp", ".jpg");
					itype = "jpg";
					break;
				case "image/png":
					imn = fom.format(new Date()) + imageFileName.replace(".tmp", ".png");
					itype = "png";
					break;
				}
				imgFile = new File(request.getServletContext().getRealPath("/img/" + imn));
				BufferedImage img = null;
				img = ImageIO.read(image);
				result = ImageIO.write(img, itype, imgFile);
				System.out.println("アップロードpath:" + imgFile.getAbsolutePath());
				while (!Files.exists(Paths.get(request.getServletContext().getRealPath("/img/" + imn)))) {

				}
				try {
					Thread.sleep(4000); // 4000ミリ秒Sleepする,画像反映用
				} catch (InterruptedException e) {
				}
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}
		}

		return result;
	}

	void deleteImage(String timn) {
		Path imgpath = Paths.get(request.getServletContext().getRealPath("/img/" + timn));
		if (Files.exists(imgpath)) {
			System.out.println("point:img");
			try {
				Files.delete(imgpath);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			System.out.println("ファイルが存在しません。");

	}

	void formatForm() {
		this.setUserid("0000000000");
		this.setName("");
		this.setPassword("");
		this.setSex(0);
		this.setBirthday_year("1950");
		this.setBirthday_month("1");
		this.setBirthday_day("1");
		this.setBlood_type("0");
		this.setEnter_year("1950");
		this.setEnter_month("1");
		this.setAuthority(0);
		this.setDepartment("01");
		this.setGroup("00");
		this.setPost("");
		this.setBusiness_type("0");
		this.setComment("");
		this.setFimn("");
		editflg = false;
	}

	@Override
	public void validate() {
		if (imageContentType != null) {
			switch (imageContentType) {
			case "image/bmp":
				break;
			case "image/jpeg":
				break;
			case "image/png":
				break;
			default:
				addFieldError("image", "サポートされていないファイルタイプです。");
			}
			if(image.length() >= 2097152)
				addFieldError("image", "画像は2MB以下のものを使用してください。");
		}

	}

	/**
	 * 
	 */

	// その他gettersetterとか
	private static final long serialVersionUID = 1L;

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	private Map<String, Object> session;

	// 以下getter,setter
	public String getName() {
		return name;
	}

	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "name", message = "名前を入力してください。") }, stringLengthFields = {
					@StringLengthFieldValidator(trim = true, maxLength = "40", fieldName = "name", message = "名前は40字以内にしてください。") })
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "password", message = "パスワードを入力してください。") }, stringLengthFields = {
					@StringLengthFieldValidator(trim = true, minLength = "4", maxLength = "8", fieldName = "password", message = "4桁以上、8桁以下のパスワードを入力してください。") })
	@RegexFieldValidator(trim = false, regex = "[0-9A-Za-z_]*", fieldName = "password", message = "パスワードには英数字のみを使用してください。")
	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getBirthday_year() {
		return birthday_year;
	}

	public void setBirthday_year(String birthday_year) {
		this.birthday_year = birthday_year;
	}

	public String getBirthday_month() {
		return birthday_month;
	}

	public void setBirthday_month(String birthday_month) {
		this.birthday_month = birthday_month;
	}

	public String getBirthday_day() {
		return birthday_day;
	}

	public void setBirthday_day(String birthday_day) {
		this.birthday_day = birthday_day;
	}

	public String getEnter_year() {
		return enter_year;
	}

	public void setEnter_year(String enter_year) {
		this.enter_year = enter_year;
	}

	public String getEnter_month() {
		return enter_month;
	}

	public void setEnter_month(String enter_month) {
		this.enter_month = enter_month;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
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

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	@Validations(stringLengthFields = {
			@StringLengthFieldValidator(maxLength = "400", fieldName = "コメント", message = "コメントは200字以内でお願いします。") })
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getBlood_type() {
		return blood_type;
	}

	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
	}

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
}
