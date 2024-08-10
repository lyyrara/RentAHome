package com.example.rentahome.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentahome.entity.User;
import com.example.rentahome.repository.UserRepository;

@Service
public class UserService {
    @Autowired
	UserRepository userRepository;

	public String addUser(User user) {
		
		userRepository.save(user);
		
		return "User Saved";
	}

	public String deleteUser(int userId) {
		User user = userRepository.findByUserId(userId);

		userRepository.delete(user);
		
		return "User Deleted";
	}

	public String updateUser(String name, String password, String email, String role) {
	
		Optional<User> existingUserOpt = Optional.ofNullable(userRepository.findByNameAndPassword(name, password));
    
    // If the user is not found, return a "User not found" message
    if (!existingUserOpt.isPresent()) {
        return "User not found";
    }
		User existingUser = existingUserOpt.get();
		
		existingUser.setName(name);
		existingUser.setPassword(password);
		existingUser.setEmail(email);
		existingUser.setRole(role);

		userRepository.saveAndFlush(existingUser);
		//entityManager.clear(); // Clear the persistence context
		return "User Updated";

		
	}

	
	public User login(String name, String password) {
		
		return userRepository.findByNameAndPassword(name, password);
	}

	public User findByUserId(int userId){
		User user = userRepository.findByUserId(userId);
		return user;
	}

}
