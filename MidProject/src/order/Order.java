package order;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import products.Product;
import users.User;

public class Order {

	private User buyer;
	private LocalDateTime dateAndTimeOfOrder;
	private Map<Product, Integer> orderedProducts;
	
	public Order(User buyer) {
		this.buyer = buyer;
		this.dateAndTimeOfOrder = LocalDateTime.now();
		this.orderedProducts = new HashMap();
	}
	
	public void addProduct(Product product) {
		if(!this.orderedProducts.containsKey(product)) {
			this.orderedProducts.put(product, 0);
		}
		
		this.orderedProducts.put(product, this.orderedProducts.get(product) + 1);
	}
	
	public void finalizeOrder() {
		this.dateAndTimeOfOrder = LocalDateTime.now();
	}
	
}
