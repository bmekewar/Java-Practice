package com.bvm.thread;

class OtherThread implements Runnable {
	Thread t;
	OtherThread(String name) {
		t = new Thread(this,name);
		t.start();
	}
	
	public void run(){ 
		try {
			System.out.println("Other Thread's run: "+(t.getState()));
			//wait(10000);
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("Other Thread Interrupted: "+(t.getState()));
		}
	}
}

class UserThread extends Thread {
	public UserThread(String name) {
		super(name);
		start();
	}
	
	public void run(){ 
		try {
			System.out.println("UserThread's run: "+Thread.currentThread());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}
public class ThreadEnumerate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserThread t1 = new UserThread("User Thread1");
		UserThread t2 = new UserThread("User Thread2");
		UserThread t3 = new UserThread("User Thread3");
		OtherThread o1 = new OtherThread("Other Thread1");
		
		for (int i = 0; i < 5; i++) {
			new UserThread("User Thread"+i);
		}
		
		Thread[] all = new Thread [Thread.activeCount()];
		int count = Thread.enumerate(all);
		
		System.out.println("Number of active threads: "+count);
		for (int i = 0; i < all.length; i++) {
			System.out.println("Thread ID: "+all[i].getId()+" : "+all[i]);
			//all[i].dumpStack();
		}
		
		try {
			o1.t.join();
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

	}

}

