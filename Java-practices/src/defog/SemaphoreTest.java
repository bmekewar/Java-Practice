package defog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SemaphoreTest {

	public static void main(String[] args) throws InterruptedException {
		Semaphore semaphore = new Semaphore(2);

		ExecutorService service = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 10; i++) {
			service.execute(new Task(semaphore));
		}

		service.shutdown();
		service.awaitTermination(1, TimeUnit.MINUTES);
	}

	static class Task implements Runnable {

		private Semaphore semaphore;

		public Task(Semaphore semaphore) {
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			semaphore.acquireUninterruptibly();
			System.out.println(Thread.currentThread().getName()+" Semaphore acquired");
			semaphore.release();
			System.out.println(Thread.currentThread().getName()+" Semaphore released");
		}

	}

}
