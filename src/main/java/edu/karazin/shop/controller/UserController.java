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
@RequestMapping("user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}


    @GetMapping
    public String userCreate(Model model){
        model.addAttribute("userForm", new UserForm());
        return "sign-in";
    }

    @PostMapping
    public String userSave(Model model, @ModelAttribute("userForm") UserForm form) {
        if (userService.createUser(form.convertToUser(Role.ROLE_USER)) == null) {
            model.addAttribute("error", "Such user already exists");
            return "sign-in";
        }
        return "forward:/login";
    }
}
