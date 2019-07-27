package com.bvm.interview;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ForEachMethod {
	public static void main(String[] args) {
		// Create a list of strings
		List<Company> names = new ArrayList<Company>();
		// Add some names to the list
		names.add(new Company("Accenture","ACC", "Bangalore"));
		names.add(new Company("SAP","SAP", "Bangalore"));
		names.add(new Company("IGATE","CAPGEMINI", "Bangalore"));
		// Print all elements of the names list
		names.forEach(System.out::println);
		
		SortedSet<Company> companiesByName =
				new TreeSet<Company>(Comparator.comparing(Company::getName));
		
		companiesByName.add(new Company("Accenture","ACC", "Bangalore"));
		companiesByName.add(new Company("SAP","SAP", "Bangalore"));
		companiesByName.add(new Company("IGATE","CAPGEMINI", "Bangalore"));
		
		companiesByName.forEach(System.out::println);
	}
}

class Company {

	private String name;
	private String group;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Company(String name, String group, String address) {
		super();
		this.name = name;
		this.group = group;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", group=" + group + ", address="
				+ address + "]";
	}

}
