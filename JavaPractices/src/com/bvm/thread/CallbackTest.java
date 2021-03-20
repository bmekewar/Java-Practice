package com.bvm.thread;

import java.util.ArrayList;

public class CallbackTest {
	
	private ArrayList<Callback> list = new ArrayList<Callback>();
	 
	public static void main(String[] args) {
		CallbackTest cbt = new CallbackTest();
	
		Callback c1 = cbt.generateCallback(1);
		cbt.register(c1);

		Callback c2 = cbt.generateCallback(12);
		cbt.register(c2);

		Callback c3 = cbt.generateCallback(2);
		cbt.register(c3);
		
		Callback c4 = cbt.generateCallback(4);
		cbt.register(c4);
		
		cbt.callback();
	}

	private void register(Callback c1) {
		this.list.add(c1);
	}

	private Callback generateCallback(final int number) {
		
		Callback c = new Callback() {
			public void call() {
				System.out.println("Called # "+number);
			}
		};
		
		return c;
	}
	
	private void callback(){
		for(Callback c : this.list){
			c.call();
		}
	}

}

interface Callback{
	void call();
}
