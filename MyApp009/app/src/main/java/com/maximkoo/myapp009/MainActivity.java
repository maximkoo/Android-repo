package com.maximkoo.myapp009;

import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import android.content.res.*;
import android.widget.RelativeLayout.*;
import android.view.*;
import android.util.*;

public class MainActivity extends Activity implements OnTouchListener
{
	int fSize=5;
	int colorsUsed=2;
	int[] res = new int[colorsUsed];
	int[][] cells = new int [fSize][fSize];
	List<List<ImageView>> imgs =new ArrayList<List<ImageView>>();
	List<ImageView> f;
	
	int ballRow=0;
	int ballPos=1;
	
	int x1=0; int x2=0; int y1=0; int y2=0; int dx; int dy;	
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//--
		refillValues();
		//--
		
		Resources r=getResources();
		res[0]=r.getIdentifier("blank1","drawable","com.maximkoo.myapp009");
		res[1]=r.getIdentifier("red","drawable","com.maximkoo.myapp009");
		
		initialFillImgs();
		initialDraw();
		GridLayout g=(GridLayout)findViewById(R.id.mainGridLayout);
		g.setColumnCount(fSize);
		g.setOnTouchListener(this);
		
    }
	
	public void refillValues(){
		for (int row=0; row<fSize; row++){
			for (int pos=0; pos<fSize; pos++){
				cells[row][pos]=0;}}
		cells[ballRow][ballPos]=1;
	}
	
	public void initialFillImgs(){
		for (int row=0; row<fSize; row++){
			f=new ArrayList<ImageView>();
			for (int pos=0; pos<fSize; pos++){
				ImageView a=new ImageView(this);
				LayoutParams p=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				a.setLayoutParams(p);
				a.setImageResource(res[cells[row][pos]]);
				f.add(a);				
				//g.addView(a);
			}
			imgs.add(f);
		}
		
	}
	
	public void refillImgs(){
		/*Log.v("myApp009","came to refillImgs");
		for (int row=0; row<fSize; row++){
			f=new ArrayList<ImageView>();
			for (int pos=0; pos<fSize; pos++){
				ImageView a=new ImageView(this);
				LayoutParams p=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				a.setLayoutParams(p);
				a.setImageResource(res[cells[row][pos]]);
				Log.v("myApp009", String.valueOf(cells[row][pos]));
				f.add(a);				
				//g.addView(a);
			}
			imgs.set(row,f);
			Log.v("myApp009","imgs row set");
		}*/
		for (int row=0; row<fSize; row++){
			for (int pos=0; pos<fSize; pos++){
			  	//ImageView a =imgs.get(row).get(pos);
				imgs.get(row).get(pos).setImageResource(res[cells[row][pos]]);
				//Log.v("myApp009","imgs row="+row + " pos="+pos+" set");
				
			}	
		}
	}
	
	public void initialDraw(){
		GridLayout g=(GridLayout)findViewById(R.id.mainGridLayout);
		
		for (int row=0; row<fSize; row++){
			for (int pos=0; pos<fSize; pos++){
				g.addView(imgs.get(row).get(pos));	
			}
		}	
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
					{if (dx<0) {ballPos=0; Log.v("myApp009", "left"); Log.v("myApp009","dx="+dx+" dy="+dy);} //left
						else {ballPos=fSize-1; Log.v("myApp009", "right"); Log.v("myApp009","dx="+dx+" dy="+dy);} //right
					} //swipe on X
					else
					{if (dy<0) {ballRow=0; Log.v("myApp009", "up"); Log.v("myApp009","dx="+dx+" dy="+dy);} //Up
						else {ballRow=fSize-1; Log.v("myApp009", "down"); Log.v("myApp009","dx="+dx+" dy="+dy);} //down
					} //swipe on Y
					refillValues();
					Log.v("myApp009", "Calling refillImgs");
					refillImgs();
					break;
				} //action_up	
		}//switch
		return true;
	}//onTouch
}
