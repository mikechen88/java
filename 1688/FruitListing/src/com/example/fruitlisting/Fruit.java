package com.example.fruitlisting;

public class Fruit {
	private String fruitName;
	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Fruit(String fruitName, String color) {
		this.fruitName = fruitName;
		this.color = color;
	}
}
