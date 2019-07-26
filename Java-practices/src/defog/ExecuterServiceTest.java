package defog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecuterServiceTest {

	public static void main(String[] args) {

/*		Thread th1 = new Thread(new Task());
		th1.start();
*/	
		
/*		int coreCount = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newFixedThreadPool(coreCount);
		for (int i=0;i<20;i++ ) {
			service.execute(new Task());
		}
*/		
		//ExecutorService service = new ThreadPoolExecutor(10, 100, 120, 
		//		TimeUnit.SECONDS, new ArrayBlockingQueue<>(300));
		
		ExecutorService service = new ThreadPoolExecutor(4, 5, 120, 
				TimeUnit.SECONDS, new ArrayBlockingQueue<>(25),new CustomRejectionHandler());

		try {
			List<Future> allFutures = new ArrayList<>();
			for (int i=0;i<100;i++ ) {
				//service.submit(new Task1());
				allFutures.add(service.submit(new Task2()));
			}
			
			for (Future future:allFutures) {
				System.out.println("Result of future: "+ future.get());
			}
			
		} catch (RejectedExecutionException e) {
			System.err.println("task rejected: " + e.getMessage());
		} catch (InterruptedException e) {
			System.err.println("task interrupted: " + e.getMessage());
		} catch (ExecutionException e) {
			System.err.println("Execution exception: " + e.getMessage());
		}
		System.out.println("Thread name: " + Thread.currentThread().getName());
		try {
			service.awaitTermination(10,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.shutdown();
		service.isShutdown();
		service.isTerminated();
	}
	
	private static class CustomRejectionHandler implements RejectedExecutionHandler {

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			System.out.println("Rejected :"+ executor.getTaskCount());
		}
		
	}

	static class Task1 implements Runnable {

		@Override
		public void run() {
			
/*			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
*/			System.out.println("Thread name: " + Thread.currentThread().getName());

		}
	}
	
	static class Task2 implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			Thread.sleep(3000);
			System.out.println("Thread name: " + Thread.currentThread().getName());
			return new Random().nextInt();
		}
		
		
	}

}
