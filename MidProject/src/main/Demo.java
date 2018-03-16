package main;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import products.Hookah;
import products.Hookah.HookahSize;
import products.Product;
import users.Admin;
import users.Guest;
import users.Member;
import users.User;

public class Demo {
	
	public static Map<String, User> users = new HashMap<>();
	public static Map<Product, Integer> availableProducts = new HashMap<>();
	public static final User guest = new Guest("Guest");
	public static User currentUser = Demo.guest;
	public static void main(String[] args) {
		
		currentUser.addProductToCatalog(new Hookah("Rashad", 124.5, HookahSize.MEDIUM), 3);
		currentUser.addToCart(new Hookah("reer", 124.5, HookahSize.MEDIUM), 5);
		currentUser.login("qwe@abv.bg", "123456");
		register("araee", "123456", "ul.2", "qwe@abv.bg", "0883475671", false);
		currentUser.login("qwe@abv.bg", "123456");
		currentUser.addToCart(new Hookah("reer", 124.5, HookahSize.MEDIUM), 1);
		register("eaaw", "qwerty", "smth.2", "admin@admin.com", "0812345678", true);
		currentUser.login("admin@admin.com", "qwerty");
		currentUser.logout();
		currentUser.login("admin@admin.com", "qwerty");
		currentUser.addProductToCatalog(new Hookah("Rashad", 124.5, HookahSize.MEDIUM), 3);
		currentUser.addProductToCatalog(new Hookah("Abbot", 300.5, HookahSize.LARGE), 5);
		currentUser.logout();
		currentUser.login("qwe@abv.bg", "123456");
		currentUser.addToCart(new Hookah("Rashad", 124.5, HookahSize.MEDIUM), 1);
		currentUser.addToCart(new Hookah("Abbot", 300.5, HookahSize.LARGE), 3);
		currentUser.makeOrder();
		currentUser.viewProfile();
		currentUser.logout();
		currentUser.viewProfile();
		currentUser.addToCart(new Hookah("Rashad", 124.5, HookahSize.MEDIUM), 1);
		currentUser.login("qwe@abv.bg", "123456");
		currentUser.logout();
		System.out.println(currentUser.getCart());
	}
	
	static void register(String name, String password, String address, String email, String number, boolean isAdmin) {
		// TODO : make it better; almost 1 A.M. and so sleepy -> making bullshits
		if (validate(name,password,address,email,number)) {
			if (!users.keySet().contains(email)) {
				//users.add(u);
				if (isAdmin) {
					users.put(email, new Admin(name, address, email, password, number));
				}
				else {
					users.put(email, new Member(name, address, email, password, number));
				}
			}
			else {
				System.out.println("Can not registrate with this email. Already has registred user.");
			}
		}
		else {
			System.out.println("Invalid data.");
		}
	}

	private static boolean validate(String name, String password, String address, String email, String number) {
		if (name.equals(null) || password.equals(null) || address.equals(null) || email.equals(null) || number.equals(null)) return false;
		if (!number.matches("08[0-9]{8}")) return false;
		if (!email.matches("[\\w|.|-]*@\\w*\\.[\\w|.]+")) return false;
		if (password.length() < 5) return false;
		
		return true;
	}
}
