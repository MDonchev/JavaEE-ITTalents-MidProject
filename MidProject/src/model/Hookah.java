package model;

public class Hookah extends Product {

	public enum HookahSize {
		SMALL, MEDIUM, LARGE;
	}
	
	private HookahSize size;
	
	public Hookah(String name, String desc, double price, HookahSize size) {
		super(name, desc, price, Product.Category.HOOKAH);
		this.size = size;
	}
	
	public HookahSize getSize() {
		return size;
	}
}
