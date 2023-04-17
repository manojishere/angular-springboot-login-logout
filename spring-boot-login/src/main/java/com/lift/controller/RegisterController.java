package com.lift.controller;

import java.util.List;
import java.util.Optional;

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
import com.lift.util.Utility;

@RestController
@RequestMapping("/register")
public class RegisterController {

	private static final Logger logger  = LoggerFactory.getLogger( RegisterController.class );
	
	@Autowired
	UserService userService;
	
	@PostMapping
	private ResponseEntity<User> createUser( @RequestBody User param_user ){
		User userCreated;
		logger.trace( "createUser param_user : " + param_user );
		
		try {
			userCreated = userService.save( param_user );
			if( userCreated != null ) {
				return ResponseEntity.ok( param_user );
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}catch( Exception e ) {
			logger.error("save Exception : ");
			return ResponseEntity.status( HttpStatus.NOT_ACCEPTABLE ).build();
		}		
		
	}
	
	/*
	@PostMapping
	private ResponseEntity<User> createUser( @RequestBody User param_user ){
		User user = null;
		try {
			logger.trace( "createUser param_user : " + param_user );
			user = Utility.createUser( param_user );
			logger.trace( "createUser User Created : " + user );	
		}catch( Exception e ) {
			logger.error("createUser Exception : ");
			return ResponseEntity.status( HttpStatus.NOT_ACCEPTABLE ).build();
		}
		return new ResponseEntity<User>( user, HttpStatus.OK );

	}
	*/
	
}
