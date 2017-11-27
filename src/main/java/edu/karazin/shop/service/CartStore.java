package edu.karazin.shop.service;

import java.util.List;

import edu.karazin.shop.model.Product;

public interface CartStore {

	List<Product> getProducts();

	void addProduct(Product prod);

	void removeProduct(Product prod);
}
