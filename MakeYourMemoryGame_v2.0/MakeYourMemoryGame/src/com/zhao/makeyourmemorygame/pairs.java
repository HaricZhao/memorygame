package com.zhao.makeyourmemorygame;

public class pairs {
	private int b1,b2,value;

	public pairs(int a, int b,int v){
		b1=a;
		b2=b;
		value=v;
	}
	public void setP(int a, int b){
		b1=a;
		b2=b;
	}
	public int getP(int m){
		if(m==b1){return b2;}
		else if(m==b2){return b1;}
		else return -1;
	}
	
}
