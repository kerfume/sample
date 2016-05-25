package jp.kerfume.app.interf;

import java.util.ArrayList;

import jp.kerfume.app.bean.InDataBean;

public interface MyDaoInterfaceEMP extends MyDaoInterface{
	
	ArrayList<InDataBean> select(Object... id) throws Exception;
	int insert(ArrayList<InDataBean> beanList) throws Exception;
	boolean delete(Object id) throws Exception;
	boolean update(ArrayList<InDataBean> beanList) throws Exception;
}
