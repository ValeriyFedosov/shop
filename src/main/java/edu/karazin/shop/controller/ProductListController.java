package edu.karazin.shop.controller;

import edu.karazin.shop.model.User;
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
	private final CartStore cartStore;
	private final UserService userService;

    public ProductListController(@Autowired ProductService productService,@Autowired ProductUtil productUtil,
                                 @Autowired CartStore cartStore, @Autowired UserService userService) {
        this.productService = productService;
        this.productUtil = productUtil;
        this.cartStore = cartStore;
        this.userService = userService;
    }

    @GetMapping
	public String listProducts(Model model, @RequestParam(name = "searchText", required = false) String searchText, HttpServletRequest request) {
		model.addAttribute("products", productService.getAll());
		model.addAttribute("searchForm", new ProductSearchForm(searchText));
        model.addAttribute("cart", cartStore.getTotalAmount());
		//model.addAttribute("discountForm", new DiscountForm());
        System.out.println(request.getSession().getId());
		User user = userService.getCurrentAuthenticatedUser();
		if (user == null) {
            System.out.println("null");
        } else {
            System.out.println(user.getLogin());
        }

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
