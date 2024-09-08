package com.jea.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jea.entities.User;
import com.jea.handler.exception.ResourceNotFoundException;
import com.jea.repository.UserRepository;
import com.jea.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public User getById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not exist in DB."));
	}

	@Override
	public List<User> getUsersList() {
		return userRepository.findAll();
	}

	@Override
	public User updateUserInfo(Long userId, User user) {
		User savedUser = getById(userId);
		savedUser.setUsername(user.getUsername());
		savedUser.setPassword(user.getPassword());
		savedUser.setEmail(user.getEmail());
		return userRepository.save(savedUser);
	}

	@Override
	public void deleteUser(Long userId) {
		User savedUser = getById(userId);
		userRepository.delete(savedUser);
	}

	@Override
	public List<User> getSortedUserList() {
		Comparator<User> comparator = (firstUser, secondUser) -> {
			if(firstUser.getUsername().equals(secondUser.getUsername()))
				return firstUser.getId().compareTo(secondUser.getId());
			else
				return firstUser.getUsername().compareTo(secondUser.getUsername());
		};
		return getUsersList().stream().sorted(comparator).collect(Collectors.toList());
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByusername(username);
	}

	@Override
	public User findByEmailId(String emailId) {
		return userRepository.searchUserByEmail(emailId);
	}

	@Override
	public User searchUserByUserNamePassword(String username, String password) {
		return userRepository.searchUserByUserNameAndPassword(username, password);
	}
}