package edu.karazin.shop.service.impl;

import edu.karazin.shop.model.*;
import edu.karazin.shop.repository.PurchaseItemRepository;
import edu.karazin.shop.repository.UserRepository;
import edu.karazin.shop.service.ProductService;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.util.ProductUtil;
import edu.karazin.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private ProductUtil productUtil;
	private final PurchaseItemRepository purchaseItemRepository;
	private final UserService userService;

	public ProductServiceImpl(@Autowired ProductRepository productRepository, @Autowired PurchaseItemRepository purchaseItemRepository,
							  @Autowired UserService userService) {
		this.productRepository = productRepository;
		this.purchaseItemRepository = purchaseItemRepository;
		this.userService = userService;
	}

    @Autowired
	public void setProductUtil(ProductUtil productUtil) {
	    this.productUtil = productUtil;
    }

	public BasketItem getBasketItems(Long id) { return productUtil.convertEntityToBasketItem(productRepository.getProductById(id)); }


    @Override
	public Product getProduct(Long id) {
		return productRepository.getProductById(id);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getBasketItems(List<BasketItem> basketItems) {
		List<Product> list = new ArrayList<>();
		for (BasketItem basketItem : basketItems) {
			list.add(productRepository.getProductById(basketItem.getProduct().getId()));
		}
		return list;
	}

	@Override
    @Transactional
	public void addPurchaseItems(List<PurchaseItem> purchaseItems) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
		String date = simpleDateFormat.format(new Date().getTime());
        for (PurchaseItem purchaseItem : purchaseItems) {
        	purchaseItem.setDate(date);
        	purchaseItem.setUser_id(userService.getCurrentAuthenticatedUser());
            purchaseItemRepository.save(purchaseItem);
        }
	}

    @Override
    public void addPurchaseItem(PurchaseItem purchaseItem) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        purchaseItem.setDate(simpleDateFormat.format(new Date().getTime()));
        purchaseItem.setUser_id(userService.getCurrentAuthenticatedUser());
	    purchaseItemRepository.save(purchaseItem);
    }

    @Override
	public List<Product> searchProducts(String searchText) {
		if (searchText == null || searchText.trim().isEmpty()) {
			return productRepository.findAll();
		}
		return productRepository.findByText(searchText);
	}

	@Override
	@Transactional
	public Long addProduct(Product prod, MultipartFile img) throws IOException {
			for (Product product : getAll()) {
				if (prod.equals(product)) {
					return null;
				}
            }
			prod.setImageName(ProductUtil.imgPersist(img));
            return productRepository.save(prod).getId();
    }

    @Override
    @Transactional
	public void setDiscountForAllProducts(Long discountPercent) {
		for (Product product : getAll()) {
			product.setCost(product.getCost() - (product.getCost() / 100 * discountPercent));
            productRepository.save(product);
		}
	}


    @Override
    @Transactional
    public void updateProduct(Product prod) {
        productRepository.save(prod);
    }

	@Override
	@Transactional
	public void updateProduct(Product prod, MultipartFile img) throws IOException {
        prod.setImageName(ProductUtil.imgPersist(img));
		productRepository.save(prod);
	}


	@Override
	@Transactional
	public void removeProduct(Long id) {
		productRepository.delete(id);
	}

    @Override
    @Transactional
    public void deleteAll() {
        for (Product product : getAll()) {
            removeProduct(product.getId());
        }
    }

}
