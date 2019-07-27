package com.bvm.java;

import java.util.*;

public class CharPositionTest {
	
	public static void main(String[] args) {
		String s = "Bhalchandra";
	    System.out.println("Actual String:= "+s);
	    System.out.println("String Length:= "+s.length());
	    System.out.println("Charecter\tOccurance\tPositions");
		for(char ch : s.toCharArray()){
			StoreData sd = (StoreData)new CharPositionTest().execute(s, ch);
			sd.Display();
			System.out.println();
		}
	}
	public StoreData execute(String s, char ch) {
		int[] positions = new int[s.length()];
		int count = 0;
	    int index = 0;
	    while ((index = s.indexOf(ch, index)) != -1) {
	    	positions[index] = ++index;
	    	++count;
	    }
	    //System.out.println("Charecter:="+ch+"\t Occurance:="+count+"\tPositions:="+positions);
		return new StoreData(ch,count,positions);
	}
	 
	class StoreData{
		int[] pos;
		int occurance;
		char character;
		StoreData(char ch, int occurance, int[] pos){
			 this.character = ch;
			 this.occurance = occurance;
			 this.pos = pos;
		}
		 
		char getCharacter(){return character; }
		int getOccurance(){ return occurance; }
		int[] getPositions(){ return pos; }
		 
		void Display(){
			 System.out.print(""+character+"\t"+occurance+"\t[");
			 for(int i: pos)
			 {
				 if(i!=0)
					 System.out.print(i+",");
			 }
			 System.out.print("]");
		}
	}
}
