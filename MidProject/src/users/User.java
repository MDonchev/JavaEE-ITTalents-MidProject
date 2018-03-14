package users;

import java.util.HashSet;
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
	
	protected Set<Product> favourite;
	
	public User() {}
		
	public User(String name, String address, String email, String password, String number, boolean isAdmin) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.number = number;
		this.isAdmin = isAdmin;
		this.favourite = new HashSet<>();
		this.isLogged = false;
	}

	
	public void login(String email, String password) {
		for(User u : Demo.users) {
			if(u.getEmail().equals(email)) {
				if(u.getPassword().equals(password)) {
					this.address = u.getAddress();
					//TODO
				}
			}
		}
	}
	
	public Set<Product> getFavourite() {
		return favourite;
	}

	public void setFavourite(Set<Product> favourite) {
		this.favourite = favourite;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getNumber() {
		return number;
	}

	public boolean isLogged() {
		return isLogged;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void logout() {
		
	}
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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
