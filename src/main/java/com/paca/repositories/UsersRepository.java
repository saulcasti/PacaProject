package com.paca.repositories; 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.paca.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.email != ?2 and (LOWER(u.email) LIKE LOWER(?1) OR LOWER(u.name) LIKE LOWER(?1))")
	Page<User> searchByEmailAndNameWithOutCurrentUser(Pageable pageable,String seachtext, String email);

	@Query("SELECT u FROM User u")
	Page<User> findAll(Pageable pageable);

	@Query("SELECT u FROM User u where u.email != ?1")
	Page<User> findAllWithOutCurrentUser(Pageable pageable, String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE User SET isAddFriend = ?1 WHERE id = ?2")
	void updateIsAddFriend(boolean revised, Long id);
}