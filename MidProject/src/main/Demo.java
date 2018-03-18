package main;
import java.util.HashMap;
import java.util.Map;

import customExceptions.RegistrationException;
import products.Hookah;
import products.Hookah.HookahSize;
import products.Product;
import users.Admin;
import users.Guest;
import users.Member;
import users.User;
import validate.Validation;

public class Demo {
	
	public static Map<String, User> users = new HashMap<>();
	public static Map<Product, Integer> availableProducts = new HashMap<>();
	
	public static final User guest = new Guest("Guest");
	public static User currentUser = Demo.guest;
	
	
	public static void main(String[] args) {
		
		currentUser.addProductToCatalog(new Hookah("Rashad", 124.5, HookahSize.MEDIUM), 3);
		currentUser.addToCart(new Hookah("reer", 124.5, HookahSize.MEDIUM), 5);
		currentUser.login("qwe@abv.bg", "123456");
		register("Member", "M123-aZ", "ul.2", "qwe@abv.bg", "0883475671", false);
		register("araee", "M123-aZ", "ul.2", "qwe@abv.bg", "0883475671", false);
		currentUser.login("qwe@abv.bg", "M123-aZ");
		currentUser.addToCart(new Hookah("reer", 124.5, HookahSize.MEDIUM), 1);
		currentUser.logout();
		register("Admin", "M123-aZ", "smth.2", "admin@admin.com", "0812345678", true);
		currentUser.login("admin@admin.com", "M123-aZ");
		currentUser.addProductToCatalog(new Hookah("Rashad", 124.5, HookahSize.MEDIUM), 3);
		currentUser.addProductToCatalog(new Hookah("Abbot", 300.5, HookahSize.LARGE), 5);
		currentUser.logout();
		currentUser.login("qwe@abv.bg", "M123-aZ");
		currentUser.login("admin@admin.com", "M123-aZ"); // -> already logged.

		currentUser.addToCart(new Hookah("Rashad", 124.5, HookahSize.MEDIUM), 1);
		currentUser.addToCart(new Hookah("Abbot", 300.5, HookahSize.LARGE), 3);
		currentUser.makeOrder();
		currentUser.viewProfile();
		currentUser.logout();
		currentUser.viewProfile();
		currentUser.addToCart(new Hookah("Rashad", 124.5, HookahSize.MEDIUM), 1);
		currentUser.login("qwe@abv.bg", "123456");
		currentUser.logout();

	}
	
	private static void register(String name, String password, String address, String email, String number, boolean isAdmin) {
		try {
			tryRegistrateUser(name, password, address, email, number);
			if (isAdmin) {
				users.put(email, new Admin(name, address, email, password, number));
			}
			else {
				users.put(email, new Member(name, address, email, password, number));
			}
		}
		catch (RegistrationException e) {
			System.out.println(e.getMessage());
		}
	}

	private static boolean validate(String name, String password, String address, String email, String number) {
		return Validation.usernameValidation(name) &&
				Validation.passwordValidation(password) &&
				Validation.stringValidation(address) &&
				Validation.mailValidation(email) &&
				Validation.numberValidation(number);
	}
	private static void tryRegistrateUser(String name, String password, String address, String email, String number) throws RegistrationException {
		
		if (!validate(name, password, address, email, number)) {
			throw new RegistrationException("Invalid data passed.");
		}
		if (users.containsKey(email)) {
			throw new RegistrationException("Already registred user with these e-mail. Put another");
		}
	}
}
