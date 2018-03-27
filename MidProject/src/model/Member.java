package users;

import customExceptions.LoginException;
import products.Product;

public final class Member extends User {

	public Member(String name, String address, String email, String password, String number) {
		super(name, address, email, password, number, false);
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

	@Override
	public void addProductToCatalog(Product product, int count) {
		System.out.println("Member; not admin");
	}
	
	@Override
	public void viewProfile() {
		System.out.println(this);
	}

	@Override
	protected void tryLoginUser(String email, String password) throws LoginException{
		throw new LoginException("Already Logged.");
	}

	@Override
	public boolean isLogged() {
		return true;
	}
	
	
}
