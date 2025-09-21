package co.edu.unbosque.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named(value = "loginbean")

@RequestScoped
public class LogInBean {

	private String user;
	private String password;
	
	public LogInBean() {
		// TODO Auto-generated constructor stub
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
