package edu.karazin.shop.controller;

import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.ProductService;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.util.ProductUtil;
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
    private final ProductUtil productUtil;
    private final UserService userService;
    private final CartStore cartStore;

    public OrderController(@Autowired ProductService productService ,@Autowired ProductUtil productUtil,
                           @Autowired UserService userService, @Autowired CartStore cartStore) {
        this.productService = productService;
        this.productUtil = productUtil;
        this.userService = userService;
        this.cartStore = cartStore;
    }

    @GetMapping(params = "cart")
    public String orderFromCart(Model model) {
        if(!(productUtil.checkForExistanceForCartAndDecrement(cartStore.getProducts(), cartStore))) {
            model.addAttribute("products", cartStore.getProducts());
            model.addAttribute("runout", "Not enough some products on store");
            return "cart-list";
        }
        model.addAttribute("products", cartStore.getProducts());
        Role role = (userService.getCurrentAuthenticatedUser() != null) ? userService.getCurrentAuthenticatedUser().getRole() : null;
        if (role != null)
        productService.addPurchaseItems(productUtil.convertBasketItemsToPurchaseItems(cartStore.getProducts()));
        return "order-list";
    }

    @GetMapping(params = "single")
    public String orderOne(@RequestParam("prodId") Long prodId, Model model) {
        if (productUtil.checkForExistanceAndDecrement(prodId)) {
            model.addAttribute("product", productService.getProduct(prodId));
            productService.addPurchaseItem(productUtil.convertProductToPurchaseItems(productService.getProduct(prodId)));
            return "order-list";
        }
        model.addAttribute("runout", "Not enough this product on store");
        model.addAttribute("product", productService.getProduct(prodId));
        return "product-view";
    }

    @GetMapping(params = "dropCart")
    public String backToProductAndDropCart() {
        cartStore.removeAll();
        return "forward:products";
    }

}
