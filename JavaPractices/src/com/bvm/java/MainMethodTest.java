package com.bvm.java;

public class MainMethodTest
{

	public static void main(String[] args)
	{
		int[] inter = new int[] {1,2};
		MainMethodTest.main(inter);
		System.out.println("Testing main method");
	}

	public static void main(int[] args)
	{
		System.out.println("Overloaded main method");
	}

}