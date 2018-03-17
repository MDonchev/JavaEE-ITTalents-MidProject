package users;

import main.Demo;
import products.Product;

public final class Admin extends User {

	public Admin(String name, String address, String email, String password, String number) {
		super(name, address, email, password, number, true);
		// TODO Auto-generated constructor stub
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

}
