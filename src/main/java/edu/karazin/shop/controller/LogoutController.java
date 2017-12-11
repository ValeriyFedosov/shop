package edu.karazin.shop.controller;

import edu.karazin.shop.model.User;
import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.ProductService;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("customLogout")
public class LogoutController {

    private final UserService userService;
    private final CartStore cartStore;
    private final ProductService productService;

    public LogoutController(@Autowired UserService userService, @Autowired CartStore cartStore,
                            @Autowired ProductService productService) {
        this.userService = userService;
        this.cartStore = cartStore;
        this.productService = productService;
    }

    @PostMapping
    public String logout(HttpServletRequest request) {
        User user = userService.getCurrentAuthenticatedUser();
        if (user.getRole().equals(Role.ROLE_USER) && cartStore.getProducts().size() != 0)
        productService.saveCart(user.getId(), cartStore.getProducts());
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
        return "forward:products";
    }
}
