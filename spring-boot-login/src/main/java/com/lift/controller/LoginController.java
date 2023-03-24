package com.lift.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lift.config.vo.User;
import com.lift.util.Utility;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@PostMapping
	public ResponseEntity<User> login( @RequestBody User param_user ){
		
		System.out.println("login userName: password :: " + param_user.getUserName() + ": " + param_user.getPassword());;
		/*
		User user = new User();
		user.setUserName("msingh");
		user.setFirstName("Manoj");
		user.setLastName("Singh");
		user.setActive( true );
		user.setAge( 42) ;
		user.setEmail("msingh@gmail.com");
		*/
		
		User user = Utility.getUser( param_user.getUserName() );
		logger.trace( "login user : " + user );
		try {
			if( user != null && 
					(param_user.getUserName() != null && param_user.getUserName().equals( user.getUserName() ) ) 
					&& ( param_user.getPassword() != null && param_user.getPassword().equals( user.getPassword() )) ) {
				return ResponseEntity.ok( user );
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}catch( Exception e ) {
			logger.error("login Exception : ");
			return ResponseEntity.status( HttpStatus.NOT_ACCEPTABLE ).build();
		}

		
		
	}

}
