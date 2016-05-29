package jp.kerfume.app.logic;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jp.kerfume.app.logic.mock.EMPtoTxtDaoMock;

public class MyDaoFactoryImplTest {

	@Test
	public void getEMPDAOテスト() {
		
		MyDaoFactoryImpl sut = new MyDaoFactoryImpl();
		sut.setDaoType("DB");
		assertThat(sut.getDAOEMP(),is(instanceOf(EMPtoDbDaoImpl.class)));
		sut.setDaoType("TXT");
		assertThat(sut.getDAOEMP(),is(instanceOf(EMPtoTxtDaoMock.class)));
		sut.setDaoType("aa");
		assertThat(sut.getDAOEMP(),is(nullValue()));

	}
	
	@Test
	public void Spring初期化テスト() throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("FileToPerpeImplContext.xml");
		
		MyDaoFactoryImpl sut = (MyDaoFactoryImpl)context.getBean("DaoFactory");
		
		sut.setDaoType("DB");
		
	}

}
