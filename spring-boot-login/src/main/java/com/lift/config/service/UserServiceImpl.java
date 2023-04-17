package com.lift.config.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lift.config.repository.UserRepository;
import com.lift.config.response.vo.UserDetail;
import com.lift.config.vo.User;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository; 
	
	@Override
	public User findByUserName(String userName) {
		logger.debug("findByUserName userName : " + userName);
		User user = null;
		try {
			user = userRepository.findByUserName( userName );
			logger.debug("findByUserName user : " + user);
		}catch( Exception e ) {
			logger.error( "findByUserName Exception : " + e );
		}
		return user;
	}
	
	@Override
	public UserDetail getUserDetail(String userName) {
		logger.debug("getUserDetail userName : " + userName);
		User user = null;
		UserDetail userDetail = null;
		try {
			user = userRepository.findByUserName( userName );
			logger.debug("getUserDetail user : " + user);
			
			userDetail = new UserDetail();
			userDetail.setUserName( user.getUserName());
			userDetail.setPassword( user.getPassword());
			userDetail.setFirstName( user.getFirstName() );
			userDetail.setLastName( user.getLastName());
			userDetail.setEmail( user.getEmail());
			userDetail.setPhoneNumber( user.getPhoneNumber());
			userDetail.setAddress( user.getAddress().get(0));
			
		}catch( Exception e ) {
			logger.error( "getUserDetail Exception : " + e );
		}
		return userDetail;
	}

	@Override
	public User save(User user) {
		logger.debug("save user : " + user);
		User userCreated = null;
		try {
			userCreated = userRepository.create( user );
			logger.debug("save user : " + userCreated);
		}catch( Exception e ) {
			logger.error( "save Exception : " + e );
		}
		return userCreated;
	}

	@Override
	public List<User> findByLastName(String lastName) {
		logger.debug("findByLastName lastName : " + lastName);
		List<User> listOfUsers = null;
		try {
			listOfUsers = userRepository.findByLastName( lastName );
			// user = userJPARepository.getInfoOfUserByUserName(userName);
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
			userList = userRepository.findAll();
			logger.debug("findAll userList : " + userList);
		}catch( Exception e ) {
			logger.error( "findAll Exception : " + e );
		}
		return userList;
	}
		
		

}
