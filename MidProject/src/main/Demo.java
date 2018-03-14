package main;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import products.Hookah;
import products.Hookah.HookahSize;
import products.Product;
import users.User;

public class Demo {
	
	public static Set<User> users = new HashSet<>();
	public static Map<Product, Integer> availableProducts = new HashMap<>();
	public static void main(String[] args) {
		User user = new User();
		user.addProduct(new Hookah("asdasd", 123.5, HookahSize.MEDIUM), 2);
		user.addToCart(new Hookah("asdasd", 123.5, HookahSize.MEDIUM), 2);
		user.login("asd@abv.bg", "12345");
		register("Chocho", "12345", "asdasds", "asd@abv.bg", "0894567821", false);
		register("Chocho2", "12345", "asdasds", "qwerty@abv.bg", "0894567821", true);
		System.out.println(users);
		user.login("qwerty@abv.bg", "12345");
		System.out.println(user);
		user.addProduct(new Hookah("asdasd", 123.5, HookahSize.MEDIUM), 2);
		user.login("asd@abv.bg", "12345");
		user.addToCart(new Hookah("asdasd", 123.5, HookahSize.MEDIUM), 1);
		System.out.println(availableProducts);
		user.addToCart(new Hookah("asdasd", 123.5, HookahSize.MEDIUM), 3);
		System.out.println(availableProducts);
	}
	
	static void register(String name, String password, String address, String email, String number, boolean isAdmin) {
		
		User u = new User(name, address, email, password, number, isAdmin);
		
		if (validate(name,password,address,email,number)) {
			if (!users.contains(u)) {
				users.add(u);
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
