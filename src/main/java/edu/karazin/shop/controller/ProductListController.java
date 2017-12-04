package edu.karazin.shop.controller;

import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.web.ProductSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.karazin.shop.service.ProductService;

@Controller
@RequestMapping("products")
public class ProductListController {

	private static final Logger log = LoggerFactory.getLogger(ProductListController.class);

	private final ProductService productService;
	private final CartStore cartStore;

	public ProductListController(@Autowired ProductService productService, @Autowired CartStore cartStore) {
		this.productService = productService;
		this.cartStore = cartStore;
	}


	@GetMapping
	public String listProducts(Model model,
			@RequestParam(name = "searchText", required = false) String searchText) {
		model.addAttribute("products", productService.getAll());
		model.addAttribute("searchForm", new ProductSearchForm(searchText));
        model.addAttribute("cart", cartStore.getTotalAmount());
		return "product-list";
	}

	@PostMapping
	public String searchProducts(Model model,
			@ModelAttribute("searchForm") ProductSearchForm form) {
		model.addAttribute("products", productService.searchProducts(form.getSearchText()));
        model.addAttribute("cart", cartStore.getTotalAmount());
		return "product-list";
	}
}
