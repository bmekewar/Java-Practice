package FY21_Interview;

import java.util.stream.IntStream;

public class CiscoQ3TestNumber {

	// numbers array: [1,2,5,10,17,26 ... N]
	// input size = 4;
	//
	// (n*n +1);

	public static void main(String[] args) {
		int[] numbers = { 1, 2, 5, 10, 17, 26 };
		int size = 4;

		new CiscoQ3TestNumber().test1(size);
	}

	public void test1(int size) {
		if (size >= 0 && size <= Integer.MAX_VALUE) {
			IntStream.range(0, size).forEach(n -> {
				System.out.println(n * n + 1);
			});
		}

	}
}
