package com.jea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jea.entities.User;
import com.jea.repository.UserRepository;
import com.jea.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public User getById(Long id) throws Exception {
		return userRepository.findById(id).orElseThrow(() -> new Exception(""));
	}

}
