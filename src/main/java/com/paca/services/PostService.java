package com.paca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paca.entities.Post;
import com.paca.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	
	public void createPost(Post post) {
		postRepository.save(post);
	}


	public Page<Post> getPosts(Pageable pageable, String email) {
		Page<Post> posts = postRepository.findAllPostByEmailAuthor(pageable, email); 
		return posts;
	}
	
}
