package defog;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class ForkJoinClass {

	public static void main(String[] args) {
		// ForkJoinClass main = new ForkJoinClass();
		Fibonacci fibo = new Fibonacci(14);
		System.out.println("Task: " + fibo.compute().intValue());
	}
}

class Fibonacci extends RecursiveTask<Integer> {
	final int n;

	Fibonacci(int n) {
		this.n = n;
	}

	public Integer compute() {
		if (n <= 1)
			return n;
		Fibonacci f1 = new Fibonacci(n - 1);
		f1.fork();
		Fibonacci f2 = new Fibonacci(n - 2);
		try {
			System.out.println("f1=" + f1.isDone() + ", f2=" + f1.isDone());
			System.out.println("f1=" + f1.get() + ", f2=" + f2.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return f2.join() + f1.join();
	}
}
