package com.maximkoo.myapp011;

import android.app.*;
import android.os.*;
import android.widget.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		TableLayout tt=(TableLayout) findViewById(R.id.T1);
		int res=getResources().getIdentifier("red","drawable","com.maximkoo.myapp011");
		
		for (int i=0; i<10; i++){
			TableRow tr=new TableRow(this);
			
			for (int ii=0; ii<10; ii++){
				ImageView iv=new ImageView(this);
				iv.setImageResource(res);
				TableRow.LayoutParams params=new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT);//,1f);
				params.weight=1;
				iv.setLayoutParams(params);
				tr.addView(iv);
			}
			TableLayout.LayoutParams params2=new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);//,1f);
			params2.weight=1;
			tr.setLayoutParams(params2);
			
			tt.addView(tr);
		}
    }
}
