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
import model.BoxOfCharcoalCubes;
import model.Hookah;
import model.Product;
import model.Tobacco;
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
	
	public void insertHookah(Hookah hookah, User user) throws SQLException {
		String sql = "INSERT INTO products (name, description, price, category, hookah_size, tobacco_flavor, number_of_cubes, users_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, hookah.getName());
			ps.setString(2, hookah.getDescription());
			ps.setFloat(3, (float)hookah.getPrice());
			ps.setString(4, hookah.getCategory().name());
			ps.setString(5, hookah.getSize().name());
			ps.setString(6, null);
			ps.setString(7, null);
			ps.setInt(8, user.getUserId());
			
		}
	}
	
	public void insertTobacco(Tobacco tobacco, User user) throws SQLException {
		String sql = "INSERT INTO products (name, description, price, category, hookah_size, tobacco_flavor, number_of_cubes, users_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, tobacco.getName());
			ps.setString(2, tobacco.getDescription());
			ps.setFloat(3, (float)tobacco.getPrice());
			ps.setString(4, tobacco.getCategory().name());
			ps.setString(5, null);
			ps.setString(6, tobacco.getFlavor().name());
			ps.setString(7, null);
			ps.setInt(8, user.getUserId());
			
		}
	}
	
	public void insertBoxOfCharcoalCubes(BoxOfCharcoalCubes box, User user) throws SQLException {
		String sql = "INSERT INTO products (name, description, price, category, hookah_size, tobacco_flavor, number_of_cubes, users_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, box.getName());
			ps.setString(2, box.getDescription());
			ps.setFloat(3, (float)box.getPrice());
			ps.setString(4, box.getCategory().name());
			ps.setString(5, null);
			ps.setString(6, null);
			ps.setString(7, box.getNumber().name());
			ps.setInt(8, user.getUserId());
			
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
