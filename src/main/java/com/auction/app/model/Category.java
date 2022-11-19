package com.auction.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="category")
public class Category extends BaseEntity {

	@Column(name="title")
	private String title;
	
}
