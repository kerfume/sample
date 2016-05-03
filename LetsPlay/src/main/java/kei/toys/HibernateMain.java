package kei.toys;

import kei.Interface.Main;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMain implements Main{
	@SuppressWarnings("deprecation")
	public void run(){
		// configures settings from hibernate.cfg.xml
		SessionFactory sessionFactory = new Configuration()
		.configure()
		.buildSessionFactory();

		Session session = sessionFactory.openSession();
		List result = session.createSQLQuery("select now()").list();
		System.out.println(result);

		if ( sessionFactory != null ) {
		sessionFactory.close();
		}
	}

}
