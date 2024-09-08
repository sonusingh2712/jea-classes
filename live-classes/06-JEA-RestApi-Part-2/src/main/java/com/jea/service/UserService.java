package com.jea.service;

import java.util.List;

import com.jea.entities.User;

public interface UserService {
	User addUser(User user);
	User getById(Long id);
	List<User> getUsersList();
	User updateUserInfo(Long userId, User user);
	void deleteUser(Long userId);
	
	List<User> getSortedUserList();
	
	//DSL method example
	User findByUserName(String username);
	
	//JPQL method example
	User findByEmailId(String emailId);
	
	//Native Query method example
	User searchUserByUserNamePassword(String username, String password);
}