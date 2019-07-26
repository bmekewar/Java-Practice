package defog;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(4);
		CountDownLatch latch = new CountDownLatch(4);

		service.submit(new Task(latch));
		service.submit(new Task(latch));
		service.submit(new Task(latch));
		service.submit(new Task(latch));

		latch.await();

		System.out.println("All dependent services initialized");
		service.shutdown();
	}

	static class Task implements Runnable {

		private CountDownLatch latch;

		public Task(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			latch.countDown();
			System.out.println("Latch:" + latch.getCount());
		}
	}
}
