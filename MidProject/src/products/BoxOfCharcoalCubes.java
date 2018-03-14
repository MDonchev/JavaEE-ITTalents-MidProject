package products;

public class BoxOfCharcoalCubes extends Product {

	public enum NumberOfPieces {
		EIGHTEEN, THIRTY_SIX, SEVENTY_TWO;
	}
	
	private NumberOfPieces quantity;
	
	public BoxOfCharcoalCubes(String name, double price, NumberOfPieces quantity) {
		super(name, price, Product.Category.BOX_OF_CHARCOAL_CUBES);
		this.quantity = quantity;
	}
	
}
