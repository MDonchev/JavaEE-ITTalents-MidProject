package controller.manager;

import model.Product;
import model.dao.ProductDao;
import model.dao.UserDao;

public class ProductManager {

	private volatile static ProductManager instance;
	private ProductDao productDao = ProductDao.getInstance();

	private ProductManager() {}
	
	public synchronized static ProductManager getManager() {
		if (instance == null) {
			instance = new ProductManager();
		}
		return instance;
	}
	
	
	
}
