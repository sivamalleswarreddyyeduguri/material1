package com.zettamine.mi.controller;
	
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zettamine.mi.entities.User;
import com.zettamine.mi.service.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/material")
public class UserController {

	private UserServiceImpl service;

	public UserController(UserServiceImpl service) {
		super();
		this.service = service;
	}

	@GetMapping("/login")
	public String loginForm(Model model) {

		model.addAttribute("user", new User());

		return "login";

	}

	@PostMapping("/data")
	public String userAuthentication(User user, Model model,HttpSession session) {
		
		User users = service.FindByUserIdAndUserPassword(user.getUserId(), user.getUserPassword());
		
		if (users != null) {
			session.setAttribute("user",users);
			return "home";
			
		} else {
			model.addAttribute("errorMsg", "invalid credentials!!!!!");

			return "login";
		}

	}

}
