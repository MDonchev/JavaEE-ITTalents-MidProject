package users;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.Demo;
import products.Product;

public class User {
	
	private String name;
	private String address;
	private String email;
	private String password;
	private String number;
	private boolean isLogged;
	private boolean isAdmin;
	
	private Set<Product> favourite;
	private Map<products.Product, Integer> cart;
	
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
		for(User u : Demo.users) {
			if(u.getEmail().equals(email)) {
				if(u.getPassword().equals(password)) {
					copyData(u);
					this.isLogged = true;
					this.cart = new HashMap<>();
					System.out.println("Welcome, " + this.getName());
					return;
				}
			}
		}
		System.out.println("Wrong data or haven't register");
	}
	
	private void copyData(User user) {
		this.name = user.getName();
		this.address = user.getAddress();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.number = user.getNumber();
		this.isAdmin = user.getAdmin();
		this.favourite = user.getFavourites();
	}
	
	public void addToCart(Product product, int count) {
		if (isLogged) {
			if(Demo.availableProducts.get(product) >= count) {
				this.cart.put(product, count);
				Demo.availableProducts.put(product, Demo.availableProducts.get(product) - count);
			}
			else {
				System.out.println("Sorry, not enough to order");
			}
		}
		else {
			System.out.println("Sorry, you're not logged. Please, login to order.");
		}
	}
	
	public void addProduct(Product product, int count) {
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
	public void logout() {
		this.isLogged = false;
	}
	public void viewProfile() {
		if (isLogged) {
			System.out.println(this);	
		}
		else {
			System.out.println("Not logged.");
		}
	}
	@Override
	public String toString() {
		return this.name  + " " + this.email + " " +isAdmin;
	}
	public Set<Product> getFavourites() {
		return this.favourite;
	}

	public boolean getAdmin() {
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
	public String getPassword() {
		return this.password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
