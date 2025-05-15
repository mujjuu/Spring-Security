package com.example.jsp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jsp.Entity.Users;

@Repository
public interface UsersRepositiory extends JpaRepository<Users,Integer >{

	Optional<Users> findByusername(String username);
}
