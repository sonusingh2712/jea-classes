package com.jea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jea.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	// Implementing DSL [Domain Specific Language] methods
	User findByusername(String userName);
	
	// Implementing JPQL
	@Query(value = "select u from User u where u.email = :emailId ")
	User searchUserByEmail(String emailId);
	
	// Implementing Native Query
	@Query(value = "select * from User u where u.username = :userName and u.password = :passWord ", nativeQuery = true)
	User searchUserByUserNameAndPassword(String userName, String passWord);
}
