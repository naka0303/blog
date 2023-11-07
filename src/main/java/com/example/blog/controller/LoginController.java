package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.blog.model.User;

@Controller
public class LoginController {
	@Value("${title.name}")
	private String titleName;
	
	@Value("${app.name}")
	private String appName;
	
	@GetMapping("/login")
	public String login(@ModelAttribute User user, Model model) {
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    
		return "/user/login";
	}
}
