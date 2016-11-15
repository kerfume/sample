package kei.quizapp;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.io.UnsupportedEncodingException;

public class QuizScrForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private String ans;
	
	public String getAns(){
		return ans;
	}
	
	public void setAns(String ans){
		this.ans = ans;
	}
	
	
	public void reset(ActionMapping mapping,HttpServletRequest request){
		super.reset(mapping, request);
		try{
			request.setCharacterEncoding("UTF-8");
			this.setAns("");
			//this.setQmax("10");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
	
}
