package model.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Order;

public class OrderDao {

	List<Order> ordersInDb; //It is as if this is our database for Orders
	
	public OrderDao() {
		this.ordersInDb = new ArrayList();
		
	}
	
	public List<Order> getAllOrders() {
		return Collections.unmodifiableList(this.ordersInDb);
	}
	
	public void insertOrder(Order order) {
		if(!this.ordersInDb.contains(order)) {
			this.ordersInDb.add(order);
		}
	}

	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
	}

	public void deleteOrder(Order order) {
		this.ordersInDb.remove(order);
	}

}
