package edu.karazin.shop.service.impl;

import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.model.PurchaseItem;
import edu.karazin.shop.repository.BasketItemRepository;
import edu.karazin.shop.repository.ProductRepository;
import edu.karazin.shop.repository.PurchaseItemRepository;
import edu.karazin.shop.service.ProductService;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.util.ProductUtil;
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
	private final BasketItemRepository basketItemRepository;

	public ProductServiceImpl(@Autowired ProductRepository productRepository, @Autowired PurchaseItemRepository purchaseItemRepository,
							  @Autowired UserService userService, @Autowired BasketItemRepository basketItemRepository) {
		this.productRepository = productRepository;
		this.purchaseItemRepository = purchaseItemRepository;
		this.userService = userService;
		this.basketItemRepository = basketItemRepository;
	}

    @Autowired
	public void setProductUtil(ProductUtil productUtil) {
	    this.productUtil = productUtil;
    }

    @Override
	public BasketItem getBasketItem(Long id) { return productUtil.convertEntityToBasketItem(productRepository.getProductById(id)); }


    @Override
	public Product getProduct(Long id) {
		return productRepository.getProductById(id);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getBasketItem(List<BasketItem> inMemoryBasketItems) {
		List<Product> list = new ArrayList<>();
		for (BasketItem inMemoryBasketItem : inMemoryBasketItems) {
			list.add(productRepository.getProductById(inMemoryBasketItem.getProduct().getId()));
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
        	purchaseItem.setUid(userService.getCurrentAuthenticatedUser());
            purchaseItemRepository.save(purchaseItem);
        }
	}

    @Override
    public void addPurchaseItem(PurchaseItem purchaseItem) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        purchaseItem.setDate(simpleDateFormat.format(new Date().getTime()));
        purchaseItem.setUid(userService.getCurrentAuthenticatedUser());
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

    @Override
    public void saveCart(Long id, List<BasketItem> products) {
	    productUtil.convertProductsToBasketItems(products);
        for (BasketItem product : products) {
            product.setUid(userService.getCurrentAuthenticatedUser());
        }
        basketItemRepository.save(products);
    }


}
