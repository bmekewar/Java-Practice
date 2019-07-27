package com.bvm.thread;
class Thread1 implements Runnable 
{
	String msg;

	public Thread1(String msg) 
	{
		Thread t = new Thread(msg);
		System.out.println("In Thread1:"+msg);
		t.start();
	}

	public void run()
	{
		System.out.println("In run:"+msg);
	}
}

public class Thread2 
{
	public static void main(String args[])
	{
		Thread1 t1= new Thread1("Hello");
		try
		{
			System.out.println("In try");
			throw new Exception("Exception");
		}
		catch (Exception e)
		{
			System.out.println("In catch: \n"+e);
		}
		finally
		{
			System.out.println("In finally");
		}
		//finally
		{
			System.out.println("In finally2");
		}
	}

/*	public interface Inter1
	{
		static String msg1= "Testing";
		
		class Nest1
		{
			static String msg1= "Testing";
			//System.out.println("Testing"+msg1);
		}
	}
*/};