package jp.kerfume.app.logic;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.kerfume.app.interf.*;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
    {
    	Logger logger = Logger.getLogger (App.class.getName ());
    	DOMConfigurator.configure("log4j_common.xml");
    	
    	logger.info ("xmin_0001 Start");
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("AppContext.xml");
    	FileToPerpe main = (FileToPerpe)context.getBean("main");
        main.run();
        
        logger.info ("xmin_0001 End");
    }
}
