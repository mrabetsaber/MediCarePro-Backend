package com.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_user")
public class User 
{
	@Id
	private String email;
	private String username;
	private String mobile;
	private String gender;
	private String age;
	private String address;
	private String password;
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User() 
	{
		super();
	}

	public User(String email, String username, String mobile, String gender, String age, String address, String password,String role)
	{
		super();
		this.email = email;
		this.username = username;
		this.mobile = mobile;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.password = password;
		this.role = role;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getGender() 
	{
		return gender;
	}

	public void setGender(String gender) 
	{
		this.gender = gender;
	}

	public String getAge()
    {
		return age;
	}

	public void setAge(String age) 
	{
		this.age = age;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
	
}
