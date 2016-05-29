package jp.kerfume.app.logic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import jp.kerfume.app.interf.*;


public class MyDaoFactoryImpl implements MyDaoFactory{

	private String daoType = null;
	private MyDaoInterfaceEMP empDaoDb;
	private MyDaoInterfaceEMP empDaoTxt;
	private Logger logger;
	
	public MyDaoFactoryImpl(){
		logger = Logger.getLogger (App.class.getName ());
		DOMConfigurator.configure("log4j_common.xml");
	}
	
	/**
	 * 設定ファイルの値を参照し、永続化用DAOを生成する
	 * @return MyDaoInterfaceEMP,設定ファイルが異常値の場合nullを返す
	 * 
	 */
	@Override
	public MyDaoInterfaceEMP getDAOEMP() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("MyDaoFactoryImplTest.xml");
		
		empDaoDb = (MyDaoInterfaceEMP)context.getBean("EMPtoDb");
		empDaoTxt = (MyDaoInterfaceEMP)context.getBean("EMPtoTxt");
		
		switch(daoType){
		
		case "DB": return empDaoDb;
		case "TXT": return empDaoTxt;
		default:break;
		
		}
		logger.error("設定ファイルに想定されていない値が格納されています。");
		
		return null;
		
		
	}

	public String getDaoType() {
		return daoType;
	}

	public void setDaoType(String daoType) {
		this.daoType = daoType;
	}
	
	

}
