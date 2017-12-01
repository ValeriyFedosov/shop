package edu.karazin.shop.service;

import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.Product;

import java.util.List;

public interface ProductService {

	Product getProduct(Long id);

	List<Product> searchProducts(String searchText);

	Long addProduct(Product prod);

	void updateProduct(Product prod);

	void removeProduct(Long id);

	BasketItem getBasketItems(Long id);
}
