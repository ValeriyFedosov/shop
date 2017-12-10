package edu.karazin.shop.service;

import edu.karazin.shop.model.InMemoryBasketItem;

import java.util.List;

public interface CartStore {

	List<InMemoryBasketItem> getProducts();

	void addProduct(InMemoryBasketItem prod);

	void removeProduct(InMemoryBasketItem prod);

	void removeAll();

	double getTotalCost();

	Long getTotalAmount();
}
