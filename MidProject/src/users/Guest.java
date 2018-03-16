package users;

import products.Product;

public class Guest extends User {

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
		System.out.println("Not logged.");
	}
}
