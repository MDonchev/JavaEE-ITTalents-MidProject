package products;

public abstract class Product {

	public enum Category {
		HOOKAH, TOBACCO, BOX_OF_CHARCOAL_CUBES;
	}
	
	private String name;
	private String description;
	private double price;
	private Category category;
	
	public Product(String name, double price, Category category) {
		this.name = name;
		this.description = "";
		this.price = price;
		this.category = category;
	}
	
}
