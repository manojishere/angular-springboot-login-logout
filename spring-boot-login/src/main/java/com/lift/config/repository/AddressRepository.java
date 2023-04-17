package com.lift.config.repository;

import java.util.List;

import com.lift.config.vo.Address;

public interface AddressRepository {
	Address create( Address address );
	List<Address> findByState( String stateName );
	List<Address> findAll();
}
