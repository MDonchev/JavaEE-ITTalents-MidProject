package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale.Category;

import controller.manager.DBManager;
import model.Product;

public class ProductDao {

	private static ProductDao instance;
	private Connection connection = DBManager.getInstance().getConnection();
	
	private ProductDao() {}
	
	public static synchronized ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	
	public List<Product> getAllProducts() {
		return Collections.EMPTY_LIST;
	}
	

	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	public void deleteProduct(Product product) {
		
	}

}
