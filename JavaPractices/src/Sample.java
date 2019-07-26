import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Comparator.*;
import java.util.stream.Stream;
import java.util.logging.*;

/**
 * @author bmekewar
 *
 */
public class Sample {
	
	Logger logger; 

	public static void main(String[] args) {

		// final integer lazy loading
		List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 6, 8, 9);
		int[] factor = new int[] { 2 };

		Stream<Integer> stream = numbers.stream().map(e -> e * factor[0]);
		factor[0] = 22;
		stream.forEach(System.out::println);

		// joining
		File dir = new File(".");

		File[] files = dir.listFiles();
		System.out
				.println(Stream.of(files).map(File::getName).map(String::toUpperCase).collect(Collectors.joining(",")));

		// Collecters.comparing
		List<Person> persons = Arrays.asList(new Person("bus", 32), new Person("jack", 23), new Person("this", 45),
				new Person("that", 4), new Person("bang", 3));

		// persons.stream().collect(Collectors.comparing(Person::getAge));
		printSorted(persons, comparing(Person::getAge).thenComparing(Person::getName));
		
	}

	public static void printSorted(List<Person> persons, Comparator<Person> comparator) {
		persons.stream().sorted(comparator).forEach(System.out::println);
	}
}
