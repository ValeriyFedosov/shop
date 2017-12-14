package edu.karazin.shop.controller;

import edu.karazin.shop.dto.DiscountDto;
import edu.karazin.shop.dto.ProductDto;
import edu.karazin.shop.dto.UserDto;
import edu.karazin.shop.service.ProductService;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.util.ProductUtil;
import edu.karazin.shop.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("rest")
public class DiscountRestController {

    private final ProductService productService;
    private final UserService userService;
    private final ProductUtil productUtil;
    private final UserUtil userUtil;

    public DiscountRestController(@Autowired ProductService productService, @Autowired UserService userService,
                                  @Autowired ProductUtil productUtil, @Autowired UserUtil userUtil) {
        this.productService = productService;
        this.userService = userService;
        this.productUtil = productUtil;
        this.userUtil = userUtil;
    }

    @GetMapping
    public DiscountDto listUsersProducts(){
        DiscountDto discountDto = new DiscountDto();
        discountDto.setProducts(productUtil.convertProductsToProductDtos(productService.getAll()));
        discountDto.setUsers(userUtil.convertUsersToUserDtos(userService.getAllUsers()));
        return discountDto;
    }


    @PostMapping(consumes="application/json")
    public void setDiscount(@RequestBody ProductDto[] productDtos) {
        //System.out.println(productDtos);
        System.out.println(Arrays.toString(productDtos));
    }

    @PostMapping(consumes="application/json", params = "u")
    public void setDiscount(@RequestBody UserDto[] productDtos) {
        //System.out.println(productDtos);
        System.out.println(Arrays.toString(productDtos));
    }

    @PostMapping(consumes="application/json", params = "w")
    public void setDiscount(@RequestBody String productDtos) {
        System.out.println(productDtos);
    }

    @PostMapping(consumes="application/json", params = "we")
    public void setDiscount(@RequestBody DiscountDto[] productDtos) {
        System.out.println(Arrays.toString(productDtos));
    }
}

