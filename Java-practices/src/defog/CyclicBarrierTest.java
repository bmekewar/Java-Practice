package defog;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(10);
		CyclicBarrier barrier = new CyclicBarrier(4);
		service.submit(new Task(barrier));
		service.submit(new Task(barrier));
		service.submit(new Task(barrier));
		service.submit(new Task(barrier));
		service.submit(new Task(barrier));
		service.submit(new Task(barrier));
		service.submit(new Task(barrier));
		service.submit(new Task(barrier));

		Thread.sleep(3000);
		System.out.println("All services completed ");
		service.shutdown();
	}

	static class Task implements Runnable {

		private CyclicBarrier barrier;

		public Task(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			//while (true) {
				try {
					System.out.println("Number of Parties Waiting:" + barrier.getNumberWaiting());
					System.out.println("Number of Parties required to trip this barrier:" + barrier.getParties());
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			//}
		}
	}
}
