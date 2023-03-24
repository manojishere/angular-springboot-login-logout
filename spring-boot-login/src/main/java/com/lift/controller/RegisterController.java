package com.lift.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lift.config.vo.User;
import com.lift.util.Utility;

@RestController
@RequestMapping("/register")
public class RegisterController {

	private static final Logger logger  = LoggerFactory.getLogger( RegisterController.class );
	
	
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
	
}
