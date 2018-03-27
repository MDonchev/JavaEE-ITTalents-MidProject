package model.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Product;

public class ProductDao {

	List<Product> productsInDb; //It is as if this is our database for Products
	
	public ProductDao() {
		this.productsInDb = new ArrayList();
	}
	
	public List<Product> getAllProducts() {
		return Collections.unmodifiableList(this.productsInDb);
	}

	public void insertProduct(Product product) {
		if(!this.productsInDb.contains(product)) {
			this.productsInDb.add(product);
		}
	}

	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	public void deleteProduct(Product product) {
		this.productsInDb.remove(product);
	}

}
