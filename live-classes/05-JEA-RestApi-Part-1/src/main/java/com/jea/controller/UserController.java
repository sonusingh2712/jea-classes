package com.jea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jea.entities.User;
import com.jea.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public User createUser(@RequestBody User user) {
		User savedUser = userService.addUser(user);
		return savedUser;
	}
	
	@GetMapping("/all-users")
	public List<User> getAllUsers(){
		return userService.getUsersList();
	}
	
	@PutMapping("{userId}")
	public User updateUserInfo(@PathVariable Long userId, @RequestBody User user) {
		User updatedUser = userService.updateUserInfo(userId, user);
		return updatedUser;
	}
	
	@DeleteMapping("/delete-user")
	public void deleteUserInfo(@RequestParam Long userId) {
		userService.deleteUser(userId);
	}
}