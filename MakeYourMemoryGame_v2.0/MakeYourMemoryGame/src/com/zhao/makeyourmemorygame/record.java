package com.zhao.makeyourmemorygame;

import java.io.Serializable;

import android.text.Editable;

public class record implements Serializable {
	private String name;
	private int time;
	public record(String n,int t){
		name=n;
		time=t;
	}
	public int getT(){
		return time;
	}
	public String getN(){
		return name;
	}
}
