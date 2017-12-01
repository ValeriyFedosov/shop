package edu.karazin.shop.web;

import edu.karazin.shop.service.CartStore;
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

	@Autowired
	private ProductService productService;

	@Autowired
	private CartStore cartStore;

//	public ProductListController(@Autowired ProductService productService) {
//		this.productService = productService;
//	}


	@GetMapping
	public String listProducts(Model model,
			@RequestParam(name = "searchText", required = false) String searchText) {
		model.addAttribute("products", productService.searchProducts(null));
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
