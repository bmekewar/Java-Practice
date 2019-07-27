package com.bvm.java;
public class Test2
{
	public static void main(String args[]){

		long a1 = 0;
		long a2 = 1;
		boolean a11=true,a22=true;
		if(a11 ^ a22)
		{
			System.out.println("true");
		}
		else
		{
			System.out.println("false");
		}
		String test="TeMp123Test";
		test = (test.toLowerCase()).trim();
		System.out.println("Test := "+test);
	}
}