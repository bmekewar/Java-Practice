package com.bvm.interview;

public class StringReverse {
	public static void main(String args[]) {
		String str = "He is the one";
		String temp = "";
		String finalString = "";
		for (int i = str.length() - 1; i >= 0; i--) {

			temp += i != 0 ? str.charAt(i) : str.charAt(i) + " ";
			
			if (str.charAt(i) == ' ' || i == 0) {
				for (int j = temp.length() - 1; j >= 0; j--) {
					finalString += temp.charAt(j);
				}
				temp = "";
			}
		}
		System.out.println(finalString);
		
		
		 String strings[] = "He is the one".split(" ");
		    String finalStr="";
		        for(int i = strings.length-1; i>= 0 ;i--){
		        	if(i==0){
			            finalStr += strings[i];
		        	} else {
			            finalStr += strings[i]+" ";
		        	}
		        }
		        System.out.println(finalStr);

	}

}
