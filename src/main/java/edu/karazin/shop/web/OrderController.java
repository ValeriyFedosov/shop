package edu.karazin.shop.web;

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

    @Autowired
    private ProductService productService;

    @Autowired
    private CartStore cartStore;

    @Autowired
    private ProductUtil productUtil;

    @GetMapping(params = "cart")
    public String orderFromCart(Model model) {
        if(!(productUtil.checkForExistanceForCart(cartStore.getProducts(), cartStore)))
            return "run-out-of-products";
        model.addAttribute("products", cartStore.getProducts());
        return "order-list";
    }

    @GetMapping(params = "single")
    public String orderOne(@RequestParam("prodId") Long prodId, Model model) {
        if (productUtil.checkForExistance(prodId)) {
            model.addAttribute("product", productService.getProduct(prodId));
            return "order-list";
        } else {
            return "run-out-of-products";
        }
    }

}
