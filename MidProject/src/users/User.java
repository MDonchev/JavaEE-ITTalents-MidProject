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

public abstract class User {
	
	private String name;
	private String address;
	private String email;
	private String password;
	private String number;

	private HashSet<Product> favourite = new HashSet<Product>();
	protected HashMap<products.Product, Integer> cart = new HashMap<Product, Integer>();
	private ArrayList<Order> orderHistory = new ArrayList<Order>();
	
	public abstract void addProductToCatalog(Product product, int count);
	public abstract boolean isAdmin();
	public abstract void viewProfile();
	
	public boolean isLogged() {
		return true;
	}
	
	public User(String name, String address, String email, String password, String number, boolean isAdmin) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.number = number;
		this.favourite = new HashSet<>();
		this.cart = new HashMap<>();
	}

	
	public void login(String email, String password) {
		
		// loginUser : TODO
		if(Demo.currentUser.isLogged()) {
			System.out.println("Already logged in!");
		}
		if(Demo.users.get(email) == null) {
			System.out.println("No user registered with such email");
			return;
		}
		if(Demo.users.get(email).checkPassword(password)) {
			Demo.currentUser = Demo.users.get(email);
			System.out.println("Welcome, " + Demo.currentUser.getName());
			return;
		}
		else {
			System.out.println("Wrong password");
		}
		
		
	}
	
	
	public void addToCart(Product product, int count) {
		
		// TODO : tryToAdd -> Exception
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
		// TODO : currentUser : cart -> emptyCart
		Demo.currentUser.emptyCart();
		// current user : orderHistory -> new
	}
	
	private boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	@Override
	public String toString() {
		return this.name  + " " + this.email + " " + isAdmin() + "\nOrder History:\n" + this.orderHistory;
	}
	public Set<Product> getFavourites() {
		return Collections.unmodifiableSet(this.favourite);
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
	public void emptyCart() {
		this.cart = new HashMap<>();
	}
}
