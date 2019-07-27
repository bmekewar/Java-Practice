package com.bvm.java;
public class TestString
{
	public String TestMethod()
	{
		return null;
	}
	public static void main(String args[])
	{
		TestString ts = new TestString();
		String test= ts.TestMethod();
		System.out.println("test = "+test);
		if(test==null)
			System.out.println("working Fine");
		else
			System.out.println("Not working Fine");

		String[] testing = new String[] {"12sdf1","1231sdf2","sdf","sdfsdf"};
		System.out.println("length:= "+testing.length);

		String t1="dfedevicetype1";
		String t2="dfedevicetype";
		StringBuffer t3=new StringBuffer("dfedevicetype");
		//boolean val = t1.contentEquals(t3);
		boolean val = t1.startsWith(t2);
		System.out.println("Equals"+val);
		System.out.println("Class Name is "+TestString.class);

		Object[] testing2 = new Object[] {new String("test1"),new Long(1)};
		System.out.println("Object Array length:= "+testing2.length);
		boolean test11 = testing2[0] instanceof java.lang.String;
		System.out.println("testing2[0] instanceof java.lang.String:= "+test11);
		boolean test12 = testing2[1] instanceof java.lang.Long;
		System.out.println("testing2[0] instanceof java.lang.Long:= "+test12);
		boolean test13 = t1 instanceof java.lang.String;
		System.out.println("t1 instanceof java.lang.String:= "+test13);
	}
}