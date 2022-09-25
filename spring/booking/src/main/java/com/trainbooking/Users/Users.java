package com.trainbooking.Users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
	@Column
	@Id private String email;
	@Column
	private String fullname;
	@Column
	private String mobno;
	@Column
	private String password;


	public Users() {
	}

	public Users(String email, String fullname, String mobno, String password) {
		super();
		this.email = email;
		this.fullname = fullname;
		this.mobno = mobno;
		this.password = password;
	}
	

	@Id
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
