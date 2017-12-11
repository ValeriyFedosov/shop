package edu.karazin.shop.controller;

import edu.karazin.shop.model.User;
import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.ProductService;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("cart")
public class CartController {


	private final ProductService productService;
	private final UserService userService;
	private final CartStore cartStore;

	public CartController(@Autowired ProductService productService, @Autowired UserService userService,
                          @Autowired CartStore cartStore) {
		this.productService = productService;
		this.userService = userService;
		this.cartStore = cartStore;
	}


	@GetMapping
    public String getCart(Model model) {
	    model.addAttribute("products", cartStore.getProducts());
		model.addAttribute("total", cartStore.getTotalCost());
	    return "cart-list";
    }


	@GetMapping(params = "add")
	public String addProduct(@RequestParam("prodId") Long prodId, Model model) {
        cartStore.addProduct(productService.getBasketItem(prodId));
        model.addAttribute("products", cartStore.getProducts());
		model.addAttribute("total", cartStore.getTotalCost());
        return "cart-list";
	}


	@GetMapping(params = "delete")
	public String removeProduct(@RequestParam("prodId") Long prodId, Model model) {
		cartStore.removeProduct(productService.getBasketItem(prodId));
        model.addAttribute("products", cartStore.getProducts());
		model.addAttribute("total", cartStore.getTotalCost());
		return "cart-list";
	}

    @GetMapping(params = "deleteAll")
    public String removeAllProducts() {
       cartStore.removeAll();
        return "cart-list";
    }
}
