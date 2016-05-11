package jp.kerfume.app.logic;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import jp.kerfume.app.bean.InDataBean;

public class MakeDtoImplTest{
	
	@Test
	public void setDataメソッドの取り込みテスト() throws Exception{
		MakeDtoImpl mdi = new MakeDtoImpl();
		InDataBean sut = new InDataBean();
		
		sut = mdi.setData("E:\\renkeifile\\receive\\IF_EMP_001.xml");
		
		assertThat(sut.getName(),is("田中"));
		assertThat(sut.getAge(),is(15));
		assertThat(sut.getSex(),is(1));
	}
	
	@Test(expected = RuntimeException.class)
	public void chekDate_名前が20字を超えた場合にRuntimeExceptionが発生する() throws Exception {
		MakeDtoImpl mdi = new MakeDtoImpl();
		InDataBean instans = new InDataBean();
		instans.setName("あいうえおかきくけこたちつてとかきくけこさし");
		instans.setAge(10);
		instans.setSex(0);
		mdi.idb = instans;
		
		mdi.chekDate();
	}
	
	@Test(expected = RuntimeException.class)
	public void chekDate_年齢が3桁を超えた場合にRuntimeExceptionが発生する() throws Exception {
		MakeDtoImpl mdi = new MakeDtoImpl();
		InDataBean instans = new InDataBean();
		instans.setName("田中");
		instans.setAge(1000);
		instans.setSex(0);
		mdi.idb = instans;
		
		mdi.chekDate();
	}
	
	@Test(expected = RuntimeException.class)
	public void chekDate_性別が不正だった場合にRuntimeExceptionが発生する() throws Exception {
		MakeDtoImpl mdi = new MakeDtoImpl();
		InDataBean instans = new InDataBean();
		instans.setName("田中");
		instans.setAge(1000);
		instans.setSex(-1);
		mdi.idb = instans;
		
		mdi.chekDate();
	}
	

}
