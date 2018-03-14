package main;
import java.util.Set;

import users.User;

public class Demo {
	
	public static Set<User> users;
	
	public static void main(String[] args) {
		
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
		if (!email.matches("[\\w|.|-]*@\\w*\\.[\\w|.]+)")) return false;
		if (password.length() < 5) return false;
		
		return true;
	}
}
