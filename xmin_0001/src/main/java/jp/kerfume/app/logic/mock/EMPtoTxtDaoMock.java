package jp.kerfume.app.logic.mock;

import java.io.IOException;
import java.util.ArrayList;

import jp.kerfume.app.bean.InDataBean;
import jp.kerfume.app.interf.MyDaoInterfaceEMP;

public class EMPtoTxtDaoMock implements MyDaoInterfaceEMP{

	@Override
	public void conect() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<InDataBean> select(Object... id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(ArrayList<InDataBean> beanList) throws IOException {
		// TODO Auto-generated method stub
		return false;
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
