package com.bvm.java;
import java.util.HashMap;


public class CharOccuranceTest {

	public static void main(String[] args) {
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		String s = "BhalChandRa";
		for(char ch: s.toCharArray())
		{
			if(map.containsKey(ch))
			{
				int count = (Integer)map.get(ch);
				map.put(ch, count+1);
			}
			else 
				map.put(ch, 1);
		}
		System.out.println(map);
		for(char ch: map.keySet()){
			int index=0;
			int count=0;
			while((index= s.indexOf(ch, index))!=-1){
				System.out.println(ch+"\t"+ index++ +"\t"+ ++count);
			}
		}
	}
}

