package com.zettamine.mi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.zettamine.mi.entities.User;

@Controller
public class LogoutController {
		
	   @GetMapping("/logout")
	    public String userLogout(Model model) {
		   
		  model.addAttribute("user", new User());
		  
		    return "login";
	  }
}
