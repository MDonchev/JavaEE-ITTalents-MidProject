package products;

public class Tobacco extends Product {

	public enum TobaccoFlavor {
		RED_FRUIT, BLUE_LEGEND, MINT;
	}
	
	private TobaccoFlavor flavor;
	
	public Tobacco(String name, double price, TobaccoFlavor flavor) {
		super(name, price, Product.Category.TOBACCO);
		this.flavor = flavor;
	}
	
	
	
}
