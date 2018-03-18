package users;

import customExceptions.LoginException;
import main.Demo;
import products.Product;

public final class Guest extends User {

	public Guest(String name) {
		super(name, null, null, null, null, false);
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

	@Override
	public void addProductToCatalog(Product product, int count) {
		System.out.println("Guest; Not admin.");
	}
	
	@Override
	public boolean isLogged() {
		return false;
	}
	
	@Override
	public void logout() {
		System.out.println("Not login, to logout");
	}

	@Override
	public void viewProfile() {
		System.out.println("You are guest. Please login.");
	}
	@Override
	public void addToFavorites(Product product) {
		System.out.println("You're guest. Please login.");
	}

	@Override
	protected void tryLoginUser(String email, String password) throws LoginException {

		if(Demo.users.get(email) == null) throw new LoginException("No user registered with such email");
		if(!Demo.users.get(email).checkPassword(password)) throw new LoginException("Wrong password!!!");
		
	}
	
}
