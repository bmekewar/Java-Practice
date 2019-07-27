package com.bvm.interview;

import java.util.Arrays;
import java.util.Comparator;

public class ComparablePersonTest2 {
	public static void main(String[] args) {
		ComparablePerson[] persons = new ComparablePerson[] {
				new ComparablePerson("John", "Jacobs"),
				new ComparablePerson("Jeff", "Jacobs"),
				new ComparablePerson("Wally", "Inman") };
		System.out.println("Original array...");
		print(persons);
		// Sort using first name
		Comparator<ComparablePerson> fnComparator = new FirstNameComparator();
		Arrays.sort(persons, fnComparator);
		System.out.println("\nAfter sorting on first name...");
		print(persons);
		// Sort using last name
		Comparator<ComparablePerson> lnComparator = new LastNameComparator();
		Arrays.sort(persons, lnComparator);
		System.out.println("\nAfter sorting on last name...");
		print(persons);
		// Sort using first name, then last name
		Comparator<ComparablePerson> firstLastComparator = fnComparator
				.thenComparing(lnComparator);
		Arrays.sort(persons, firstLastComparator);
		System.out.println("\nAfter sorting on first, then last name...");
		print(persons);
		// Sort using first name, then last name in reversed order
		Comparator<ComparablePerson> firstLastReverseComparator = firstLastComparator
				.reversed();

		Arrays.sort(persons, firstLastReverseComparator);
		System.out
				.println("\nAfter sorting on first, then last name in reversed...");
		print(persons);
		// Sort using first name, then last name using null first
		Comparator<ComparablePerson> nullFirstComparator = Comparator
				.nullsFirst(firstLastComparator);
		ComparablePerson[] personsWithNulls = new ComparablePerson[] {
				new ComparablePerson("John", "Jacobs"), null,
				new ComparablePerson("Jeff", "Jacobs"),
				new ComparablePerson("Wally", "Inman"), null };
		Arrays.sort(personsWithNulls, nullFirstComparator);
		System.out.println("\nAfter sorting on first, then last name "
				+ "using null first...");
		print(personsWithNulls);
	}

	public static void print(ComparablePerson[] persons) {
		for (ComparablePerson person : persons) {
			System.out.println(person);
		}
	}
}

class FirstNameComparator implements Comparator<ComparablePerson> {
	public int compare(ComparablePerson p1, ComparablePerson p2) {
		String firstName1 = p1.getFirstName();
		String firstName2 = p2.getFirstName();
		int diff = firstName1.compareTo(firstName2);
		return diff;
	}
}

class LastNameComparator implements Comparator<ComparablePerson> {
	public int compare(ComparablePerson p1, ComparablePerson p2) {
		String lastName1 = p1.getLastName();
		String lastName2 = p2.getFirstName();
		int diff = lastName1.compareTo(lastName2);
		return diff;
	}
}
