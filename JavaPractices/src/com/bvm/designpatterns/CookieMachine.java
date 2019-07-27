package com.bvm.designpatterns;
//import java.util.*;
//import java.io.*;

class Cookie implements Cloneable {
	
	@Override
	public Cookie clone() 
	{
		Cookie cookie1 = null;
		try
		{
			cookie1 = (Cookie) super.clone();
		}
		catch (CloneNotSupportedException ce)
		{
			System.out.println("Exception: "+ce);
		}
		return cookie1;
	}

	public void print() { System.out.println("in cookie"); }
}

class CoconutCookie extends Cookie {
	public void print() { System.out.println("in coconut cookie"); }
}

public class CookieMachine 
{
	public static void main(String[] args)
	{
		Cookie c1 = new Cookie();
		//CoconutCookie cc1 = null;// = new CoconutCookie();
		Cookie c2 = (Cookie)c1.clone();
		c2.print();
		//reference objects cant call the instance methods without initialization
		//Reference cc1.print();
		if (c1 == c2)
		{	System.out.println("Cloned"); }
		else 
		{	System.out.println("Not Cloned"); }
	}
};



