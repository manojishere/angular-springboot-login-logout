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

import com.lift.config.service.AddressService;
import com.lift.config.vo.Address;
import com.lift.config.vo.User;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	public static final Logger logger = LoggerFactory.getLogger( AddressController.class );
	
	@Autowired
	AddressService addressService;
	
	
	@PostMapping
	public ResponseEntity<Address> save( @RequestBody Address param_address ){
		
		System.out.println("save param_address : " + param_address );
		logger.debug("save param_address : " + param_address );
		
		Address addressCreated;
		
		try {
			addressCreated = addressService.save( param_address );
			if( addressCreated != null ) {
				return ResponseEntity.ok( addressCreated );
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}catch( Exception e ) {
			logger.error("save Exception : ");
			return ResponseEntity.status( HttpStatus.NOT_ACCEPTABLE ).build();
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<Address>> get( @RequestBody Address param_address ){
		
		logger.debug("get param_address : " + param_address.getAddress1() + " , " + 
				param_address.getCity() + "," + param_address.getState() + ", " + param_address.getPostalCode());
		List<Address> addressList;
		Optional<Address> address = Optional.of( param_address );
		try {
			
			if( param_address != null ) {
				addressList = addressService.findByState( param_address.getState() );
				return ResponseEntity.ok( addressList );
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}catch( Exception e ) {
			logger.error( "get Exception : " + e);
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).build();
		}
		
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Address>> findAll() {

		logger.debug("findAll");
		List<Address> addressList = null;
		try {
			addressList = addressService.findAll();
			return ResponseEntity.ok( addressList );

		}catch( Exception e ) {
			logger.error( "get Exception : " + e);
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).build();
		}
		
	}	

}
