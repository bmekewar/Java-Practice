package FY21_Interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CiscoQ1SequentialThread {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(1);
		System.out.println("Task Started");
		IntStream.range(0, 5).forEach(i -> {
			service.submit(() -> System.out.println(i));
		});
		service.shutdown();
	}
}
