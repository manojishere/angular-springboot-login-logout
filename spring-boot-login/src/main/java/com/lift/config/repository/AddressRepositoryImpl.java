package com.lift.config.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lift.config.vo.Address;
import com.lift.config.vo.User;

@Repository
public class AddressRepositoryImpl implements AddressRepository{
	
	private static final Logger logger = LoggerFactory.getLogger(AddressRepositoryImpl.class);
	
	@Autowired
	AddressJPARepository addressJPARepository; 

	@Override
	public Address create(Address address) {
		logger.debug("create address : " + address);
		Address addressCreated = null;
		try {
			addressCreated = addressJPARepository.save( address );
			logger.debug("create address in Repository : " + addressCreated);
		}catch( Exception e ) {
			logger.error( "create Exception : " + e );
		}
		
		return addressCreated;
	}

	@Override
	public List<Address> findByState(String stateName) {
		logger.debug("findByState stateName : " + stateName );
		List<Address> addressList = null;
		try {
			addressList = addressJPARepository.findByState(stateName);
			logger.debug("findByState address in Repository : " + addressList);
		}catch( Exception e ) {
			logger.error( "findByState Exception : " + e );
		}
		
		return addressList;
	}

	@Override
	public List<Address> findAll() {
		logger.debug("findAll");
		List<Address> addressList = null;
		try {
			addressList = addressJPARepository.findAll();
			logger.debug("findAll Repository : " + addressList);
		}catch( Exception e ) {
			logger.error( "findAll Exception : " + e );
		}
		
		return addressList;
	}
	
}
