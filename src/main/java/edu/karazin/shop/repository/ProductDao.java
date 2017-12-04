package edu.karazin.shop.repository;

import java.util.List;

import edu.karazin.shop.model.Product;

public interface ProductDao {

	Product findById(Long id);

	List<Product> findByText(String searchText);

	List<Product> findAll();

	Product save(Product prod);

	void delete(Long id);
}
