package controller.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import customExceptions.LoginException;
import customExceptions.RegistrationException;
import model.Order;
import model.Product;
import model.User;
import model.dao.UserDao;

public class UserManager implements IUserManager{
	
	
	private volatile static UserManager instance;
	private UserDao userDao = UserDao.getInstance();

	private UserManager() {}
	
	public synchronized static UserManager getManager() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	
	public User login(String username, String password) {
		try {
			return this.userDao.getUserFromLogin(username, password);
		}
		catch (SQLException | LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean register (String name, String email, String address, String password, String number, double balance) {
		User u = null;
		try {
			Set<Product> fav = new HashSet<Product>();
			Map<Product, Integer> cart = new HashMap<Product, Integer>();
			ArrayList<Order> ord = new ArrayList<Order>();
			u = new User(name, address, email, password, number, balance, fav, cart, ord);
			this.userDao.saveUser(u);
			return true;
		}
		catch (SQLException | RegistrationException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public void addProductToCart(User user, Product product, int count) {
		Map<Product, Integer> userCart = user.getCart();
		if(!userCart.containsKey(product)) {
			userCart.put(product, 0);
		}
		userCart.put(product, userCart.get(product) + count);
	}
	
	public void addToFavorites(User user, Product product) {
		user.getFavourites().add(product);
		try {
			userDao.addProductToFavoritesByIds(user.getUserId(), product.getProductId());
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public List<Product> getAllOrderedProducts(User user) {
		try {
			return userDao.getAllOrderedProductsById(user.getUserId());
		} catch (SQLException e) {
			return Collections.EMPTY_LIST;
		}
	}
	
}
