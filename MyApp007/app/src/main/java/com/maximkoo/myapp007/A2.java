package com.maximkoo.myapp007;
import android.app.*;
import android.os.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.widget.*;

public class A2 extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a2);
		Intent i=getIntent();
		TextView t=(TextView)findViewById(R.id.a2TextView);
    	t.setText(i.getStringExtra("textValue"));
	}
}
