package com.lift.config.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","address"})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String userName;

	private String firstName;

	private String lastName;

	private String password;

	private String email;

	private long phoneNumber;
	
	/**
	 * @JoinColumn means 
	 * (a) a column userId created on the address table and will 
	 * (b) the column userId on address table will be a foreign key that will reference the 
	 *     id column of the user table.
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@JsonIgnoreProperties("user")
	private List<Address> address = new ArrayList<Address>();


	private int age;
	private boolean active;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> addresses) {
		this.address = addresses;
	}
	
	
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address="
//				+ address + ", age=" + age + ", active=" + active + "]";
//	}
	
	
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber + ", age=" + age + ", active=" + active + "]";
//	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber + ", age=" + age
				+ ", active=" + active + "]";
	}

}
