package com.paca.entities; import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
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

	private Boolean isAddFriend = false;
	
	private Boolean acceptFriend = false;

	@ManyToMany(cascade = CascadeType.ALL) @JoinTable(name = "requestsReceived",
			joinColumns = { @JoinColumn(name = "userSend_id") },
			inverseJoinColumns = { @JoinColumn(name = "userReceived_id") } )
	public Set<User> requestsReceived = new HashSet<User>();
	
	@ManyToMany(cascade = CascadeType.ALL) @JoinTable(name = "users_meet",
			joinColumns = { @JoinColumn(name = "user_id") },
			inverseJoinColumns = { @JoinColumn(name = "userFriend_id") } )
	public Set<User> friends = new HashSet<User>();


	public User(String email, String name, String lastName) { 
//		super();
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
	public Boolean getAcceptFriend() {
		return acceptFriend;
	}
	public void setAcceptFriend(Boolean acceptFriend) {
		this.acceptFriend = acceptFriend;
	}
	public Set<User> getFriends() {
		return friends;
	}
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
	public Set<User> getRequestsReceived() {
		return requestsReceived;
	}
	public void setRequestsReceived(Set<User> requestsReceived) {
		this.requestsReceived = requestsReceived;
	}

	
	
}
