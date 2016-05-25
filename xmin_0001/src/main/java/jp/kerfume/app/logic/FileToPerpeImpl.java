package jp.kerfume.app.logic;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.kerfume.app.interf.FileToPerpe;
import jp.kerfume.app.interf.MakeDto;

public class FileToPerpeImpl implements FileToPerpe{
	
	private String path;
	private String filename;


	public void run(){
		Logger logger = Logger.getLogger (FileToPerpeImpl.class.getName ());
    	DOMConfigurator.configure("log4j_common.xml");
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("FileToPerpeImplContext.xml");
    	MakeDto mdi = (MakeDto)context.getBean("MakeDto");
        
    	//ビジネスロジックstart
    	
    	//xmlload部
    	File fileobj = new File(path + "\\" + filename);
    	
    	if(!fileobj.exists()){
    		logger.error("xmin_0001_001 取り込み対象ファイルが存在しません");
    		System.exit(404);
    	}
    	
    	try{
    		mdi.setData(path + "\\" + filename);
    		logger.info("xmlの読み込みに成功しました。");
    	}catch(Exception e){
    		logger.error(e);
    	}
    	
		
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
