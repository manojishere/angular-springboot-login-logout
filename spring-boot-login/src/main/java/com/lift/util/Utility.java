package com.lift.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.pattern.Util;

public class Utility {

	public static final Logger logger = LoggerFactory.getLogger( Utility.class );
	public static boolean isSame( String param1, String param2 ) {
		logger.debug("isSame param1 : param2 :: " + param1 +" : " + param2 );
		if(param1.equals( param2 )) {
			return true;
		}
		return false;
	}
}
