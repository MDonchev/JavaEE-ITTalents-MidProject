package model;

import customExceptions.LoginException;
import main.Demo;

public final class Admin extends User {

	public Admin(String name, String address, String email, String password, String number) {
		super(name, address, email, password, number, true);
	}

	@Override
	public boolean isAdmin() {
		return true;
	}

	@Override
	public void addProductToCatalog(Product product, int count) {
		
		if(Demo.availableProducts.get(product) == null) {
			Demo.availableProducts.put(product, 0);
		}
		Demo.availableProducts.put(product, Demo.availableProducts.get(product) + count);
	}

	@Override
	public void viewProfile() {
		System.out.println(this);
	}

	@Override
	protected void tryLoginUser(String email, String password) throws LoginException {
		throw new LoginException("Already Logged.");
	}

	@Override
	public boolean isLogged() {
		return true;
	}

}
