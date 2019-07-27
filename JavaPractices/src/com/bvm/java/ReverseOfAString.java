package com.bvm.java;

public class ReverseOfAString {

	public String reverse(String str) {
		char[] char1 = str.toCharArray();
		int len = char1.length - 1;
		int middleElement = len / 2;

		for (int i = 0; i <= middleElement; i++) {
			char temp = char1[i];
			char1[i] = char1[len - i];
			char1[len - i] = temp;
			System.out.println(char1);
		}
		return new String(char1);

	}

	public static void main(String argc[]) {
		ReverseOfAString r = new ReverseOfAString();
		String rev = r.reverse("Hi! How are You Bala?");
		//String rev = r.reverse("ABCdEF");
		//System.out.println(rev);
	}
}
