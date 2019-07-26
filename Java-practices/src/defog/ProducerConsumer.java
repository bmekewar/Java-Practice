package defog;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class ProducerConsumer {

	public static void main(String[] args) {

		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

		// producer
		final Runnable producer = () -> {

			while (true) {
				try {
					int num = new Random().nextInt();
					queue.put(num);
					System.out.println("Produced:" + num);
				} catch (InterruptedException e) {
					System.out.println("Producert interrupted");
				}
			}

		};
		// consumer
		final Runnable consumer = () -> {

			while (true) {
				try {
					System.out.println("Consumed : " + queue.take());
				} catch (InterruptedException e) {
					System.out.println("Consumer interrupted");
				}
			}

		};

		IntStream.range(1, 20).forEach(num -> {
			new Thread(producer).start();
		});

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("Consumer interrupted");
		}
		IntStream.range(1, 20).forEach(num -> {
			new Thread(consumer).start();
		});
	}

}
