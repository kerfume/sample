package jp.kerfume.app.logic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import jp.kerfume.app.bean.InDataBean;

public class EMPtoTxtDaoImplTest {

	@Test
	public void テキスト永続化テスト() throws Exception{
		EMPtoTxtDaoImpl sut = new EMPtoTxtDaoImpl();
		sut.setPath("");
		sut.setFile("E:\\aaa.txt");
		sut.conect();
		
		ArrayList<InDataBean> instance = new ArrayList<>();
		InDataBean ib = new InDataBean();
		ib.setName("てすとです");
		ib.setAge(16);
		ib.setSex(0);
		instance.add(ib);
		
		assertThat(sut.insert(instance),is(true));
		
		sut.close();
	}

}
