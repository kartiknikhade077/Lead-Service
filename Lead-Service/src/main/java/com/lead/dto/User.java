package com.lead.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;





public class User {

	private int id;
	private String name;
	private String email;
	@JsonIgnore
	private String password;
	private String about;
	private String role;
	private boolean enabled;
	private byte[] images;
	private String image;
	private LocalDate expirayDate;
	
	public User(int id, String name, String email, String password, String about, String role, boolean enabled,
			byte[] images, String image) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.role = role;
		this.enabled = enabled;
		this.images = images;
		this.image = image;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public byte[] getImages() {
		return images;
	}

	public void setImages(byte[] images) {
		this.images = images;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDate getExpirayDate() {
		return expirayDate;
	}

	public void setExpirayDate(LocalDate expirayDate) {
		this.expirayDate = expirayDate;
	}

	
	
}

