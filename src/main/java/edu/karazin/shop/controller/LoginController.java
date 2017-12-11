package edu.karazin.shop.controller;

import edu.karazin.shop.model.User;
import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

    private final UserService userService;
    private final CartStore cartStore;

    public LoginController(@Autowired UserService userService, @Autowired CartStore cartStore) {
        this.userService = userService;
        this.cartStore = cartStore;
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @GetMapping(params = "checkForUserCart")
    public String checkForUserCart() {
        User user = userService.getCurrentAuthenticatedUser();
        if (user != null && user.getRole().equals(Role.ROLE_USER)) {
            userService.checkForCart(user);
        }
        return "forward:products";
    }
}
