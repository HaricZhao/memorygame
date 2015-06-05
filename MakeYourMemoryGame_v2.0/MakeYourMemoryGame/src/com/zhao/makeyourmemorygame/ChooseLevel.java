package com.zhao.makeyourmemorygame;




import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ChooseLevel extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_level);
		final Button easy=(Button) findViewById(R.id.easy);
		easy.setBackgroundResource(R.drawable.p);
		Button.OnClickListener easyLis=new Button.OnClickListener(){

        	@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
        		Intent easyIn = new Intent(ChooseLevel.this,MainActivity.class);
        		startActivity(easyIn);
        		ChooseLevel.this.finish();
        		
        	}
    	
        };
        easy.setOnClickListener(easyLis);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_level, menu);
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
