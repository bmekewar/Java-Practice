package com.bvm.thread;
class TestRun implements Runnable 
{
	String name;
	
	public TestRun(String name)
	{
		Thread worker = new Thread(this, name);
		System.out.println("In Constructor: "+name);
		worker.start();
	}
	public void run()
	{
		System.out.println("In Run method: "+Thread.currentThread().getName());
	}
}
public class TestRunnable 
{
	public static void main(String args[])
	{
		TestRun t = new TestRun("First");
		System.out.println("Thread Name: "+Thread.currentThread().getName());
	}
}
