package edu.karazin.shop.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.karazin.shop.model.Product;
import edu.karazin.shop.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductEditController {

	private static final Logger log = LoggerFactory.getLogger(ProductEditController.class);

	private final ProductService productService;

	public ProductEditController(@Autowired ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String newProduct(Model model) {
		model.addAttribute("product", new Product(null, "", ""));
		return "product-edit";
	}

	@RequestMapping(method = RequestMethod.GET, path = "{id}")
	public String editProduct(Model model, @PathVariable("id") Long productId) {
			Product p = productService.getProduct(productId);
		if (p == null) {
			throw new NotFoundException();
		}
		model.addAttribute("product", p);
		return "product-edit";
	}

	@PostMapping
	public String addProduct(Model model, Product product) {
		productService.addProduct(product);
		return "redirect:/products";
	}

	@PostMapping(params = "update", path = "{id}")
	public String updateProduct(Model model, @PathVariable Long id) {
		productService.updateProduct(productService.getProduct(id));
		return "redirect:/products";
	}

	@GetMapping(params = "delete")
	public String deleteProduct(@RequestParam("prodId") Long prodId, Model model) {
		productService.removeProduct(prodId);
		return "products";
	}
}
