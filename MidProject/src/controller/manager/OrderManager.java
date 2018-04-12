package controller.manager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import model.Order;
import model.Product;
import model.User;
import model.dao.OrderDao;
import model.dao.UserDao;

public class OrderManager {
	
	private volatile static OrderManager instance;
	private OrderDao orderDao = OrderDao.getInstance();
	
	private OrderManager() {}
	
	public synchronized static OrderManager getManager() {
		if (instance == null) {
			instance = new OrderManager();
		}
		return instance;
	}
	
	public void addProductToOrder(Order order, Product product, int count, LocalDateTime dateTime) {
		Map<Product, Integer> orders = order.getOrderedProducts();
		if(!orders.containsKey(product)) {
			orders.put(product, 0);
		}
		orders.put(product, orders.get(product) + count);
	}
	
	public void finalizeOrder(Order order, User user) {
		order.setDateOfOrder(LocalDate.now());
	}
}
