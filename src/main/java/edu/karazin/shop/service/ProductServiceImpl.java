package edu.karazin.shop.service;

import java.util.List;

import javax.transaction.Transactional;

import edu.karazin.shop.dto.ProductDto;
import edu.karazin.shop.utils.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.karazin.shop.dao.ProductDao;
import edu.karazin.shop.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao dao;

	@Autowired
	private ProductUtil productUtil;

	public ProductDto getProductDto(Long id) { return productUtil.convertEntityToDto(dao.findById(id)); }

	@Override
	public Product getProduct(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Product> searchProducts(String searchText) {
		if (searchText == null || searchText.trim().isEmpty()) {
			return dao.findAll();
		}
		return dao.findByText(searchText);
	}

	@Override
	@Transactional
	public Long addProduct(Product prod) {
		return dao.save(prod).getId();
	}

	@Override
	@Transactional
	public void updateProduct(Product prod) {
		dao.save(prod);
	}

	@Override
	@Transactional
	public void removeProduct(Long id) {
		dao.delete(id);
	}
}
