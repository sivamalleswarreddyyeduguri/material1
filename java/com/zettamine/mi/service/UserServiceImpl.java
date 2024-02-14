package com.zettamine.mi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zettamine.mi.entities.User;
import com.zettamine.mi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public User FindByUserIdAndUserPassword(Integer id, String password) {
		
		 Optional<User> user = repository.findByUserIdAndUserPassword(id, password);
		    
		 
		     if(user.isPresent()) {
		    	 
		    	 return user.get();
		     }
		     
		     else {
		    	 
		    	 return null;
		     }
		
		
	}



}
