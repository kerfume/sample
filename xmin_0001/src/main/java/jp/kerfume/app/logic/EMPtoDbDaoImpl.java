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
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import jp.kerfume.app.bean.InDataBean;
import jp.kerfume.app.interf.*;

/**
 * EMPレコードのDBへの永続化を管理するDAO
 * 
 * @author kei
 *
 */
public class EMPtoDbDaoImpl implements MyDaoInterfaceEMP{

	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;
	private Session session;
	private Logger logger;
	
	public EMPtoDbDaoImpl(){
		logger = Logger.getLogger (EMPtoTxtDaoImpl.class.getName ());
		DOMConfigurator.configure("log4j_common.xml");
	}
	
	/**
	 * DBへの接続を確立するクラス
	 * @param -
	 * @return -
	 * 
	 */
	@Override
	public void conect() throws IOException {
		
		Configuration configuration =  new  Configuration (); 
	    configuration . configure (); 
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration . buildSessionFactory ( serviceRegistry );
	    session = sessionFactory.openSession();
		
	}
	/**
	 * DBから切断するクラス
	 * @param -
	 * @return -
	 * 
	 */
	@Override
	public void close() throws IOException {
		sessionFactory.close();
		
	}

	@Override
	public ArrayList<InDataBean> select(Object... id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * EMPレコードをDBへの永続化を行うクラス
	 * @param EMPレコードのリスト
	 * @return 成功の場合はtrue,失敗の場合はfalse
	 * 
	 */
	@Override
	public boolean insert(ArrayList<InDataBean> beanList) throws IOException {
		try{
			session.beginTransaction();
			for (InDataBean emp : beanList){
				session.save(emp);
			}
			session.getTransaction().commit();
		}catch(HibernateException e){
			logger.error(e);
			logger.error("EMPテーブルへのデータの挿入に失敗しました。");
			session.getTransaction().rollback();
	    	return false;
	    }
		return true;
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
