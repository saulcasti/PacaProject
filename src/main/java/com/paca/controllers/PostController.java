package com.paca.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paca.entities.Post;
import com.paca.services.PostService;
import com.paca.services.UsersService;
import com.paca.validators.CreatePostFormValidator;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CreatePostFormValidator createPostFormValidator;
	
	@RequestMapping(value = "/post/create", method = RequestMethod.GET) 
	public String signup(Model model) {
		model.addAttribute("post", new Post());
		return "post/create"; 
	}

	@RequestMapping(value = "/post/create", method= RequestMethod.POST)
	public String signup(@Validated Post post, BindingResult result,Model model, Principal principal) {
		
		createPostFormValidator.validate(post, result);
		 if (result.hasErrors()) {
			 return "/post/create";
		 }
		 
		post.setAuthor(usersService.getUserEmail(principal.getName()));
		post.setDateToday();
		postService.createPost(post);
		
		 return "home";
	}
	
	@RequestMapping("/post/list" )
	public String getListado(Model model, Pageable pageable, Principal principal){
		
		String email = principal.getName();
		
		Page<Post> post = new PageImpl<Post>(new LinkedList<Post>());
		
		post = postService.getPosts(pageable, email);
		
		model.addAttribute("postsList", post.getContent() );
		model.addAttribute("page", post);
		
		return "post/list";
	}

}
