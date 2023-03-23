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

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@PostMapping
	public ResponseEntity<User> login( @RequestBody User param_user ){
		
		System.out.println("userName: password :: " + param_user.getUserName() + ": " + param_user.getPassword());;
		
		User user = new User();
		user.setUserName("msingh");
		user.setFirstName("Manoj");
		user.setLastName("Singh");
		user.setActive( true );
		user.setAge( 42) ;
		user.setEmail("msingh@gmail.com");
		
		if( (param_user.getUserName() != null && param_user.getUserName().equals( "manojishere" ) ) 
				&& ( param_user.getPassword() != null && param_user.getPassword().equals( "msingh1" )) ) {
			return ResponseEntity.ok( user );
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		
	}

}
