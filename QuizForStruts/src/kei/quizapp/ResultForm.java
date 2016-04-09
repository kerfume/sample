package kei.quizapp;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.io.UnsupportedEncodingException;


public class ResultForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	//Write Here!!
	
	public void reset(ActionMapping mapping,HttpServletRequest request){
		super.reset(mapping, request);
		try{
			request.setCharacterEncoding("UTF-8");
			//this.setQmax("10");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
}
