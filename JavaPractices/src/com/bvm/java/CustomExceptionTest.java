package com.bvm.java;
class CustomException extends Exception 
{
	public CustomException(String msg) {
		super(msg);
		this.getMessage();
	}
}

public class CustomExceptionTest
{
	public static void main(String[] args){
		try
		{
			System.out.println("In try");
			throw new CustomException("Custom Exception");
		}
		catch (CustomException ce)
		{
			System.out.println("In catch :: "+ce);
		}
	}
};