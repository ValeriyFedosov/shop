package edu.karazin.shop.controller;

import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.web.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
		model.addAttribute("roles", Role.values());
		return "user";
	}

	@PostMapping
	public String searchProducts(Model model,
			@ModelAttribute("userForm") UserForm form) {
		userService.createUser(form.convertToUser());
		return "forward:/login";
	}
}
