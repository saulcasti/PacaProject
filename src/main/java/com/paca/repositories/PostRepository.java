package com.paca.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.paca.entities.Post;


public interface PostRepository extends CrudRepository<Post, Long>{

	@Query("SELECT p FROM Post p WHERE p.author.email = ?1 ORDER BY p.id ASC ")
	Page<Post> findAllPostByEmailAuthor(Pageable pageable, String email);

	@Query("SELECT p FROM Post p WHERE p.author.id = ?1 ORDER BY p.id ASC ")
	Page<Post> findAllPostByIdAuthor(Pageable pageable, Long id);

	@Query("SELECT p.id FROM Post p WHERE p.author.id = ?1 ")
	List<Long> findByUserId(Long id);
}
