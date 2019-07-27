package com.bvm.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {

	public static void main(String[] args) {
		
		ForkJoinPool pool = new ForkJoinPool();
		RandomIntTask task = new RandomIntTask(5);
		System.out.println("Sum is " + pool.invoke(task));
	}

}

class RandomIntTask extends RecursiveTask<Long> {
	private static final long serialVersionUID = 8720830193568706748L;

	private int count;
	private static Random randomNoGenerator = new Random();

	public RandomIntTask(int count) {
		super();
		this.count = count;
	}

	public int getRandomNumber() {
		int n = randomNoGenerator.nextInt(100) + 1;
		System.out.println("Generated a random integer :" + n);
		return n;
	}

	@Override
	protected Long compute() {
		long result = 0L;
		if(count == 0)
			return 0L;
		if(count == 1) {
			return (long) this.getRandomNumber();
		}

		List<RandomIntTask> forks = new ArrayList<RandomIntTask>();
		
		for(int c=0;c <this.count;c++){
			RandomIntTask task = new RandomIntTask(1);
			task.fork();
			forks.add(task);
		}
		for(RecursiveTask<Long> t : forks){
			result = result + t.join();
		}
		
		return result;
	}
}