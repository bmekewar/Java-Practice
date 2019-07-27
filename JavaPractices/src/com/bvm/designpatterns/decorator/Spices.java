package com.bvm.designpatterns.decorator;

public class Spices extends DrinkDecorator {
	public Spices(Drink drink) {
		this.drink = drink;
		this.name = "Spices";
		this.price = 0.10;
	}
}
