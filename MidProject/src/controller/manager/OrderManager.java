package controller.manager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import model.Order;
import model.Product;

public class OrderManager {

	private Order model;
	
	public OrderManager(Order model) {
		this.model = model;
	}
	
	public void addProductToOrder(Product product, int count) {
		Map<Product, Integer> modelProducts = this.model.getOrderedProducts();
		if(!modelProducts.containsKey(product)) {
			modelProducts.put(product, 0);
		}
		modelProducts.put(product, modelProducts.get(product) + count);
	}
	
	public void finalizeOrder() {
		this.model.setDateOfOrder(LocalDate.now());
	}
}
