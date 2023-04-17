package com.lift.config.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lift.config.repository.AddressRepository;
import com.lift.config.repository.UserRepositoryImpl;
import com.lift.config.vo.Address;
import com.lift.config.vo.User;

@Service
public class AddressServiceImpl implements AddressService{

	private static final Logger logger = LoggerFactory.getLogger( AddressServiceImpl.class );
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Address save(Address address) {
		logger.debug("save address : " + address);
		Address addressCreated = null;
		try {
			addressCreated = addressRepository.create( address );
			logger.debug("save address : " + addressCreated);
		}catch( Exception e ) {
			logger.error( "save Exception : " + e );
		}
		return addressCreated;
	}

	@Override
	public List<Address> findByState(String stateName) {
		logger.debug("findByState stateName : " + stateName );
		List<Address> addressList = null;
		try {
			addressList = addressRepository.findByState(stateName);
			logger.debug("findByState address in Repository : " + addressList);
			addressList.forEach(v -> logger.debug(v.getUser() + ""));
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
			addressList = addressRepository.findAll();
			logger.debug("findByState addressList from Repository : " + addressList);
		}catch( Exception e ) {
			logger.error( "findAll Exception : " + e );
		}
		return addressList;
	}

}
