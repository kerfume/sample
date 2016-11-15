package kei.webapp.module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import kei.webapp.beans.manualclassbean;
import kei.webapp.beans.manualcontentbean;
import kei.webapp.beans.manualinbean;
import kei.webapp.beans.udatabean;
import kei.webapp.beans.userbean;

public class DBconnector {
	static Connection conn = null;
	/*
	 * public static void main(String[] srgs){
	 * 
	 * System.out.println(login_user_saerch("0010000001","admin").getUser_name()
	 * ); }
	 */
	int unum;

	public userbean login_user_saerch(String user_id, String password) {
		userbean ub = new userbean();
		try {
			Conect();

			String sql = "select user_id,user_name,company_id,company_name,user_authority,group1_id FROM tb_mst_user inner JOIN tb_mst_company using(company_id) WHERE user_id = ? AND password = ? AND tb_mst_user.delete_flag = 0;";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ub.setUser_id(rs.getString("user_id"));
				ub.setUser_name(rs.getString("user_name"));
				ub.setCompany_id(rs.getString("company_id"));
				ub.setCompany_name(rs.getString("company_name"));
				ub.setUser_authority(rs.getString("user_authority"));
				ub.setGroup1_id(rs.getString("group1_id"));
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ub;

	}

	public void updateLogin(String user_id) throws Exception {
		Conect();

		String sql = "UPDATE tb_mst_user SET login_time = NOW() WHERE user_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);
		pstmt.executeUpdate();
		if (conn != null) {
			conn.close();
		}

	}

	public udatabean getOneuser(String user_id) throws Exception {
		udatabean ud = new udatabean();
		// select文変える
		Conect();

		String sql = "select user_name,password,sex,DATE_FORMAT(birth_date,'%Y%m%d') as birth_date,blood_group,DATE_FORMAT(company_entrance_years,'%Y%m%d') as company_entrance_years"
				+ ",user_authority" + ",group1_id,group2_id,position,user_classification,comment,picture"
				+ " FROM tb_mst_user WHERE user_id = ? AND delete_flag = 0;";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			ud.setName(rs.getString("user_name"));
			ud.setPassword(rs.getString("password"));
			ud.setSex(rs.getInt("sex"));
			ud.setBirthday(rs.getString("birth_date"));
			ud.setBlood_type(rs.getString("blood_group"));
			ud.setEnter_years(rs.getString("company_entrance_years"));
			ud.setAuthority(rs.getInt("user_authority"));
			ud.setDepartment(rs.getString("group1_id"));
			ud.setGroup(rs.getString("group2_id"));
			ud.setPost(rs.getString("position"));
			ud.setBusiness_type(rs.getString("user_classification"));
			ud.setComment(rs.getString("comment"));
			ud.setImn(rs.getString("picture"));
		} else
			throw new SQLException();

		if (conn != null) {
			conn.close();
		}

		return ud;
	}

