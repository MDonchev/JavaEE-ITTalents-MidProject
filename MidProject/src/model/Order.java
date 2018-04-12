package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {

	private LocalDate dateOfOrder;
	private Map<Product, Integer> orderedProducts;
	
	public Order(LocalDate dateOfOrder, Map<Product, Integer> orderedProducts) {
		this.dateOfOrder = dateOfOrder;
		this.orderedProducts = orderedProducts;
	}
	
	public void setDateAndTimeOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public Map<Product, Integer> getOrderedProducts() {
		return orderedProducts;
	}
	
//	public void addProduct(Product product, int count) {
//		if(!this.orderedProducts.containsKey(product)) {
//			this.orderedProducts.put(product, 0);
//		}
//		
//		this.orderedProducts.put(product, this.orderedProducts.get(product) + count);
//	}
//	
//	public void finalizeOrder() {
//		this.dateAndTimeOfOrder = LocalDateTime.now();
//	}
//
//	@Override
//	public String toString() {
//		return this.orderedProducts.toString() + " Date and time: " + this.dateAndTimeOfOrder;
//	}
	
}
