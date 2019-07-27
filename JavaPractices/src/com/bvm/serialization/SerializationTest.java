package com.bvm.serialization;

import java.io.*;
import java.util.*;

class NonSerial {//implements Serializable{ 
	//This is a non-serializable class 
}
public class SerializationTest implements Serializable{ 
	private static final long serialVersionUID = 1L; 
	private NonSerial nonSerial; 

	SerializationTest(NonSerial nonSerial)	{ this.nonSerial = nonSerial; } 
	
	public static void main(String [] args) { 
		NonSerial nonSer = new NonSerial(); 
		SerializationTest c = new SerializationTest(nonSer); 
		
		try { 
			FileOutputStream fs = new FileOutputStream("test1.ser"); 
			ObjectOutputStream os = new ObjectOutputStream(fs);
			System.out.println("Serialize test1.ser");
			os.writeObject(c); 
			os.close(); 
		} catch (Exception e)	{ e.printStackTrace(); } 
		
		try { 
			FileInputStream fis = new FileInputStream("test1.ser"); 
			ObjectInputStream ois = new ObjectInputStream(fis); 
			System.out.println("De-Serialize test1.ser");
			c = (SerializationTest) ois.readObject(); 
			ois.close(); 
		} catch (Exception e)	{ e.printStackTrace(); } 
	} 
} 

