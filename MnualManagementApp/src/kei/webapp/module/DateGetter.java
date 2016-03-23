package kei.webapp.module;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGetter {
	Date nowDate = new Date();
	public String getNowyear(){
		String nowyear = "";
		SimpleDateFormat formatA = new SimpleDateFormat("yyyy");
		nowyear = formatA.format(nowDate);
		return nowyear;
	}

}
