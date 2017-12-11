package edu.karazin.shop.controller;

import edu.karazin.shop.model.User;
import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.util.ProductUtil;
import edu.karazin.shop.web.DiscountForm;
import edu.karazin.shop.web.ProductSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.karazin.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("products")
public class ProductListController {

	private final ProductService productService;
	private final ProductUtil productUtil;
	private final UserService userService;
	private final CartStore cartStore;


    public ProductListController(@Autowired ProductService productService,@Autowired ProductUtil productUtil,
                                  @Autowired UserService userService, @Autowired CartStore cartStore) {
        this.productService = productService;
        this.productUtil = productUtil;
        this.userService = userService;
        this.cartStore = cartStore;
    }

    @GetMapping
	public String listProducts(Model model, @RequestParam(name = "searchText", required = false) String searchText, HttpServletRequest request) {
		model.addAttribute("products", productService.getAll());
		model.addAttribute("searchForm", new ProductSearchForm(searchText));
        model.addAttribute("cart", cartStore.getTotalAmount());
		//model.addAttribute("discountForm", new DiscountForm());
        return "product-list";
	}

	@PostMapping
	public String searchProducts(Model model,
			@ModelAttribute("searchForm") ProductSearchForm form) {
		model.addAttribute("products", productService.searchProducts(form.getSearchText()));
        //model.addAttribute("cart", cartStore.getTotalAmount());
		return "product-list";
	}


}
