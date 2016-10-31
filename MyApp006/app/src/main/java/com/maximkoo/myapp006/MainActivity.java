package com.maximkoo.myapp006;

import android.app.*;
import android.os.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.util.*;

public class MainActivity extends Activity implements OnClickListener
{

	@Override
	public void onClick(View p1)
	{
		// TODO: Implement this method
		Intent i=new Intent(this, A2.class);
		startActivity(i);
	}


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	Button b = (Button) findViewById(R.id.mainButton);
		b.setOnClickListener(this);
		Log.v("MyApp006","Activity 1 onCreate");
	}

	@Override
	protected void onStart()
	{
		// TODO: Implement this method
		Log.v("MyApp006","Activity 1 onStart");
		super.onStart();
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		Log.v("MyApp006","Activity 1 onResume");
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		Log.v("MyApp006","Activity 1 onPause");
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		// TODO: Implement this method
		Log.v("MyApp006","Activity 1 onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		Log.v("MyApp006","Activity 1 onDestroy");
		super.onDestroy();
	}
	
}
