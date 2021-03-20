package defog;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(4);
		Phaser phaser = new Phaser(3);
		
		service.submit(new Task(phaser));
		service.submit(new Task(phaser));
		service.submit(new Task(phaser));
		
		phaser.awaitAdvance(1);
		System.out.println("All dependent service initialized");
		service.shutdown();
	}
	static class Task implements Runnable {

		private Phaser phaser;

		public Task(Phaser phaser) {
			this.phaser = phaser;
		}

		@Override
		public void run() {
			phaser.arrive();
			System.out.println("Current Phase:" + phaser.getPhase());
			System.out.println("Number of parties arrived:" + phaser.getRegisteredParties());
			System.out.println("Number of registered parties arrived:" + phaser.getRegisteredParties());
		}
	}

}
