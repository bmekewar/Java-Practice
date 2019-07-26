import java.util.function.BiConsumer;

public class ExceptionHadleingExample {

	public static void main(String[] args) {
		int[] numbers = { 1, 234, 45, 56, 23, 45, 9, 2, 6 };
		int key = 2;

		// perform(numbers, key, (v, k) -> System.out.println(v / k));

		/*
		 * perform(numbers, key, (v, k) -> { try { System.out.println(v / k); } catch
		 * (Exception e) { System.out.println("Exception occurred:" + e.getMessage()); }
		 * });
		 */

		perform(numbers, key, wrapLambda((v, k) -> System.out.println(v / k)));
	}

	private static BiConsumer<Integer, Integer> wrapLambda(BiConsumer<Integer, Integer> consumer) {
		return (v, k) -> {
			try {
				consumer.accept(v, k);
			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.getMessage());
			}
		};
	}

	private static void perform(int[] numbers, int key, BiConsumer<Integer, Integer> consumer) {
		for (int i : numbers) {
			// System.out.println(i / key);
			consumer.accept(i, key);
		}
	}
}
