package com.zhao.makeyourmemorygame;





import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;



import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity implements LoaderCallbacks<Cursor> {
	private int numOfPic=0;
	private Bitmap bitmap=null;
	private byte[] mContent=null;
	//static String add1M = "com.zhao.makeyourmemorygame.MESSAGE1";
	static String add1M = "com.zhao.makeyourmemorygame.MESSAGE1";
	static String add2M = "com.zhao.makeyourmemorygame.MESSAGE2";
	static String add3M = "com.zhao.makeyourmemorygame.MESSAGE3";
	static String add4M = "com.zhao.makeyourmemorygame.MESSAGE4";
	static String add5M = "com.zhao.makeyourmemorygame.MESSAGE5";
	static String add6M = "com.zhao.makeyourmemorygame.MESSAGE6";
	static String add7M = "com.zhao.makeyourmemorygame.MESSAGE7";
	static String add8M = "com.zhao.makeyourmemorygame.MESSAGE8";
	String[] add={add1M,add2M,add3M,add4M,add5M,add6M,add7M,add8M};

	private ListView listView=null;
	private SimpleCursorAdapter simpleCursorAdapter =null;
	private String[] address=new String[8];

	private static final String[] STORE_IMAGES={
		MediaStore.Images.Media.DISPLAY_NAME,
		MediaStore.Images.Media.LATITUDE,
		MediaStore.Images.Media.LONGITUDE,
		MediaStore.Images.Media._ID,
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listView = (ListView)findViewById(android.R.id.list);
        simpleCursorAdapter = new SimpleCursorAdapter(
        		this, 
        		R.layout.simple_list_item, 
        		null, 
        		STORE_IMAGES, 
        		new int[] { R.id.item_title, R.id.item_value}, 
        		0
        		);
        
        simpleCursorAdapter.setViewBinder(new ImageLocationBinder());
        listView.setAdapter(simpleCursorAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
        

        listView.setOnItemClickListener(new ShowItemImageOnClickListener());
        
    }
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1){
    	CursorLoader cursorLoader=new CursorLoader(
    			this,
    			MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
    			STORE_IMAGES,
    			null,
    			null,
    			null);
    	return cursorLoader;
    }
    public void onLoaderReset(Loader<Cursor> arg0){
    	simpleCursorAdapter.swapCursor(null);
    }
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
    	
    	simpleCursorAdapter.swapCursor(cursor);
    }
    private class ImageLocationBinder implements ViewBinder{ 
    	@Override
    	public boolean setViewValue(View view, Cursor cursor, int arg2) {
    		// TODO Auto-generated method stub
    		if (arg2 == 1) {
    			
                double latitude = cursor.getDouble(arg2);
                double longitude = cursor.getDouble(arg2 + 1);
                
                
                
                return true;
            } else {
                return false;
            }
    	}
    }
    private class ShowItemImageOnClickListener implements OnItemClickListener{
    	@Override
    	public void onItemClick(AdapterView<?> parent, View view, int position,
    			long id) {
    		// TODO Auto-generated method stub
    		final Dialog dialog = new Dialog(MainActivity.this);
    		
			dialog.setContentView(R.layout.image_show);
			dialog.setTitle("Pick 8 images! And"+(8-numOfPic)+" left!");
			
			ImageView ivImageShow = (ImageView) dialog.findViewById(R.id.ivImageShow);
			Button yes = (Button) dialog.findViewById(R.id.yes);
			Button no=(Button) dialog.findViewById(R.id.no);
			yes.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					File fileDir=MainActivity.this.getFilesDir();
					//String add1=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/pic.ser";
					address[numOfPic]=fileDir.getParent() + java.io.File.separator + fileDir.getName()+"/pic"+numOfPic+".ser";
					FileOutputStream fos=null;
					try {
						fos = new FileOutputStream(address[numOfPic]);
						bitmap.compress(Bitmap.CompressFormat.PNG, 100,fos);
						//ObjectOutputStream os= new ObjectOutputStream(fos);
						//ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					   // bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);
					    //byte[] newBit=baos.toByteArray();
						//os.writeObject(newBit);
						//os.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					if(bitmap != null){
						
						/*File f=new File(add1);
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
						try {
							os.writeObject(bitmap);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							os.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						bitmap.recycle();
						
						numOfPic++;
						if(numOfPic==8){
							Intent intent=new Intent(MainActivity.this,EasyGame.class);
							for(int i=0;i<8;i++){
								intent.putExtra(add[i],address[i]);
								//intent.putExtra(add1M,add1);
							}
							
							
							startActivity(intent);
							MainActivity.this.finish();
						}
					}
					
				}
			});
			no.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					
					
					if(bitmap != null){
						bitmap.recycle();
					}
				}
			});
			
			Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().
					  appendPath(Long.toString(id)).build();
			FileUtil file = new FileUtil();
			ContentResolver resolver = getContentResolver();
			
			
			try {
				mContent = file.readInputStream(resolver.openInputStream(Uri.parse(uri.toString())));
				bitmap = file.getBitmapFromBytes(mContent, null);
				ivImageShow.setImageBitmap(bitmap);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			dialog.show();
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
