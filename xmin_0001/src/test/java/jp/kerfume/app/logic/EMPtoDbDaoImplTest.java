package jp.kerfume.app.logic;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;

import org.junit.Test;

import jp.kerfume.app.bean.InDataBean;

public class EMPtoDbDaoImplTest {

	@Test
	public void 接続テスト() throws Exception{
		EMPtoDbDaoImpl sut = new EMPtoDbDaoImpl();
		
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
