package com.example.jsp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jsp.Entity.Users;
import com.example.jsp.Repository.UsersRepositiory;

@Service
public class UserService {
	
	@Autowired
	private UsersRepositiory usersRepositiory;
	
	//private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public Users saveUser(Users user) {
		//user.setPassword(encoder.encode(user.getPassword()));
		return usersRepositiory.save(user);
	}


	public List<Users> getAllUsers() {
		return usersRepositiory.findAll();
	}

}
