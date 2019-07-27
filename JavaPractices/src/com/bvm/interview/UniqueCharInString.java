package com.bvm.interview;

import java.util.HashMap;

public class UniqueCharInString {

	public static void main(String[] args) {
		String s = "abcdefgh";//"Hi How are you?";
		char[] chArray = s.toCharArray(); 
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(char c : chArray){
			if(map.containsKey(c)){
				System.out.print("String doesn't contains Unique characters.");
				break;
				//count = map.get(c);
			}
			else 
				map.put(c, 1);	
		}
		System.out.print("String contains Unique characters.");
	}

}
