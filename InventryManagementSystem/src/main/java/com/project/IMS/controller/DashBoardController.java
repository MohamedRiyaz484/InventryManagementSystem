package com.project.IMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {
	@GetMapping("/home")
	public String homePage() 
	{
		return "home";
	}
}
