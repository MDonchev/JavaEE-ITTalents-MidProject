package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import controller.manager.DBManager;
import customExceptions.LoginException;
import customExceptions.RegistrationException;
import model.BoxOfCharcoalCubes;
import model.BoxOfCharcoalCubes.NumberOfPieces;
import model.Hookah;
import model.Hookah.HookahSize;
import model.Order;
import model.Product;
import model.Tobacco;
import model.Tobacco.TobaccoFlavor;
import validate.Validation;
import model.User;

public final class UserDao implements IUserDao{
	
	private static UserDao instance;
	private Connection connection = DBManager.getInstance().getConnection();
	
	
	public static synchronized UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}


	@Override
	public User getUserFromLogin(String username, String password) throws SQLException, LoginException{
		User user = null;
		String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, username);
			ps.setString(2, password);
			
			try(ResultSet rs = ps.executeQuery();){
				if(rs.next()) {
					user = getUserById(rs.getInt(1));
				}
			}
		}
		return user;
	}


	@Override
	public User getUserById(int userId) throws SQLException {
		User user = null;
		String sql = "SELECT id, username, email, password, address, phone_number FROM users WHERE id = ?;";
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, userId);
			try(ResultSet rs = ps.executeQuery();){
				
				rs.next();
				
				Set<Product> favs = new HashSet<Product>(getFavouritesByUserId(userId));
				ArrayList<Order> orders = new ArrayList<Order>(getOrdersById(userId));
				
				
				user = new User(rs.getString("username"),
								rs.getString("address"),
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("number"),
								favs,
								new HashMap<Product,Integer>(),
								orders
								);
			}
		}
		return user;
	}


	private ArrayList<Order> getOrdersById(int userId) throws SQLException {
		ArrayList<Order> orders = new ArrayList<>();
		
		// TODO
		
		return orders;
	}


	private Set<Product> getFavouritesByUserId(int userId) throws SQLException{
		
		Set<Product> products = new HashSet<>();
		String sql = "SELECT name, description, price, category, hookah_size, tobacco_flavor, number_of_cubes FROM products AS p JOIN users_have_favorite_products AS fav ON fav.products_id = p.id WHERE fav.users_id = ?;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, userId);
			
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
				
		return products;
	}


	public synchronized void saveUser(User u) throws SQLException, RegistrationException {
		tryRegistrateUser(u);
		
		String sql = "INSERT INTO users (username,email,password,address,phone_number) VALUES (?,?,?,?,?);";
		
		PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, u.getName());
		ps.setString(2, u.getEmail());
		ps.setString(3, u.getPassword());
		ps.setString(4, u.getAddress());
		ps.setString(5, u.getNumber());
		
		
	}
	
	private boolean validate(String name, String password, String address, String email, String number) {
		return Validation.usernameValidation(name) &&
				Validation.passwordValidation(password) &&
				Validation.stringValidation(address) &&
				Validation.mailValidation(email) &&
				Validation.numberValidation(number);
	}
	private void tryRegistrateUser(User u) throws SQLException, RegistrationException {
		
		if (!validate(u.getName(), u.getPassword(), u.getAddress(), u.getEmail(), u.getNumber())) {
			throw new RegistrationException("Invalid data passed.");
		}
	}
}
