package com.auction.app.model;

import com.auction.app.enums.AuctionStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name="auction")
public class Auction extends  BaseEntity {

	@Column(name="title")
	private String title;

	@Column(name="start_price")
	private double startPrice;

	@Column(name = "featured")
	protected Boolean featured = false;

	@NotNull
	@Column(name = "category_id" , nullable=false)
	@Type(type = "org.hibernate.type.UUIDCharType")
	protected UUID categoryId;

	@ManyToOne
	@JoinColumn(name="category_id", insertable = false, updatable = false)
	private Category category;

	@NotNull
	@Column(name = "author_id", nullable = false)
	@Type(type = "org.hibernate.type.UUIDCharType")
	protected UUID authorId;

	@ManyToOne
	@JoinColumn(name="author_id", insertable = false, updatable = false)
	private User author;

	@Column(name = "winner_id")
	@Type(type = "org.hibernate.type.UUIDCharType")
	protected UUID winnerId;

	@ManyToOne
	@JoinColumn(name="winner_id", insertable = false, updatable = false)
	private Bid winner;

	@Column(name="description")
	private String description;

	@Enumerated(EnumType.STRING)
	private AuctionStatus status;

	private String image;
}
