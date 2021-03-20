import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CiscoQ2CovidPatient {

	public static void main(String[] args) {
		CiscoQ2CovidPatient t = new CiscoQ2CovidPatient();
		Patient pt1 = new CiscoQ2CovidPatient().new Patient("p13", 40, "loc1", Instant.now().toString());
		Patient pt2 = new CiscoQ2CovidPatient().new Patient("p21", 41, "loc2", Instant.now().toString());
		Patient pt3 = new CiscoQ2CovidPatient().new Patient("p10", 42, "loc3", Instant.now().toString());

		List<Patient> list = new ArrayList<Patient>();
		list.add(pt1);
		list.add(pt2);
		list.add(pt3);

		list.sort(new Comparator<Patient>() {

			@Override
			public int compare(Patient o1, Patient o2) {
				return o1.getName().compareTo(o2.getName());
			}

		});

		Collections.sort(list, (p1, p2) -> p1.getName().compareTo(p2.getName()));
		System.out.println("Print all patiants sorted by name");
		t.performConditionally(list, p -> true, p -> System.out.println(p));

		Collections.sort(list, (p1, p2) -> p1.getAge().compareTo(p2.getAge()));
		System.out.println("Print all patiants sorted by age");
		t.performConditionally(list, p -> true, p -> System.out.println(p));

		Collections.sort(list, (p1, p2) -> p1.getLocation().compareTo(p2.getLocation()));
		System.out.println("Print all patiants sorted by location");
		t.performConditionally(list, p -> true, p -> System.out.println(p));

		Collections.sort(list, (p1, p2) -> p1.getDateTestedPositive().compareTo(p2.getDateTestedPositive()));
		System.out.println("Print all patiants sorted by age");
		t.performConditionally(list, p -> true, p -> System.out.println(p));
	}

	private void performConditionally(List<Patient> list, Predicate<Patient> p, Consumer<Patient> c) {
		list.stream().forEach(pt -> {
			if (p.test(pt)) {
				c.accept(pt);
			}
		});
	}

	class Patient {

		String name;
		Integer age;
		String location;
		String dateTestedPositive;

		public Patient() {
			super();
		}

		public Patient(String name, Integer age, String location, String dateTestedPositive) {
			super();
			this.name = name;
			this.age = age;
			this.location = location;
			this.dateTestedPositive = dateTestedPositive;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getDateTestedPositive() {
			return dateTestedPositive;
		}

		public void setDateTestedPositive(String dateTestedPositive) {
			this.dateTestedPositive = dateTestedPositive;
		}

		@Override
		public String toString() {
			return "Patient [name=" + name + ", age=" + age + ", location=" + location + ", dateTestedPositive="
					+ dateTestedPositive + "]";
		}

	}
}
