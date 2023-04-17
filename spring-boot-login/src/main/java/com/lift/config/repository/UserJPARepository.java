package com.lift.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lift.config.vo.User;

public interface UserJPARepository extends JpaRepository<User, Integer>{
	User findByUserName( String userName );
	
	List<User> findByLastName( String lastName );
	
	@Query("select u from User u where u.userName = :userName ")
	User getInfoOfUserByUserName(@Param("userName") String userName);
}
