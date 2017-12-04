package edu.karazin.shop.converter;

import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductUtil {

    private  ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    public ProductService getProductService() {
        return productService;
    }

    public BasketItem convertEntityToBasketItem(Product product) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(productService.getProduct(product.getId()));
        return basketItem;
    }

    public void increaseProduct(BasketItem prod, List<BasketItem> basketItems) {
        for (BasketItem basketItem : basketItems) {
            if (basketItem.equals(prod)) {
                basketItem.setCountOfCost(basketItem.getCountOfCost() + basketItem.getProduct().getCost());
                basketItem.setCountOfProducts(basketItem.getCountOfProducts() + 1);
            }
        }
    }

    public boolean checkForExistance(Long prodId) {
        if (productService.getProduct(prodId).getBalance() != 0) {
            Product product = productService.getProduct(prodId);
            product.setBalance(product.getBalance() - 1);
            productService.updateProduct(product);
            return true;
        }
        return false;
    }

    public boolean checkForExistanceForCart(List<BasketItem> products, CartStore cartStore) {
        for (BasketItem basketItem : cartStore.getProducts()) {
            if (!(basketItem.getCountOfProducts() <= basketItem.getProduct().getBalance()))
                return false;
        }
        for (BasketItem basketItem : products) {
            basketItem.getProduct().setBalance(basketItem.getProduct().getBalance() - basketItem.getCountOfProducts());
            productService.updateProduct(basketItem.getProduct());
        }
        return true;
    }
}
