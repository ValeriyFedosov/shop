package edu.karazin.shop.web;

import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("product-view")
public class ProductViewController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartStore cartStore;

    @GetMapping
    public String listProducts(Model model, @RequestParam(name = "prodId") Long prodId) {
        model.addAttribute("product", productService.getProduct(prodId));
        model.addAttribute("cart", cartStore.getTotalAmount());
        return "product-view";
    }

}
