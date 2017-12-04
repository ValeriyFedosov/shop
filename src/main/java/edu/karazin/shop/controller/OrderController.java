package edu.karazin.shop.controller;

import edu.karazin.shop.converter.ProductUtil;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("order")
public class OrderController {

    private final ProductService productService;
    private final CartStore cartStore;
    private final ProductUtil productUtil;

    public OrderController(@Autowired ProductService productService,@Autowired CartStore cartStore,
                           @Autowired ProductUtil productUtil) {
        this.productService = productService;
        this.cartStore = cartStore;
        this.productUtil = productUtil;
    }

    @GetMapping(params = "cart")
    public String orderFromCart(Model model) {
        if(!(productUtil.checkForExistanceForCart(cartStore.getProducts(), cartStore))) {
            model.addAttribute("products", cartStore.getProducts());
            model.addAttribute("runout", "Not enough some products on store");
            return "cart-list";
        }
        model.addAttribute("products", cartStore.getProducts());
        return "order-list";
    }

    @GetMapping(params = "single")
    public String orderOne(@RequestParam("prodId") Long prodId, Model model) {
        if (productUtil.checkForExistance(prodId)) {
            model.addAttribute("product", productService.getProduct(prodId));
            return "order-list";
        }
        model.addAttribute("runout", "Not enough this product on store");
        model.addAttribute("product", productService.getProduct(prodId));
        return "product-view";
    }

}
