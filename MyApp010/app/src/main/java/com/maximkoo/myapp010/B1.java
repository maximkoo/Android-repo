package com.maximkoo.myapp010;
import java.util.*;
import android.graphics.*;
import android.util.*;

public class B1
{		int fSize;
		int[][] gf;//=new int[fSize][fSize]; 
		int hwm=50;
		int colorsUsed; //Количество цветов
		static int criticalChainLen=3;
		List<List<Point>> chains;
		List<Point> f;
		List<Point> allChecked=new ArrayList<Point>();
		List<Point> emptyCells;
		int newCells=5;
		int dropCount=0;
		Score sc;

B1(int fSize, int colorsUsed){
	this.fSize=fSize;
	this.colorsUsed=colorsUsed;
	this.gf=new int[fSize][fSize];
	sc=new Score();
	Log.v("MyApp010","fSize="+fSize);
}		

public void initialSeed(){
	for (int row=0; row<fSize; row++){
		for (int pos=0; pos<fSize; pos++){
			if ((int)(100*Math.random())<=hwm) {
				gf[row][pos]= (int)((1+colorsUsed)*Math.random());}
				}
		}
	}
	
	public void additionalSeed(int dropCount){
		//newCells=(int)(2+5*Math.random()); //Количество появляющихся шариков
		newCells=5;
		newCells=Math.max(newCells,Math.round((dropCount+1)/2));
		for (int c=0; c<newCells; c++){
			emptyCells=new ArrayList<Point>();
			for (int row=0; row<fSize; row++){
				for (int pos=0; pos<fSize; pos++){
					if (gf[row][pos]==0){
						emptyCells.add(new Point(pos,row));
					}
				}
			}
			if (!emptyCells.isEmpty()){ //1
				int cellNum=(int)(emptyCells.size()*Math.random());
				int x=emptyCells.get(cellNum).x;
				int y=emptyCells.get(cellNum).y;
				int newValue=(int)((1+colorsUsed)*Math.random());
				gf[y][x]=newValue;
				emptyCells.remove(cellNum);
			}//1
		}
	}

public int[][] getGF(){
	return gf;
}	

public int getGF(int row, int pos){
	return gf[row][pos];
}

public void setGF(int row, int pos, int val){
	gf[row][pos]=val;
}

public void outPut(){
	String s;
	for (int row=0; row<fSize; row++){ 
		s="";
		for (int pos=0; pos<fSize; pos++){
			s=s+gf[row][pos]+" ";
		}
	Log.v("MyApp010",s);	
	}
}

	public int[][] transpose(int[][] a1){
			int[][] a2=new int[fSize][fSize];
			for (int i=0; i<fSize; i++){ 
				for (int j=0; j<fSize; j++){
				a2[j][i]=a1[i][j];
			}
		}
		return a2;
	} 

	public int[] reverse(int[] a1){
			int[] a2=new int[fSize]; 
			int c=0;
			for (int i=a1.length-1; i>=0; i--){
			a2[c]=a1[i];
			c++;
		}
		return a2;
	}

	public int[] compress(int[] a1){
			int[] a2=new int[a1.length];
			Arrays.fill(a2,0);
			int c=0;
			for (int i=0; i<a1.length; i++){
				if (a1[i]>0) {a2[c]=a1[i]; c++;}
			}
		return a2;
	}

	public void move(String direction){
			if ("left".equals(direction)) 
			{for (int row=0; row<fSize; row++){
				gf[row]=compress(gf[row]);
			}
		}
		if ("right".equals(direction))
			{for (int row=0; row<fSize; row++){
				gf[row]=reverse(gf[row]);
				gf[row]=compress(gf[row]);
				gf[row]=reverse(gf[row]);
			}}
		if ("up".equals(direction))
			{gf=transpose(gf);
			for (int row=0; row<fSize; row++){
				gf[row]=compress(gf[row]);
			}
			gf=transpose(gf);
		}
		if ("down".equals(direction)) 
		{gf=transpose(gf);
			for (int row=0; row<fSize; row++){
				gf[row]=reverse(gf[row]);
				gf[row]=compress(gf[row]);
				gf[row]=reverse(gf[row]);
			}
			gf=transpose(gf);}
		Log.v("MyApp010","direction="+direction);
		outPut();
		}

	public int getCell(int x, int y, String where){
		int res=-1;
		if (where.equals("here") || (where==null)) {return gf[y][x];}
		if (where.equals("upper")) {if (y>0)	{return gf[y-1][x];} else {return -1;}}
		if (where.equals("lower")) {if (y<fSize-1) {return gf[y+1][x];} else {return -1;}}
		if (where.equals("left"))  {if (x>0)	{return gf[y][x-1];} else {return -1;}}
		if (where.equals("right")) {if (x<fSize-1) {return gf[y][x+1];} else {return -1;}}
		return res;
	}

	public void checkAdj(int x, int y){
		f.add(new Point(x,y));
		allChecked.add(new Point(x,y));
		if (getCell(x,y,"here")==getCell(x,y,"right")&&!(f.contains(new Point(x+1,y)))){checkAdj(x+1,y);}
		if (getCell(x,y,"here")==getCell(x,y,"left")&& !(f.contains(new Point(x-1,y)))){checkAdj(x-1,y);}
		if (getCell(x,y,"here")==getCell(x,y,"upper")&&!(f.contains(new Point(x,y-1)))){checkAdj(x,y-1);}
		if (getCell(x,y,"here")==getCell(x,y,"lower")&&!(f.contains(new Point(x,y+1)))){checkAdj(x,y+1);}
	}

	public void findChains(){
		chains=new ArrayList<List<Point>>();
		allChecked=new ArrayList<Point>();
		for (int row=0; row<fSize; row++){
			for (int pos=0; pos<fSize; pos++){
				f=new ArrayList<Point>();
				if (!allChecked.contains(new Point(pos, row))&&(gf[row][pos]!=0))
				{//System.out.println("Running checkAdj");
					checkAdj(pos,row);}
				if (f.size()>=criticalChainLen)
				{chains.add(f);}
			}
		}
	}

	public int dropLongChains(){
		//int dropCount=0;
		dropCount=0;
		for (int row=0; row<chains.size(); row++){
			for (int pos=0; pos<chains.get(row).size(); pos++){
				gf[(chains.get(row).get(pos)).y][(chains.get(row).get(pos)).x]=0;
				dropCount+=1;
			}
		}
		return dropCount;
	}
	
	public int getDropCount(){return dropCount;}
	
}
