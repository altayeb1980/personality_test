package com.sparknetworks.service;

import com.sparknetworks.model.User;

public interface UserService {

	User findByEmail(String email);
	User persist(User user);
	void delete(User user);
}
