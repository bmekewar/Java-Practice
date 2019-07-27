package com.bvm.designpatterns.decorator;

public abstract class Drink {
	protected String name;
	protected double price;

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}