package com.bvm.java;



/**
 * Test for including class in interface
 * @author BVM
 *
 */
interface Interface1
{
	public static String msg1= "Interface Message";
	
	class Nest1
	{
		static String msg1= "Nest1 Message";
		//System.out.println("Testing"+msg1);

		public String test(){
			return "Nest1 "+msg1;
		}
	}
	
}

public class TestNestedClassInInterface implements Interface1{
	
	public static void main(String arg[]){
		TestNestedClassInInterface in = new TestNestedClassInInterface();
		System.out.println(Interface1.Nest1.msg1);
		System.out.println(""+(new Nest1().test()));
	}
}