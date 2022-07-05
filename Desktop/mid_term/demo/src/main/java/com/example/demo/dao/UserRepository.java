package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.enittiy.User;

public interface UserRepository extends JpaRepository<User,Integer> 
	{
	
	
	Optional<User> findUserByEmail(String email);

void deleteByEmailAndFirstName(String email, String name);




@Modifying
@Transactional
@Query("Delete From User Where email = :email")
void deleteByEmail(String email);
	
	
	}

