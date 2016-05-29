package jp.kerfume.app.interf;

import java.io.IOException;
import java.util.ArrayList;

import jp.kerfume.app.bean.InDataBean;

public interface MyDaoInterfaceEMP extends MyDaoInterface{
	
	ArrayList<InDataBean> select(Object... id) throws Exception;
	boolean insert(ArrayList<InDataBean> beanList) throws IOException;
	boolean delete(Object id) throws Exception;
	boolean update(ArrayList<InDataBean> beanList) throws Exception;
}
