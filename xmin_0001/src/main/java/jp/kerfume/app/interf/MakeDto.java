package jp.kerfume.app.interf;

import jp.kerfume.app.bean.InDataBean;

public interface MakeDto{
	public InDataBean setData(String file) throws Exception;
	public void chekDate() throws Exception;
}
