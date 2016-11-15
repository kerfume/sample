package kei.toys;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kei.Interface.Main;
import kei.Interface.JpgEditer;

public class MainImpl implements Main{
	public void run(){
		
		//myprop();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("MainContext.xml");
		JpgEditer je = (JpgEditer)context.getBean("jpgEditer");
        
        je.format();
        je.editJpg();
        je.close();
	}
	/*private void myprop(){
		Properties prop = new Properties();
		try{
			InputStream inputStream = new FileInputStream(new File("./filescontext.properties"));
			prop.load(inputStream);
			System.out.println(prop.getProperty("jpgediter.path"));
			inputStream = null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
}
