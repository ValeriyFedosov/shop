package edu.karazin.shop.controller;

import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.web.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String adminCreate(Model model){
        model.addAttribute("userForm", new UserForm());
        return "sign-in";
    }


    @PostMapping
    public String adminSave(Model model, @ModelAttribute("userForm") UserForm form) {
        if (userService.createUser(form.convertToUser(Role.ROLE_ADMIN)) == null) {
            model.addAttribute("error", "Such user already exists");
            return "sign-in";
        }
        return "forward:/login";
    }
}
