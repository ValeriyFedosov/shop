package edu.karazin.shop.converter;

import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductUtil {

    @Autowired
    private ProductService productService;

    public ProductUtil() {
    }

    public BasketItem convertEntityToBasketItem(Product product) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(productService.getProduct(product.getId()));
        return basketItem;
    }

    public void increaseProduct(BasketItem prod, List<BasketItem> basketItems) {
        for (BasketItem basketItem : basketItems) {
            if(basketItem.equals(prod)){
                basketItem.setCountOfCost(basketItem.getCountOfCost() + basketItem.getProduct().getCost());
                basketItem.setCountOfProducts(basketItem.getCountOfProducts() + 1);
            }
        }
    }

}
