package com.example.jsp.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jsp.Config.MyUserDetails;
import com.example.jsp.Entity.Users;
import com.example.jsp.Repository.UsersRepositiory;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepositiory usersRepositiory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> opt= usersRepositiory.findByusername(username);
		
		if(opt.isPresent()) {
			return new MyUserDetails(opt.get());
		}
		else {
			throw new UsernameNotFoundException("Username Not Available in Database");
		}
	}

}
