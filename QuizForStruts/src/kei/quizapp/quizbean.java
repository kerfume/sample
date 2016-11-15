package kei.quizapp;

import java.io.Serializable;

public class quizbean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String question;
	private String ch1;
	private String ch2;
	private String ch3;
	private String ch4;
	private int answer;
	private int errata;
	
	public quizbean(){
		question = "";
		ch1 = "";
		ch2 = "";
		ch3 = "";
		ch4 = "";
		answer = 0;
		errata = -1;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public void setQuestion(String question){
		this.question = question;
	}
	
	public String getCh1(){
		return ch1;
	}
	
	public void setCh1(String ch1){
		this.ch1 = ch1;
	}
	
	public String getCh2(){
		return ch2;
	}
	
	public void setCh2(String ch2){
		this.ch2 = ch2;
	}
	
	public String getCh3(){
		return ch3;
	}
	
	public void setCh3(String ch3){
		this.ch3 = ch3;
	}
	
	public String getCh4(){
		return ch4;
	}
	
	public void setCh4(String ch4){
		this.ch4 = ch4;
	}
	
	public int getAnswer(){
		return answer;
	}
	
	public void setAnswer(int answer){
		this.answer = answer;
	}
	
	public int getErrata(){
		return errata;
	}
	
	public void setErrata(int errata){
		this.errata = errata;
	}

}
