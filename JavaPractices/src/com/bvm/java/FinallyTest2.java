package com.bvm.java;
public class FinallyTest2{

	public static void main(String[] args)
	{
		try
		{
			System.out.println("In try");
			throw new Exception("Test");
		}
		catch (Exception e)
		{
			try
			{
				Thread.sleep(5000);
			}
			catch (InterruptedException ie)
			{
				System.out.println("In catch InterruptedException:: "+ie);
			}
			System.out.println("In catch Exception:: "+e);
		}
		finally { System.out.println("In finally"); }
		//finally { System.out.println("In finally2"); }

	}
}