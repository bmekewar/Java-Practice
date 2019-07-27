package com.bvm.collection;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueTest {
	public static void main(String[] args) {
		Queue<ComparablePerson> pq = new PriorityQueue<ComparablePerson>();
		pq.add(new ComparablePerson(1, "John"));
		pq.add(new ComparablePerson(4, "Ken"));
		pq.add(new ComparablePerson(2, "Richard"));
		pq.add(new ComparablePerson(3, "Donna"));
		pq.add(new ComparablePerson(4, "Adam"));
		System.out.println("Priority queue: " + pq);
		while (pq.peek() != null) {
			System.out.println("Head Element: " + pq.peek());
			pq.remove();
			System.out.println("Removed one element from Queue");
			System.out.println("Priority queue: " + pq);
		}

	}
}

class ComparablePerson extends Person implements Comparable {
	public ComparablePerson(int id, String name) {
		super(id, name);
	}

	@Override
	public int compareTo(Object o) {
		ComparablePerson cp = (ComparablePerson) o;
		int cpId = cp.getId();
		String cpName = cp.getName();
		if (this.getId() < cpId) {
			return -1;
		}
		if (this.getId() > cpId) {
			return 1;
		}
		if (this.getId() == cpId) {
			return this.getName().compareTo(cpName);
		}
		// Should not reach here
		return 0;
	}
}

class Person {
	private String name;
	private int id;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Person(int id, String name) {
		super();
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {
		return "[" + id + "," + name + "]";
	}

}
