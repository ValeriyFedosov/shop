package edu.karazin.shop.service;

import java.util.List;

import edu.karazin.shop.dto.ProductDto;
import edu.karazin.shop.model.Product;

public interface ProductService {

	Product getProduct(Long id);

	List<Product> searchProducts(String searchText);

	Long addProduct(Product prod);

	void updateProduct(Product prod);

	void removeProduct(Long id);

	ProductDto getProductDto(Long id);
}
