package defog;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ScatterGatherPattern {

	ExecutorService service = Executors.newFixedThreadPool(4);

	// write methods to send productId and url
	private Set<Integer> getPrices(int productId) throws InterruptedException {

		Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());

		CountDownLatch latch = new CountDownLatch(3);

		service.submit(new Task1("http://...", productId, prices, latch));
		service.submit(new Task1("http://...", productId, prices, latch));
		service.submit(new Task1("http://...", productId, prices, latch));

		latch.await(3, TimeUnit.SECONDS);

		// Thread.sleep(3000);
		return prices;
	}

	// write methods to send productId and url
	private Set<Integer> getPricesUsingCompletableFeature(int productId) throws InterruptedException {

		Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());

		CompletableFuture<Void> task1 = CompletableFuture.runAsync(new Task2("http://...", productId, prices));
		CompletableFuture<Void> task2 = CompletableFuture.runAsync(new Task2("http://...", productId, prices));
		CompletableFuture<Void> task3 = CompletableFuture.runAsync(new Task2("http://...", productId, prices));

		CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
		try {
			allTasks.get(3, TimeUnit.SECONDS);
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prices;
	}

	private class Task1 implements Runnable {

		private String url;
		private int productId;
		private Set<Integer> prices;
		private CountDownLatch latch;

		public Task1(String url, int productId, Set<Integer> prices, CountDownLatch latch) {
			this.url = url;
			this.productId = productId;
			this.prices = prices;
			this.latch = latch;
		}

		// make http call to get price
		@Override
		public void run() {
			int price = 0;
			prices.add(price);
			latch.countDown();
		}
	}

	
	private class Task2 implements Runnable {

		private String url;
		private int productId;
		private Set<Integer> prices;

		public Task2(String url, int productId, Set<Integer> prices) {
			this.url = url;
			this.productId = productId;
			this.prices = prices;
		}

		// make http call to get price
		@Override
		public void run() {
			int price = 0;
			prices.add(price);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ScatterGatherPattern sg = new ScatterGatherPattern();
		sg.getPrices(123);
		sg.getPricesUsingCompletableFeature(123);
	}

}
