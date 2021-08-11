package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<User> user = userService.getAllUserService();
		model.addAttribute("users", user);
		return "index";
	}

	@GetMapping(value = "users/add")
	public String editPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "edit";
	}

	@PostMapping(value = "users/add")
	public String addUser(@ModelAttribute("user") User user) {
		userService.createUserService(user);
		return "redirect:/";
	}

	@GetMapping(value="users/delete")
	public String deleteUs(@RequestParam("id") int id) {
		userService.deleteUserService(id);
		return "redirect:/";
	}

	@GetMapping(value = "users/update")
	public String updateUs(ModelMap model, @RequestParam("id") int id) {
		User user = userService.getUserByIdService(id);
		model.addAttribute("user", user);
		return "update";
	}

	@PostMapping(value = "users/update")
	public String update(@ModelAttribute("user") User user) {
		userService.updateUserService(user);
		return "redirect:/";
	}
}