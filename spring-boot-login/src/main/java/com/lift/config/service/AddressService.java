package com.lift.config.service;

import java.util.List;

import com.lift.config.vo.Address;

public interface AddressService {
	Address save( Address address );
	List<Address> findByState( String stateName );
	List<Address> findAll();
}
