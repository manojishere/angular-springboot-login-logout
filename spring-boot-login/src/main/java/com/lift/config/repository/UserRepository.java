package com.lift.config.repository;

import java.util.List;

import com.lift.config.vo.User;

public interface UserRepository {
	User create( User user );
	User findByUserName( String userName );
	List<User> findByLastName( String lastName );
	List<User> findAll();
}
