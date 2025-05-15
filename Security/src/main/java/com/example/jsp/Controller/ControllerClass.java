package com.example.jsp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {

	@GetMapping("/home")
	public String homePage() {
		return "Welcome to HomePage";
	}
	
	@GetMapping("/registerUser")
	public String registeruser() {
		return "Welcome Registration";
	}
	
	@GetMapping("/users/save")
	public String saveuser() {
		return "Saved Admin";
	}
}
