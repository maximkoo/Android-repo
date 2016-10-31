package com.maximkoo.myapp007;

import android.app.*;
import android.os.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.widget.EditText;
import android.util.*;

public class MainActivity extends Activity implements OnClickListener
{
Button b;
EditText eee;

	

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	b=(Button)findViewById(R.id.mainButton);
		eee=(EditText) findViewById(R.id.mainEditText);
		b.setOnClickListener(this);
		Log.v("MyApp007","onCreate ");
	}
	
	@Override
	public void onClick(View p1)
	{
		Intent i=new Intent(this, A2.class);
		Log.v("MyApp007","onClick");
//		if (eee!=null){Log.v("MyApp007","Is not null");} else {Log.v("MyApp007","Is null");} 
		i.putExtra("textValue",eee.getText().toString());
		startActivity(i);
	}

}

