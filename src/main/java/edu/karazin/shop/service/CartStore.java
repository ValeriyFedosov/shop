package edu.karazin.shop.service;

import edu.karazin.shop.model.BasketItem;

import java.util.List;

public interface CartStore {

	List<BasketItem> getProducts();

	void addProduct(BasketItem prod);

	void removeProduct(BasketItem prod);

	void removeAll();

	double getTotalCost();

	Long getTotalAmount();

	void setProducts(List<BasketItem> products);

}
