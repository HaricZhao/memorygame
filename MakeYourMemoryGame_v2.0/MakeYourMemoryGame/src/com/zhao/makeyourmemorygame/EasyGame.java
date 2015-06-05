package com.zhao.makeyourmemorygame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Random;


import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EasyGame extends ActionBarActivity {
	public int step=0;
	private int preV=-1;
	private int preId=-1;
	pairList P=new pairList(8);
    Button.OnClickListener[] listener=new Button.OnClickListener[16];
    //private final static floatTARGET_HEAP_UTILIZATION = 0.75f; 
    
    ArrayList<Integer> list=new ArrayList<Integer>();
    ArrayList<Boolean> bingo=new ArrayList<Boolean>();
    public Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {

        int w = bitmap.getWidth();

        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();

        float scaleWidth = ((float) width / w);

        float scaleHeight = ((float) height / h);

        matrix.postScale(scaleWidth, scaleHeight);// 利用矩阵进行缩放不会造成内存溢出

        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);

        return newbmp;

    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//VMRuntime.getRuntime().setTargetHeapUtilization(TARGET_HEAP_UTILIZATION);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_easy_game);
		Intent intent = getIntent();
		final String[] add=new String[8];
		String[] addM={MainActivity.add1M,MainActivity.add2M,MainActivity.add3M,MainActivity.add4M,MainActivity.add5M,MainActivity.add6M,MainActivity.add7M,MainActivity.add8M};
		for(int i=0;i<8;i++){
			add[i]= intent.getStringExtra(addM[i]);
		}
		
		//final Bitmap[] bitmap=new Bitmap[8];
		//File fileDir=EasyGame.this.getFilesDir();
		//String add1=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/pic.png";
		/*try {
			
			for(int i=0;i<8;i++){
				FileInputStream is=new FileInputStream(add[i]);

				bitmap[i]=BitmapFactory.decodeStream(is);
				int rawHeight = bitmap[i].getHeight(); 
				int rawWidth = bitmap[i].getWidth(); 

				int newHeight = 500; 
				int newWidth = 500; 
				
				float heightScale = ((float) newHeight) / rawHeight; 
				float widthScale = ((float) newWidth) / rawWidth; 
				
				Matrix matrix = new Matrix(); 
				matrix.postScale(heightScale, widthScale); 
				//File f=new File(add[i]);
				//f.delete();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		 getRecord gr=new getRecord();
	        record r;
	        try {
				r=gr.getRecord(EasyGame.this, 1);
				final TextView recordShow=(TextView) findViewById(R.id.record);
		        recordShow.setText("Record: "+r.getN()+" "+r.getT()+"moves");
		        recordShow.setTextSize(20);
				recordShow.getPaint().setFakeBoldText(true);
			} catch (StreamCorruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        
	        for(int i=1;i<17;i++){
	        	list.add(i);
	        	bingo.add(false);
	        }//give the list 1 to 16 buttons
	        int n=16;
	        for(int i=0;i<8;i++){
	        	Random random = new Random();
	        	int a = random.nextInt(n);
	        	int num1=list.get(a);
	        	list.remove(a);
	        	n--;
	        	int b = random.nextInt(n);
	        	int num2=list.get(b);
	        	list.remove(b);
	        	n--;
	        	P.add(i,num1,num2);
	        	//final Button button1=(Button) findViewById(2131034172+num1-1);
	        	//final Button button2=(Button) findViewById(2131034172+num2-1);
	        	//button1.setText(String.valueOf(i));
	        	//button2.setText(String.valueOf(i));
	        }

	        final Button[] b=new Button[16];
	        for(int i=0;i<16;i++){
	        	b[i]=(Button) findViewById(R.id.b1+i);//2131034172 is R.id.b1
	        }
	        final TextView steps = (TextView) findViewById(R.id.steps);
	     
	        for( int i=0;i<16;i++){
	        	final int j=i;
	        	listener[i]=new Button.OnClickListener() 
	    		{
	        		
	        		@SuppressWarnings("deprecation")
					public void onClick(View v)
	    			{	
	    				
	    				if(bingo.get(j)==false&&preId!=j){
	    					step++;
	    					if(preV!=-1&&preId!=-1){
	    						if(preV==P.getValue(j+1)){
	    							bingo.set(j,true);
	    							bingo.set(preId,true);
	    							P.setRight();

	    						}else{
	    							if(bingo.get(preId)==false){
	    								final Button button2=(Button) findViewById(R.id.b1+preId);
	    								//button2.setText("");
	    								button2.setBackgroundResource(R.drawable.lconfootball);
	    							}
	    						}
	    					}
	    					final Button button1=(Button) findViewById(R.id.b1+j);
	    					
	    					FileInputStream is = null;
	    					try {
								is = new FileInputStream(add[P.getValue(j+1)]);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    					Bitmap tempBitmap=BitmapFactory.decodeByteArray(Util.decodeBitmap(add[P.getValue(j+1)]), 0, Util.decodeBitmap(add[P.getValue(j+1)]).length);  
	    					//Bitmap tempBitmap = BitmapFactory.decodeStream(is);
	    		            //Bitmap newBitmap = zoomBitmap(tempBitmap, tempBitmap.getWidth(), tempBitmap.getHeight() / 3);
	    		           
	    		            BitmapDrawable bmpD=new BitmapDrawable(tempBitmap);
	    					button1.setBackgroundDrawable(bmpD);
	    					
	    					//newBitmap.recycle();
	    					//tempBitmap.recycle();
	    					
	    					/*FileInputStream is;
							try {
								is = new FileInputStream(add[P.getValue(j+1)]);
								ObjectInputStream os=new ObjectInputStream(is);
								Object one=os.readObject();
								byte[] newBit=(byte[]) one;
								Bitmap bmp=BitmapFactory.decodeByteArray(newBit, 0, newBit.length);
		    					/*int rawHeight = bmp.getHeight(); 
		    					int rawWidth = bmp.getWidth(); 

		    					int newHeight = 500; 
		    					int newWidth = 500; 
		    					
		    					float heightScale = ((float) newHeight) / rawHeight; 
		    					float widthScale = ((float) newWidth) / rawWidth; 
		    					
		    					Matrix matrix = new Matrix(); 
		    					matrix.postScale(heightScale, widthScale); 
		    					
								//File f=new File(add[P.getValue(j+1)]);
								//f.delete();
		    					BitmapDrawable bmpD=new BitmapDrawable(bmp);
		    					button1.setBackgroundDrawable(bmpD);
		    					final ImageView iv=(ImageView) findViewById(R.id.test);
		    					iv.setImageBitmap(bmp);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								AlertDialog.Builder good =new AlertDialog.Builder(EasyGame.this);
							} catch (StreamCorruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

	    					*/
	    					//File f=new File(add[i]);
	    					//f.delete();
	    					//button1.setText(String.valueOf(P.getValue(j+1)));
	    					/*try {
								FileInputStream fileStream=new FileInputStream(add1);
								ObjectInputStream os=new ObjectInputStream(fileStream);
								Object one = null;
								for(int i=0;i<j+1;i++){
									one=os.readObject();
								}
								Bitmap bmp=(Bitmap) one;
								BitmapDrawable bmpD=new BitmapDrawable(bmp);
								button1.setBackground(bmpD);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (StreamCorruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    					*/
	    					
	    					if(P.ifAllRight()==true){
	    						steps.setText("Done!\n"+"Moves\n"+String.valueOf(step));
	    						for(int i=0;i<8;i++){
	    							File f=new File(add[i]);
	    	    					f.delete();
	    						}
	    						checkRecord cr=new checkRecord();
	    						try {
									cr.check(1, step, EasyGame.this);
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	    					
	    						
	    					}else{
	    						steps.setText("Moves\n"+String.valueOf(step));
	    					}
	    				}
	    					
	    					if(bingo.get(j)==false&&preId!=j){
	    						preId=j;
	    						preV=P.getValue(j+1);
	    					
	    					
	    					}
	    					
	    			}
	    		};
	        }
	       
	        for(int j=0;j<16;j++){
	        	b[j].setOnClickListener(listener[j]);
	        }
	        		
	        Button restart=(Button) findViewById(R.id.restart);
	        restart.setBackgroundResource(R.drawable.restart);
	        Button.OnClickListener restartLis=new Button.OnClickListener(){

	        	@Override
				public void onClick(View v) {
				// TODO Auto-generated method stub
	        		Intent intent = getIntent();
	        		finish();
	        		startActivity(intent);
	        	}
	    	
	        };
	        restart.setOnClickListener(restartLis);
	        Button back=(Button) findViewById(R.id.back);
	        back.setBackgroundResource(R.drawable.back);
	        Button.OnClickListener backLis=new Button.OnClickListener(){
	        	public void onClick(View v){
	        		Intent intent=new Intent(EasyGame.this,MainActivity.class);
	        		startActivity(intent);
	        		EasyGame.this.finish();
	        	}
	        };
	        back.setOnClickListener(backLis);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.easy_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
