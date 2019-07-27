package com.bvm.interview;

import java.util.Arrays;

public class ComparablePersonTest {
	public static void main(String[] args) {
		ComparablePerson[] persons = new ComparablePerson[] {
				new ComparablePerson("John", "Jacobs"),
				new ComparablePerson("Jeff", "Jacobs"),
				new ComparablePerson("Wally", "Inman") };
		System.out.println("Before sorting...");
		print(persons);
		// Sort the persons list
		Arrays.sort(persons);
		System.out.println("\nAfter sorting...");
		print(persons);
	}

	public static void print(ComparablePerson[] persons) {
		for (ComparablePerson person : persons) {
			System.out.println(person);
		}
	}
}

class ComparablePerson implements Comparable<ComparablePerson> {
	private String firstName;
	private String lastName;

	public ComparablePerson(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// Compares two persons based on their last names. If last names are
	// the same, use first names
	public int compareTo(ComparablePerson anotherPerson) {
		int diff = getLastName().compareTo(anotherPerson.getLastName());
		if (diff == 0) {
			diff = getFirstName().compareTo(anotherPerson.getFirstName());
		}
		return diff;
	}

	public String toString() {
		return getLastName() + ", " + getFirstName();
	}
}
