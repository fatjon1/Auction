package com.auction.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User extends BaseEntity {
	
	// @NotBlank(message = "Username is required")
	@Column(name = "username")
	private String username;
	
	// @NotBlank(message = "Username is required")
	@Column(name = "password")
	private String password;
	
	// @NotBlank(message = "Phone Number is required")
	@Column(name = "phone_number")
	private String phoneNumber;
	
	// @NotBlank(message = "Addess is required")
	@Column(name = "address")
	private String address;
	
	@Column(name = "active")
	private Integer active=1;

	private String roles;

}