	public List<udatabean> getUsers(String company_id) {
		List<udatabean> ubL = new ArrayList<>();
		try {
			Conect();

			String sql = "SELECT user_id,group1_name,position,user_name,picture,u.update_time FROM tb_mst_user AS u"
					+ " JOIN tb_mst_group AS g ON u.company_id = g.company_id AND u.group1_id = g.group1_id AND u.group2_id = g.group2_id"
					+ " WHERE u.company_id = ? AND u.delete_flag = 0 ORDER BY user_id;";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 以下編集
			pstmt.setString(1, company_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				udatabean ud = new udatabean();
				ud.setUserid(rs.getString("user_id"));
				ud.setName(rs.getString("user_name"));
				ud.setGroup(rs.getString("group1_name"));
				ud.setPost(rs.getString("position"));
				ud.setImn(rs.getString("picture"));
				ud.setUpdate_time(rs.getString("update_time"));
				ubL.add(ud);
				// System.out.println(ud.getName());
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ubL;

	}

	public HashMap<String, String> getDepartmentList(String company_id, int gettype) {// gettype
																						// =
																						// 0:Mapの先頭に空行を含まない
																						// ,
																						// 1:Mapの先頭に空行を含む（selectリスト用)
		HashMap<String, String> dptlist = new HashMap<>();
		if (gettype == 1)
			dptlist.put("", "");
		try {
			Conect();
			String sql = "SELECT group1_id,group1_name FROM tb_mst_group WHERE company_id = ? AND delete_flag = 0 ORDER BY group1_id;";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dptlist.put(rs.getString("group1_id"), rs.getString("group1_name"));
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dptlist;
	}

	public HashMap<String, String> getGroupList(String company_id, String group_id, int gettype) {// gettype
																									// =
																									// 0:Mapの先頭に空行を含まない
																									// ,
																									// 1:Mapの先頭に空行を含む（selectリスト用)
		HashMap<String, String> grplist = new HashMap<>();
		if (gettype == 1)
			grplist.put("00", "");
		try {
			Conect();
			String sql = "SELECT group2_id,group2_name FROM tb_mst_group WHERE company_id = ? AND group1_id = ? AND delete_flag = 0 AND NOT group2_id = 0 ORDER BY group1_id;";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company_id);
			pstmt.setString(2, group_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				grplist.put(rs.getString("group2_id"), rs.getString("group2_name"));
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return grplist;
	}

	public HashMap<String, String> getManuList(String company_id, String dpt_id, String grp_id, int gettype) {
		HashMap<String, String> manulist = new HashMap<>();
		if (gettype == 1)
			manulist.put("00", "");
		try {
			Conect();
			String sql = "SELECT DISTINCT manual_classification_id ,manual_classification_name FROM mma.tb_trn_manual_classification where company_id = ? and group1_id = ? and group2_id = ? and delete_flag = 0;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company_id);
			pstmt.setString(2, dpt_id);
			pstmt.setString(3, grp_id);
			ResultSet rs = pstmt.executeQuery();
			boolean notrecode = true;
			while (rs.next()) {
				notrecode = false;
				manulist.put(rs.getString(1), rs.getString(2));
			}
			if (notrecode)
				manulist.put("00", "該当マニュアルなし");
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return manulist;
	}

	public manualcontentbean getManuBean(String company_id, String dept_id, String grp_id, String manu_id, int updcnt) {
		//メンバ宣言
		final manualcontentbean mb = new manualcontentbean();
		
		class LocalUtil{
			public boolean checkAndSet(ResultSet rs) throws SQLException{
				if(rs.getString("manual_id").length() > 6){
					mb.setManual_content1(rs.getString("manual_content1"));
					mb.setManual_content2(rs.getString("manual_content2"));
					
					return true;
				}
				return false;
			}
		}
		
		//logic
		try {
			Conect();

			String sql = "SELECT * FROM tb_trn_manual_details "
					+ "where delete_flag = 0 and MID(manual_id,1,2) = ? and company_id = ? and group1_id = ? and group2_id = ? and update_count = ? order by LENGTH(manual_id),manual_id;";

			mb.setManual_ID(company_id + dept_id + grp_id + manu_id);// 企業id+部署id+グループid+マニュアル種別id
			mb.setUpdcnt(updcnt);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, manu_id);
			pstmt.setString(2, company_id);
			pstmt.setString(3, dept_id);
			pstmt.setString(4, grp_id);
			pstmt.setInt(5, updcnt);
			ResultSet rs = pstmt.executeQuery();
			int cnt = 0;
			LocalUtil lu = new LocalUtil();
			while (rs.next()) {
				switch (++cnt) {
				case 1:
					if(lu.checkAndSet(rs))
						mb.setKaisou4(rs.getString("manual_name"));
					else mb.setKaisou1(rs.getString("manual_name"));
					
					break;
				case 2:
					if(lu.checkAndSet(rs))
						mb.setKaisou4(rs.getString("manual_name"));
					else mb.setKaisou2(rs.getString("manual_name"));
					
					
					break;
				case 3:
					if(lu.checkAndSet(rs))
						mb.setKaisou4(rs.getString("manual_name"));
					else mb.setKaisou3(rs.getString("manual_name"));
					
					break;
				case 4:
					lu.checkAndSet(rs);
					mb.setKaisou4(rs.getString("manual_name"));
					
					break;
				}
			}
			
		} catch (

		SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
			if (conn != null) {
				conn.close();
			}			
			}catch(SQLException e){}
		}
		
		return mb;
	}

	public List<manualcontentbean> getManuBeanList(String company_id, String dept_id, String grp_id, String manu_id) {
		List<manualcontentbean> mbL = new ArrayList<>();
		List<Integer> updcntList = new ArrayList<>();

		try {
			Conect();
			String sql = "SELECT DISTINCT UPDATE_COUNT FROM tb_trn_manual_classification where delete_flag = 0 and manual_classification_id =? and company_id=? and group1_id = ? and group2_id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, manu_id);
			pstmt.setString(2, company_id);
			pstmt.setString(3, dept_id);
			pstmt.setString(4, grp_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				updcntList.add(rs.getInt(1));
			}

			sql = "SELECT manual_name FROM tb_trn_manual_details "
					+ "where delete_flag = 0 and MID(manual_id,1,2) = ? and company_id = ? and group1_id = ? and group2_id = ? and update_count = ? order by LENGTH(manual_id),manual_id;";

			for (int updcnt : updcntList) {
				manualcontentbean mb = new manualcontentbean();
				mb.setManual_ID(company_id + dept_id + grp_id + manu_id);// 企業id+部署id+グループid+マニュアル種別id
				mb.setUpdcnt(updcnt);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, manu_id);
				pstmt.setString(2, company_id);
				pstmt.setString(3, dept_id);
				pstmt.setString(4, grp_id);
				pstmt.setInt(5, updcnt);
				rs = pstmt.executeQuery();
				int cnt = 0;
				while (rs.next()) {
					switch (++cnt) {
					case 1:
						mb.setKaisou1(rs.getString(1));
						break;
					case 2:
						mb.setKaisou2(rs.getString(1));
						break;
					case 3:
						mb.setKaisou3(rs.getString(1));
						break;
					case 4:
						mb.setKaisou4(rs.getString(1));
						break;
					}
				}
				while (cnt < 4) {
					switch (++cnt) {
					case 1:
						mb.setKaisou1("");
						break;
					case 2:
						mb.setKaisou2("");
						break;
					case 3:
						mb.setKaisou3("");
						break;
					case 4:
						mb.setKaisou4("");
						break;
					}
				}
				mbL.add(mb);
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mbL;
	}

	public void newUser(udatabean ud) throws Exception {
		// 登録処理書く

		Conect();
		unum = 0;
		String sql = "SELECT auto_inc_user FROM auto_inc;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			unum = rs.getInt("auto_inc_user");
		} else
			throw new SQLException();

		sql = "INSERT INTO tb_mst_user"
				+ " VALUES(?,?,?,?,?,?,?,?,?,STR_TO_DATE(?,'%Y/%m/%d'),STR_TO_DATE(?, '%Y/%m/%d'),?,?,?,?,NOW(),NOW(),NOW(),0);";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ud.getCompany_id());
		pstmt.setString(2, ud.getDepartment());
		pstmt.setString(3, ud.getGroup());
		pstmt.setString(4, ud.getCompany_id() + String.format("%07d", unum));
		pstmt.setString(5, ud.getPassword());
		pstmt.setString(6, ud.getPost());
		pstmt.setString(7, ud.getName());
		pstmt.setInt(8, ud.getSex());
		pstmt.setString(9, ud.getBlood_type());
		pstmt.setString(10, ud.getBirthday());
		pstmt.setString(11, ud.getEnter_years());
		pstmt.setString(12, ud.getImn());
		pstmt.setString(13, ud.getComment());
		pstmt.setString(14, ud.getBusiness_type());
		pstmt.setInt(15, ud.getAuthority());

		int rnum = pstmt.executeUpdate();
		System.out.println("更新:" + rnum);

		if (conn != null) {
			conn.close();
		}
	}

	public void updateUser(udatabean ud) throws Exception {
		Conect();

		String sql = "UPDATE tb_mst_user SET group1_id = ?,group2_id = ?,password = ?,position = ?,user_name = ?,sex = ?,blood_group =?"
				+ ",birth_date = ?,company_entrance_years = ?,picture = ?,comment =?,user_classification = ?,user_authority = ?, update_time = ? ,picture = ?"
				+ " WHERE user_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// pstmt.setString(1,ud.getCompany_id());
		pstmt.setString(1, ud.getDepartment());
		pstmt.setString(2, ud.getGroup());
		// pstmt.setString(4,ud.getCompany_id() + String.format("%07d",unum));
		pstmt.setString(3, ud.getPassword());
		pstmt.setString(4, ud.getPost());
		pstmt.setString(5, ud.getName());
		pstmt.setInt(6, ud.getSex());
		pstmt.setString(7, ud.getBlood_type());
		pstmt.setString(8, ud.getBirthday());
		pstmt.setString(9, ud.getEnter_years());
		pstmt.setString(10, ud.getImn());
		pstmt.setString(11, ud.getComment());
		pstmt.setString(12, ud.getBusiness_type());
		pstmt.setInt(13, ud.getAuthority());
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		pstmt.setString(14, sdf.format(c.getTime()));
		pstmt.setString(15, ud.getImn());
		pstmt.setString(16, ud.getUserid());

		int rnum = pstmt.executeUpdate();
		System.out.println("更新:" + rnum);

		if (conn != null) {
			conn.close();
		}
	}

	// 基本 newUser メソッドとセット
	public void autoInc() throws Exception {
		Conect();

		String sql = "UPDATE auto_inc SET auto_inc_user = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, unum + 1);
		int rnum = pstmt.executeUpdate();
		System.out.println("更新:" + rnum);
		unum = 0;
		if (conn != null) {
			conn.close();
		}
	}

	public int selectUsernum() throws Exception {
		int resnum = 0;
		Conect();
		String sql = "SELECT COUNT(*) FROM tb_mst_user;";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			resnum = rs.getInt(1);
		}
		if (conn != null) {
			conn.close();
		}
		return resnum;
	}

	public void deleteUser(String user_id) throws Exception {
		Conect();

		String sql = "UPDATE tb_mst_user SET delete_flag = 1 WHERE user_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);
		pstmt.executeUpdate();
		if (conn != null) {
			conn.close();
		}

	}

	/**
	 * 
	 * @param mcb
	 * @return 0:該当なし,1:同一企業グループあり,2:同一マニュアルもあり
	 * @throws Exception
	 */
	public int manualSelect(manualclassbean mcb) throws Exception {
		int resnum = 0;
		Conect();
		String sql = "SELECT COUNT(*) FROM tb_trn_manual_classification WHERE company_id = ? AND group1_id = ? AND group2_id = ?;";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mcb.getCompany_id());
		pstmt.setString(2, mcb.getGroup1_id());
		pstmt.setString(3, mcb.getGroup2_id());

		ResultSet rs = pstmt.executeQuery();
		rs.next();
		if (rs.getInt(1) > 0)
			resnum++;

		sql = "SELECT COUNT(*) FROM tb_trn_manual_classification WHERE company_id = ? AND group1_id = ? AND group2_id = ? AND manual_classification_name = ?;";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mcb.getCompany_id());
		pstmt.setString(2, mcb.getGroup1_id());
		pstmt.setString(3, mcb.getGroup2_id());
		pstmt.setString(4, mcb.getManual_classification_name());

