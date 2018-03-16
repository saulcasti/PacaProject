package com.paca.repositories;

import org.springframework.data.repository.CrudRepository;

import com.paca.entities.Post;


public interface PostRepository extends CrudRepository<Post, Long>{

}
