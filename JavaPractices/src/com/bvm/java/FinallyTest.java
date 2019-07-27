package com.bvm.java;

public class FinallyTest {

	public static void main(String[] args) {

		System.out.println("first");
		try{
			System.out.println("try");
			throw new Exception("test");
		}catch(NullPointerException e1){
			System.out.println("catch1");
		}catch(Exception e){
			System.out.println("catch2");
			return;
		}finally{
			System.out.println("finally");
			return;
		}
	}

}
