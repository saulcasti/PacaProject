package com.paca.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.paca.entities.Post;
import com.paca.services.FriendshipService;
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
	private FriendshipService friendshipService;
	
	@Autowired
	private CreatePostFormValidator createPostFormValidator;
	
	@RequestMapping(value = "/post/create", method = RequestMethod.GET) 
	public String signup(Model model) {
		model.addAttribute("post", new Post());
		return "post/create"; 
	}

	
	
	@RequestMapping("/post/list" )
	public String getListado(Model model, Pageable pageable, Principal principal){
		
		String email = principal.getName();
		
		Page<Post> post = new PageImpl<Post>(new LinkedList<Post>());
		
		post = postService.getPosts(pageable, email);
		
		model.addAttribute("postsList", post.getContent() );
		model.addAttribute("nameAuthor", "Mis Publicaciones");
		model.addAttribute("page", post);
		
		return "post/list";
	}

	@RequestMapping(value="/post/{id}/list", method=RequestMethod.GET) 
	public String setResendTrue(Model model, @PathVariable Long id , Pageable pageable, Principal principal){
		
		if(!friendshipService.areFriends(id, usersService.getUserEmail(principal.getName()).getId())) {
			return "redirect:/user/friendsList";
		}
		Page<Post> post = new PageImpl<Post>(new LinkedList<Post>());
		
		post = postService.getPosts(pageable, usersService.getUser(id).getEmail());
		
		model.addAttribute("postsList", post.getContent() );
		model.addAttribute("nameAuthor", "Publicaciones de " + usersService.getUser(id).getFullName());
		model.addAttribute("page", post);
		return "/post/list";
	}
	

	
	@PostMapping(value = "/post/create")
	public String signup(@Validated Post post, BindingResult result,Model model, Principal principal,
			@RequestParam("image") MultipartFile image) {
		
		createPostFormValidator.validate(post, result);
		if (result.hasErrors()) {
			return "/post/create";
		}

		try {
			String fileName = image.getOriginalFilename();
			InputStream is = image.getInputStream();
			Files.copy(is, Paths.get("src/main/resources/static/img/post/" + fileName),
					StandardCopyOption.REPLACE_EXISTING);
			post.setImageURL("/img/post/" + fileName); 
		} catch (IOException e) { 
//			e.printStackTrace();
		}

		post.setAuthor(usersService.getUserEmail(principal.getName()));
		post.setDateToday();
		postService.createPost(post);
		return "home";
	}
}
