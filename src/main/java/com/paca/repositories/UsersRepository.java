package com.paca.repositories; 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.paca.entities.*;

public interface UsersRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE (LOWER(u.email) LIKE LOWER(?1) OR LOWER(u.name) LIKE LOWER(?1))")
	Page<User> searchByDNIAndName(Pageable pageable,String seachtext);

	@Query("SELECT u FROM User u")
	Page<User> findAll(Pageable pageable);
	
}