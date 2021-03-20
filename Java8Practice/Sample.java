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

class Person implements Comparable<Person> {

	private String name;
	private int age;
	private String phone;
	private String email;

	public Person(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	public Person(String name,int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", phone=" + phone + ", email=" + email + "]";
	}

	@Override
	public int compareTo(Person person) {
		return ((Integer) age).compareTo(person.getAge());
	}

}

