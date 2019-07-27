package com.bvm.serialization;

import java.io.*;

public class TestTransientVal implements Serializable
{ 
	private static final long serialVersionUID = -22L; 
	private String name; 
	transient private int age; 
	transient String test;
	
	TestTransientVal(int age, String name, String test) { 
		this.age = age; 
		this.name = name; 
		this.test = test;
	}

	public static void main(String [] args) { 
		TestTransientVal c = new TestTransientVal(1,"ONE","Transient String"); 

		System.out.println("Before serialization: - Name:" + c.name + " - Age:"+ c.age+" - Test:"+c.test); 
		
		try { 
			FileOutputStream fs = new FileOutputStream("testTransients.ser"); 
			ObjectOutputStream os = new ObjectOutputStream(fs); 
			os.writeObject(c); 
			os.close(); 
		} catch (Exception e) { e.printStackTrace(); } 

		try { 
			FileInputStream fis = new FileInputStream("testTransients.ser"); 
			ObjectInputStream ois = new ObjectInputStream(fis); 
			c = (TestTransientVal) ois.readObject(); 
			ois.close(); 
		} catch (Exception e) { e.printStackTrace(); } 

		System.out.println("After de-serialization: : - Name:" + c.name + " - Age:"+ c.age+" - Test:"+c.test);
	} 
}
