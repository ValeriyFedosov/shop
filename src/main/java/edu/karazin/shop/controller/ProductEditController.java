package edu.karazin.shop.controller;

import edu.karazin.shop.dto.ProductDtoForEdit;
import edu.karazin.shop.service.ProductService;
import edu.karazin.shop.util.ProductUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("productEdit")
public class ProductEditController {

	private static final Logger log = LoggerFactory.getLogger(ProductEditController.class);

	private final ProductService productService;
	private final ProductUtil productUtil;

	public ProductEditController(@Autowired ProductService productService,
								 @Autowired ProductUtil productUtil) {
		this.productService = productService;
		this.productUtil = productUtil;
	}

	// create

	@GetMapping
	public String newProduct(Model model) {
		model.addAttribute("product", new ProductDtoForEdit(null, "", "","", "", ""));
		return "product-edit";
	}

    @PostMapping
    public String saveProduct(@RequestParam("img") MultipartFile img, ProductDtoForEdit product, Model model) throws IOException {
		if (!(ProductUtil.validate(product))) {
			model.addAttribute("product", new ProductDtoForEdit(null, "", "","", "", ""));
			model.addAttribute("error", "Some fields have not passed validation");
			return "product-edit";
		}
        if (productService.addProduct(productUtil.convertProductDtoForEditToProduct(product), img) == null) {
            model.addAttribute("product", new ProductDtoForEdit(null, "", "", "","", ""));
            model.addAttribute("error", "Such product already exists");
            return "product-edit";
        }
        return "redirect:/products";
    }

    // edit

	@GetMapping(path = "{id}")
	public String editProduct(Model model, @PathVariable("id") Long productId) {
		model.addAttribute("product", productUtil.convertProductToProductDtoForEdit(productService.getProduct(productId)));
		return "product-edit";
	}

    @PostMapping(path = "{id}")
    public String updateProduct(@RequestParam("img") MultipartFile img, ProductDtoForEdit product, Model model) throws IOException {
		if (!(ProductUtil.validate(product))) {
            model.addAttribute("product", product);
			model.addAttribute("error", "Some fields have not passed validation");
			return "product-edit";
		}
        productService.updateProduct(productUtil.convertProductDtoForEditToProduct(product), img);
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
