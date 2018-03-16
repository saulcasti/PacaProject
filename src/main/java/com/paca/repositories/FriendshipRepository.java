package com.paca.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.paca.entities.Friendship;
import com.paca.entities.User;

public interface FriendshipRepository extends CrudRepository<Friendship, Long>{

	@Query("SELECT f.friend FROM Friendship f where f.user.email = ?1")
	Page<User> findFriendOfOneUser(Pageable pageable, String email);

}
