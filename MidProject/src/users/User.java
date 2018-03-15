package users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.Demo;
import order.Order;
import products.Product;

public class User {
	
	private String name;
	private String address;
	private String email;
	private String password;
	private String number;
	private boolean isLogged;
	private boolean isAdmin;
	
	private Set<Product> favourite = new HashSet();
	private Map<products.Product, Integer> cart = new HashMap();
	private ArrayList<Order> orderHistory = new ArrayList();
	
	public User() {}
		
	public User(String name, String address, String email, String password, String number, boolean isAdmin) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.number = number;
		this.isAdmin = isAdmin;
		this.favourite = new HashSet<>();
		this.cart = new HashMap<>();
		this.isLogged = false;
	}

	
	public void login(String email, String password) {
		if(Demo.currentUser.isLogged) {
			System.out.println("Already logged in!");
		}
		if(Demo.users.get(email) == null) {
			System.out.println("No user registered with such email");
			return;
		}
		if(Demo.users.get(email).checkPassword(password)) {
			Demo.currentUser = Demo.users.get(email);
			Demo.currentUser.setLogged(true);
			return;
		}
		else {
			System.out.println("Wrong password");
		}
		
		
	}
	
	
	public void addToCart(Product product, int count) {
		if (isLogged) {
			if(!Demo.availableProducts.containsKey(product)) {
				System.out.println("Product is not listed in catalog");
			}
			else {
				if(Demo.availableProducts.get(product) >= count) {
					this.cart.put(product, count);
				}
				else {
					System.out.println("Sorry, not enough to order");
				}
			}
		}
		else {
			System.out.println("Sorry, you're not logged. Please, login to order.");
		}
	}
	
	public void addProductToCatalog(Product product, int count) {
		if (isLogged) {
			if (isAdmin) {
				if(Demo.availableProducts.get(product) == null) {
					Demo.availableProducts.put(product, 0);
				}
				Demo.availableProducts.put(product, Demo.availableProducts.get(product) + count);
			}
			else {
				System.out.println("Sorry, you're not an admin.");	
			}
		}
		else {
			System.out.println("Sorry, not logged.");
		}
	}
	
	public void makeOrder() {
		Order order = new Order(this);
		for(Map.Entry<Product, Integer> entry : this.cart.entrySet()) {
			Demo.availableProducts.put(entry.getKey(), Demo.availableProducts.get(entry.getKey()) - entry.getValue());
			order.addProduct(entry.getKey(), entry.getValue());
		}
		
		this.cart = new HashMap();
		
		order.finalizeOrder();
		this.orderHistory.add(order);
	}
	
	public void addToFavorites(Product product) {
		this.favourite.add(product);
	}
	
	public void logout() {
		Demo.currentUser = new User();
	}
	public void viewProfile() {
		if (isLogged) {
			System.out.println(this);	
		}
		else {
			System.out.println("Not logged.");
		}
	}
	
	private boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public void setLogged(boolean logged) {
		this.isLogged = logged;
	}
	
	@Override
	public String toString() {
		return this.name  + " " + this.email + " " + isAdmin + "\nOrder History:\n" + this.orderHistory;
	}
	public Set<Product> getFavourites() {
		return Collections.unmodifiableSet(this.favourite);
	}

	public boolean isAdmin() {
		return this.isAdmin;
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
	
}
