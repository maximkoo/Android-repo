package com.maximkoo.myapp010;

import android.app.*;
import android.content.res.*;
import android.os.*;
import android.widget.*;
import android.widget.GridLayout.*;
import java.util.*;
import android.util.*;
import android.view.*;

public class MainActivity extends Activity implements OnTouchListener
{
	int fSize=8;
	int colorsUsed=6; //Количество цветов
    
	int[] res = new int[20];
	List<List<ImageView>> imgs =new ArrayList<List<ImageView>>();
	List<ImageView> f;
	
	B1 b;
	Score sc;
	
	int x1=0; int x2=0; int y1=0; int y2=0; int dx; int dy;	
	
	TextView scoreView;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		/************/
		b=new B1(fSize, colorsUsed);
		sc=new Score();
		/************/
		b.initialSeed();
		
		Resources r=getResources();
		res[0]=r.getIdentifier("blank_120","drawable","com.maximkoo.myapp010");
		res[1]=r.getIdentifier("red_120","drawable","com.maximkoo.myapp010");
		res[2]=r.getIdentifier("blue_120","drawable","com.maximkoo.myapp010");
		res[3]=r.getIdentifier("green_120","drawable","com.maximkoo.myapp010");
		res[4]=r.getIdentifier("aqua_120","drawable","com.maximkoo.myapp010");
		res[5]=r.getIdentifier("gray_120","drawable","com.maximkoo.myapp010");
		res[6]=r.getIdentifier("yellow_120","drawable","com.maximkoo.myapp010");
		res[7]=r.getIdentifier("violet_120","drawable","com.maximkoo.myapp010");
    
		//Log.v("MyApp010","gf(1,1)="+b.getGF(1,1));
		initialFillImgs();	
		initialDraw();
		//GridLayout g=(GridLayout)findViewById(R.id.mainGridLayout);
		//g.setColumnCount(fSize);
		TableLayout g=(TableLayout)findViewById(R.id.mainTableLayout);
		g.setOnTouchListener(this);
		scoreView=(TextView)findViewById(R.id.scoreView);
	}
	
	public void initialFillImgs(){
		for (int row=0; row<fSize; row++){
			f=new ArrayList<ImageView>();
			for (int pos=0; pos<fSize; pos++){
				ImageView a=new ImageView(this);
				LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				a.setLayoutParams(p);
				//a.setImageResource(res[b.getGF()[row][pos]]);
				a.setImageResource(res[b.getGF(row,pos)]);
				f.add(a);				
				//g.addView(a);
			}
			imgs.add(f);
		}
	}
	
	public void initialDraw(){
		//GridLayout g=(GridLayout)findViewById(R.id.mainGridLayout);
		TableLayout g=(TableLayout)findViewById(R.id.mainTableLayout);
		for (int row=0; row<fSize; row++){
			TableRow tr=new TableRow(this);
			for (int pos=0; pos<fSize; pos++){
				
				//TableLayout.LayoutParams params=(TableLayout.LayoutParams) imgs.get(row).get(pos).getLayoutParams();
				TableRow.LayoutParams params=new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT);//,1f);
				params.weight=1;
				imgs.get(row).get(pos).setLayoutParams(params);
				tr.addView(imgs.get(row).get(pos));	
			}
			TableLayout.LayoutParams params2=new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);//,1f);
			params2.weight=1;
			tr.setLayoutParams(params2);
			g.addView(tr);
		}	
	}
	
	public void redrawImgs(){ //aka refillImgs
		for (int row=0; row<fSize; row++){
			for (int pos=0; pos<fSize; pos++){
			  	//ImageView a =imgs.get(row).get(pos);
				imgs.get(row).get(pos).setImageResource(res[b.getGF(row,pos)]);
			}	
		}
	}
	
	public void setScore(int dropCount){
		scoreView.setText("Score: "+sc.increaseScore(dropCount));
	}
	@Override
	public boolean onTouch(View v, MotionEvent e){

		int action=e.getAction();
		switch (action) {
			case MotionEvent.ACTION_DOWN: {
					x1=(int)e.getRawX(); y1=(int)e.getRawY();
					Log.v("myApp009", "x1="+x1+" y1="+y1);
					break;
				}
			case MotionEvent.ACTION_UP: {
					x2=(int)e.getRawX(); y2=(int)e.getRawY(); 	dx=x2-x1; dy=y2-y1;
					if (Math.abs(dx)>Math.abs(dy))
					{if (dx<0) {b.move("left");} //left
						else {b.move("right");} //right
					} //swipe on X
					else
					{if (dy<0) {b.move("up");} //Up
						else {b.move("down");} //down
					} //swipe on Y
					redrawImgs();
					Handler h = new Handler();
					h.postDelayed(new Runnable(){
							@Override
							public void run(){
								b.findChains();
								int dropCount=b.dropLongChains();
								setScore(dropCount);
								redrawImgs();
								}
						},500);
					h = new Handler();
					h.postDelayed(new Runnable(){
						@Override
						public void run(){
							b.additionalSeed(b.getDropCount());
							redrawImgs();
							}
						},800);
					break;
				} //action_up	
		}//switch
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// TODO: Implement this method
		int x=0;
		super.onSaveInstanceState(outState);
		for (int row=0; row<fSize; row++){
			for (int pos=0; pos<fSize; pos++){
				x=x+1;
				outState.putInt("key_"+x, b.getGF(row,pos));
			}	
		}
		
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onRestoreInstanceState(savedInstanceState);
		int x=0;
		int val;
		for (int row=0; row<fSize; row++){
			for (int pos=0; pos<fSize; pos++){
			  	//ImageView a =imgs.get(row).get(pos);
				//imgs.get(row).get(pos).setImageResource(res[b.getGF(row,pos)]);
				x=x+1;
				val=savedInstanceState.getInt("key_"+x);
				b.setGF(row,pos,val);
			}	
		}
	redrawImgs();
	}
	  
	}
