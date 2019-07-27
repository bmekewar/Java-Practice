package com.bvm.thread;

class ThreadA implements Runnable {

	public void methodA() {
		System.out.println("In TheadA-MethodA");
		ThreadB b = new ThreadB();
		synchronized (b) {
			System.out.println("In TheadA-MethodA- Thread B lock acquired");
			b.methodB();
		}
	}

	@Override
	public void run() {
		System.out.println("In TheadA-run");
		methodA();
	}

}

class ThreadB implements Runnable {

	public void methodB() {
		System.out.println("In TheadB-MethodB");
		ThreadA a = new ThreadA();
		synchronized (a) {
			System.out.println("In TheadB-MethodB- Thread A lock acquired");
			a.methodA();
		}
	}

	@Override
	public void run() {
		System.out.println("In TheadB-run");
		methodB();
	}

}

public class Deadlock {

	public static void main(String args[]) {
		Thread threadA = new Thread(new ThreadA());
		threadA.start();

		Thread threadB = new Thread(new ThreadB());
		threadB.start();

	}
}
