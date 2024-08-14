package com.jea.service;

import com.jea.entities.User;

public interface UserService {
	User addUser(User user);
	User getById(Long id) throws Exception;
}
