package edu.karazin.shop.service;

import edu.karazin.shop.dao.ProductDao;
import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.converter.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDao dao;
	private final ProductUtil productUtil;

	public ProductServiceImpl(@Autowired ProductDao dao,@Autowired ProductUtil productUtil) {
		this.dao = dao;
		this.productUtil = productUtil;
	}

	public BasketItem getBasketItems(Long id) { return productUtil.convertEntityToBasketItem(dao.findById(id)); }


    @Override
	public Product getProduct(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Product> getAll() {
		return dao.findAll();
	}

	@Override
	public List<Product> getList(List<BasketItem> basketItems) {
		List<Product> list = new ArrayList<>();
		for (BasketItem basketItem : basketItems) {
			list.add(dao.findById(basketItem.getProduct().getId()));
		}
		return list;
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
        try {
            return dao.save(prod).getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    @Override
    @Transactional
    public void deleteAll() {
        for (Product product : getAll()) {
            removeProduct(product.getId());
        }
    }
}
