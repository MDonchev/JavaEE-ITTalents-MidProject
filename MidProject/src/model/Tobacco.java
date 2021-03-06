package model;

public class Tobacco extends Product {

	public enum TobaccoFlavor {
		RED_FRUIT, BLUE_LEGEND, MINT;
	}
	
	private TobaccoFlavor flavor;
	
	public Tobacco(String name, String desc, double price, TobaccoFlavor flavor) {
		super(name, desc, price, Product.Category.TOBACCO);
		this.flavor = flavor;
	}
	
	public TobaccoFlavor getFlavor() {
		return flavor;
	}
	
}
