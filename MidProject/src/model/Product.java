package model;

public abstract class Product {

	public enum Category {
		HOOKAH, TOBACCO, BOX_OF_CHARCOAL_CUBES;
	}
	
	private int productId;
	private String name;
	private String description;
	private double price;
	private Category category;
	
	public Product(int productId, String name, String desc, double price, Category category) {
		this(name, desc, price, category);
		this.productId = productId;
	}
	
	public Product(String name, String desc, double price, Category category) {
		this.name = name;
		this.description = desc;
		this.price = price;
		this.category = category;
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public int getProductId() {
		return productId;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

}
