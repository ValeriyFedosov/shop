
package edu.karazin.shop.service;

import java.util.ArrayList;
import java.util.List;

import edu.karazin.shop.dto.ProductDto;
import edu.karazin.shop.utils.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class InMemoryCartStore implements CartStore {

	private final List<ProductDto> products = new ArrayList<>();

	@Autowired
	private ProductUtil productUtil;

	@Override
	public List<ProductDto> getProducts() {
		return products;
	}

	@Override
	public void addProduct(ProductDto prod) {
		if (products.contains(prod)) {
			System.out.println(products.get((int) prod.getId()-1));
			productUtil.count(products.get((int) prod.getId()-1));
            System.out.println(products.get((int) prod.getId()-1));
        } else {
            products.add(prod);
        }
	}

	@Override
	public void removeProduct(ProductDto prod) {
		products.remove(prod);
	}

    @Override
    public void removeAll() {
        products.clear();
    }

}
