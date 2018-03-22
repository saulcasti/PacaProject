package com.paca.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.paca.entities.Friendship;
import com.paca.entities.User;

public interface FriendshipRepository extends CrudRepository<Friendship, Long>{

	@Query("SELECT f.friend FROM Friendship f where f.user.email = ?1")
	Page<User> findFriendOfOneUser(Pageable pageable, String email);

	@Modifying
	@Transactional
	@Query("DELETE FROM Friendship f where f.friend.id=?1 or f.user.id=?1")
	void deleteByUserId(Long id);

}
