package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale.Category;

import controller.manager.DBManager;
import model.Product;
import model.User;

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
	
	public void insertProduct(Product product, User user) throws SQLException {
		String sql = "INSERT INTO products (name, description, price, category, hookah_size, tobacco_flavor, number_of_cubes, users_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setFloat(3, (float)product.getPrice());
			ps.setString(4, product.getCategory().name());
			
		}
	}
	
	public List<Product> getAllProductsUploadedByUserId(int uploaderId) {
		
		
		return Collections.EMPTY_LIST;
	}
	

	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	public void deleteProduct(Product product) {
		
	}

}
