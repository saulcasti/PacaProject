package com.uniovi.entities;

import java.util.Set;

import javax.persistence.*;


@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String email;
	private String name;
	private String lastName;
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private Set<Post> posts;

	public User(){}
	
	public User(String email, String name, String password, String passwordConfirm) {
		this(email,name);
		this.password = password;
		this.passwordConfirm = passwordConfirm;
	}

	public User(String email, String name) {
		this();
		this.email = email;
		this.name = name;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
