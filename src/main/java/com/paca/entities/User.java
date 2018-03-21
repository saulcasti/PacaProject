package com.paca.entities;
import java.util.Set;

import javax.persistence.*;

import com.paca.services.RolesService;
//A collection that contains no duplicate elements 

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id; 
	
	@Column(unique=true) 
	private String email; 

	private String name; 
	private String lastName; 

	private String password;
	@Transient //propiedad que no se almacena e la tabla.
	private String passwordConfirm;
	
	private String role;
	
	

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	private Boolean isAddFriend = true;


	@OneToMany(mappedBy = "transmitter", cascade = CascadeType.ALL)
	private Set<Request> sent /*= new HashSet<Request>()*/;

	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private Set<Request> received /*= new HashSet<Request>()*/;

	// Amistades actuales
	@OneToMany(mappedBy = "friend", cascade = CascadeType.ALL)
	private Set<Friendship> friends ;
	
	// Publicaciones realizadas
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private Set<Post> posts ;
	
	public User(String email, String name, String lastName) { 
		super();
		this.email = email; 
		this.name = name; 
		this.lastName = lastName;
	}
	public User() { }

	public long getId() { return id;
	}

	public void setId(long id) {
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

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) { 
		this.lastName = lastName;
	}

	public Set<Request> getSent() {
		return sent;
	}
	public void setSent(Set<Request> sent) {
		this.sent = sent;
	}
	public Set<Request> getReceived() {
		return received;
	}
	public void setReceived(Set<Request> received) {
		this.received = received;
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
	public String getFullName() { 
		return this.name + " " + this.lastName;
	}
	public Boolean getIsAddFriend() {
		return isAddFriend;
	}
	public void setIsAddFriend(Boolean isAddFriend) {
		this.isAddFriend = isAddFriend;
	}

	public void addFriend(Friendship user) {
		friends.add(user);
	}
	public Set<Friendship> getFriends() {
		return friends;
	}
	public void setFriends(Set<Friendship> friends) {
		this.friends = friends;
	}
	
	
}
