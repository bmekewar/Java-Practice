package com.bvm.interview2;

class VelankaniTest {

/*	static{
		System.out.println("Hi");
	}
	public static void main(String[] args) {

	}
*/
	
	void method(Object obj){ System.out.println(obj); }
	String method(String s){ System.out.println("String: "+s); return s;}
	public static void main(String args[]){
		VelankaniTest a = new VelankaniTest();
		Object o=null;
		a.method(null);
		a.method(o);
	}

}
