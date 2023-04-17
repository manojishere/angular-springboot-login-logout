package com.lift.config.service;

import java.util.List;

import com.lift.config.response.vo.UserDetail;
import com.lift.config.vo.User;

public interface UserService {
	
	User findByUserName( String userName );
	User save( User user );
	List<User> findByLastName( String lastName );
	List<User> findAll();
	UserDetail getUserDetail(String userName);
}
