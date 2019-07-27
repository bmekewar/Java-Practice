package com.bvm.java;
import java.lang.reflect.Method;

public class JavaReflectionExample1
{

	public JavaReflectionExample1()
	{
		Class c;
		try
		{
			c = Class.forName("JavaReflectionExample1");
			Method m[] = c.getDeclaredMethods();
			System.out.println(m[0].toString());
		}
		catch (ClassNotFoundException e)
		{
			// deal with the exception here ...
			e.printStackTrace();
		}
	}

	public void method1(){ System.out.println("in method1"); };
	public void method2(){ System.out.println("in method2"); };
	public void method3(){ System.out.println("in method3"); };
	
	public static void main(String[] args)
	{
		new JavaReflectionExample1();
	}
}