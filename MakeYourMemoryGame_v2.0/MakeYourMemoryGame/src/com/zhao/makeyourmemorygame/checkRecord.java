package com.zhao.makeyourmemorygame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.content.Context;

public class checkRecord {
	

	public void check(int level,int time,Context con) throws IOException, ClassNotFoundException{
		File fileDir=con.getFilesDir();
		String add1=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/easyRecords.ser";
		String add2=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/mediumRecords.ser";
		String add3=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/hardRecords.ser";
		if(level==1){
			
			 File file = new File(add1);
			 if (!file.exists()) {
				  

				  wellDone good=new wellDone(level,time,con);
			  }else{
				  FileInputStream fis=new FileInputStream(add1);
				  ObjectInputStream os=new ObjectInputStream(fis);
				  record r=(record) os.readObject();
				  os.close();
				  if(r.getT()<=time){
					  justSs js=new justSs(con,time);
				  }else{
					  wellDone yes=new wellDone(level,time,con);
				  }
				  
			  }
		}else if (level==2){
			File file = new File(add2);
			 if (!file.exists()) {
				  

				  wellDone good=new wellDone(level,time,con);
			  }else{
				  FileInputStream fis=new FileInputStream(add2);
				  ObjectInputStream os=new ObjectInputStream(fis);
				  record r=(record) os.readObject();
				  os.close();
				  if(r.getT()<=time){
					  justSs js=new justSs(con,time);
				  }else{
					  wellDone yes=new wellDone(level,time,con);
				  }
				  
			  }
		}else if(level==3){
			File file = new File(add3);
			 if (!file.exists()) {
				  

				  wellDone good=new wellDone(level,time,con);
			  }else{
				  FileInputStream fis=new FileInputStream(add3);
				  ObjectInputStream os=new ObjectInputStream(fis);
				  record r=(record) os.readObject();
				  os.close();
				  if(r.getT()<=time){
					  justSs js=new justSs(con,time);
				  }else{
					  wellDone yes=new wellDone(level,time,con);
				  }
				  
			  }
		}
		
	}
}
