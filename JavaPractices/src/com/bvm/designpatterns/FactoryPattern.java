package com.bvm.designpatterns;
class Person {
	private String name;
	private String gender;
	
	public String getName()		{ return name; }
public String getGender()	{ return name; }
}

class Male extends Person {
	public Male(String name) { System.out.println("Hello Mr."+name); }
}
class Female extends Person {
	public Female(String name) { System.out.println("Hello Ms."+name); }
}

public class FactoryPattern {
	public static void main(String[] args)
	{
		FactoryPattern fp = new FactoryPattern();
		fp.testMessage("ABC", "male");
	}

	private Person testMessage(String name, String gender) {
		if(gender.equalsIgnoreCase("male"))
			return new Male(name);
		else if(gender.equalsIgnoreCase("female"))
			return new Female(name);
		else 
			return null;
	}
}
