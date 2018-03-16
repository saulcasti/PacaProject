package com.paca.entities;

import javax.persistence.*;

@Entity
public class Request {
	
	@Id
	@GeneratedValue
	private Long id;
	private String descripcion;

	@ManyToOne
	private User transmitter; //emite
	
	@ManyToOne
	private User receiver; //recibe
	
	public Request() {}
	
	
	public Request(Long id, User transmitter, User receiver) {
		super();
		this.id = id;
		this.transmitter = transmitter;
		this.receiver = receiver;
	}
	
	public Request(String descripcion, User transmitter, User receiver) {
		super();
		this.descripcion = descripcion;
		this.transmitter = transmitter;
		this.receiver = receiver;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(User transmitter) {
		this.transmitter = transmitter;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", transmitter=" + transmitter + ", receiver=" + receiver + "]";
	}
	
	
	

}
