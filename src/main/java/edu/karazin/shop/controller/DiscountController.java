package edu.karazin.shop.controller;

import edu.karazin.shop.service.ProductService;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.util.ProductUtil;
import edu.karazin.shop.util.UserUtil;
import edu.karazin.shop.web.ProductSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("discount")
public class DiscountController {

    private final UserService userService;
    private final ProductService productService;
    private final ProductUtil productUtil;
    private final UserUtil userUtil;

    public DiscountController(@Autowired UserService userService,@Autowired ProductService productService,
                              @Autowired ProductUtil productUtil, @Autowired UserUtil userUtil) {
        this.userService = userService;
        this.productService = productService;
        this.productUtil = productUtil;
        this.userUtil = userUtil;
    }

    // , @RequestParam(name = "searchText", required = false) String searchText


    @GetMapping(params = "permanently")
    public String getDiscount(Model model, @RequestParam(name = "id", required = false) Long id, @RequestParam(name = "discount", required = false) Double discount) {
        productService.setDiscountToProductPermanently(id, discount);
        //model.addAttribute("products", productUtil.convertProductsToProductDtos(productService.getAll()));
        return "discount-list";
    }


}
