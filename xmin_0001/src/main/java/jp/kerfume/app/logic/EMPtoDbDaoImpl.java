package jp.kerfume.app.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import jp.kerfume.app.bean.InDataBean;
import jp.kerfume.app.interf.*;

public class EMPtoDbDaoImpl implements MyDaoInterfaceEMP{

	private  SessionFactory sessionFactory;
	private  ServiceRegistry serviceRegistry;
	private  Session session;
	
	@Override
	public void conect() throws IOException {
		
		Configuration configuration =  new  Configuration (); 
	    configuration . configure (); 
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration . buildSessionFactory ( serviceRegistry );
	    session = sessionFactory.openSession();
		
	}

	@Override
	public void close() throws IOException {
		sessionFactory.close();
		
	}

	@Override
	public ArrayList<InDataBean> select(Object... id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ArrayList<InDataBean> beanList) throws Exception {
		try{
			session.beginTransaction();
			for (InDataBean emp : beanList){
				session.save(emp);
			}
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
	    	return 1;
	    }
		return 0;
	}

	@Override
	public boolean delete(Object id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ArrayList<InDataBean> beanList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
