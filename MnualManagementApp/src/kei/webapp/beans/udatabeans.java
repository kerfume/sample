package kei.webapp.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class udatabeans implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<udatabean> udList;
	
	public udatabeans(){
		udList = new ArrayList<udatabean>();
	}
	public List<udatabean> getUdList(){
		return udList;
	}
	public void setUdList(List<udatabean> udList) {
		this.udList = udList;
	}
	public void addUdList(udatabean ud) {
		udList.add(ud);
	}

}
