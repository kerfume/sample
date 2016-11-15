package kei.toys;

import kei.Interface.Main;
import kei.beans.*;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMain2 implements Main {
	@SuppressWarnings("deprecation")
	public void run() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(new Event("Our very first event!", new Date()));
		session.save(new Event("A follow up event", new Date()));
		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
		session.beginTransaction();
		List result = session.createQuery("from Event").list();
		for (Event event : (List<Event>) result) {
			System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
		}
		session.getTransaction().commit();
		session.close();

		if (sessionFactory != null) {
			sessionFactory.close();
		}

	}
}
