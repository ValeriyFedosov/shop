
package edu.karazin.shop.service;

import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.converter.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class InMemoryCartStore implements CartStore {

	private final List<BasketItem> products = new ArrayList<>();
	private long totalCost;
	private long totalAmount;

	private final ProductUtil productUtil;

	public InMemoryCartStore(@Autowired ProductUtil productUtil) {
		this.productUtil = productUtil;
	}

	@Override
	public List<BasketItem> getProducts() {
		return products;
	}


	@Override
	public void addProduct(BasketItem prod) {
		if (products.contains(prod)) {
			productUtil.increaseProduct(prod, products);
        } else {
			prod.setCountOfCost(prod.getProduct().getCost());
			prod.setCountOfProducts(prod.getCountOfProducts() + 1);
            products.add(prod);
        }
        ++totalAmount;
        totalCost+=prod.getProduct().getCost();
    }

	@Override
	public void removeProduct(BasketItem prod) {
        for (BasketItem basketItem : products) {
            if(basketItem.equals(prod)){
                totalAmount-=basketItem.getCountOfProducts();
            }
        }
	    products.remove(prod);
	    totalCost -= prod.getProduct().getCost() * prod.getCountOfProducts();
	}

    @Override
    public void removeAll() {
        totalAmount = 0;
        totalCost = 0;
	    products.clear();
    }


    @Override
    public Long getTotalCost() {
        return totalCost;
    }


	@Override
	public Long getTotalAmount() {
		return totalAmount;
	}

}
