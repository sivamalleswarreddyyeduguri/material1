package com.zettamine.mi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.mi.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	  Optional<User> findByUserIdAndUserPassword(Integer id, String password);
	
}
