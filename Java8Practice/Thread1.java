import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Thread1 {

	public static void main(String[] args) {

		ExecutorService service = Executors.newFixedThreadPool(10);
		IntStream.range(0, 10).forEach(i -> {
			service.submit(() -> System.out.println(i));
		});
		System.out.println("Task Started");
		service.shutdown();

		List<Double> sqrtOffFirst100Primes;

		sqrtOffFirst100Primes = Stream.iterate(1, e -> e++).filter(Thread1::isPrime).map(Math::sqrt).limit(100)
				.collect(Collectors.toList());
		System.out.println(String.format("sqrtOffFirst100Primes: %g", sqrtOffFirst100Primes.size()));
	}

	public static boolean isPrime(int number) {
		return number > 1 && IntStream.range(2, number).noneMatch(i -> number % i == 0);
	}
}
