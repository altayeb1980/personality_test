package com.sparknetworks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparknetworks.model.User;
import com.sparknetworks.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User persist(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);

	}
}
