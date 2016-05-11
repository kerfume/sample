package jp.kerfume.app.logic;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.kerfume.app.interf.FileToPerpe;
import jp.kerfume.app.interf.MakeDto;

public class FileToPerpeImpl implements FileToPerpe{
	
	private String path;
	private String file;


	public void run(){
		Logger logger = Logger.getLogger (FileToPerpeImpl.class.getName ());
    	DOMConfigurator.configure("log4j_common.xml");
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("FileToPerpeImplContext.xml");
    	MakeDto mdi = (MakeDto)context.getBean("MakeDto");
        
    	try{
    		mdi.setData(path + "\\" + file);
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
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
