package com.zhao.makeyourmemorygame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.Editable;
import android.widget.EditText;

public class wellDone {
	int time;
	String name;
	
	public wellDone(final int level,int t,Context context) throws IOException{
		
		time=t;
		File fileDir=context.getFilesDir();
		final String add1=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/easyRecords.ser";
		final String add2=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/mediumRecords.ser";
		final String add3=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/hardRecords.ser";
		AlertDialog.Builder good =new AlertDialog.Builder(context);
		good.setTitle("Well Done!\nYou break the record! Enter your name! Your moves are "+t);
		final EditText nameET=new EditText(context);
		good.setView(nameET);
		OnClickListener listener=new OnClickListener(){

		@Override
			public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			name=nameET.getText().toString();
			if(level==1){
				File f=new File(add1);
				if(f.exists()){
					f.delete();
				}	
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(add1);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ObjectOutputStream os = null;
				try {
					os = new ObjectOutputStream(fos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				record r=new record(name,time);
				try {
					os.writeObject(r);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(level==2){
				File f=new File(add2);
				if(f.exists()){
					f.delete();
				}	
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(add2);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ObjectOutputStream os = null;
				try {
					os = new ObjectOutputStream(fos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				record r=new record(name,time);
				try {
					os.writeObject(r);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(level==3){
				File f=new File(add3);
				if(f.exists()){
					f.delete();
				}	
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(add3);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ObjectOutputStream os = null;
				try {
					os = new ObjectOutputStream(fos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				record r=new record(name,time);
				try {
					os.writeObject(r);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		};
		good.setPositiveButton("Yes", listener);
		good.show();
	
		
		
		
		
	}
	
}
