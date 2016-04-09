package kei.quizapp;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.io.UnsupportedEncodingException;


public class TopForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String qmax;
	private String hid;
	public String getQmax(){
		return qmax;
	}
	public void setQmax(String qmax){
		this.qmax = qmax;
	}
	
	public String getHid(){
		return hid;
	}
	public void setHid(String hid){
		this.hid = hid;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request){
		super.reset(mapping, request);
		try{
			request.setCharacterEncoding("UTF-8");
			this.setQmax("5");
			this.setHid("ERR");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
	}
}
