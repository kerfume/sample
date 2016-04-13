package kei.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;  

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println( "Offcose!" );
        
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        App ma = (App)context.getBean("main");
        ma.execute();
    }
    
    private void execute(){
    	System.out.println( "tstめそっど" );
    }
}
