package model;

public class Hookah extends Product {

	public enum HookahSize {
		SMALL, MEDIUM, LARGE;
	}
	
	private int numberOfHoses;
	private HookahSize size;
	
	public Hookah(String name, double price, HookahSize size) {
		super(name, price, Product.Category.HOOKAH);
		this.size = size;
	}
}
