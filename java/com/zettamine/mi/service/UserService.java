package com.zettamine.mi.service;

import com.zettamine.mi.entities.User;

public interface UserService {
		
	User FindByUserIdAndUserPassword(Integer id, String password);
	
}
