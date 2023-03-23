package com.lift.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/")
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@GetMapping
	public ResponseEntity<String> helloWorld(){
		System.out.println("helloWorld 1");
		logger.trace("TRACE helloWorld");
		logger.debug("DEBUG helloWorld");
		logger.info("INFO helloWorld");
		logger.warn("WARN helloWorld");
		logger.error("ERROR helloWorld");
		// return new ResponseEntity<String>("Hello World", HttpStatus.OK);
		return ResponseEntity.ok().body("Hello World Again");
	}
	
	@GetMapping(value="/welcome", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> welcome(){
		System.out.println("welcome");
		logger.trace("TRACE welcome");
		logger.debug("DEBUG welcome");
		logger.info("INFO welcome");
		logger.warn("WARN welcome");
		logger.error("ERROR welcome");
		String message = "Welcome user";
		return ResponseEntity.ok(message);
	}

}
