package com.maximkoo.myapp010;

public class Score
{
	int score=0;
	
	public int increaseScore(int dropCount){
		int i=0;
		if (dropCount>=3) {
			i=3+(dropCount-3)*3;
		}
		score=score+i;
		return score;	
	} 
	
	public int getScore(){
		return score;
	}
	
}
