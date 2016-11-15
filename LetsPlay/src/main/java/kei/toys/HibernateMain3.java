package kei.toys;

import kei.Interface.Main;
import kei.beans.*;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMain3 implements Main{
	@SuppressWarnings("deprecation")
	public void run() throws HibernateException{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(new Emp(3,"Augs","Enpel",new Date()));
		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
		session.beginTransaction();
		List result = session.createQuery("from Emp").list();
		for (Emp emp : (List<Emp>) result) {
			System.out.println("Emp :" + emp.getEmpno() + " : " + emp.getEname());
		}
		session.getTransaction().commit();
		session.close();

		if (sessionFactory != null) {
			sessionFactory.close();
		}

	}
}
