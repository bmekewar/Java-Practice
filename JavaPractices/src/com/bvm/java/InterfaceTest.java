package com.bvm.java;
interface Interface {
		//abstract void method() { System.out.println("final method"); }
		public static String test="Interface String";
		public abstract void method();
}

public class InterfaceTest extends Abstract implements Interface 
{
	public void method() { System.out.println("In method"); }
	public void method2() { System.out.println("In method2"); }
	public static void main(String args[]){
		InterfaceTest it = new InterfaceTest();
		it.method();
		it.method2();
		//it.test = "class interface string";
		System.out.println("Interface Variables::"+it.test);
		System.out.println("Abstract Variables::"+it.test1);
		System.out.println("Abstract Variables::"+it.test2);
		System.out.println("Abstract Variables::"+it.test3);
		System.out.println("Abstract Variables::"+it.test4);
	}
}

abstract class Abstract
{
	volatile String test1="volatile string";
	transient String test2="transient string";
	protected static String test3="protected static string";
	final String test4="final string";
	abstract void method2();
};