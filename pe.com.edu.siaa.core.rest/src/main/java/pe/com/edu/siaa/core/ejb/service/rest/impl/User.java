package pe.com.edu.siaa.core.ejb.service.rest.impl;

public class User  {
private	String email = null;
private String token;
private String username = null;
private String bio = null;
private String image = null;


public User() {
	super();
}
public User(String email, String token, String username, String bio, String image) {
	super();
	this.email = email;
	this.token = token;
	this.username = username;
	this.bio = bio;
	this.image = image;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getBio() {
	return bio;
}
public void setBio(String bio) {
	this.bio = bio;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}

}