		rs = pstmt.executeQuery();
		rs.next();
		if (rs.getInt(1) > 0)
			resnum++;

		if (conn != null) {
			conn.close();
		}
		return resnum;
	}

	public int manualUpdcnt(manualclassbean mcb) {
		int resnum = 0;
		try {
			Conect();
			String sql = "SELECT update_count,manual_classification_id FROM tb_trn_manual_classification WHERE company_id = ? AND group1_id = ? AND group2_id = ? AND manual_classification_name = ? ORDER BY update_count DESC LIMIT 1;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mcb.getCompany_id());
			pstmt.setString(2, mcb.getGroup1_id());
			pstmt.setString(3, mcb.getGroup2_id());
			pstmt.setString(4, mcb.getManual_classification_name());

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			resnum = rs.getInt(1);
			mcb.setManual_classification_id(rs.getString(2));

			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resnum;
	}

	/**
	 * 02以降のマニュアル種別IDの取得
	 * 
	 * @param mcb
	 * @return 現在採番されている最大のマニュアル種別ID
	 */
	public String getManualclassid(manualclassbean mcb) {
		String resstr = null;
		try {
			Conect();
			String sql = "SELECT DISTINCT manual_classification_id FROM tb_trn_manual_classification"
					+ " WHERE company_id = ? AND group1_id = ? AND group2_id = ? ORDER BY manual_classification_id desc LIMIT 1;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mcb.getCompany_id());
			pstmt.setString(2, mcb.getGroup1_id());
			pstmt.setString(3, mcb.getGroup2_id());

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			resstr = rs.getString(1);
			mcb.setManual_classification_id(rs.getString(1));

			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resstr;
	}

	// 新規用
	public void manualInsert(manualclassbean mcb, manualinbean mib) {
		try {
			Conect();
			conn.setAutoCommit(false);
			// 登録処理
			PreparedStatement pstmt;
			String sql = "INSERT INTO tb_trn_manual_classification" + " VALUES(?,?,?,?,?,?,?,NOW(),NOW(),0);";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mcb.getCompany_id());
			pstmt.setString(2, mcb.getGroup1_id());
			pstmt.setString(3, mcb.getGroup2_id());
			pstmt.setString(4, mcb.getManual_classification_id());
			pstmt.setInt(5, 0);
			pstmt.setString(6, mcb.getManual_classification_name());
			pstmt.setString(7, mcb.getColor_category());

			int rnum = pstmt.executeUpdate();
			System.out.println("更新:" + rnum);

			// mibの登録
			sql = "INSERT INTO tb_trn_manual_details VALUES(?,?,?,?,0,?,?,?,null,NOW(),NOW(),0);";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mib.getCompany_id());
			pstmt.setString(2, mib.getGroup1_id());
			pstmt.setString(3, mib.getGroup2_id());
			pstmt.setString(4, mib.getManual_id());
			pstmt.setString(5, mib.getManual_name());
			pstmt.setString(6, mib.getManual_content1());
			pstmt.setString(7, mib.getManual_content2());

			rnum = pstmt.executeUpdate();
			System.out.println("更新:" + rnum);
			// mib(各階層の登録)
			for (int i = 0; i < 4; i++) {
				if (mib.getDir_name(i) != null && mib.getDir_id(i) != null) {
					sql = "INSERT INTO tb_trn_manual_details VALUES(?,?,?,?,0,?,null,null,null,NOW(),NOW(),0);";

					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, mib.getCompany_id());
					pstmt.setString(2, mib.getGroup1_id());
					pstmt.setString(3, mib.getGroup2_id());
					pstmt.setString(4, mib.getDir_id(i));
					pstmt.setString(5, mib.getDir_name(i));

					rnum = pstmt.executeUpdate();
					System.out.println("更新:" + rnum);
				}
			}

			conn.commit();
		} catch (Exception e) {
			try {
				// ロールバック …… 【3】
				e.printStackTrace();
				conn.rollback();
			} catch (SQLException se) {
				/* ログ出力 */
				e.printStackTrace();
			}
		} finally

		{
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				/* ログ出力 */
				e.printStackTrace();
			}
		}

	}

	// 更新用
	public void manualUpdate(manualclassbean mcb, manualinbean mib) {
		try {
			Conect();
			conn.setAutoCommit(false);
			PreparedStatement pstmt;

			boolean delflg = false;

			String sql = "SELECT COUNT(manual_name) FROM tb_trn_manual_details WHERE manual_name = ?;";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mib.getManual_name());

			ResultSet rs = pstmt.executeQuery();
			rs.next();

			if (rs.getInt(1) > 0) {
				// 同盟のマニュアルが一件以上ヒットした場合
				sql = "UPDATE tb_trn_manual_details a,(SELECT MAX(update_count) tar FROM tb_trn_manual_details WHERE manual_name = ? ) b SET delete_flag = 1 WHERE a.update_count = b.tar;";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, mib.getManual_name());

				pstmt.executeUpdate();

				// マニュアル種別用
				sql = "UPDATE tb_trn_manual_classification a,(SELECT MAX(update_count) tar FROM tb_trn_manual_details WHERE manual_name = ? ) b SET delete_flag = 1 WHERE a.update_count = b.tar;";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, mib.getManual_name());

				pstmt.executeUpdate();
			}

			// 登録処理

			sql = "SELECT regist_time FROM tb_trn_manual_classification WHERE company_id = ? AND group1_id = ? AND group2_id = ? AND manual_classification_name = ? ORDER BY update_count DESC LIMIT 1;";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mcb.getCompany_id());
			pstmt.setString(2, mcb.getGroup1_id());
			pstmt.setString(3, mcb.getGroup2_id());
			pstmt.setString(4, mcb.getManual_classification_name());

			rs = pstmt.executeQuery();
			rs.next();

			sql = "INSERT INTO tb_trn_manual_classification" + " VALUES(?,?,?,?,?,?,?,NOW(),?,0);";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mcb.getCompany_id());
			pstmt.setString(2, mcb.getGroup1_id());
			pstmt.setString(3, mcb.getGroup2_id());
			pstmt.setString(4, mcb.getManual_classification_id());
			pstmt.setInt(5, mcb.getUpdate_count());
			pstmt.setString(6, mcb.getManual_classification_name());
			pstmt.setString(7, mcb.getColor_category());
			pstmt.setTimestamp(8, rs.getTimestamp(1));

			int rnum = pstmt.executeUpdate();
			System.out.println("更新:" + rnum);

			// mibの登録
			sql = "INSERT INTO tb_trn_manual_details VALUES(?,?,?,?,?,?,?,?,null,NOW(),?,0);";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mib.getCompany_id());
			pstmt.setString(2, mib.getGroup1_id());
			pstmt.setString(3, mib.getGroup2_id());
			pstmt.setString(4, mib.getManual_id());
			pstmt.setInt(5, mib.getUpdate_count());
			pstmt.setString(6, mib.getManual_name());
			pstmt.setString(7, mib.getManual_content1());
			pstmt.setString(8, mib.getManual_content2());
			pstmt.setTimestamp(9, rs.getTimestamp(1));

			rnum = pstmt.executeUpdate();
			System.out.println("更新:" + rnum);
			// mib(各階層の登録)
			for (int i = 0; i < 4; i++) {
				if (mib.getDir_name(i) != null && mib.getDir_id(i) != null) {
					sql = "INSERT INTO tb_trn_manual_details VALUES(?,?,?,?,?,?,null,null,null,NOW(),?,0);";

					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, mib.getCompany_id());
					pstmt.setString(2, mib.getGroup1_id());
					pstmt.setString(3, mib.getGroup2_id());
					pstmt.setString(4, mib.getDir_id(i));
					pstmt.setInt(5, mib.getUpdate_count());
					pstmt.setString(6, mib.getDir_name(i));
					pstmt.setTimestamp(7, rs.getTimestamp(1));

					rnum = pstmt.executeUpdate();
					System.out.println("更新:" + rnum);
				}
			}

			conn.commit();
		} catch (Exception e) {
			try {
				// ロールバック …… 【3】
				e.printStackTrace();
				conn.rollback();
			} catch (SQLException se) {
				/* ログ出力 */
				e.printStackTrace();
			}
		} finally

		{
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				/* ログ出力 */
				e.printStackTrace();
			}
		}
	}

	// コネクション確立用メソッド
	private static void Conect() throws Exception {
		conn = null;

		String url = "jdbc:mysql://localhost/mma";
		String user = "mmauser";
		String password = "mmapass";

		// 接続の確立
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(url, user, password);

	}
}
