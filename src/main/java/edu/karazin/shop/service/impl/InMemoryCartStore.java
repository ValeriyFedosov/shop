
package edu.karazin.shop.service.impl;

import edu.karazin.shop.model.InMemoryBasketItem;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.util.ProductUtil;
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

	private final List<InMemoryBasketItem> products = new ArrayList<>();
	private double totalCost;
	private long totalAmount;

	private final ProductUtil productUtil;

	public InMemoryCartStore(@Autowired ProductUtil productUtil) {
		this.productUtil = productUtil;
	}

	@Override
	public List<InMemoryBasketItem> getProducts() {
		return products;
	}


	@Override
	public void addProduct(InMemoryBasketItem prod) {
		if (products.contains(prod)) {
			productUtil.addTheSameProductToCart(prod, products);
        } else {
			prod.setCountOfCost(prod.getProduct().getCost());
			prod.setCountOfProducts(prod.getCountOfProducts() + 1);
            products.add(prod);
        }
        ++totalAmount;
        totalCost+=prod.getProduct().getCost();
    }

	@Override
	public void removeProduct(InMemoryBasketItem prod) {
        for (InMemoryBasketItem inMemoryBasketItem : products) {
            if(inMemoryBasketItem.equals(prod)){
                totalAmount-= inMemoryBasketItem.getCountOfProducts();
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
    public double getTotalCost() {
        return totalCost;
    }


	@Override
	public Long getTotalAmount() {
		return totalAmount;
	}

}
