package order;

import java.time.LocalDateTime;
import java.util.Map;

import products.Product;
import users.User;

public class Order {

	private User buyer;
	private LocalDateTime dateAndTimeOfOrder;
	private Map<Product, Integer> orderedProducts;
	
	public Order(User buyer) {
		this.buyer = buyer;
	}
	
	public void addProduct(Product product) {
		if(this.orderedProducts.get(product) == null) {
			this.orderedProducts.put(product, 0);
		}
		
		this.orderedProducts.put(product, this.orderedProducts.get(product) + 1);
	}
	
	public void finalizeOrder() {
		this.dateAndTimeOfOrder = LocalDateTime.now();
	}
	
}
