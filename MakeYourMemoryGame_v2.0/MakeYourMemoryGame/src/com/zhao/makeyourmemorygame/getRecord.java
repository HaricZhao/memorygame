package com.zhao.makeyourmemorygame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.text.Editable;

public class getRecord {
	public record getRecord(Context con,int level) throws StreamCorruptedException, IOException, ClassNotFoundException{
		File fileDir=con.getFilesDir();
		String add1=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/easyRecords.ser";
		String add2=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/mediumRecords.ser";
		String add3=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/hardRecords.ser";
		if(level==1){
			
			 File file = new File(add1);
			 if (!file.exists()) {

				 record r=new record("none",999);
				 return r;
			  }else{
				  FileInputStream fis=new FileInputStream(add1);
				  ObjectInputStream os=new ObjectInputStream(fis);
				  record r=(record) os.readObject();
				  os.close();
				  return r;
				  
			  }
			
		}else if(level==2){
			 File file = new File(add2);
			 if (!file.exists()) {

				 record r=new record("none",999);
				 return r;
			  }else{
				  FileInputStream fis=new FileInputStream(add2);
				  ObjectInputStream os=new ObjectInputStream(fis);
				  record r=(record) os.readObject();
				  os.close();
				  return r;
				  
			  }
		}else if(level==3){
			File file = new File(add3);
			 if (!file.exists()) {

				 record r=new record("none",999);
				 return r;

			  }else{
				  FileInputStream fis=new FileInputStream(add3);
				  ObjectInputStream os=new ObjectInputStream(fis);
				  record r=(record) os.readObject();
				  os.close();
				  return r;
				  
			  }
		}
		return null;
	
	}
}
