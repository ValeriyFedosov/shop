package edu.karazin.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import edu.karazin.shop.model.Product;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class InMemoryCartStore implements CartStore {

	private final List<Product> products = new ArrayList<>();

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void addProduct(Product prod) {
		products.add(prod);
	}

	@Override
	public void removeProduct(Product prod) {
		products.remove(prod);
	}
}
