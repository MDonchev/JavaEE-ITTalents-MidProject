package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import customExceptions.LoginException;
import customExceptions.OrderException;

public class User {
	
	private int userId;
	private String name;
	private String address;
	protected String email;
	private String password;
	protected String number;
	private double balance;

	private Set<Product> favourite = new HashSet<Product>();
	private Map<model.Product, Integer> cart = new HashMap<Product, Integer>();
	private ArrayList<Order> orderHistory = new ArrayList<Order>();
	
	
	public User(int userId, String name, String address, String email, String password, String number, double balance, Set<Product> favourites, Map<Product, Integer> cart, ArrayList<Order> orders) {
		this(name, address, email, password, number, balance, favourites, cart, orders);
		this.userId = userId;
	}

	public User(String name, String address, String email, String password, String number, double balance, Set<Product> favourites, Map<Product, Integer> cart, ArrayList<Order> orders) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.number = number;
		this.balance = balance;
		this.favourite = favourites;
		this.cart = cart;
		this.orderHistory = orders;
	}
	
	/*
	
	public void addToCart(Product product, int count) {
		try {
			tryToAddToCart(product, count);
			this.cart.put(product, count);
		} catch (OrderException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void tryToAddToCart(Product product, int count) throws OrderException{

		if(!Demo.availableProducts.containsKey(product)) {
			throw new OrderException("Product is not listed in the catalog!!!");
		}
		if(!(Demo.availableProducts.get(product) >= count)) {
			throw new OrderException("Sorry, not enough to order!!!");
		}
	}
	
	
	public void makeOrder() {
		Order order = new Order(this);
		for(Map.Entry<Product, Integer> entry : this.cart.entrySet()) {
			Demo.availableProducts.put(entry.getKey(), Demo.availableProducts.get(entry.getKey()) - entry.getValue());
			order.addProduct(entry.getKey(), entry.getValue());
		}
		
		this.cart = new HashMap<Product, Integer>();
		
		order.finalizeOrder();
		this.orderHistory.add(order);
	}
	
	public void addToFavorites(Product product) {
		this.favourite.add(product);
	}
	
	public void logout() {
		System.out.println("Bye, " + Demo.currentUser.getName());
		Demo.currentUser = Demo.guest;
		Demo.currentUser.emptyCart();
	}
	
	protected boolean checkPassword(String password) {
		return this.password.equals(password);
	}*/
	
	@Override
	public String toString() {
		return this.name  + " " + this.email + " "  + "\nOrder History:\n" + this.orderHistory;
	}
	public Set<Product> getFavourites() {
		return Collections.unmodifiableSet(this.favourite);
	}

	public Map<model.Product, Integer> getCart() {
		return cart;
	}
	
	public String getNumber() {
		return this.number;
	}

	public String getAddress() {
		return this.address;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}
	public String getPassword() {
		return this.password;
	}
	public int getUserId() {
		return userId;
	}
	public void emptyCart() {
		this.cart = new HashMap<>();
	}
}
