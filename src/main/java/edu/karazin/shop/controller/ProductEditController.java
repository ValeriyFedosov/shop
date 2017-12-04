package edu.karazin.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.karazin.shop.model.Product;
import edu.karazin.shop.service.ProductService;

import java.io.IOException;

@Controller
@RequestMapping("product")
public class ProductEditController {

	private static final Logger log = LoggerFactory.getLogger(ProductEditController.class);

	private final ProductService productService;

	public ProductEditController(@Autowired ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public String newProduct(Model model) {
		model.addAttribute("product", new Product(null, "", "", 0L, 0));
		return "product-edit";
	}

	@GetMapping(path = "{id}")
	public String editProduct(Model model, @PathVariable("id") Long productId) {
		model.addAttribute("product", productService.getProduct(productId));
		return "product-edit";
	}

	@PostMapping
	public String saveProduct(Product product, Model model) throws IOException {
	    if (productService.addProduct(product) == null) {
            model.addAttribute("product", new Product(null, "", "", 0L, 0));
            model.addAttribute("error", "Such product already exists");
            return "product-edit";
        }
		return "redirect:/products";
	}

//    @PostMapping
//    public String saveProduct(@RequestParam("file") MultipartFile file, Model model) throws IOException {
//        model.addAttribute("image", file);
//        return "product-view";
//    }

	@PostMapping(path = "{id}")
	public String updateProduct(Product product) throws IOException {
		productService.updateProduct(product);
		return "redirect:/products";
	}

	@GetMapping(params = "delete")
	public String deleteProduct(@RequestParam("prodId") Long prodId, Model model) {
		productService.removeProduct(prodId);
		return "redirect:/products";
	}

	@GetMapping(params = "deleteAll")
	public String deleteAllProducts() {
		productService.deleteAll();
		return "redirect:/products";
	}
}
