package com.bvm.serialization;

import java.io.*;

// incomplete program 
public class TestCustomizedSerialization implements Serializable
{
	private static final long serialVersionUID =-22L; 
	private String noOfSerVar; 
	transient private int noOfTranVar; 

	TestCustomizedSerialization(int noOfTranVar, String noOfSerVar) { 
		this.noOfTranVar = noOfTranVar; 
		this.noOfSerVar = noOfSerVar; 
	} 

	private void writeObject(ObjectOutputStream os) 
	{ 
		try { 
			os.defaultWriteObject(); 
			os.writeInt(noOfTranVar); 
		} catch (Exception e)	{ e.printStackTrace(); } 
	} 

	private void readObject(ObjectInputStream is) { 
		try { 
			is.defaultReadObject(); 
			int noOfTransients = (is.readInt()); 
		} catch (Exception e)	{ e.printStackTrace(); } 
	} 

	public int getNoOfTranVar()	{ return noOfTranVar; } 
	public String getNoOfSerVar() {	return noOfSerVar;	}

	public static void main(String [] args) { 
		TestCustomizedSerialization c = new TestCustomizedSerialization(1,"ONE"); 

		System.out.println("Before serialization: - Name:" + c.getNoOfSerVar() + " - Age:"+ c.getNoOfTranVar()); 
		
		try { 
			FileOutputStream fs = new FileOutputStream("testTransients.ser"); 
			ObjectOutputStream os = new ObjectOutputStream(fs); 
			//os.writeObject(c); 
			c.writeObject(os);
			os.close(); 
		} catch (Exception e) { e.printStackTrace(); } 

		try { 
			FileInputStream fis = new FileInputStream("testTransients.ser"); 
			ObjectInputStream ois = new ObjectInputStream(fis); 
			//c = (TestCustomizedSerialization) ois.readObject(); 
			c.readObject(ois);
			ois.close(); 
		} catch (Exception e) { e.printStackTrace(); } 

		System.out.println("After de-serialization: : - Name:" + c.getNoOfSerVar() + " - Age:"+ c.getNoOfTranVar());
	} 

}
