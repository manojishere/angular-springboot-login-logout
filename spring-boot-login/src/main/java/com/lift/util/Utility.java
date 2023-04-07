package com.lift.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lift.config.vo.Address;
import com.lift.config.vo.User;

import ch.qos.logback.classic.pattern.Util;

public class Utility {

	public static final Logger logger = LoggerFactory.getLogger( Utility.class );
	public static Map<String, User> listOfRegisteredUsers = new HashMap<String, User>();
	
	static {
		User user = new User();
		user.setUserName("manojishere");
		user.setFirstName("Manoj");
		user.setLastName("Singh");
		user.setPassword("msingh1");
		user.setActive( true );
		user.setAge( 42) ;
		user.setEmail("msingh@gmail.com");
		user.setPhoneNumber( 6787002336L );
		
		Address address = new Address();
		address.setAddress1("4320 Marking Lane");
		address.setCity("Plano");
		address.setState("TX");
		address.setPostalCode(22345);
		
		user.setAddress( address );
		
		createUser( user );
	}
	
	public static boolean isSame( String param1, String param2 ) {
		logger.debug("isSame param1 : param2 :: " + param1 +" : " + param2 );
		if(param1.equals( param2 )) {
			return true;
		}
		return false;
	}
	
	public static User createUser( User user ) {
		logger.trace("createUser number of users before : " + listOfRegisteredUsers.size() );
		int id = listOfRegisteredUsers.size();
		user.setId( id +1 );
		listOfRegisteredUsers.put( user.getUserName() , user );
		logger.trace("createUser number of users after : " + listOfRegisteredUsers.size() );
		return user;
	}
	
	public static User getUser( String userName ) {
		logger.trace("getUser userName : " + userName );
		return listOfRegisteredUsers.get( userName );
	}
}
