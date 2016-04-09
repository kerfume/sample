package kei.quizapp;

import java.io.Serializable;
import java.util.ArrayList;

public class q_beans implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<quizbean> quizList;
	
	public q_beans(){
		quizList = new ArrayList<>();
	}
	
	public ArrayList<quizbean> getQuizList(){
		return quizList;
	}
	
	public void setQuizList(ArrayList<quizbean> quizList){
		this.quizList = quizList;
	}
	
	public void add(quizbean qb){
		quizList.add(qb);
	}
	
	public quizbean get(int num){
		return quizList.get(num);
	}
	
	public int getCorrect(){
		int cnt = 0;
		
		for(quizbean qb:quizList){
			if(qb.getErrata() == 1){
				cnt++;
			}
		}
		
		return cnt;
	}

}
