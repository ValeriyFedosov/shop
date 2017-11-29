package edu.karazin.shop.service;

import edu.karazin.shop.dto.ProductDto;
import edu.karazin.shop.model.Product;

import java.util.List;

public interface CartStore {

	List<ProductDto> getProducts();

	void addProduct(ProductDto prod);

	void removeProduct(ProductDto prod);

	void removeAll();
}
