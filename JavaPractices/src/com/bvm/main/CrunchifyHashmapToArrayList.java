package com.bvm.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CrunchifyHashmapToArrayList {
	public static void main(String... args) {
		HashMap<String, List<Contact>> companyDetails = new HashMap<String, List<Contact>>();

		// create hashmap with keys and values (CompanyName, #Employees)
		List<Contact> ls = new ArrayList<Contact>();
		Contact c1 = new Contact("a", "X", "abc");
		Contact c2 = new Contact("b", "Y", "xyz");
		Contact c3 = new Contact("c", "Z", "abc12");
		Contact c4 = new Contact("d", "X", "121");

		ls.add(c1);
		ls.add(c2);
		ls.add(c3);
		ls.add(c4);

		companyDetails.put("eBay", ls);
		companyDetails.put("Paypal", ls);
		companyDetails.put("IBM", ls);
		companyDetails.put("Google", ls);
		companyDetails.put("Yahoo", ls);

		System.out.println("==> Size of companyDetails Map: "
				+ companyDetails.size());
		Iterator it = companyDetails.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
		}

		// Converting HashMap keys into ArrayList
		List<String> keyList = new ArrayList<String>(companyDetails.keySet());
		System.out.println("\n==> Size of Key list: " + keyList.size());

		for (String temp : keyList) {
			System.out.println(temp);
		}

		List<Contact> result = new ArrayList<Contact>();
		System.out.println("\n==> Size of Value list: "
				+ companyDetails.values().size());
		for (List<Contact> temp : companyDetails.values()) {
			System.out.println(temp);
			for (Contact c : temp) {
				result.add(c);
			}
		}

		System.out.println(result);

		List<Entry> entryList = new ArrayList<Entry>(companyDetails.entrySet());
		System.out.println("\n==> Size of Entry list: " + entryList.size());
		for (Entry temp : entryList) {
			System.out.println(temp);
		}
	}
}

class Contact implements Comparable<Contact> {
	private String name;
	private String surname;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Contact(String name, String surname, String address) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
	}

	@Override
	public int compareTo(Contact o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", surname=" + surname + ", address="
				+ address + "]";
	}

}
