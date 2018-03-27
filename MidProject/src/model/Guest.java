package users;

import java.util.ArrayList;
import java.util.Scanner;

import customExceptions.LoginException;
import customExceptions.RegistrationException;
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
	
	@Override
	public void makeOrder() {
		try {
			this.initializeAccountInfo();
			super.makeOrder();
			this.orderHistory = new ArrayList();
		}
		catch (RegistrationException e) {
			System.out.println("Email or phone number are of invalid format");
		}
	}
	
	//asks for console input of the mandatory account info
	//required to make an order
	public void initializeAccountInfo() throws RegistrationException {
		if(this.email == null || this.number == null) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Input email for guest user:");
			String email = scanner.nextLine();
			System.out.println("Input phone number for guest user:");
			String phone = scanner.nextLine();
			if(validate.Validation.mailValidation(email) && validate.Validation.numberValidation(phone)) {
				this.email = email;
				this.number = phone;
			}
			else {
				throw new RegistrationException("Invalid email, try again");
			}
		}
		else {
			System.out.println("Guest info is initialized");
		}
	}
	
}
