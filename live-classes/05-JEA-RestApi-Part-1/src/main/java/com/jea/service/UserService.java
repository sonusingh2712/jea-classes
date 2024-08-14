package com.jea.service;

import java.util.List;

import com.jea.entities.User;

public interface UserService {
	User addUser(User user);
	User getById(Long id);
	List<User> getUsersList();
	User updateUserInfo(User user);
	void deleteUser(Long userId);
	
	List<User> getSortedUserList();
}
