package com.jea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
