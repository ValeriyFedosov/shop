package edu.karazin.shop.service;

import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.model.PurchaseItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

	Product getProduct(Long id);

	List<Product> getAll();

	void addPurchaseItems(List<PurchaseItem> purchaseItems);

	void addPurchaseItem(PurchaseItem purchaseItem);

	List<Product> getBasketItem(List<BasketItem> inMemoryBasketItems);

    BasketItem getBasketItem(Long prodId);

	List<Product> searchProducts(String searchText);

	Long addProduct(Product prod, MultipartFile img) throws IOException;

	void updateProduct(Product prod, MultipartFile img) throws IOException;

	void updateProduct(Product prod);

	void removeProduct(Long id);

	void deleteAll();

	void saveCart(Long id, List<BasketItem> products);
}
