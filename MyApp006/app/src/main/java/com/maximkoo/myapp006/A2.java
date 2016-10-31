package com.maximkoo.myapp006;
import android.app.*;
import android.os.*;
import android.view.View.*;
import android.view.*;
import android.util.*;

public class A2 extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a2);
		Log.v("MyApp006","Activity 2 onCreate");
    }
	
	@Override
	protected void onStart()
	{
		// TODO: Implement this method
		Log.v("MyApp006","Activity 2 onStart");
		super.onStart();
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		Log.v("MyApp006","Activity 2 onResume");
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		Log.v("MyApp006","Activity 2 onPause");
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		// TODO: Implement this method
		Log.v("MyApp006","Activity 2 onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		Log.v("MyApp006","Activity 2 onDestroy");
		super.onDestroy();
	}
	
	
}
