package com.zhao.makeyourmemorygame;

import android.app.AlertDialog;
import android.content.Context;

public class justSs {
	public justSs(Context context,int time){
		AlertDialog.Builder good =new AlertDialog.Builder(context);
		good.setTitle("Good job!\nTry harder to break the record! Your time is "+time);
		good.setNegativeButton("Yes", null);
		good.show();
	}
	
}
