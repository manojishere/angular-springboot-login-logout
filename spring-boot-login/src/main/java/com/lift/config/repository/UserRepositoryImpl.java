package com.lift.config.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lift.config.vo.User;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	@Autowired
	UserJPARepository userJPARepository;
	
	@Override
	public User create(User user) {
		logger.debug("create user : " + user);
		User userCreated = null;
		try {
			userCreated = userJPARepository.save( user );
			logger.debug("create user from Repository : " + userCreated);
		}catch( Exception e ) {
			logger.error( "create Exception : " + e );
		}
		
		return userCreated;
	}
	
	@Override
	public User findByUserName( String userName ) {
		logger.debug("findByUserName userName : " + userName);
		User user = null;
		try {
			user = userJPARepository.findByUserName(userName);
			// user = userJPARepository.getInfoOfUserByUserName(userName);
			logger.debug("findByUserName user from Repository : " + user);
		}catch( Exception e ) {
			logger.error( "findByUserName Exception : " + e );
		}
		
		return user;
	}

	@Override
	public List<User> findByLastName(String lastName) {
		logger.debug("findByLastName lastName : " + lastName);
		List<User> listOfUsers = null;
		try {
			listOfUsers = userJPARepository.findByLastName( lastName );
			logger.debug("findByLastName user from Repository : " + listOfUsers);
		}catch( Exception e ) {
			logger.error( "findByLastName Exception : " + e );
		}
		
		return listOfUsers;
	}
	
	@Override
	public List<User> findAll() {
		logger.debug("findAll");
		List<User> userList = null;
		try {
			userList = userJPARepository.findAll();
			logger.debug("findAll userList : " + userList);
		}catch( Exception e ) {
			logger.error( "findAll Exception : " + e );
		}
		return userList;
	}
	
	
	/*
	@Override
	public List<User> findAll() {
		logger.debug("create user : " + user);
		User userCreated = null;
		try {
			logger.debug("create user from Repository : " + userCreated);
		}catch( Exception e ) {
			logger.error( "create Exception : " + e );
		}
		
		return null;
	}
	*/



}
