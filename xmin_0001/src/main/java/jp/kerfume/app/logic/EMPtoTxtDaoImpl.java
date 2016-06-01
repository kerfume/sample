package jp.kerfume.app.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.HibernateException;

import jp.kerfume.app.bean.InDataBean;
import jp.kerfume.app.interf.*;

/**
 * EMPレコードのTXTファイルへの永続化を管理するDAO
 * 
 * @author kei
 *
 */
public class EMPtoTxtDaoImpl implements MyDaoInterfaceEMP{
	
	String path;
	String file;
	BufferedWriter bw;
	private Logger logger;
	
	public EMPtoTxtDaoImpl(){
		logger = Logger.getLogger (EMPtoTxtDaoImpl.class.getName ());
		DOMConfigurator.configure("log4j_common.xml");
	}
	
	/**
	 * TXTファイルへの接続を確立するクラス
	 * @param -
	 * @return -
	 * 
	 */
	@Override
	public void conect() throws IOException {
		if(Files.exists(Paths.get(path +"\\"+ file))){
			bw = new BufferedWriter(new FileWriter(path +"\\"+ file,true));
		}else{
			logger.error("書き込み先ファイルが存在しません。");
			throw new IOException("class:EMPtoDbDaoImpl");
		}
		
		
	}
	
	/**
	 * TXTファイルから切断するクラス
	 * @param -
	 * @return -
	 * 
	 */
	@Override
	public void close() throws IOException {
		bw.close();
		bw = null;
	}

	@Override
	public ArrayList<InDataBean> select(Object... id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * EMPレコードをTXTファイルへの永続化を行うクラス
	 * @param EMPレコードのリスト
	 * @return 成功の場合はtrue,失敗の場合はfalse
	 * 
	 */
	@Override
	public boolean insert(ArrayList<InDataBean> beanList) throws IOException {
		try{
			for (InDataBean emp : beanList){
				bw.write("name:" + emp.getName());
				bw.newLine();
				bw.write("age:" + emp.getAge());
				bw.newLine();
				if(emp.getSex() == 0){
					bw.write("sex:male");
					bw.newLine();
				}else{
					bw.write("sex:female");
					bw.newLine();
				}
			}
		}catch(HibernateException e){
			logger.error(e);
			logger.error("テキストファイルへのデータの挿入に失敗しました。");
	    	return false;
	    }
		return true;
	}

	@Override
	public boolean delete(Object id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ArrayList<InDataBean> beanList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
}
