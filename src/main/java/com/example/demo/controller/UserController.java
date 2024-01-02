package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	
	@Autowired
	private UserService service;
	
	@PostMapping("/registerUser")
	public String registerUser(@RequestBody User user) {
		return service.saveUser(user);
	}
	
	@GetMapping("/getUser/{email}")
	public String getUser(@PathVariable("email")String email) {
		return service.getUser(email);
	}
	
	@GetMapping("/getallUsers")
	public List<User> getUsers() {
		return service.getUsers();
	}
	@PutMapping("/updateUser")
	public String updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
	
	@DeleteMapping("/deleteByEmail/{email}")
	public String deleteByEmail(@PathVariable("email") String email) {
		return service.deleteUser(email);
	}
}
