package com.bvm.java;
import java.util.Enumeration; 
import java.util.HashMap; 
import java.util.Hashtable; 
import java.util.Iterator; 

public class MapTest 
{ 
	/** * @param args */ 
	public static void main(String[] args) 
	{ 
		int count=0;

		Hashtable ht=new Hashtable(); 
		ht.put("a","1"); 
		ht.put("b","2"); 
		ht.put("c","3"); 
		ht.put("d","4"); 
		ht.put("e","5"); 
		ht.put("a","6"); 
		
		ht.put("","12");
		//ht.put("1",null);
		ht.put("","13");
		//Iterator myVeryOwnIterator1 = ht.entrySet().iterator(); 
		Enumeration it = ht.elements(); 
		ht.put("g","7"); 
		ht.put("f","12");
		System.out.println("In HashTable");
		//while(myVeryOwnIterator1.hasNext()){ 
		//	System.out.println(myVeryOwnIterator1.next()); 
		//	count++;
		//} 
		while(it.hasMoreElements()) { System.out.println(it.nextElement()); count++; } 
		System.out.println("Count In HashTable::"+count);
		
		HashMap<Object, String> hm=new HashMap<Object, String>(); 
		hm.put("1","2"); 
		hm.put("2","4"); 
		hm.put("3","6"); 
		hm.put("4","8"); 
		hm.put("4","2"); 
		
		hm.put("","12");
		hm.put("","13");
		hm.put("","");
		hm.put("a",null);
		
		Iterator myVeryOwnIterator = hm.entrySet().iterator(); 
		//hm.put("5","12");
		//hm.put("6","13");
		System.out.println("In HashMap");
		count=0;
		while(myVeryOwnIterator.hasNext()){ 
			System.out.println(myVeryOwnIterator.next()); 
			count++;
		}
		System.out.println("Count In HashMap::"+count);
	} 
} 