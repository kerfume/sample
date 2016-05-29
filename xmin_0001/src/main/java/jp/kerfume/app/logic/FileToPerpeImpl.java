package jp.kerfume.app.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.kerfume.app.interf.FileToPerpe;
import jp.kerfume.app.interf.MakeDto;
import jp.kerfume.app.interf.MyDaoInterfaceEMP;
import jp.kerfume.app.bean.*;

public class FileToPerpeImpl implements FileToPerpe{
	
	private String path;
	private String filename;


	public void run(){
		Logger logger = Logger.getLogger (FileToPerpeImpl.class.getName ());
    	DOMConfigurator.configure("log4j_common.xml");
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("FileToPerpeImplContext.xml");
    	InDataBean indata = null;
        
    	//ビジネスロジックstart
    	
    	//xmlload部
    	MakeDto mdi = (MakeDto)context.getBean("MakeDto");
    	
    	File fileobj = new File(path + "\\" + filename);
    	
    	if(!fileobj.exists()){
    		logger.error("xmin_0001_001 取り込み対象ファイルが存在しません");
    		System.exit(1);
    	}
    	
    	try{
    		indata = mdi.setData(path + "\\" + filename);
    		logger.info("xmlの読み込みに成功しました。");
    	}catch(Exception e){
    		logger.error(e);
    		System.exit(1);
    	}
    	
    	//永続化部
    	MyDaoFactoryImpl mydaofac = (MyDaoFactoryImpl)context.getBean("DaoFactory");
    	
    	ArrayList<InDataBean> arrayIndata = new ArrayList<InDataBean>();
    	arrayIndata.add(indata);
    	try(MyDaoInterfaceEMP empDao = mydaofac.getDAOEMP();){
    		empDao.conect();
    		if(!empDao.insert(arrayIndata)) throw new IOException("データの挿入に失敗しました。");
    		
    	}catch(IOException e){
    		logger.error(e);
    		System.exit(1);
    	}
    	
    	//後理部
		
	}
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
