package com.bvm.interview2;

public class InstanceTest {

	public static void main(String[] args) {
		A a = new A();
		
		if(A.isCreated())
			System.out.print("Instance of A is created");
	}
}

class A {
	private static boolean isCreated = false;

	A() {
		isCreated = true;
		//notify();
	}

	public static boolean isCreated() {
		return isCreated;
	}
}
