package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.manager.DBManager;
import model.BoxOfCharcoalCubes;
import model.Hookah;
import model.Order;
import model.Product;
import model.Tobacco;
import model.BoxOfCharcoalCubes.NumberOfPieces;
import model.Hookah.HookahSize;
import model.Tobacco.TobaccoFlavor;

public class OrderDao {

	private static OrderDao instance;
	private Connection connection = DBManager.getInstance().getConnection();
	
	private OrderDao() {}
	
	public static synchronized OrderDao getInstance() {
		if(instance == null) {
			instance = new OrderDao();
		}
		return instance;
	}
	
	public List<Product> getProductsByOrderId(int orderId) throws SQLException{
		ArrayList<Product> products = new ArrayList();
		
		String sql = "SELECT name, description, price, category FROM products WHERE orders_id = ?;";
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, orderId);
			
			try(ResultSet rs = ps.executeQuery();){
				while(rs.next()) {
					switch (rs.getString("catagory")) {
					case "Hookah":
						products.add(new Hookah(rs.getString("name"), rs.getString("description"), (double)rs.getFloat("price"), HookahSize.valueOf(rs.getString("hookah_size"))));
						break;
					case "Tobacco":
						products.add(new Tobacco(rs.getString("name"), rs.getString("description"), (double)rs.getFloat("price"), TobaccoFlavor.valueOf(rs.getString("tobacco_flavor"))));
						break;
					case "BoxOfCubes":
						products.add(new BoxOfCharcoalCubes(rs.getString("name"), rs.getString("description"), (double)rs.getFloat("price"), NumberOfPieces.valueOf(rs.getString("number_of_cubes"))));
						break;
					default:
						break;
					}
				}
			}
		}
		
		return Collections.unmodifiableList(products);
		
	}
	
	public void insertOrder(Order order) {
//		String sql = "INSERT INTO ordres";
	}

	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
	}

	public void deleteOrder(Order order) {
		
	}

}
