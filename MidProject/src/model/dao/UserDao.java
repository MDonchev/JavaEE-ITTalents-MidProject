package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
	
	private UserDao() {}
	
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
		String sql = "SELECT id, username, email, password, address, phone_number, balance FROM users WHERE id = ?;";
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
								rs.getString("phone_number"),
								rs.getDouble("balance"),
								favs,
								new HashMap<Product,Integer>(),
								orders
								);
			}
		}
		return user;
	}

	@Override
	public void insertProductByUserId(int userId, Product product, HookahSize size, TobaccoFlavor flavor, NumberOfPieces number) throws SQLException {
		String sql = "INSERT INTO products (name, description, price, category, hookah_size, tobacco_flavor, number_of_cubes, users_id) "
				     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setFloat(3, (float)product.getPrice());
			ps.setString(4, product.getCategory().toString());
			ps.setInt(8, userId);
			switch (product.getCategory()) {
				case HOOKAH: ps.setString(5, size.toString()); ps.setString(6, null); ps.setString(7, null); break;
				case TOBACCO: ps.setString(5, null); ps.setString(6, flavor.toString()); ps.setString(7, null); break;
				case BOX_OF_CHARCOAL_CUBES: ps.setString(5, null); ps.setString(6, null); ps.setString(7, number.toString()); break;
			default:
				break;
			}
			
		}
	}

	private ArrayList<Order> getOrdersById(int userId) throws SQLException{
		ArrayList<Order> orders = new ArrayList();
		
		//orders.add(new Order(dateAndTimeOfOrder, orderedProducts));
		String allOrderDatesOfUserQuery = "SELECT date_of_issue FROM orders WHERE user_id = ?";
		try(PreparedStatement ps = connection.prepareStatement(allOrderDatesOfUserQuery);){
			ps.setInt(1, userId);
			
			try(ResultSet rs = ps.executeQuery();){
					while(rs.next()) {
						java.sql.Date dateOfIssue = rs.getDate("date_of_issue");
						HashMap<Product, Integer> productMap = new HashMap();
						
						String allOrderedProductsFromUserOnGivenDateQuery = "SELECT name, description, price, category, hookah_size, tobacco_flavor, number_of_cubes "
																			+ "FROM users u JOIN orders o ON u.id = o.user_id "
																			+ "JOIN products p ON o.id = orders_id "
																			+ "WHERE o.date_of_issue = ? AND u.id = ?";
						try(PreparedStatement ps1 = connection.prepareStatement(allOrderedProductsFromUserOnGivenDateQuery);){
							ps1.setDate(1, dateOfIssue);
							ps1.setInt(2, userId);
							
							try(ResultSet rs1 = ps1.executeQuery();){
								while(rs1.next()) {
									Product currentProduct = null;
									
									switch (rs1.getString("catagory")) {
										case "Hookah":
											currentProduct = new Hookah(rs1.getString("name"), rs1.getString("description"), (double)rs1.getFloat("price"), HookahSize.valueOf(rs1.getString("hookah_size")));
											break;
										case "Tobacco":
											currentProduct = new Tobacco(rs1.getString("name"), rs1.getString("description"), (double)rs1.getFloat("price"), TobaccoFlavor.valueOf(rs1.getString("tobacco_flavor")));
											break;
										case "BoxOfCubes":
											currentProduct = new BoxOfCharcoalCubes(rs1.getString("name"), rs1.getString("description"), (double)rs1.getFloat("price"), NumberOfPieces.valueOf(rs1.getString("number_of_cubes")));
											break;
										default:
											break;
									}
									if(!productMap.containsKey(currentProduct)) {
										productMap.put(currentProduct, 0);
									}
									productMap.put(currentProduct, productMap.get(currentProduct) + 1);
								
							}
						}
					}
					orders.add(new Order(dateOfIssue.toLocalDate(), productMap));	
				}
			}
		}
		return orders;
	}
	
	public ArrayList<Product> getAllOrderedProductsById(int userId) throws SQLException {
		ArrayList<Product> ordered = new ArrayList<>();
		
		// TODO
		
		String sql = "SELECT name, description, price, category, hookah_size, tobacco_flavor, number_of_cubes "
					 + "FROM orders o "
					 + "JOIN products p ON o.id = p.orders_id "
					 + "WHERE o.user_id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, userId);
			
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					switch (rs.getString("catagory")) {
					case "Hookah":
						ordered.add(new Hookah(rs.getString("name"), rs.getString("description"), (double)rs.getFloat("price"), HookahSize.valueOf(rs.getString("hookah_size"))));
						break;
					case "Tobacco":
						ordered.add(new Tobacco(rs.getString("name"), rs.getString("description"), (double)rs.getFloat("price"), TobaccoFlavor.valueOf(rs.getString("tobacco_flavor"))));
						break;
					case "BoxOfCubes":
						ordered.add(new BoxOfCharcoalCubes(rs.getString("name"), rs.getString("description"), (double)rs.getFloat("price"), NumberOfPieces.valueOf(rs.getString("number_of_cubes"))));
						break;
					default:
						break;
					}
				}
			}
		}
		
		return ordered;
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
