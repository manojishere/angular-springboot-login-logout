package com.lift.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lift.config.service.UserService;
import com.lift.config.vo.Address;
import com.lift.config.vo.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<User> save( @RequestBody User param_user ){
		
		System.out.println("save param_user : " + param_user );
		logger.debug("save param_user : " + param_user );
		
		User userCreated;
		
		try {
			userCreated = userService.save( param_user );
			if( userCreated != null ) {
				logger.debug("save address is : " + userCreated.getAddress() );
				return ResponseEntity.ok( userCreated );
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}catch( Exception e ) {
			logger.error("save Exception : ");
			return ResponseEntity.status( HttpStatus.NOT_ACCEPTABLE ).build();
		}
		
	}
	
	@GetMapping
	public ResponseEntity<User> get( @RequestBody User param_user  ){
		
		System.out.println("get userName : " + param_user.getUserName() );
		logger.debug("get userName : " + param_user.getUserName() );
		
		User user;
		
		try {
			user = userService.findByUserName( param_user.getUserName() );
			if( user != null ) {
				logger.debug("get address is : " + user.getAddress() );
				return ResponseEntity.ok( user );
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}catch( Exception e ) {
			logger.error("get Exception : ");
			return ResponseEntity.status( HttpStatus.NOT_ACCEPTABLE ).build();
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll() {

		logger.debug("findAll");
		List<User> userList = null;
		try {
			userList = userService.findAll();
			return ResponseEntity.ok( userList );

		}catch( Exception e ) {
			logger.error( "get Exception : " + e);
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).build();
		}
	}

}
