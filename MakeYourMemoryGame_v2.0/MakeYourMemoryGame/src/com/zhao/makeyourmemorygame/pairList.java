package com.zhao.makeyourmemorygame;

public class pairList {
	private int n;
	private pairs[] P;
	private int right=0;
	public pairList(int num){
		n=num;
		P=new pairs[n];
	}
	public void add(int i,int a,int b){
		P[i]=new pairs(a,b,i);
	}
	public int getValue(int a){
		for(int i=0;i<n;i++){
			if(P[i].getP(a)!=-1){
				return i;
			}
		}
		return -1;
	}
	public void setRight(){
		right++;
	}
	
	public boolean ifAllRight(){
		if(right==n){
			return true;
		}
		return false;
	}
}
