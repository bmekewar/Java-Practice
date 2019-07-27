package com.bvm.java;
public class TestSwitch
{
	
	public static void main(String args[])
	{
		int i = (int)Integer.parseInt(args[0]);
		switch(i)
		{
			case 0:
				System.out.println("In case 0");
			case 3:
				System.out.println("In case 3");
			case 6:
				System.out.println("In case 6");
			break;
			case 10: 
				System.out.println("In case 10");
			break;
			default : 
				System.out.println("In default");
		}
	}
}