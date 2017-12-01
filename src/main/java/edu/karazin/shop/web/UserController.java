package edu.karazin.shop.web;

import edu.karazin.shop.model.Role;
import edu.karazin.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String userCreate(Model model){
		model.addAttribute("userForm", new UserForm());
		model.addAttribute("roles", Role.values());
		return "user";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String searchProducts(Model model,
			@ModelAttribute("userForm") UserForm form) {
		userService.createUser(form.convertToUser());
		return "forward:/login";
	}
}
