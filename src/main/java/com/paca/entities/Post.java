package com.paca.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private User author;
	
	private String date; // con el formato YYYY/MM/DD
	private String text;
	private String title;
	
	public Post() {}

	public Post(User author, String date, String text, String title) {
		super();
		this.author = author;
		this.date = date;
		this.text = text;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDateToday() {
		Date d = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss"); 
		String today = dt.format(d);
		this.date=today;
	}
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", author=" + author + ", date=" + date + ", text=" + text + ", title=" + title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	
}
