import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CollectionPractice {

	public static void main(String args[]) {

		CollectionPractice cp = new CollectionPractice();

		List<Person> list = new ArrayList<>();
		list.add(new Person("bala", "1234234", "bala@test.com"));
		list.add(new Person("john", "134334", "john@test.com"));
		list.add(new Person("sara", "11234", "sara@test.com"));
		list.add(new Person("harry", "234234", "harry@test.com"));
		list.add(new Person("jason", "566734", "jason@test.com"));
		list.add(new Person("bill", "686734", "bill@test.com"));

		Collections.sort(list, new Comparator<Person>() {

			@Override
			public int compare(Person p1, Person p2) {
				return p1.getName().compareToIgnoreCase(p2.getName());
			}
		});

		//System.out.println("Print sorted list");
		Collections.sort(list, (p1, p2) -> p1.getName().compareTo(p2.getName()));

		System.out.println("Print all persons");
		cp.performConditionally(list, p -> true, p-> System.out.println(p));

		System.out.println("\nPrint persons starting name with letter 'b'");
		cp.performConditionally(list, p -> p.getName().startsWith("b"), p-> System.out.println(p));

		System.out.println("\nPrint persons having phone numbers ends with '734'");
		cp.performConditionally(list, p -> p.getPhone().endsWith("734"), p-> System.out.println(p.getName()));

	}

	/*
	 * Behavioral methods which perform on random behaviors
	 */
	void performConditionally(List<Person> list, Predicate<Person> predicate, Consumer<Person> consumer) {
		for (Person person : list) {
			if (predicate.test(person)) {
				//System.out.println(person);
				consumer.accept(person);
			}
		}
	}

}

/*interface Condition {
	boolean test(Person p);
}*/