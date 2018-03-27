package model;

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

}
