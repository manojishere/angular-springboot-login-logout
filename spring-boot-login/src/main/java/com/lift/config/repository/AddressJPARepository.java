package com.lift.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lift.config.vo.Address;


public interface AddressJPARepository extends JpaRepository<Address, Integer>{
	
	List<Address> findByState( String stateName );

}
