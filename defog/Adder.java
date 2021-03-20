package defog;

import java.util.concurrent.atomic.LongAdder;

public class Adder {

	public static void main(String[] args) {
		LongAdder adder = new LongAdder();

		new Thread(() -> {
			adder.increment();
		}).start();
		
		new Thread(() -> {
			adder.increment();
		}).start();
		
		new Thread(() -> {
			adder.increment();
		}).start();
		
	}

}
