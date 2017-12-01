package edu.karazin.shop.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
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

    @GetMapping
    public String listProducts(Model model, @RequestParam(name = "prodId") Long prodId) {
        model.addAttribute("product", productService.getProduct(prodId));
        return "product-view";
    }

}
