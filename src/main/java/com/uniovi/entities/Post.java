package com.uniovi.entities;

import javax.persistence.*;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Long id;
	
	// Se deberán añadir nuevos atributos.
	
	public Post() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
