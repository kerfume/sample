package kei.toys;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import kei.Interface.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Logger logger = Logger.getLogger (App.class.getName ());
    	DOMConfigurator.configure("deploy_res/log4j_common.xml");
    	
    	logger.info ("Start Program");
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("AppContext.xml");
        Main main = (Main)context.getBean("main");
        main.run();
        
        logger.info ("End Program");
    }
}
