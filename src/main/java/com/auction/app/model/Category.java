package com.auction.app.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="category")
public class Category extends BaseEntity {

	@Column(name="title")
	private String title;
	
}
